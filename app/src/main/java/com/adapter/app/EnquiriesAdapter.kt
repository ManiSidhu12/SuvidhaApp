package com.adapter.app

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suvidha.app.R
import kotlinx.android.synthetic.main.enquiry_adapter.view.*

class EnquiriesAdapter (var ctx : Context,var type : String) : RecyclerView.Adapter<EnquiriesAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.enquiry_adapter,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        if(type.equals("user")){
            holder.txtName.text = "Anuj Mehta"

        }
        else{
            holder.txtName.text = "Already purchase in another quote."
        }
    }

    class ViewHolder(itemView : View) :  RecyclerView.ViewHolder(itemView){
     var txtName = itemView.user_name
    }
}