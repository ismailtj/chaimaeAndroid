package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Main:AppCompatActivity() {

    private lateinit var addPositionFAB :FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_lyout)

        addPositionFAB = findViewById(R.id.addPositionFAB)

        addPositionFAB.setOnClickListener{
            var intent = Intent(this,AddPostionForm::class.java)
            startActivity(intent)
        }
   }
}