package com.suvidha.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class Splash : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Thread(splashThread).start()
    }
    var splashThread: Runnable = Runnable {
        try {
            Thread.sleep(1000)
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()


        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}
