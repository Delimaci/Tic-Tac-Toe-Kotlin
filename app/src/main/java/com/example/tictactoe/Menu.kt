package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //SPLASH SCREEN, changes to menu activity once the textview is clicked.

        val textView31 = findViewById<TextView>(R.id.textView31);

        textView31.setOnClickListener({
            val nextPage = Intent(this, Options::class.java);
            startActivity(nextPage)
            finish();
        })


    }








}