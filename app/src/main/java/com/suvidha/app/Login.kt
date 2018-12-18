package com.suvidha.app

import android.app.Activity
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import kotlinx.android.synthetic.main.signin_screen.*

class Login : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.signin_screen)

        txt_forgot.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        work()
    }
    private fun work(){


        btn_login.setOnClickListener {
          //  if (Common.validateUsername(this@Login,edt_username_login,input_lay_username) && Common.validatePassword(this@Login,edt_pswd_login,input_lay_pswd)) {
                val intent = Intent(this@Login, Home::class.java)
                startActivity(intent)
                finish()
            //}
        }
    }
}