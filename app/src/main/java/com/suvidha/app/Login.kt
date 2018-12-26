package com.suvidha.app

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.ArrayAdapter
import com.common.app.Common
import kotlinx.android.synthetic.main.signin_screen.*
import kotlinx.android.synthetic.main.user_data_dialog.*

class Login : Activity(){
    var listFy = ArrayList<String>()
    var listBranch = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.signin_screen)
        listFy.add("2017-18")
        listFy.add("2018-19")

        listBranch.add("Unit-SAHA")
        listBranch.add("Unit-Ambala")
        listBranch.add("Spares-Division")

        txt_forgot.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        work()
    }
    private fun work(){


        btn_login.setOnClickListener {
         if (Common.validateUsername(this@Login,edt_email) && Common.validatePassword(this@Login,edt_pswd)) {
             openDialogFollow(this@Login)
               /* val intent = Intent(this@Login, Home::class.java)
                startActivity(intent)
                finish()*/
          }
        }
    }

    private fun openDialogFollow(ctx : Context) {

        val dialog1 = Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar)
        dialog1.setContentView(R.layout.user_data_dialog)
        val adapPriority = ArrayAdapter(ctx, R.layout.spinner_txt1, listBranch)
        adapPriority.setDropDownViewResource(R.layout.spinner_txt)
        dialog1.spin_unit.adapter = adapPriority
        val adapPriority1 = ArrayAdapter(ctx, R.layout.spinner_txt1, listFy)
        adapPriority1.setDropDownViewResource(R.layout.spinner_txt)
        dialog1.spin_fy.adapter = adapPriority1

        dialog1.show()
dialog1.btn_sub_data.setOnClickListener {
    dialog1.dismiss()
    val intent = Intent(this@Login, Home::class.java)
    startActivity(intent)
    finish()
}
    }
}