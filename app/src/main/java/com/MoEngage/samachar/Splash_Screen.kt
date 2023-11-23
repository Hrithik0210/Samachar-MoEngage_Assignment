package com.MoEngage.samachar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val time:Long=4000

        Handler().postDelayed(Runnable{
            val intent= Intent(Splash_Screen@this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },time)
    }
}