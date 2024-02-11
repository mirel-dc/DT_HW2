package com.example.dt_hw2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.io.File
import java.io.FileOutputStream

class SecondActivity : AppCompatActivity() {
    private lateinit var fileOutputStream: FileOutputStream


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val filename = "lifecycle_log.txt"
        val file= File(filesDir,filename)
        fileOutputStream = FileOutputStream(file)

        Log.d("SecondActivity", "onCreate called")
        writeLogToFile("onCreate called")

        val squareValue = intent.getIntExtra(COUNTER_VALUE,0)*intent.getIntExtra(COUNTER_VALUE,0)

        val tvCounterSquare = findViewById<TextView>(R.id.tv_counterSquare).apply {
            text="$squareValue"
        }
    }

    private fun writeLogToFile(message: String) {
        fileOutputStream.write("$message\n".toByteArray())
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondActivity", "onStart called")
        writeLogToFile("onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "onResume called")
        writeLogToFile("onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondActivity", "onPause called")
        writeLogToFile("onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondActivity", "onStop called")
        writeLogToFile("onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "onDestroy called")
        writeLogToFile("onDestroy called")
        fileOutputStream.close()
    }
}