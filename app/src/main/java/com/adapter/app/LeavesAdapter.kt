package com.adapter.app

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suvidha.app.R
import kotlinx.android.synthetic.main.leave_adapter.view.*

class LeavesAdapter (var ctx : Context) : RecyclerView.Adapter<LeavesAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.leave_adapter,p0,false)
        return ViewHolder(v)    }

    override fun getItemCount(): Int {
return 5    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        if(p1 == 0){
            p0.layHide.visibility = View.VISIBLE
        }
        else{
            p0.layHide.visibility = View.GONE
        }
    }

    class ViewHolder (itemView :View) : RecyclerView.ViewHolder(itemView){
var layHide = itemView.lay_hide
    }
}