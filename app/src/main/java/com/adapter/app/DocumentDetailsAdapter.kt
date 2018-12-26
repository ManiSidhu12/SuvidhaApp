package com.adapter.app

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suvidha.app.R

class DocumentDetailsAdapter(var ctx : Context) :  RecyclerView.Adapter<DocumentDetailsAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.document_details_adapter,p0,false)
        return ViewHolder(v)    }

    override fun getItemCount(): Int {
return 4    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
    }

    class ViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView){

    }
}