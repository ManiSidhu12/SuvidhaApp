package com.suvidha.app

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.new_conveyance.*
import java.text.DateFormat
import java.util.*

class NewConveyance : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_conveyance)

        work()
    }
    private fun work(){
        img_back.setOnClickListener {
            onBackPressed()
        }
        lay_conv_date.setOnClickListener {
            showDatePicker(txt_convo_date,"","")
        }
        lay_date.setOnClickListener {
            showDatePicker(txt_date,"","")
        }
        lay_time_in.setOnClickListener {
            showDatePicker(txt_time_in,"time_in","")

        }

        lay_time_out.setOnClickListener {
            showDatePicker(txt_time_out,"time_out","")

        }
        img_cncl.setOnClickListener {
            txt_time_out.text = "mm/dd/yyyy --:--:--"
            img_cncl.visibility = View.GONE
        }
        img_cncl1.setOnClickListener {
            txt_time_in.text = "mm/dd/yyyy --:--:--"
            img_cncl1.visibility = View.GONE

        }
    }
    private fun showDatePicker(txt : TextView, value : String, dateFrom : String) {
        val c      = Calendar.getInstance()
        val mYear  = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay   = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this@NewConveyance, DatePickerDialog.OnDateSetListener
        { view, year, monthOfYear, dayOfMonth ->


if(value.equals("time_in") || value.equals("time_out")){

    val timePickerDialog = TimePickerDialog(this@NewConveyance, TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
        c.set(Calendar.HOUR_OF_DAY, hourOfDay)
        c.set(Calendar.MINUTE, minute)
        val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime())
        Log.d("MainActivity", "Selected time is $time")
        txt.text = (monthOfYear + 1).toString() + "/" + dayOfMonth.toString()  + "/" + year + " "+ time
        if(value.equals("time_in") && !txt.text.equals("mm/dd/yyyy --:--:--")){
            img_cncl1.visibility = View.VISIBLE
        }
        if(value.equals("time_out") && !txt.text.equals("mm/dd/yyyy --:--:--")){
            img_cncl.visibility = View.VISIBLE
        }
       // txt_time.text = time
    }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true)
    timePickerDialog.show()
}
            else{
    txt.text =  (monthOfYear + 1).toString() + "/" + dayOfMonth.toString()  + "/" + year

}



        }, mYear, mMonth, mDay)
        datePickerDialog.show()
    }

}