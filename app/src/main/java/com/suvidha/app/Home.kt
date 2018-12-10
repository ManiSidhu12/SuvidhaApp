package com.suvidha.app

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.adapter.app.SimpleChild
import com.adapter.app.SimpleExpandableAdapter
import com.adapter.app.SimpleParentItem
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem
import kotlinx.android.synthetic.main.home_screen.*
import kotlinx.android.synthetic.main.app_bar_home.*
import java.util.*

class Home : AppCompatActivity() {

    val expandableListDetail = HashMap<String, List<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.home_screen)

        setSupportActionBar(toolbar)

         setData()

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        recycler_nav.layoutManager = LinearLayoutManager(this@Home)

        val adap = SimpleExpandableAdapter(this@Home, generateMockData(),drawer_layout)
        recycler_nav.setAdapter(adap)

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
    cricket.add("Prepare Your Local Conveyance")

    val football = ArrayList<String>()
    football.add("Installation Complaint/Call Register")

    val basketball = ArrayList<String>()
    basketball.add("Purchase Order Approval For Confirmation")
    basketball.add("Plan Approval For Purchase Order")
    basketball.add("View Pending Plan")

    val football1 = ArrayList<String>()
    football1.add("User Wise Enquiries Followed-Up")
    football1.add("Prepare/Modify an Enquiry")
    football1.add("Enquiry Register & Followed-Up")
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

}
