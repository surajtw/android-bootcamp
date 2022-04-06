package com.example.mysweetmovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class NewActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var editName: EditText
    lateinit var editAge:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        val submitButton: Button =findViewById(R.id.button)
        editName=findViewById(R.id.edit_name)
        editAge=findViewById(R.id.edit_age)
        submitButton.setOnClickListener (this)
    }
    override fun onClick(p0: View?) {
        Toast.makeText(
            this,
            "Hello ${editName.text} ,your age is ${editAge.text}",
            Toast.LENGTH_SHORT
        ).show()
    }
}