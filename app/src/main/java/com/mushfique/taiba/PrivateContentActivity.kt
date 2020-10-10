package com.mushfique.taiba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_private_content.*
import kotlinx.android.synthetic.main.activity_results.toolbar

class PrivateContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_private_content)
        val subjectName =  intent.getStringExtra("subjectName")
        toolbar.setTitle(subjectName)

        val intent = Intent(this, FreeMtitles::class.java)
        privcontent_opt_objtest.setOnClickListener {
            intent.putExtra("firebaseNode",subjectName)
            intent.putExtra("whichOption","objectiveTest")
            startActivity(intent)
        }
        privcontent_opt_desctest.setOnClickListener {
            intent.putExtra("firebaseNode",subjectName)
            intent.putExtra("whichOption","descriptiveTest")
            startActivity(intent)
        }
        privcontent_opt_anskey.setOnClickListener {
            intent.putExtra("firebaseNode",subjectName)
            intent.putExtra("whichOption","answerKey")
            startActivity(intent)
        }
        privcontent_opt_notes.setOnClickListener {
            intent.putExtra("firebaseNode",subjectName)
            intent.putExtra("whichOption","Notes")
            startActivity(intent)
        }
    }
}