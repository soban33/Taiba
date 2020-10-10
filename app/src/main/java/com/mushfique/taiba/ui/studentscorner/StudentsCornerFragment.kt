package com.mushfique.taiba.ui.studentscorner

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mushfique.taiba.PrivateContentActivity
import com.mushfique.taiba.R
import kotlinx.android.synthetic.main.fragment_studentscorner.*
import kotlinx.android.synthetic.main.fragment_studentscorner.view.*

class StudentsCornerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_studentscorner, container, false)

        val validCode = arrayOf("tcipm11bio","tcipm11phy","tcipm11chem","tcipm11math","tcipm12bio","tcipm12phy","tcipm12chem","tcipm12math")
        root.btn_submitcode.setOnClickListener(){
            val enteredcode = edt_entercode.text.toString()
            if (validCode.contains(enteredcode)){
                val intent = Intent(context, PrivateContentActivity::class.java)
                if (enteredcode=="tcipm11bio"){
                    intent.putExtra("subjectName","Biology(eleventh)")
                    startActivity(intent)
                }else if (enteredcode=="tcipm11phy"){
                    intent.putExtra("subjectName","Physics(eleventh)")
                    startActivity(intent)
                }else if (enteredcode=="tcipm11chem"){
                    intent.putExtra("subjectName","Chemistry(eleventh)")
                    startActivity(intent)
                }else if (enteredcode=="tcipm11math"){
                    intent.putExtra("subjectName","Maths(eleventh)")
                    startActivity(intent)
                }else if (enteredcode=="tcipm12bio"){
                    intent.putExtra("subjectName","Biology(twelth)")
                    startActivity(intent)
                }else if (enteredcode=="tcipm12phy"){
                    intent.putExtra("subjectName","Physics(twelth)")
                    startActivity(intent)
                }else if (enteredcode=="tcipm12chem"){
                    intent.putExtra("subjectName","Chemistry(twelth)")
                    startActivity(intent)
                }else if (enteredcode=="tcipm12math"){
                    intent.putExtra("subjectName","Maths(twelth)")
                    startActivity(intent)
                }
            }else{
                Toast.makeText(context,"please enter a valid code",Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }
}