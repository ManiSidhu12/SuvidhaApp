package com.suvidha.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.common.app.Common
import kotlinx.android.synthetic.main.login_screen.*

class Login : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_screen)
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