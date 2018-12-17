package com.adapter.app

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.common.app.NothingSelectedSpinnerAdapter
import com.suvidha.app.R
import kotlinx.android.synthetic.main.complaint_dialog.*
import kotlinx.android.synthetic.main.followupdetails_dialog.*
import java.util.*

class InstallAdapter(var ctx : Context) : RecyclerView.Adapter<InstallAdapter.ViewHolder>(){

    val priorArray = arrayOf("Cold", "Hot","Warm")
    val deptArray  = arrayOf("All", "Automation Dept.","Design","Development Dept.")

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        val v = LayoutInflater.from(ctx).inflate(R.layout.install_adapter,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

       return 3
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {

       holder.itemView.setOnClickListener {
           openDialog(ctx)
       }
    }

    private fun openDialog(ctx : Context) {

        val dialog = Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.setContentView(R.layout.complaint_dialog)
        dialog.show()

        dialog.btn_close.setOnClickListener {
            dialog.dismiss()
        }
        dialog.followup.setOnClickListener {
            openDialogFollow(ctx,"follow")
        }
        dialog.forward.setOnClickListener {
            openDialogFollow(ctx,"forward")
        }
    }
    private fun openDialogFollow(ctx : Context, type : String) {

        val dialog1 = Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar)
        dialog1.setContentView(R.layout.followupdetails_dialog)

        if(type.equals("follow")){
            dialog1.txt_title.text = "FollowUp Details"
            dialog1.lay_prev.visibility = View.VISIBLE
            dialog1.lay_depart.visibility = View.GONE
            dialog1.lay_prior.visibility = View.VISIBLE

            val adapPriority = ArrayAdapter(ctx, R.layout.spinner_txt1, priorArray)
            adapPriority.setDropDownViewResource(R.layout.spinner_txt)
            dialog1.spin_prior.adapter = adapPriority
            dialog1.spin_prior.adapter = NothingSelectedSpinnerAdapter(adapPriority, R.layout.selection, ctx)
        }
        else{
            dialog1.txt_title.text = "Forward to Department"
            dialog1.lay_prev.visibility = View.GONE
            dialog1.lay_prior.visibility = View.GONE
            dialog1.lay_depart.visibility = View.VISIBLE

            val adapDept = ArrayAdapter(ctx, R.layout.spinner_txt1, deptArray)
            adapDept.setDropDownViewResource(R.layout.spinner_txt)
            dialog1.spin_depart.adapter = adapDept
            dialog1.spin_depart.adapter = NothingSelectedSpinnerAdapter(adapDept, R.layout.selection, ctx)
        }


        dialog1.show()

        dialog1.btn_close_follow.setOnClickListener {
            dialog1.dismiss()
        }
        dialog1.lay_date.setOnClickListener {
            showDatePicker(ctx,dialog1.txt_date_followup)
        }
    }

    private fun showDatePicker(ctx : Context,txt : TextView) {
        val c = Calendar.getInstance()
        val mYear  = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay   = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(ctx, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

         txt.text =  (monthOfYear + 1).toString() + "/" + dayOfMonth.toString()  + "/" + year

        }, mYear, mMonth, mDay)
        datePickerDialog.show()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

}