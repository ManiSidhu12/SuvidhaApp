package com.fragments.app

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.suvidha.app.LeaveApplication
import com.suvidha.app.R
import kotlinx.android.synthetic.main.attendence_screen.view.*
import java.text.DateFormat
import java.util.*

class AttendenceFragment : Fragment(){
    lateinit var v : View
    lateinit var toolBar : Toolbar
    lateinit var btnFilter : ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.attendence_screen,container,false)
        toolBar = activity!!.findViewById(R.id.toolbar)
        toolBar.title = "Attendence"
        btnFilter = toolBar.findViewById(R.id.img_filter)
        btnFilter.visibility = View.GONE

        work()
        return v
    }
    fun work(){
        v.leave_request.setOnClickListener {
            startActivity(Intent(activity!!,LeaveApplication::class.java))
        }

        v.lay_start_time.setOnClickListener {
            openTimePicker(v.txt_starttime)
        }
        v.lay_duty_time.setOnClickListener {
            openTimePicker(v.txt_dutytime)
        }
        v.lay_dutyoff_time.setOnClickListener {
            openTimePicker(v.txt_dutyofftime)
        }
    }
    fun openTimePicker(txt : TextView){
        val c = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(activity, TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
            c.set(Calendar.HOUR_OF_DAY, hourOfDay)
            c.set(Calendar.MINUTE, minute)
            val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime())
            Log.d("MainActivity", "Selected time is $time")
            txt.text = time
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true)
        timePickerDialog.show()

    }

}