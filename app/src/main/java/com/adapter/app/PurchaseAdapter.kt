package com.adapter.app

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suvidha.app.PurchaseItemDetail
import com.suvidha.app.R
import kotlinx.android.synthetic.main.purchase_adapter.view.*

class PurchaseAdapter(var ctx : Context) :  RecyclerView.Adapter<PurchaseAdapter.ViewHolder>(){

   var expandValue  = -1
    var pos = -1

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.purchase_adapter,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        //holder.txtOrderNo.paintFlags = holder.txtOrderNo.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        if(pos == p1){
            holder.imgChk.setImageResource(R.drawable.sel)

        }
        else{
            holder.imgChk.setImageResource(R.drawable.unslct)
        }
        if(p1 == 1){
            holder.txtName.text = "Devi Dayal Welding Works"
            holder.txtStatus.text = "Canceled"
            holder.txtStatus.setTextColor( ContextCompat.getColor(ctx, R.color.red))
        }
       if(expandValue == p1){
            holder.line.visibility = View.GONE
            holder.layDetails .visibility = View.VISIBLE
            holder.line1 .visibility = View.VISIBLE
           // holder.btnMore.setImageResource(R.drawable.ic_expand_less_black_24dp)
        }
        else{
            holder.line.visibility = View.VISIBLE
            holder.layDetails.visibility = View.GONE
            holder.line1 .visibility = View.GONE
          //  holder.btnMore.setImageResource(R.drawable.ic_expand_more_black_24dp)

        }
        holder.itemView.setOnClickListener {
           if(expandValue == p1){
               expandValue = -1
           }
            else{
               expandValue = p1
           }
            notifyDataSetChanged()
        }
        holder.txtDetails.setOnClickListener {
            ctx.startActivity(Intent(ctx,PurchaseItemDetail::class.java))
        }
        holder.imgChk.setOnClickListener {
            if(pos  == p1){
              pos = -1
            }
            else{
               pos  = p1
            }
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var layDetails = itemView.lay_details
        var line = itemView.line
        var line1 = itemView.line1
        var txtDetails = itemView.txt_detail
        var txtOrderNo = itemView.txt_orderno
        var imgChk = itemView.img_chk
        var txtStatus = itemView.txt_confirmation_status
        var txtName = itemView.txt_suppliername
    }
}