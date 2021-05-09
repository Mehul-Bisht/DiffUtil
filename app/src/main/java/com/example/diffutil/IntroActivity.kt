package com.example.diffutil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.diffutil.GridRecycler.GridActivity
import com.example.diffutil.LinearRecycler.MainActivity
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        linear.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        grid.setOnClickListener {
            val intent = Intent(this,GridActivity::class.java)
            startActivity(intent)
        }
    }
}