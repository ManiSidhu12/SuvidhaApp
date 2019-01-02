package com.adapter.app

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.suvidha.app.PurchaseItemDetail
import com.suvidha.app.R
import kotlinx.android.synthetic.main.msg_popup.*
import kotlinx.android.synthetic.main.purchase_adapter.view.*

class PurchaseAdapter(var ctx: Context, var listNames: ArrayList<String>, var listData: ArrayList<String>,
  var  list2: ArrayList<String>,var btnApprove : Button,var btnRefuse :Button) :  RecyclerView.Adapter<PurchaseAdapter.ViewHolder>(){

   var expandValue  = -1
    var pos = -1

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.purchase_adapter,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listNames.size
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        //holder.txtOrderNo.paintFlags = holder.txtOrderNo.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        if(list2[p1].equals("1")){
            holder.imgChk.setImageResource(R.drawable.sel)
        }
        else{
            holder.imgChk.setImageResource(R.drawable.unslct)
        }

        holder.txtName.text = listNames[p1]
        holder.txtStatus.text = listData[p1]

        if(listData[p1].equals("Pending")) {
            holder.txtStatus.setTextColor(ContextCompat.getColor(ctx, R.color.dark_blue))
        }
        else if(listData[p1].equals("Cancelled")) {
            holder.txtStatus.setTextColor(ContextCompat.getColor(ctx, R.color.red))
        }
        else  if(listData[p1].equals("Approved")) {
            holder.txtStatus.setTextColor(ContextCompat.getColor(ctx, R.color.green))
        }
        else  if(listData[p1].equals("Hold")) {
            holder.txtStatus.setTextColor(ContextCompat.getColor(ctx, R.color.orange))
        }
        if(expandValue == p1){
            holder.line.visibility = View.GONE
            holder.layDetails.visibility = View.VISIBLE
            holder.line1.visibility = View.VISIBLE
            holder.imgDrop.setImageResource(R.drawable.ic_expand_less_black_24dp)
            holder.imgDrop.setColorFilter(ContextCompat.getColor(ctx, R.color.dark_blue), android.graphics.PorterDuff.Mode.SRC_IN)

        }
        else{
            holder.line.visibility = View.VISIBLE
            holder.layDetails.visibility = View.GONE
            holder.line1.visibility = View.GONE
            holder.imgDrop.setImageResource(R.drawable.ic_expand_more_black_24dp)
            holder.imgDrop.setColorFilter(ContextCompat.getColor(ctx, R.color.dark_blue), android.graphics.PorterDuff.Mode.SRC_IN)

        }
        holder.layClick.setOnClickListener {
            if(expandValue == p1){
                expandValue = -1
            }
            else {
                expandValue = p1
            }
            notifyDataSetChanged()
        }
        holder.txtDetails.setOnClickListener {
            ctx.startActivity(Intent(ctx,PurchaseItemDetail::class.java))
        }
        holder.imgChk.setOnClickListener {
            if(list2[p1].equals("0")){
             list2.set(p1,"1")
            }
            else{
                list2.set(p1,"0")

            }
            notifyDataSetChanged()
        }
        holder.txtDownload.setOnClickListener {
         openAlert(ctx,"Download")
        }
        holder.txtEmail.setOnClickListener {
            openAlert(ctx,"Email")
        }

        btnApprove.setOnClickListener {
           for(i in 0 until list2.size){
               if(list2[i].equals("1")){
                   listData.set(i,"Approved")
                   list2.set(i,"0")
               }
           }
            notifyDataSetChanged()
        }
        btnRefuse.setOnClickListener {
            for(i in 0 until list2.size){
                if(list2[i].equals("1")){
                    listData.set(i,"Cancelled")
                    list2.set(i,"0")
                }
            }
            notifyDataSetChanged()
        }
    }
    private fun openAlert(ctx : Context, type : String) {

        val dialog1 = Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar)
        dialog1.setContentView(R.layout.msg_popup)
        dialog1.show()
        if(type.equals("Download")){
            dialog1.txt_msg.text = "File not available for downloading..."
        }
        else{
            dialog1.txt_msg.text = "Unable to send email to Invalid Email Id..."

        }
        dialog1.cncl.setOnClickListener {
            dialog1.dismiss()
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
        var layClick = itemView.lay_click
        var imgDrop = itemView.drop_img
        var txtDownload = itemView.txt_download
        var txtEmail = itemView.txt_email
    }
}