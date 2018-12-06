package com.adapter.app

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suvidha.app.R
import kotlinx.android.synthetic.main.item_detail_adapter.view.*

class PurchaseDetailAdapter(var ctx : Context) : RecyclerView.Adapter<PurchaseDetailAdapter.ViewHolder>(){
    var pos = -1;
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var v = LayoutInflater.from(ctx).inflate(R.layout.item_detail_adapter,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
return 5    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.txtPrice.text = "@85"
        holder.txtNo.paintFlags = holder.txtNo.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        if(pos == p1){
            holder.layBtns.visibility = View.VISIBLE
            holder.lineBtns.visibility = View.VISIBLE
        }
        else{
            holder.layBtns.visibility = View.GONE
            holder.lineBtns.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            if(pos == p1){
             pos = -1
            }
            else{
                pos = p1

            }
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
var lineBtns = itemView.line_btns
        var layBtns = itemView.lay_btns
        var txtPrice = itemView.txt_item_price
        var txtNo = itemView.txt_item_no

    }
}