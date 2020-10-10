package com.mushfique.taiba

import android.content.Intent
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
import kotlinx.android.synthetic.main.yeas_single_element.view.*

class YearsActivity : AppCompatActivity() {

    lateinit var RecyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_years)
        val eventName =  intent.getStringExtra("eventName")
        toolbar.setTitle(eventName)

        val progressLayout: RelativeLayout = findViewById(R.id.progressLayout)
        progressLayout.visibility = View.VISIBLE

        RecyclerView = findViewById(R.id.years_recyclerview)

        //checking internet.........................
        if (ConnectionManager().checkConnectivity(this)){

        }
        //internet not found
        else{
            Toast.makeText(this,"internet not found", Toast.LENGTH_SHORT).show()
        }


        val ref = FirebaseDatabase.getInstance().getReference("/years")
        ref.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                p0.children.forEach{
                    progressLayout.visibility = View.GONE
                    val title = it.getValue(Yeartitles::class.java)
                    if (title!=null){
                        adapter.add(Yearitem(title))
                    }
                    adapter.setOnItemClickListener(){item, _ ->
                        val yearitem = item as Yearitem
                        val intent = Intent(this@YearsActivity, ImagesActivity::class.java)
                        intent.putExtra("whichYear",yearitem.title.title)
                        intent.putExtra("eventName",eventName)
                        startActivity(intent)
                    }
                }
                RecyclerView.adapter = adapter
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}

class Yearitem(val title: Yeartitles) : Item<ViewHolder>(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.years_txt_single.text = title.title
    }

    override fun getLayout(): Int {
        return R.layout.yeas_single_element
    }

}