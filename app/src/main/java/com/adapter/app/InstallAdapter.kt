package com.adapter.app

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.common.app.NothingSelectedSpinnerAdapter
import com.suvidha.app.R
import kotlinx.android.synthetic.main.complaint_dialog.*
import kotlinx.android.synthetic.main.followupdetails_dialog.*
import kotlinx.android.synthetic.main.upload_document.*
import java.util.*

class InstallAdapter(var ctx : Context) : RecyclerView.Adapter<InstallAdapter.ViewHolder>(){


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        val v = LayoutInflater.from(ctx).inflate(R.layout.install_adapter,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

       return 3
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {


    }



    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

}