package com.example.taiba.ui.studentscorner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taiba.R
import kotlinx.android.synthetic.main.fragment_studentscorner.*
import kotlinx.android.synthetic.main.fragment_studentscorner.view.*

class StudentsCornerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_studentscorner, container, false)

        val validCode = arrayOf("marvel","dc")
        root.btn_submitcode.setOnClickListener(){
            val enteredcode = edt_entercode.text.toString()
            if (validCode.contains(enteredcode)){
                Toast.makeText(context,"code is valid",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"please enter a valid code",Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }
}