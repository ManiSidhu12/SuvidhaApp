package com.suvidha.app

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.followup_details.*

class FollowUpDetails : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.followup_details)

        img_back_detail_follow.setOnClickListener {
            onBackPressed()
        }
    }
}