package com.mushfique.taiba.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mushfique.taiba.R
import com.mushfique.taiba.YearsActivity
import kotlinx.android.synthetic.main.fragment_gallery.view.*

class GalleryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

        val intent = Intent(context,YearsActivity::class.java)


        root.gallery_opt_teachersday.setOnClickListener {
            intent.putExtra("eventName","TeachersDay")
            startActivity(intent)
        }
        root.gallery_opt_annualtour.setOnClickListener {
            intent.putExtra("eventName","AnnualTour")
            startActivity(intent)
        }
        root.gallery_opt_farewell.setOnClickListener {
            intent.putExtra("eventName","Farewell")
            startActivity(intent)
        }
        root.gallery_opt_careerguid.setOnClickListener {
            intent.putExtra("eventName","CareerGuidance")
            startActivity(intent)
        }
        root.gallery_opt_felicitation.setOnClickListener {
            intent.putExtra("eventName","Felicitation")
            startActivity(intent)
        }
        root.gallery_opt_parentsmeet.setOnClickListener {
            intent.putExtra("eventName","ParentsMeet")
            startActivity(intent)
        }
        root.gallery_opt_weeklytest.setOnClickListener {
            intent.putExtra("eventName","WeeklyTest")
            startActivity(intent)
        }
        root.gallery_opt_projector.setOnClickListener {
            intent.putExtra("eventName","Projector")
            startActivity(intent)
        }

        return root
    }
}