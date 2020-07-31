package com.example.taiba.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taiba.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)


        val adapter = GroupAdapter<ViewHolder>()
        adapter.add(NotificationItem())
        adapter.add(NotificationItem())
        adapter.add(NotificationItem())
        adapter.add(NotificationItem())
        adapter.add(NotificationItem())
        adapter.add(NotificationItem())
        adapter.add(NotificationItem())
        adapter.add(NotificationItem())
        adapter.add(NotificationItem())
        root.notification_recyclerview.adapter = adapter

        return root
    }
}

class NotificationItem: Item<ViewHolder>(){

    override fun bind(viewHolder: ViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
        return R.layout.notification_single_element
    }

}








