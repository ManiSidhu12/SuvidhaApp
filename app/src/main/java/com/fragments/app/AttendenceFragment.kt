package com.fragments.app

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.suvidha.app.LeaveApplication
import com.suvidha.app.R
import kotlinx.android.synthetic.main.attendence_screen.view.*

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
    }
}