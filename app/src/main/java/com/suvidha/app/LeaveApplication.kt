package com.suvidha.app

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Paint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.adapter.app.LeavesAdapter
import kotlinx.android.synthetic.main.leave_alert.*
import kotlinx.android.synthetic.main.leave_application.*
import android.widget.ArrayAdapter
import android.widget.TextView
import com.common.app.Common
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import android.view.inputmethod.InputMethodManager
import com.common.app.GlobalConstants


class LeaveApplication : Activity(){
    var listTo = ArrayList<String>()
    var listReasons = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.leave_application)
        leaves_available.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        listTo.add("Dr. Suri")
        listTo.add("Amit Rana")

        listReasons.add("Health Issue")
        listReasons.add("Emergency")

        val adapterTo = ArrayAdapter<String>(this, R.layout.spinner_txt1, listTo)
        adapterTo.setDropDownViewResource(R.layout.spinner_txt)
        spin_to.adapter = adapterTo

        val adapterReason = ArrayAdapter<String>(this, R.layout.spinner_txt1, listReasons)
        adapterReason.setDropDownViewResource(R.layout.spinner_txt)
        spin_reason.adapter = adapterReason

        work()

    }

    fun work(){
btn_close_key.setOnClickListener {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(edt_remark_attendence.getWindowToken(), 0)
}
        leaves_available.setOnClickListener {
            openAlert()
        }
        img_back_detail.setOnClickListener {
         // GlobalConstants.backValue="yes"

            onBackPressed()
            //finish()

        }
        lay_from_date_leave.setOnClickListener {
            txt_to_date_leave.text = ""
            showDatePicker(txt_from_date_leave,"from","")
        }
        lay_to_date_leave.setOnClickListener {
            showDatePicker(txt_to_date_leave,"to",txt_from_date_leave.text.toString())
        }
    }
    fun openAlert(){
        val dialog = Dialog(this, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.setContentView(R.layout.leave_alert)
        dialog.show()
        dialog.recycler_leaves.layoutManager = LinearLayoutManager(this)
        dialog.recycler_leaves.adapter = LeavesAdapter(this)
        dialog.btn_close_leave.setOnClickListener {
        dialog.dismiss()
        }
    }

    private fun showDatePicker(txt : TextView, value : String, dateFrom : String) {
        val c = Calendar.getInstance()
        val mYear  = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay   = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener
        { view, year, monthOfYear, dayOfMonth ->

            if(value.equals("to")){
                if(checkDate(dateFrom,(monthOfYear + 1).toString() + "/" + dayOfMonth.toString()  + "/" + year)){
                    txt.text =  (monthOfYear + 1).toString() + "/" + dayOfMonth.toString()  + "/" + year

                }
                else{
                    Common.showToast(this,"Please Select Valid Date...")
                }

            }
            else{
                txt.text =  (monthOfYear + 1).toString() + "/" + dayOfMonth.toString()  + "/" + year

            }
        }, mYear, mMonth, mDay)
        datePickerDialog.show()
    }
    private fun checkDate(dateFrom : String,dateTo : String) : Boolean{
        var bool =  false
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        var convertedDate = Date()
        var convertedDate2 = Date()
        try  {
            convertedDate = dateFormat.parse(dateFrom)
            convertedDate2 = dateFormat.parse(dateTo)
            if(convertedDate2.after(convertedDate) || convertedDate2.equals(convertedDate)){
                bool =  true
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return bool
    }


}