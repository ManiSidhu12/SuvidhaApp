package com.fragments.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.adapter.app.InstallAdapter
import com.common.app.NothingSelectedSpinnerAdapter
import com.suvidha.app.R
import kotlinx.android.synthetic.main.installation_screen.view.*

class InstallFragment : Fragment(){
    lateinit var v : View
    lateinit var toolBar : Toolbar
    lateinit var btnFilter : ImageView
    var listComplain = ArrayList<String>()
    var listPriority = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        v = inflater.inflate(R.layout.installation_screen,container,false)

        toolBar = activity!!.findViewById(R.id.toolbar)
        toolBar.title = "Installation & Services"
        btnFilter = toolBar.findViewById(R.id.img_filter)
        btnFilter.visibility = View.VISIBLE

        v.recycler_install.layoutManager = LinearLayoutManager(activity!!)
        v.recycler_install.adapter = InstallAdapter(activity!!)

        listComplain.add("All")
        listComplain.add("Pending")
        listComplain.add("Under Follow Up")
        listComplain.add("Closed")
        listComplain.add("Canceled")

        listPriority.add("Attempt to Contact")
        listPriority.add("Cold")
        listPriority.add("Hot")
        listPriority.add("Warm")

        val adapterComplain = ArrayAdapter<String>(activity!!, R.layout.spinner_txt1, listComplain)
        adapterComplain.setDropDownViewResource(R.layout.spinner_txt)
        v.spin_complain_status.adapter = adapterComplain

        val adapterPriority = ArrayAdapter<String>(activity!!, R.layout.spinner_txt1, listPriority)
        adapterPriority.setDropDownViewResource(R.layout.spinner_txt)
        v.spin_priority.adapter = adapterPriority

        v.spin_priority.adapter = NothingSelectedSpinnerAdapter(adapterPriority, R.layout.selection, activity!!)

        ///////*It is irrefuultable that eating together is a social activiity,sharing dinner together gives everyone a sense of identity.It can helpeas
        // */


        work()


        return v

    }
    private fun work(){

        btnFilter.setOnClickListener {
            if(v.lay_filters_install.visibility == View.VISIBLE){
                v.lay_filters_install.visibility = View.GONE
            }
            else{
                v.lay_filters_install.visibility = View.VISIBLE
            }
        }
    }
}