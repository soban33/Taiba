package com.mushfique.taiba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_results.*
import kotlinx.android.synthetic.main.image_single_element.view.*

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        toolbar.setTitle("Results")

        val RecyclerView: RecyclerView = findViewById(R.id.results_recyclerview)

        val progressLayout: RelativeLayout = findViewById(R.id.progressLayout)
        progressLayout.visibility = View.VISIBLE

        val ref = FirebaseDatabase.getInstance().getReference("/Results")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                p0.children.forEach{
                    progressLayout.visibility = View.GONE
                    val image = it.getValue(ImagesFire::class.java)
                    if (image!=null){
                        adapter.add(ResultsItem(image))
                    }
                }
                RecyclerView.adapter = adapter
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}

class ResultsItem(val image: ImagesFire) : Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(image.image).into(viewHolder.itemView.images_image_single)
    }

    override fun getLayout(): Int {
        return R.layout.image_single_element
    }
}