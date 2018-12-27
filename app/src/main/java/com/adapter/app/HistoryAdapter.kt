package com.adapter.app

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suvidha.app.R
import kotlinx.android.synthetic.main.history_adapter.view.*

class HistoryAdapter(var ctx : Context): RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.history_adapter,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 4

    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        if(p1% 2 == 0){
            p0.txtSupplier.text = "S R Industries"
        }
        else{
            p0.txtSupplier.text = "Jagdish Woord Works"

        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
var txtSupplier = itemView.supplier
    }
}