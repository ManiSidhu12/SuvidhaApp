package com.fragments.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.suvidha.app.R
import kotlinx.android.synthetic.main.purchase_screen.view.*

class EnquiryFollowUp : Fragment(){
    lateinit var v : View
    lateinit var toolBar : Toolbar
    lateinit var btnFilter : ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.purchase_screen,container,false)

        toolBar = activity!!.findViewById(R.id.toolbar)
        toolBar.title = "Enquiry Follow Up"
        btnFilter = toolBar!!.findViewById(R.id.img_filter)
        btnFilter.visibility = View.VISIBLE

        work()


        return v
    }
    fun work(){
        btnFilter.setOnClickListener {
            if(v.lay_filters.visibility == View.VISIBLE){
                v.lay_filters.visibility = View.GONE
                v.lay_enquiry.visibility = View.GONE

            }
            else{
                v.lay_filters.visibility = View.VISIBLE
                v.select_branch.text = "Select"
                v.prepare.text = "Enquiry Type"
                v.txt_action.visibility = View.GONE
                v.lay_btns.visibility = View.GONE
                v.po_status.visibility = View.GONE
                v.lay_status.visibility = View.GONE
                v.lay_enquiry.visibility = View.VISIBLE
            }
        }
    }
}