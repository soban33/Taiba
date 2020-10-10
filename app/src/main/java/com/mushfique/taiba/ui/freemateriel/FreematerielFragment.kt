package com.mushfique.taiba.ui.freemateriel

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mushfique.taiba.FreeMtitles
import com.mushfique.taiba.R
import kotlinx.android.synthetic.main.fragment_freemateriel.view.*

class FreematerielFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_freemateriel, container, false)

        val intent = Intent(context, FreeMtitles::class.java)

        root.freem_neet.setOnClickListener {
            intent.putExtra("firebaseNode","FreeMateriel")
            intent.putExtra("whichOption","NEET")
            startActivity(intent)
        }
        root.freem_boardtwo.setOnClickListener {
            intent.putExtra("firebaseNode","FreeMateriel")
            intent.putExtra("whichOption","Twelth(board)")
            startActivity(intent)
        }
        root.freem_boardone.setOnClickListener {
            intent.putExtra("firebaseNode","FreeMateriel")
            intent.putExtra("whichOption","Eleventh(board)")
            startActivity(intent)
        }
        root.freem_ncerttwo.setOnClickListener {
            intent.putExtra("firebaseNode","FreeMateriel")
            intent.putExtra("whichOption","Twelth(ncert)")
            startActivity(intent)
        }
        root.freem_ncertone.setOnClickListener {
            intent.putExtra("firebaseNode","FreeMateriel")
            intent.putExtra("whichOption","Eleventh(ncert)")
            startActivity(intent)
        }
        root.freem_generel.setOnClickListener {
            intent.putExtra("firebaseNode","FreeMateriel")
            intent.putExtra("whichOption","Generel")
            startActivity(intent)
        }
        return root
    }
}