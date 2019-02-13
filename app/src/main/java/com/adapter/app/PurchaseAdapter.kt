package com.adapter.app

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.model.login.purchase.Table
import com.suvidha.app.PurchaseItemDetail
import com.suvidha.app.R
import kotlinx.android.synthetic.main.msg_popup.*
import kotlinx.android.synthetic.main.purchase_adapter.view.*
import java.lang.Exception
import java.text.SimpleDateFormat

class PurchaseAdapter(var ctx: Context, var listPO : MutableList<Table>, var btnApprove : Button, var btnRefuse:Button) :  RecyclerView.Adapter<PurchaseAdapter.ViewHolder>(){

   var expandValue  = -1
    var pos = -1

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.purchase_adapter,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listPO.size
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        //holder.txtOrderNo.paintFlags = holder.txtOrderNo.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        if(listPO[p1].chkStatus != null && listPO[p1].chkStatus.equals("1")){
            holder.imgChk.setImageResource(R.drawable.sel)
        }
        else{
            holder.imgChk.setImageResource(R.drawable.unslct)
        }
        holder.txtName.text = listPO[p1].name
        holder.txtOrderNo.text = listPO[p1].orderno.toString()
        if(!listPO[p1].orderdate.toString().isEmpty()) {
            convertDate(listPO[p1].orderdate.toString(), holder.txtOrderDate)

        }
//        holder.txtDept.text = listPO[p1].deptname.toString() +" (Dept.)"
        if(listPO[p1].status.equals("A")) {
            holder.txtStatus.text = "Approved"

            holder.txtStatus.setTextColor(ContextCompat.getColor(ctx, R.color.dark_blue))
        }
        else if(listPO[p1].status.equals("R")) {
            holder.txtStatus.text = "Refused"
            holder.txtStatus.setTextColor(ContextCompat.getColor(ctx, R.color.red))
        }
        else  if(listPO[p1].status.equals("C")) {
            holder.txtStatus.text = "Confirmed"
            holder.txtStatus.setTextColor(ContextCompat.getColor(ctx, R.color.green))
        }
        else  if(listPO[p1].status.equals("H")) {
            holder.txtStatus.text = "Held Back"

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
            Log.e("values",listPO[p1].orderno.toString()+","+listPO[p1].orderid)
            ctx.startActivity(Intent(ctx,PurchaseItemDetail::class.java).putExtra("order_id",listPO[p1].orderid.toString()).putExtra("order_no",listPO[p1].orderno.toString()))
        }
        holder.imgChk.setOnClickListener {
            if(listPO[p1].chkStatus == null || listPO[p1].chkStatus.equals("0")){
             listPO[p1].chkStatus = "1"
            }
            else{
                listPO[p1].chkStatus = "0"

            }
            notifyDataSetChanged()
        }
        holder.txtDownload.setOnClickListener {
         openAlert(ctx,"Download")
        }
        holder.txtEmail.setOnClickListener {
            //openAlert(ctx,"Email")
            sendEmail(listPO[p1].email)
        }

        btnApprove.setOnClickListener {
          /* for(i in 0 until list2.size){
               if(list2[i].equals("1")){
                   listData.set(i,"Approved")
                   list2.set(i,"0")
               }
           }*/
            notifyDataSetChanged()
        }
        btnRefuse.setOnClickListener {
           /* for(i in 0 until list2.size){
                if(list2[i].equals("1")){
                    listData.set(i,"Cancelled")
                    list2.set(i,"0")
                }
            }*/
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
    fun convertDate(dd: String, txtOrderDate: TextView){
        var df =  SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")

        try {
           var result = df.parse(dd)
            System.out.println("date:"+result); //prints date in current locale
            var sdf =  SimpleDateFormat("yyyy-MM-dd")
           // sdf.setTimeZone(TimeZone.getTimeZone("GMT"))
            System.out.println(sdf.format(result)) //prints date in the format sdf
            txtOrderDate.text = sdf.format(result)
        }
        catch (ex : Exception){

        }
    }
    fun  sendEmail(emailId : String) {
        var ids = arrayOf(emailId)
        var emailIntent =  Intent(Intent.ACTION_SEND)

        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL,ids)
       // emailIntent.putExtra(Intent.EXTRA_CC, "aman")
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "PO Details")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here")

        try {
            ctx.startActivity(Intent.createChooser(emailIntent, "Send mail..."))
           // (ctx as Activity).finish()
        } catch (ex : android.content.ActivityNotFoundException ) {
         //   Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var layDetails = itemView.lay_details
        var line = itemView.line
        var line1 = itemView.line1
        var txtDetails = itemView.txt_detail
        var txtOrderNo = itemView.txt_orderno
        var txtOrderDate = itemView.txt_orderdate
        var imgChk = itemView.img_chk
        var txtDept = itemView.txt_postatus
        var txtStatus = itemView.txt_confirmation_status
        var txtName = itemView.txt_suppliername
        var layClick = itemView.lay_click
        var imgDrop = itemView.drop_img
        var txtDownload = itemView.txt_download
        var txtEmail = itemView.txt_email
    }
}