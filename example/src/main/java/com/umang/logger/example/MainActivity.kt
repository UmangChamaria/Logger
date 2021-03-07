package com.umang.logger.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umang.logger.*

class MainActivity : AppCompatActivity(), SmartLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        info("info log")
        error("error log")
        warn("warning log")
        debug("debug log")
        verbose("verbose log")
    }
}
