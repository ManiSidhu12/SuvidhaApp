package com.fragments.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.adapter.app.InstallAdapter
import com.suvidha.app.R
import kotlinx.android.synthetic.main.installation_screen.view.*

class InstallFragment : Fragment(){
    lateinit var v : View
    lateinit var toolBar : Toolbar
    lateinit var btnFilter : ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        v = inflater.inflate(R.layout.installation_screen,container,false)

        toolBar = activity!!.findViewById(R.id.toolbar)
        toolBar.title = "Installation & Services"
        btnFilter = toolBar.findViewById(R.id.img_filter)
        btnFilter.visibility = View.VISIBLE

        v.recycler_install.layoutManager = LinearLayoutManager(activity!!)
        v.recycler_install.adapter = InstallAdapter(activity!!)

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