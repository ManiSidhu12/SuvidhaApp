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
import com.adapter.app.PurchaseAdapter
import com.common.app.NothingSelectedSpinnerAdapter
import com.suvidha.app.R
import kotlinx.android.synthetic.main.purchase_screen.view.*

class PurchaseFragment : Fragment(){
    lateinit var v : View
    lateinit var toolBar : Toolbar
    lateinit var btnFilter : ImageView
    var listBranch = ArrayList<String>()
    var listPrepared = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       v = inflater.inflate(R.layout.purchase_screen,container,false)

        toolBar = activity!!.findViewById(R.id.toolbar)
        toolBar.title = "PO Approval"
        btnFilter = toolBar.findViewById(R.id.img_filter)
        btnFilter.visibility = View.VISIBLE
        v.recycler_purchase.layoutManager = LinearLayoutManager(activity!!)
        v.recycler_purchase.adapter = PurchaseAdapter(activity!!)


        listBranch.add("Unit-SAHA")
        listBranch.add("Unit-Ambala")
        listBranch.add("Spares-Division")


        listPrepared.add("All")
        listPrepared.add("Purchase")
        listPrepared.add("MPP")


        val adapterBranch = ArrayAdapter<String>(activity!!, R.layout.spinner_txt1, listBranch)
        adapterBranch.setDropDownViewResource(R.layout.spinner_txt)
        v.spin_branch.adapter = adapterBranch

        v.spin_branch.adapter = NothingSelectedSpinnerAdapter(adapterBranch, R.layout.selection, activity!!)

        val adapterPrepare = ArrayAdapter<String>(activity!!, R.layout.spinner_txt1, listPrepared)
        adapterPrepare.setDropDownViewResource(R.layout.spinner_txt)
        v.spin_user.adapter = adapterPrepare

        work()

        return v
    }
    fun work(){
        btnFilter.setOnClickListener {
            if(v.lay_filters.visibility == View.VISIBLE){
                v.lay_filters.visibility = View.GONE
            }
            else{
                v.lay_filters.visibility = View.VISIBLE
            }
        }
    }



}