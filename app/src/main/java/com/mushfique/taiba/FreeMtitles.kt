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
import kotlinx.android.synthetic.main.activity_results.toolbar
import kotlinx.android.synthetic.main.notification_single_element.view.*

class FreeMtitles : AppCompatActivity() {

    lateinit var RecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_free_mtitles)
        val whichOption =  intent.getStringExtra("whichOption")
        val whichNode = intent.getStringExtra("firebaseNode")
        toolbar.setTitle(whichNode)

        RecyclerView = findViewById(R.id.freemtitles_recyclerview)

        //checking internet.........................
        if (ConnectionManager().checkConnectivity(this)){

        }
        //internet not found
        else{
            Toast.makeText(this,"internet not found", Toast.LENGTH_SHORT).show()
        }

        val progressLayout: RelativeLayout = findViewById(R.id.progressLayout)
        progressLayout.visibility = View.VISIBLE

        //fetching titles from the firebase
        val ref = FirebaseDatabase.getInstance().getReference(whichNode).child(whichOption)
        ref.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                p0.children.forEach{
                    progressLayout.visibility = View.GONE
                    val title = it.getValue(FreeMattitles::class.java)
                    val link = it.getValue(FreeMattitles::class.java)
                    if (title!=null && link!=null){
                        adapter.add(FreeMitem(title,link))
                    }
                    adapter.setOnItemClickListener(){item, _ ->
                        val freeMitem = item as FreeMitem
                        val intent = Intent(this@FreeMtitles, PdfWebActivity::class.java)
                        intent.putExtra("link",freeMitem.link.link)
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

class FreeMitem(val title: FreeMattitles, val link: FreeMattitles) : Item<ViewHolder>(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.notification_txttitle_single.text = title.title
        viewHolder.itemView.notification_txtdesc_single.text = link.link
    }

    override fun getLayout(): Int {
        return R.layout.notification_single_element
    }
}