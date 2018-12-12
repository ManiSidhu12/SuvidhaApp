package com.adapter.app

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suvidha.app.NewConveyance
import com.suvidha.app.R
import kotlinx.android.synthetic.main.hr_adapter.view.*

class HRAdapter(var ctx : Context) : RecyclerView.Adapter<HRAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.hr_adapter,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
return 4
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.layEdit.setOnClickListener {

            ctx.startActivity(Intent(ctx,NewConveyance::class.java))
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
val layEdit = itemView.lay_edit_hr
    }


}