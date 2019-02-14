package com.suvidha.app

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.adapter.app.PurchaseItemDetailAdapter
import com.adapter.app.SimpleChild
import com.adapter.app.SimpleParentItem
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem
import com.common.app.Common
import com.common.app.CommonUtils
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.model.login.purchase.item.POItemsRoot
import kotlinx.android.synthetic.main.purchase_detail_screen.*
import java.io.StringReader
import java.util.*

class PurchaseItemDetail : Activity(){

   // val expandableListDetail = HashMap<String, List<String>>()
    var order_id = ""
    var order_no =""
var rootItem : POItemsRoot ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.purchase_detail_screen)

       // setData()

        recycler_purchase_detail.layoutManager = LinearLayoutManager(this@PurchaseItemDetail)

       // recycler_purchase_detail.adapter = PurchaseItemDetailAdapter(this@PurchaseItemDetail, generateMockData())

        img_back_detail.setOnClickListener {
            onBackPressed()
        }
if(intent != null && intent.extras != null){
    order_id = intent.extras.getString("order_id")
    order_no = intent.extras.getString("order_no")
}

        if(CommonUtils.getConnectivityStatusString(this).equals("true")){
            getPOItems(order_id,order_no)
        }
        else{
            CommonUtils.openInternetDialog(this)
        }
        }



    private fun generateMockData(): List<ParentListItem> {
        val parentListItems = ArrayList<ParentListItem>()
        for (i in 0 until rootItem!!.table.size) {
            //val key = entry.key
            val simpleParentItem = SimpleParentItem()
            simpleParentItem.itemName = rootItem!!.table[i].itemname
            simpleParentItem.itemId = rootItem!!.table[i].id.toString()
            simpleParentItem.itemCode = rootItem!!.table[i].itemcode
            simpleParentItem.itemQuantity = rootItem!!.table[i].orderedquantity.toDouble()
            simpleParentItem.itemRate = rootItem!!.table[i].itemrate.toDouble()
            simpleParentItem.orderId = rootItem!!.table[i].orderid.toString()
            val childItemList = ArrayList<SimpleChild>()

           // val values = entry.value
            for(j in 0 until rootItem!!.table1.size) {
                childItemList.add(SimpleChild(rootItem!!.table1[j].id.toString(),rootItem!!.table1[j].orderid.toString(),rootItem!!.table1[j].itemid.toString(),rootItem!!.table1[j].remarks,rootItem!!.table1[j].deliverydate,rootItem!!.table1[j].requiredqty.toString(),rootItem!!.table1[j].receivedqty.toString(),rootItem!!.table1[j].receivedon))
            }
            simpleParentItem.childItemList = childItemList
         //   println("Keyss = ${simpleParentItem.title}")
           // println("Values = " + simpleParentItem.childItemList + "n")
            parentListItems.add(simpleParentItem)

        }
        return parentListItems

    }

/*
   private fun setData(){

        val cricket = ArrayList<String>()
        cricket.add("Prepare Your Local Conveyance")

        val football = ArrayList<String>()
        football.add("Installation Complaint/Call Register")

        val basketball = ArrayList<String>()
        basketball.add("Purchase Order Approval For Confirmation")
      //  basketball.add("Plan Approval For Purchase Order")

        val football1 = ArrayList<String>()
        football1.add("User Wise Enquiries Followed-Up")
      //  football1.add("Prepare/Modify an Enquiry")

        val football2 = ArrayList<String>()
        football2.add("Sunday working Master")

        expandableListDetail["HR"] = cricket
        expandableListDetail["Installation & services"] = football
        expandableListDetail["Purchase"] = basketball
        expandableListDetail["Quotes & Enquiries"] = football1
        expandableListDetail["T.A. Bill"] = football2
    }
*/


    private fun getPOItems(order_id : String,order_no : String) {

        val pd = ProgressDialog.show(this, "", "Loading", false)
val url = "http://suvidhaapi.suvidhacloud.com/api/PO/getPOItemsDetail?orderid=" + order_id + "&orderno=" + order_no
        Log.e("path",url)
        val postRequest = object : StringRequest(
            Request.Method.GET, url, { response ->
                pd.dismiss()
                Log.e("item details Response", response)
                val gson = Gson()
                val reader = JsonReader(StringReader(response))
                reader.isLenient = true
                 rootItem  = gson.fromJson<POItemsRoot>(reader, POItemsRoot::class.java)

                if (rootItem != null) {
                    if (rootItem!!.table != null && rootItem!!.table.size > 0) {
                        recycler_purchase_detail.adapter = PurchaseItemDetailAdapter(this@PurchaseItemDetail, generateMockData(),rootItem!!.table2)

                    }
                } else {
                    Common.showToast(this, "Failure")

                }
            },

            { error: VolleyError ->
                  pd.dismiss()
                Common.showToast(this, error.message.toString())

            }) {
        }

        postRequest.retryPolicy =
            DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(postRequest)

    }

}