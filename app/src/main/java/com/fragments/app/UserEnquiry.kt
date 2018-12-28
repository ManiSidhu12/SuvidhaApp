package com.fragments.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.adapter.app.EnquiriesAdapter
import com.suvidha.app.R
import kotlinx.android.synthetic.main.user_enquiries.*
import kotlinx.android.synthetic.main.user_enquiries.view.*

class UserEnquiry : Fragment(){
    lateinit var v : View
    lateinit var toolBar : Toolbar
    lateinit var btnFilter : ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.user_enquiries,container,false)

        toolBar = activity!!.findViewById(R.id.toolbar)
        btnFilter = toolBar.findViewById(R.id.img_filter)
        v.lay_dates.visibility = View.GONE
        v.btn_apply.visibility = View.GONE
        v.recycler_enquiry.layoutManager = LinearLayoutManager(activity!!)

        if(arguments!!.getString("type") != null && arguments!!.getString("type").equals("user")) {
            toolBar.title = "User Enquiries"
            btnFilter.visibility = View.VISIBLE

            v.recycler_enquiry.adapter = EnquiriesAdapter(activity!!,"user")

        }
        else if(arguments!!.getString("type") != null && arguments!!.getString("type").equals("reject")){
            btnFilter.visibility = View.GONE
            toolBar.title = "Rejected Quotes/Enquiries"
            v.recycler_enquiry.adapter = EnquiriesAdapter(activity!!,"reject")

        }


work()
        return  v}
    fun work(){
        btnFilter.setOnClickListener {
if(v.lay_dates.visibility == View.GONE){
    v.lay_dates.visibility = View.VISIBLE
    btn_apply.visibility = View.VISIBLE
    btnFilter.setImageResource(R.drawable.filledfilter)
    btnFilter.setColorFilter(
        ContextCompat.getColor(activity!!,android.R.color.white),
        android.graphics.PorterDuff.Mode.SRC_IN
    )
}
            else{
    v.lay_dates.visibility = View.GONE
    btn_apply.visibility = View.GONE
    btnFilter.setImageResource(R.drawable.filter)
    btnFilter.setColorFilter(
        ContextCompat.getColor(activity!!,android.R.color.white),
        android.graphics.PorterDuff.Mode.SRC_IN
    )
            }
        }
    }
}