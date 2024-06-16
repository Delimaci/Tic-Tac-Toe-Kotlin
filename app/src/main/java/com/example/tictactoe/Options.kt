package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible


class Options : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        var BO3 = 1
        var timer = 1
        var theme = 1

        val textView9 = findViewById<TextView>(R.id.textView9);
        val textView6 = findViewById<TextView>(R.id.textView6);
        val textView10 = findViewById<TextView>(R.id.textView10);


        //timer options
        val textView17 = findViewById<TextView>(R.id.textView17);
        val textView16 = findViewById<TextView>(R.id.textView16);


        //theme options
        val textView3 = findViewById<TextView>(R.id.textView3);
        val textView4 = findViewById<TextView>(R.id.textView4);


        val view2 = findViewById<View>(R.id.view2);
        val view4 = findViewById<View>(R.id.view4);

        //timer view

        val view6 = findViewById<View>(R.id.view6);
        val view5 = findViewById<View>(R.id.view5);


        //theme view
        val view7 = findViewById<View>(R.id.view7);
        val view8 = findViewById<View>(R.id.view8);


        val textView11 = findViewById<TextView>(R.id.textView11);
        textView6.setOnClickListener({

            view2.isVisible = false   //hides/shows the white underlines under the options
            view4.isVisible = false



            //timer view

            view5.isVisible = false
            view6.isVisible = false



            if(BO3 == 1){ //if conditions for variables when either "yes" or "no" is clicked. 1 = yes, 0 = no
             //   val name = textView10.text.toString()
              //  val timer = textView16.text.toString()

                val intent = Intent(this@Options, MainActivity::class.java )
                intent.putExtra("Name", BO3)
                intent.putExtra("Timer", timer)
                intent.putExtra("Theme", theme)

                startActivity(intent)
            }
        if(BO3 == 0){
      //      val name = textView11.text.toString()
        //    val timer = textView16.text.toString()
            val intent = Intent(this@Options, MainActivity::class.java )
            intent.putExtra("Name", BO3)
            intent.putExtra("Timer", timer)
            intent.putExtra("Theme", theme)
            startActivity(intent)

        }



        })
        //BEST OF 3
        textView10.setOnClickListener({
            BO3 = 1

            view2.isVisible = true

            view4.isVisible = false

            Log.d("Options", " best of 3 VAR IS 1");



        })
        textView11.setOnClickListener({
            BO3 = 0
            view2.isVisible = false

            view4.isVisible = true
            Log.d("Options", " best of 3 VAR IS 0");

        })

        //TIMER
        textView16.setOnClickListener({
            timer = 0
            view6.isVisible = false

            view5.isVisible = true
            Log.d("Options", " timer VAR IS 0");

        })

        textView17.setOnClickListener({
            timer = 1
            view5.isVisible = false

            view6.isVisible = true
            Log.d("Options", " timer VAR IS 1");

        })


        textView3.setOnClickListener({ //purple theme
            theme = 1
            view7.isVisible = false

            view8.isVisible = true
            Log.d("Options", " theme VAR IS 1");

        })

        textView4.setOnClickListener({ //blue theme
            theme = 0
            view8.isVisible = false

            view7.isVisible = true
            Log.d("Options", " theme VAR IS 0");

        })

    }
}