package com.fragments.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.adapter.app.EnquiriesAdapter
import com.suvidha.app.R
import kotlinx.android.synthetic.main.user_enquiries.view.*

class UserEnquiry : Fragment(){
    lateinit var v : View
    lateinit var toolBar : Toolbar
    lateinit var btnFilter : ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.user_enquiries,container,false)

        toolBar = activity!!.findViewById(R.id.toolbar)
        toolBar.title = "List Of Conveyance"
        btnFilter = toolBar.findViewById(R.id.img_filter)
        btnFilter.visibility = View.GONE

        v.recycler_enquiry.layoutManager = LinearLayoutManager(activity!!)
        v.recycler_enquiry.adapter = EnquiriesAdapter(activity!!)


        return  v}
}