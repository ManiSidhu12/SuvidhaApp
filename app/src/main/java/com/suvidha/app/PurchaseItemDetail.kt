package com.suvidha.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.adapter.app.PurchaseItemDetailAdapter
import com.adapter.app.SimpleChild
import com.adapter.app.SimpleParentItem
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem
import kotlinx.android.synthetic.main.purchase_detail_screen.*
import java.util.*

class PurchaseItemDetail : AppCompatActivity(){

    val expandableListDetail = HashMap<String, List<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar!!.title = "Purchase Item Detail"

        setContentView(R.layout.purchase_detail_screen)

        setData()

        recycler_purchase_detail.layoutManager = LinearLayoutManager(this@PurchaseItemDetail)

        recycler_purchase_detail.adapter = PurchaseItemDetailAdapter(this@PurchaseItemDetail, generateMockData())

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

    fun setData(){

        val cricket = ArrayList<String>()
        cricket.add("Prepare Your Local Conveyance")

        val football = ArrayList<String>()
        football.add("Installation Complaint/Call Register")

        val basketball = ArrayList<String>()
        basketball.add("Purchase Order Approval For Confirmation")
        basketball.add("Plan Approval For Purchase Order")

        val football1 = ArrayList<String>()
        football1.add("User Wise Enquiries Followed-Up")
        football1.add("Prepare/Modify an Enquiry")

        val football2 = ArrayList<String>()
        football2.add("Sunday working Master")

        expandableListDetail["HR"] = cricket
        expandableListDetail["Installation & services"] = football
        expandableListDetail["Purchase"] = basketball
        expandableListDetail["Quotes & Enquiries"] = football1
        expandableListDetail["T.A. Bill"] = football2
    }
}