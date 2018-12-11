package com.adapter.app

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suvidha.app.R

class InstallAdapter(var ctx : Context) : RecyclerView.Adapter<InstallAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.install_adapter,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return 3
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }
}