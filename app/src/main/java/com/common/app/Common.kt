package com.common.app

import android.app.Activity
import android.content.Context
import android.support.design.widget.TextInputLayout
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import com.suvidha.app.R

class Common{
    companion object {
        //==================== validations =============
        fun validateUsername(c: Context, edt_name: EditText, lay_name: TextInputLayout): Boolean {
            if (edt_name.text.toString().trim ().isEmpty()) {
                lay_name.error = c.getString(R.string.err_name)
                requestFocus(c, edt_name)
                return false
            } else {
                lay_name.isErrorEnabled = false
            }

            return true
        }
        fun validatePassword(c: Context, edt_pswd: EditText, lay_pswd: TextInputLayout): Boolean {
            if (edt_pswd.text.toString().trim().isEmpty()) {
                lay_pswd.error = c.getString(R.string.err_msg_password)
                requestFocus(c, edt_pswd)
                return false
            }/* else if (edt_pswd.text.toString().length < 6) {
                lay_pswd.error = c.getString(R.string.err_pswd)
                requestFocus(c, edt_pswd)
                return false
            }*/ else {
                lay_pswd.isErrorEnabled = false
            }

            return true
        }
        fun requestFocus(c: Context, view: View) {
            if (view.requestFocus()) {
                (c as Activity).window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
            }
        }
fun showToast(c : Context,msg: String){
    Toast.makeText(c,msg,Toast.LENGTH_SHORT).show()
}
    }
}