package com.suvidha.app

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.common.app.Common
import com.common.app.CommonUtils
import com.common.app.SharedPrefManager
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.model.login.LoginRoot
import kotlinx.android.synthetic.main.forgot_password.*
import kotlinx.android.synthetic.main.signin_screen.*
import kotlinx.android.synthetic.main.user_data_dialog.*
import java.io.StringReader

class Login : Activity() {
    var listFy = ArrayList<String>()
    var listBranch = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.signin_screen)

        if (SharedPrefManager.getInstance(this@Login).isLoggedIn) {
            val intent = Intent(this@Login, Home::class.java)
            startActivity(intent)
            finish()
        }

        txt_forgot.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        work()

    }

    private fun work() {
        txt_forgot.setOnClickListener {
            openDialogPassword()
        }

        btn_login.setOnClickListener {
            if (Common.validateUsername(this@Login, edt_email) && Common.validatePassword(this@Login, edt_pswd)) {

                /* val intent = Intent(this@Login, Home::class.java)
                 startActivity(intent)
                 finish()*/
                if (CommonUtils.getConnectivityStatusString(this@Login).equals("true")) {
                    loginWebService(edt_email.text.toString().trim(), edt_pswd.text.toString().trim())
                } else {
                    CommonUtils.openInternetDialog(this@Login)
                }
            }
        }
    }

    private fun openDialog(ctx: Context) {

        val dialog1 = Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar)
        dialog1.setContentView(R.layout.user_data_dialog)
        val adapPriority = ArrayAdapter(ctx, R.layout.spinner_txt1, listBranch)
        adapPriority.setDropDownViewResource(R.layout.spinner_txt)
        dialog1.spin_unit.adapter = adapPriority
        val adapPriority1 = ArrayAdapter(ctx, R.layout.spinner_txt1, listFy)
        adapPriority1.setDropDownViewResource(R.layout.spinner_txt)
        dialog1.spin_fy.adapter = adapPriority1

        dialog1.show()
        dialog1.btn_close_follow.visibility = View.GONE
        dialog1.btn_sub_data.setOnClickListener {
            if (dialog1.spin_unit.selectedItem == null) {
                Common.showToast(this@Login, "Please Select Branch/Unit..")
            } else if (dialog1.spin_fy.selectedItem == null) {
                Common.showToast(this@Login, "Please Select Financial Year..")
            } else {
                SharedPrefManager.getInstance(ctx).fy = dialog1.spin_fy.selectedItem.toString()
                SharedPrefManager.getInstance(ctx).unit = dialog1.spin_unit.selectedItem.toString()
                dialog1.dismiss()
                val intent = Intent(this@Login, Home::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


    //============= Login Web Service =====
    private fun loginWebService(email: String, pswd: String) {

        val url = "http://suvidhaapi.suvidhacloud.com/api/Logins/LoginValidation/Login?UserName=" + email + "&password=" + pswd
        Log.e("url login", url)
        val pd = ProgressDialog.show(this@Login, "", "Loading", false)

        val postRequest = object : StringRequest(
            Request.Method.GET, url, { response ->
                pd.dismiss()
                Log.e("login Response", response)
                val gson = Gson()
                val reader = JsonReader(StringReader(response))
                reader.isLenient = true
                var rootLogin = gson.fromJson<LoginRoot>(reader, LoginRoot::class.java)

                if (rootLogin != null) {
                    if (rootLogin.table != null && rootLogin.table[0].userexists == null) {
                        Common.showToast(this@Login, "Logged In Successfully...")
                        SharedPrefManager.getInstance(this@Login).setLoginResponse(response)
                        SharedPrefManager.getInstance(this@Login).userLogin(
                            rootLogin.table[0].userid.toString(),
                            rootLogin.table[0].firstname,
                            rootLogin.table[0].middlename,
                            rootLogin.table[0].lastname,
                            rootLogin.table[0].emailid,
                            rootLogin.table[0].designation
                        )

                        if (rootLogin.table1 != null && rootLogin.table1.size > 0) {
                            for (i in 0 until rootLogin.table1.size) {
                                listBranch.add(rootLogin.table1[i].unitname)
                            }
                        }
                        if (rootLogin.table2 != null && rootLogin.table2.size > 0) {
                            for (j in 0 until rootLogin.table2.size) {
                                listFy.add(rootLogin.table2[j].fyname)
                            }
                        }
                        if (listBranch != null && listFy != null) {
                            openDialog(this@Login)
                        }
                    } else {
                        Common.showToast(this@Login, "Login Failed")

                    }
                } else {
                    Common.showToast(this@Login, "Login Failed")

                }
            },

            { error: VolleyError ->
                pd.dismiss()
                Common.showToast(this@Login, error.message.toString())

            }) {
        }

        postRequest.retryPolicy = DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(postRequest)

    }



    //================== Forget Password Dialog ==============
    private fun openDialogPassword() {
        val dialog = Dialog(this@Login, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.setContentView(R.layout.forgot_password)

        dialog.show()


        dialog.edt_email_forget.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                Common.validateEmail(this@Login, dialog.edt_email_forget, dialog.input_layout_emailforget)
            }
        })

        dialog.btn_ok.setOnClickListener { v: View ->
            if (Common.validateEmail(this@Login, dialog.edt_email_forget, dialog.input_layout_emailforget)) {
                /*if (CommonUtils.getConnectivityStatusString(this@Login).equals("true")) {
                      //  forgotPasswordWebService(dialog)
                    } else {
                        CommonUtils.openInternetDialog(this@Login)
                    }*/
            }
}
        dialog.btn_cancel.setOnClickListener { v: View ->
            dialog.dismiss()
            if (v != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }


        }
    }


}