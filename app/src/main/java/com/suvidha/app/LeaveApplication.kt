package com.suvidha.app

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.adapter.app.LeavesAdapter
import kotlinx.android.synthetic.main.leave_alert.*
import kotlinx.android.synthetic.main.leave_application.*

class LeaveApplication : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.leave_application)

        leaves_available.setOnClickListener {
            openAlert()
        }
    }
    fun openAlert(){
        val dialog = Dialog(this, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.setContentView(R.layout.leave_alert)
        dialog.show()
dialog.recycler_leaves.layoutManager = LinearLayoutManager(this)
        dialog.recycler_leaves.adapter = LeavesAdapter(this)
        dialog.btn_close_leave.setOnClickListener {
            dialog.dismiss()
        }
    }
}