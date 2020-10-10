package com.mushfique.taiba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_results.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_single_element.view.*

class ImagesActivity : AppCompatActivity() {

    lateinit var RecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)
        val whichYear = intent.getStringExtra("whichYear")
        val eventName = intent.getStringExtra("eventName")
        toolbar.setTitle(whichYear)
        RecyclerView = findViewById(R.id.images_recyclerview)


        //checking internet.........................
        if (ConnectionManager().checkConnectivity(this)){

        }
        //internet not found
        else{
            Toast.makeText(this,"internet not found", Toast.LENGTH_SHORT).show()
        }


        val progressLayout: RelativeLayout = findViewById(R.id.progressLayout)
        progressLayout.visibility = View.VISIBLE

        val ref = FirebaseDatabase.getInstance().getReference("/Gallery").child(eventName).child(whichYear)
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                p0.children.forEach{
                    progressLayout.visibility = View.GONE
                    val image = it.getValue(ImagesFire::class.java)
                    if (image!=null){
                        adapter.add(ImageItem(image))
                    }
                }
                RecyclerView.adapter = adapter
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}

class ImageItem(val image: ImagesFire) : Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(image.image).into(viewHolder.itemView.images_image_single)
    }

    override fun getLayout(): Int {
        return R.layout.image_single_element
    }
}