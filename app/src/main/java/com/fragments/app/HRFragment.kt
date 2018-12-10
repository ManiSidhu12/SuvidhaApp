package com.fragments.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.suvidha.app.R

class HRFragment : Fragment(){
    lateinit var v : View
    lateinit var toolBar : Toolbar
    lateinit var btnFilter : ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.hr_screen,container,false)

        toolBar = activity!!.findViewById(R.id.toolbar)
        toolBar.title = "List Of Conveyance"
        btnFilter = toolBar.findViewById(R.id.img_filter)
        btnFilter.visibility = View.GONE

        return v
    }
}