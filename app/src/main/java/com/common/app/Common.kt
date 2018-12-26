package com.common.app

import android.app.Activity
import android.content.Context
import android.support.design.widget.TextInputLayout
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import com.suvidha.app.R

class Common{
    companion object {
        //==================== validations =============
        fun validateUsername(c: Context, edt_name: EditText): Boolean {
            if (edt_name.text.toString().trim ().isEmpty()) {
               showToast(c,"Please enter Username...")
                return false
            } else {
              return true
            }

            return true
        }
        fun validatePassword(c: Context, edt_pswd: EditText): Boolean {
            if (edt_pswd.text.toString().trim().isEmpty()) {
                showToast(c,c.getString(R.string.err_msg_password))
                return false
            }/* else if (edt_pswd.text.toString().length < 6) {
                lay_pswd.error = c.getString(R.string.err_pswd)
                requestFocus(c, edt_pswd)
                return false
            }*/ else {
return true            }

            return true
        }
        fun requestFocus(c: Context, view: View) {
            if (view.requestFocus()) {
                (c as Activity).window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
            }
        }
fun showToast(c : Context,msg: String){
    var toast = Toast.makeText(c,msg,Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}
    }
}