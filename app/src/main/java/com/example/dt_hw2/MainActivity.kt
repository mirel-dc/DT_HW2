package com.example.dt_hw2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    private var counter: Int = 0

    private lateinit var fileOutputStream: FileOutputStream

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filename = "lifecycle_log.txt"
        val file=File(filesDir,filename)
        fileOutputStream = FileOutputStream(file)

        Log.d("MainActivity", "onCreate called")
        writeLogToFile("onCreate called")

        val tvCounter = findViewById<TextView>(R.id.tv_counter).apply {
            text = "$counter"
        }

        findViewById<Button>(R.id.btn_secondActivity)?.apply {
            setOnClickListener {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra(COUNTER_VALUE,counter)
                startActivity(intent)
            }
        }
    }

    private fun writeLogToFile(message: String) {
        fileOutputStream.write("$message\n".toByteArray())
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart called")
        writeLogToFile("onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume called")
        writeLogToFile("onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause called")
        writeLogToFile("onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop called")
        writeLogToFile("onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy called")
        writeLogToFile("onDestroy called")
        fileOutputStream.close()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_BUNDLE, counter + 1)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        counter = savedInstanceState.getInt(STATE_BUNDLE)
        val tvCounter = findViewById<TextView>(R.id.tv_counter).apply {
           text = "$counter"
        }
    }
}

const val STATE_BUNDLE = "stateBundle"
const val COUNTER_VALUE = "counterValue"