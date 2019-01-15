package com.suvidha.app

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentActivity
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import com.adapter.app.SimpleChild
import com.adapter.app.SimpleExpandableAdapter
import com.adapter.app.SimpleParentItem
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem
import com.common.app.Common
import com.common.app.SharedPrefManager
import com.fragments.app.HRFragment
import com.fragments.app.PurchaseFragment
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.model.login.LoginRoot
import kotlinx.android.synthetic.main.home_screen.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.home_screen.view.*
import kotlinx.android.synthetic.main.nav_header_home.*
import kotlinx.android.synthetic.main.nav_header_home.view.*
import kotlinx.android.synthetic.main.user_data_dialog.*
import java.io.StringReader
import java.util.*
import kotlin.math.exp

class Home : AppCompatActivity() {

    val expandableListDetail = HashMap<String, List<String>>()
    var listFy = ArrayList<String>()
    var listBranch = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.home_screen)

     //   setSupportActionBar(toolbar)

         setData()

       getResponse()
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        recycler_nav.layoutManager = LinearLayoutManager(this@Home)

        var navigationView = nav_view
        var header = navigationView.getHeaderView(0)
        header.user_name_side.text = SharedPrefManager.getInstance(this@Home).name
        header.textView.text = SharedPrefManager.getInstance(this@Home).unit
        header.textView2.text = SharedPrefManager.getInstance(this@Home).fy

        val adap = SimpleExpandableAdapter(this@Home, generateMockData(),drawer_layout)
        recycler_nav.setAdapter(adap)

        val f = PurchaseFragment()
       supportFragmentManager.beginTransaction().replace(R.id.frame, f).commit()

        header.clicks.setOnClickListener {
         openDialog(this@Home)
        }
navigationView.aly.setOnClickListener {
    SharedPrefManager.getInstance(this@Home).logout()
}

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    private fun generateMockData(): List<ParentListItem> {
        val parentListItems = ArrayList<ParentListItem>()
        for (entry in expandableListDetail.entries) {
            val key = entry.key
            val simpleParentItem = SimpleParentItem()
            simpleParentItem.title = key
            simpleParentItem.id = key
            val childItemList = ArrayList<SimpleChild>()

            val values = entry.value
            for(i in 0 until values.size) {
                childItemList.add(SimpleChild(values[i],""))
            }
          simpleParentItem.childItemList = childItemList
            println("Keyss = ${simpleParentItem.title}")
            println("Values = " + simpleParentItem.childItemList + "n")
            parentListItems.add(simpleParentItem)

        }
        parentListItems.reverse()
        return parentListItems
    }

private fun setData(){
    val cricket = ArrayList<String>()
    cricket.add("Prepare your local conveyance")

    val football = ArrayList<String>()
    football.add("Installation complaint/call register")

    val basketball = ArrayList<String>()
    basketball.add("Purchase order approval for confirmation")
    basketball.add("Plan approval for purchase order")
    basketball.add("View pending plan")

    val football1 = ArrayList<String>()
    football1.add("User-wise enquiries Followed-up")
    football1.add("Prepare/modify an Enquiry")
    football1.add("Enquiry Register & Follow-up")
    football1.add("Quotation Ageing")
    football1.add("Rejection Analysis")
    val football2 = ArrayList<String>()
    football2.add("Sunday working Master")

    expandableListDetail["HR"] = cricket
    expandableListDetail["Installation & services"] = football
    expandableListDetail["Purchase"] = basketball
    expandableListDetail["Quotes & Enquiries"] = football1
    expandableListDetail["T.A. Bill"] = football2

}
    private fun openDialog(ctx : Context) {

        val dialog1 = Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar)
        dialog1.setContentView(R.layout.user_data_dialog)
        val adapPriority = ArrayAdapter(ctx, R.layout.spinner_txt1, listBranch)
        adapPriority.setDropDownViewResource(R.layout.spinner_txt)
        dialog1.spin_unit.adapter = adapPriority
        val adapPriority1 = ArrayAdapter(ctx, R.layout.spinner_txt1, listFy)
        adapPriority1.setDropDownViewResource(R.layout.spinner_txt)
        dialog1.spin_fy.adapter = adapPriority1

        dialog1.show()
        if(SharedPrefManager.getInstance(this@Home).unit != null && !SharedPrefManager.getInstance(this@Home).unit.isEmpty()) {
            dialog1.spin_unit.setSelection(adapPriority.getPosition(SharedPrefManager.getInstance(this@Home).unit))
        }
        if(SharedPrefManager.getInstance(this@Home).fy != null && !SharedPrefManager.getInstance(this@Home).fy.isEmpty()) {
            dialog1.spin_fy.setSelection(adapPriority1.getPosition(SharedPrefManager.getInstance(this@Home).fy))
        }
        dialog1.btn_sub_data.setOnClickListener {
            if(dialog1.spin_unit.selectedItem == null){
                Common.showToast(this@Home,"Please Select Branch/Unit..")
            }
            else if(dialog1.spin_fy.selectedItem == null){
                Common.showToast(this@Home,"Please Select Financial Year..")
            }
            else {
                SharedPrefManager.getInstance(ctx).fy = dialog1.spin_fy.selectedItem.toString()
                SharedPrefManager.getInstance(ctx).unit = dialog1.spin_unit.selectedItem.toString()
                dialog1.dismiss()
                var navigationView = nav_view
                var header = navigationView.getHeaderView(0)
                header.user_name_side.text = SharedPrefManager.getInstance(this@Home).name
                header.textView.text = SharedPrefManager.getInstance(this@Home).unit
                header.textView2.text = SharedPrefManager.getInstance(this@Home).fy
            }
        }
        dialog1.btn_close_follow.setOnClickListener {
            dialog1.dismiss()
        }
    }
  fun getResponse(){
    val gson = Gson()
    val reader = JsonReader(StringReader(SharedPrefManager.getInstance(this@Home).login_Response))
    reader.isLenient = true
    var  rootLogin = gson.fromJson<LoginRoot>(reader, LoginRoot::class.java)
    if(rootLogin != null){
        if(rootLogin.table1 != null && rootLogin.table1.size > 0){
            for(i in 0 until rootLogin.table1.size) {
                listBranch.add(rootLogin.table1[i].unitname)
            }
        }
        if(rootLogin.table2 != null && rootLogin.table2.size > 0){
            for(j in 0 until rootLogin.table2.size) {
                listFy.add(rootLogin.table2[j].fyname)
            }
        }
    }
}
}
