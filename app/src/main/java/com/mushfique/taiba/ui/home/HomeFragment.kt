package com.mushfique.taiba.ui.home

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mushfique.taiba.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.customdial_contactus.view.*
import kotlinx.android.synthetic.main.customdial_events.view.*
import kotlinx.android.synthetic.main.customdial_facilities.view.*
import kotlinx.android.synthetic.main.customdial_faculty.view.*
import kotlinx.android.synthetic.main.customdial_fees.view.*
import kotlinx.android.synthetic.main.customdial_location.view.*
import kotlinx.android.synthetic.main.customdial_oursubjects.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.notification_single_element.view.*

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val RecyclerView: RecyclerView = root.findViewById(R.id.notification_recyclerview)


        //checking internet.........................
        if (ConnectionManager().checkConnectivity(activity as Context)){
            //do nothing
        }
        //internet not found
        else{
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("ALERT!!")
            dialog.setMessage("Internet not found\nPlease connect to the INTERNET")
            dialog.setPositiveButton("Okay"){_, _->}
            dialog.create()
            dialog.show()
        }



        //events custom Dialogue........................
        root.homenav_events.setOnClickListener{
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.customdial_events,null)
            val mBuilder = AlertDialog.Builder(context).setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            val intent = Intent(context,YearsActivity::class.java)
            mDialogView.events_opt_teachersday.setOnClickListener {
                mAlertDialog.dismiss()
                intent.putExtra("eventName","TeachersDay")
                startActivity(intent)
            }
            mDialogView.events_opt_annualtour.setOnClickListener {
                mAlertDialog.dismiss()
                intent.putExtra("eventName","AnnualTour")
                startActivity(intent)
            }
            mDialogView.events_opt_farewell.setOnClickListener {
                mAlertDialog.dismiss()
                intent.putExtra("eventName","Farewell")
                startActivity(intent)
            }
            mDialogView.events_opt_careerguid.setOnClickListener {
                mAlertDialog.dismiss()
                intent.putExtra("eventName","CareerGuidance")
                startActivity(intent)
            }
            mDialogView.events_opt_felicitation.setOnClickListener {
                mAlertDialog.dismiss()
                intent.putExtra("eventName","Felicitation")
                startActivity(intent)
            }
            mDialogView.txt_customdial_events_cancel.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

        //address custom dialogue..........................
        root.homenav_location.setOnClickListener{
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.customdial_location, null)
            val mBuilder = AlertDialog.Builder(context).setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            mDialogView.txt_customdial_location_okay.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }


        //fees custom dialogue........................
        root.homenav_fee.setOnClickListener{
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.customdial_fees, null)
            val mBuilder = AlertDialog.Builder(context).setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            mDialogView.txt_customdial_fee_okay.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }


        //faculty custom dialog...........................
        root.homenav_faculty.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.customdial_faculty, null)
            val mBuilder = AlertDialog.Builder(context).setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            mDialogView.txt_customdial_faculty_okay.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }


        //imp links custom dialogues......................
        root.homenav_implinks.setOnClickListener {
            Toast.makeText(context,"No Links added !", Toast.LENGTH_SHORT).show()
        }


        //contactus custom dialogue.......................
        root.homenav_contactus.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.customdial_contactus, null)
            val mBuilder = AlertDialog.Builder(context).setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            mDialogView.txt_customdial_contactus_okay.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

        //results onclickListener.......................
        root.homenav_results.setOnClickListener {
            val intent = Intent(context, ResultsActivity::class.java)
            startActivity(intent)
        }
        root.img_homenav_results.setOnClickListener {
            val intent = Intent(context, ResultsActivity::class.java)
            startActivity(intent)
        }

        //facility custom dialog..........................
        root.homenav_facilities.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.customdial_facilities, null)
            val mBuilder = AlertDialog.Builder(context).setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            mDialogView.txt_customdial_facilities_okay.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }


        //our subjects custom dialog.......................
        root.homenav_oursubjects.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.customdial_oursubjects, null)
            val mBuilder = AlertDialog.Builder(context).setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            mDialogView.txt_customdial_oursubjects_okay.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

        //fetching notifications from firebase.....................................
        val ref = FirebaseDatabase.getInstance().getReference("/Notification")
        ref.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                p0.children.forEach{
                    val title = it.getValue(Notification::class.java)
                    val desc = it.getValue(Notification::class.java)
                    if (title != null && desc != null){
                        adapter.add(NotificationItem(title,desc))
                    }
                }
                RecyclerView.adapter = adapter
            }

            override fun onCancelled(p0: DatabaseError) { }
        })

        return root
    }
}

class NotificationItem(val title: Notification, val desc: Notification) : Item<ViewHolder>(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.notification_txttitle_single.text = title.title
        viewHolder.itemView.notification_txtdesc_single.text = desc.desc
    }

    override fun getLayout(): Int {
        return R.layout.notification_single_element
    }
}