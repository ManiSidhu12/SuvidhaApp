package com.adapter.app

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.model.login.purchase.item.Table2
import com.suvidha.app.R
import kotlinx.android.synthetic.main.history_adapter.view.*

class HistoryAdapter(
    var ctx: Context,
   var list: MutableList<Table2>
): RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.history_adapter,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

            p0.txtSupplier.text = list[p1].name
            p0.txtPrice.text = list[p1].suplitemrate.toString()

    }

class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
var txtSupplier = itemView.supplier
var txtPrice = itemView.txt_history_price
var txtDated = itemView.txt_dated_history
    }
}