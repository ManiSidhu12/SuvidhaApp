package com.adapter.app

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suvidha.app.FollowUpDetails
import com.suvidha.app.R

class EnquiryFollowUpAdapter(var ctx : Context) :  RecyclerView.Adapter<EnquiryFollowUpAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.enquiry_followup,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.itemView.setOnClickListener {
            ctx.startActivity(Intent(ctx,FollowUpDetails::class.java))
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }
}