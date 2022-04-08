package com.example.mysweetmovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)

    val nameFlow: Flow<String> = flow {
        val nameList = arrayListOf("tom", "cat", "john")
        while (true) {
            for (name in nameList) {
                emit(name)
                delay(2000L)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        scope.launch {
////                makeToast()
//            nameFlow.collect { MainScope().launch {
//                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show() }
//                }
//            }
//
//        Toast.makeText(this@MainActivity, "Not blocking delay", Toast.LENGTH_LONG).show()
        Toast.makeText(this@MainActivity, "Before blocking", Toast.LENGTH_SHORT).show()

        scope.launch {
            delay(3000L)
            Toast.makeText(this@MainActivity, "in blocking code", Toast.LENGTH_SHORT).show()
        }



        Toast.makeText(this@MainActivity, "After blocking", Toast.LENGTH_SHORT).show()

    }

    private suspend fun makeToast() {
        delay(3000L)
        MainScope().launch {
            Toast.makeText(this@MainActivity, "After delay", Toast.LENGTH_LONG).show()
        }


    }
}