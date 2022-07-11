package com.example.livedatacountdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val number_txt =  findViewById<TextView>(R.id.number_txt)
        val start_button = findViewById<Button>(R.id.start_button)
        val stop_button = findViewById<Button>(R.id.stop_button)
        val number_input = findViewById<EditText>(R.id.number_input)

        val viewModel = ViewModelProvider(this).get(MainActiviyViewModel::class.java)


        viewModel.seconds().observe(this, Observer {
           number_txt.text = it.toString()
        })

        viewModel.finished.observe(this, Observer {
            if (it) {
                Toast.makeText(this,"Finished.",Toast.LENGTH_SHORT).show()
            }
        })

        start_button.setOnClickListener() {
            if (number_input.text.isEmpty() || number_input.text.length<4) {
                Toast.makeText(this,"Invalid Number",Toast.LENGTH_SHORT).show()
            } else {
                viewModel.timerValue.value = number_input.text.toString().toLong()
                viewModel.startTimer()
        }
        stop_button.setOnClickListener(){
            viewModel.stopTimer()
        }


    }
}



    }