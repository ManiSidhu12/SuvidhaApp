package com.fragments.app

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.adapter.app.PurchaseAdapter
import com.common.app.Common
import com.suvidha.app.R
import kotlinx.android.synthetic.main.purchase_screen.view.*
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.common.app.CommonUtils
import com.common.app.NothingSelectedSpinnerAdapter
import com.common.app.SharedPrefManager
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.model.login.LoginRoot
import com.model.login.postatus.POStatusRoot
import com.model.login.postatus.POUserRoot
import com.model.login.purchase.PORoot
import kotlinx.android.synthetic.main.hold_popup.*
import java.io.StringReader


class PurchaseFragment : Fragment() {
    lateinit var v: View
    lateinit var toolBar: Toolbar
    lateinit var btnFilter: ImageView
    var selectedItem = 0
    var selectedItem1 = 0
    var selectedItem2 = 0
    var listBranch = ArrayList<String>()
    var listPrepared = ArrayList<String>()
    var listStatus = ArrayList<String>()
    var listPrepareId = ArrayList<String>()
    var listStatusCode = ArrayList<String>()
    var coid: String = ""
    var boid: String = ""
    var prepareId: String = ""
    var statusCode: String = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.purchase_screen, container, false)

        toolBar = activity!!.findViewById(R.id.toolbar)
        toolBar.title = "POs Pending Approval"
        btnFilter = toolBar.findViewById(R.id.img_filter)
        btnFilter.visibility = View.VISIBLE


        getResponse()
        coid = SharedPrefManager.getInstance(activity!!).coId
        boid = SharedPrefManager.getInstance(activity!!).boId

        if (CommonUtils.getConnectivityStatusString(activity!!).equals("true")) {
            getPO("1", "10", "", coid, boid, "", v.edt_srch_purchase.text.toString(), "", "")
        } else {
            CommonUtils.openInternetDialog(activity!!)
        }




        v.recycler_purchase.layoutManager = LinearLayoutManager(activity!!)
        //v.recycler_purchase.adapter = PurchaseAdapter(activity!!,listNames,listData,list,v.btn_approve,v.btn_refuse)

        //v.lay_reset.visibility = View.VISIBLE
        val adapterBranch = ArrayAdapter<String>(activity!!, R.layout.spinner_txt1, listBranch)
        adapterBranch.setDropDownViewResource(R.layout.spinner_txt)

        //v.spin_branch.adapter = NothingSelectedSpinnerAdapter(adapterBranch, R.layout.selection, activity!!)

        work()

        return v
    }

    fun work() {

        v.spin_branch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedItem = position

            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        v.spin_user.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedItem1 = position - 1
                if (v.spin_postatus.selectedItem != null) {
                    statusCode = listStatusCode[v.spin_postatus.selectedItemPosition - 1]
                }
                /* else{
                     if(listStatusCode != null && listStatusCode.size > 0) {
                         statusCode = listStatusCode[0]
                     }
                     else{
                         statusCode = ""
                     }
                 }*/
                if (v.spin_user.selectedItem != null) {
                    prepareId = listPrepareId[v.spin_user.selectedItemPosition - 1]
                }
                /* else{
                     prepareId = listPrepareId[0]
                 }*/
                if (CommonUtils.getConnectivityStatusString(activity!!).equals("true")) {
                    getPO("1", "10", "", coid, boid, "", v.edt_srch_purchase.text.toString(), statusCode, prepareId)
                } else {
                    CommonUtils.openInternetDialog(activity!!)
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        v.spin_postatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedItem2 = position - 1
                if (v.spin_postatus.selectedItem != null) {
                    statusCode = listStatusCode[v.spin_postatus.selectedItemPosition - 1]
                }
                /* else{
                     if(listStatusCode != null && listStatusCode.size > 0){
                         statusCode = listStatusCode[0]

                     }
                     else{
                         statusCode = ""
                     }
                 }*/
                if (v.spin_user.selectedItem != null) {
                    prepareId = listPrepareId[v.spin_user.selectedItemPosition - 1]
                }
//                else{
//                    if(listPrepareId  != null && listPrepareId.size > 0) {
//                        prepareId = listPrepareId[0]
//                    }
//                    else{
//                        prepareId = ""
//                    }
//                }
                if (CommonUtils.getConnectivityStatusString(activity!!).equals("true")) {
                    getPO("1", "10", "", coid, boid, "", v.edt_srch_purchase.text.toString(), statusCode, prepareId)
                } else {
                    CommonUtils.openInternetDialog(activity!!)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        v.edt_srch_purchase.addTextChangedListener(object : TextWatcher {

            // Before EditText text change.
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            // This method is invoked after user input text in EditText.
            override fun afterTextChanged(editable: Editable) {
                if (v.edt_srch_purchase.text.toString().isNotEmpty()) {
                    v.btn_srch.setBackgroundColor(Color.parseColor("#044A6C"))
                    v.btn_srch.setTextColor(Color.parseColor("#ffffff"))
                } else {
                    v.btn_srch.setBackgroundResource(android.R.drawable.btn_default)
                    v.btn_srch.setTextColor(activity!!.resources.getColor(android.R.color.darker_gray))
                }
            }
        })

        /*v.btn_approve.setOnClickListener {
          // Common.showToast(activity!!,"work In progress")
        }*/
        v.btn_refuse.setOnClickListener {
            // Common .showToast(activity!!,"Please select POs for refusal")
        }
        v.btn_hold.setOnClickListener {
            //  Common.showToast(activity!!,"Please select POs to hold")
            openDialogHold()
        }
        v.btn_srch.setOnClickListener {

            if (v.spin_postatus.selectedItem != null) {
                statusCode = listStatusCode[v.spin_postatus.selectedItemPosition]
            }
//            else{
//                statusCode = listStatusCode[0]
//            }
            if (v.spin_user.selectedItem != null) {
                prepareId = listPrepareId[v.spin_user.selectedItemPosition]
            }
            /* else{
                prepareId = listPrepareId[0]
             }*/
            if (CommonUtils.getConnectivityStatusString(activity!!).equals("true")) {
                getPO("1", "10", "", coid, boid, "", v.edt_srch_purchase.text.toString(), statusCode, prepareId)
            } else {
                CommonUtils.openInternetDialog(activity!!)
            }
            v.edt_srch_purchase.text = Editable.Factory.getInstance().newEditable("")

            val imm = activity!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.edt_srch_purchase.windowToken, 0)
        }
        btnFilter.setOnClickListener {
            if (v.lay_filters.visibility == View.VISIBLE) {
                v.lay_filters.visibility = View.GONE
                btnFilter.setImageResource(R.drawable.filter)
                btnFilter.setColorFilter(
                    ContextCompat.getColor(activity!!, android.R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )


            } else {
                v.lay_filters.visibility = View.VISIBLE
                btnFilter.setImageResource(R.drawable.filledfilter)
                btnFilter.setColorFilter(
                    ContextCompat.getColor(activity!!, android.R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                if (CommonUtils.getConnectivityStatusString(activity!!).equals("true")) {
                    getPOStatus()
                    getPOUsers()
                } else {
                    CommonUtils.openInternetDialog(activity!!)
                }
            }
        }
    }

    fun setSpinnerAdapter(spin: Spinner, list: ArrayList<String>, type: String) {
        val mAdapter = object : ArrayAdapter<String>(activity!!, R.layout.support_simple_spinner_dropdown_item, list) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                // Cast the spinner collapsed item (non-popup item) as a text view
                val tv = super.getView(position, convertView, parent) as TextView

                // Set the text color of spinner item
                tv.setTextColor(Color.BLACK)

                // Return the view
                return tv
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                // Cast the drop down items (popup items) as text view
                val tv = super.getDropDownView(position, convertView, parent) as TextView

                // Set the text color of drop down items
                //tv.setTextColor(Color.RED)

                // If this item is selected item
                if (type.equals("branch")) {
                    if (position == selectedItem) {
                        // Set spinner selected popup item's text color
                        tv.setBackgroundColor(Color.parseColor("#044A6C"))
                        tv.setTextColor(Color.WHITE)
                    } else {
                        tv.setBackgroundColor(Color.WHITE)
                        tv.setTextColor(Color.BLACK)
                    }
                } else if (type.equals("user")) {
                    if (position == selectedItem1) {
                        // Set spinner selected popup item's text color
                        tv.setBackgroundColor(Color.parseColor("#044A6C"))
                        tv.setTextColor(Color.WHITE)
                    } else {
                        tv.setBackgroundColor(Color.WHITE)
                        tv.setTextColor(Color.BLACK)
                    }
                } else {
                    if (position == selectedItem2) {
                        // Set spinner selected popup item's text color
                        tv.setBackgroundColor(Color.parseColor("#044A6C"))
                        tv.setTextColor(Color.WHITE)
                    } else {
                        tv.setBackgroundColor(Color.WHITE)
                        tv.setTextColor(Color.BLACK)
                    }
                }

                // Return the modified view
                return tv
            }
        }
        spin.adapter = mAdapter
        spin.adapter = NothingSelectedSpinnerAdapter(mAdapter, R.layout.selection, activity!!)

    }

    fun openDialogHold() {
        val dialog = Dialog(activity!!, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.setContentView(R.layout.hold_popup)

        dialog.show()
        dialog.btn_close_hold.setOnClickListener {
            dialog.dismiss()
        }

        dialog.btn_sub_hold.setOnClickListener {
            if (dialog.edt_remark_hold.text.toString().isEmpty()) {
                Common.showToast(activity!!, "Please enter Remark..")
            } else if (dialog.edt_reason.text.toString().isEmpty()) {
                Common.showToast(activity!!, "Please enter Reason for holding PO..")

            } else {
                dialog.dismiss()
            }
        }
    }

    fun getResponse() {
        val gson = Gson()
        val reader = JsonReader(StringReader(SharedPrefManager.getInstance(activity!!).login_Response))
        reader.isLenient = true
        var rootLogin = gson.fromJson<LoginRoot>(reader, LoginRoot::class.java)
        if (rootLogin != null) {
            if (rootLogin.table1 != null && rootLogin.table1.size > 0) {
                for (i in 0 until rootLogin.table1.size) {
                    listBranch.add(rootLogin.table1[i].unitname)
                }
            }


        }
        if (listBranch != null && listBranch.size > 0) {
            setSpinnerAdapter(v.spin_branch, listBranch, "branch")

        } else {
            v.lay_branch.visibility = View.GONE
            v.select_branch.visibility = View.GONE
        }

    }


    //============= PO Web Service =====
    private fun getPO(
        page: String,
        size: String,
        sort: String,
        coid: String,
        boid: String,
        order: String,
        srch: String,
        status: String,
        user: String
    ) {
        val url =
            "http://suvidhaapi.suvidhacloud.com/api/PO/getPODetails?PageNumber=" + page + "&PageSize=" + size + "&sort=" + sort + "&coid=" + coid + "&boid=" + boid + "&sortorder=" + order + "&search=" + srch + "&postatus=" + status + "&preparedby=" + user
        Log.e("url po details", url)
        val pd = ProgressDialog.show(activity, "", "Loading", false)

        val postRequest = object : StringRequest(
            Request.Method.GET, url, { response ->
                pd.dismiss()
                Log.e("purchase Response", response)
                val gson = Gson()
                val reader = JsonReader(StringReader(response))
                reader.isLenient = true
                var rootPurchase = gson.fromJson<PORoot>(reader, PORoot::class.java)

                if (rootPurchase != null) {
                    if (rootPurchase.table != null && rootPurchase.table.size > 0) {
                        v.recycler_purchase.visibility = View.VISIBLE
                        v.txt_nodata.visibility = View.GONE
                        //v.recycler_purchase.adapter = PurchaseAdapter(activity!!,listNames,listData,list,v.btn_approve,v.btn_refuse)
                        v.recycler_purchase.adapter =
                            PurchaseAdapter(activity!!, rootPurchase.table, v.btn_approve, v.btn_refuse)
                        btnFilter.visibility = View.VISIBLE
                        btnFilter.setImageResource(R.drawable.filter)
                        btnFilter.setColorFilter(
                            ContextCompat.getColor(activity!!, android.R.color.white),
                            android.graphics.PorterDuff.Mode.SRC_IN
                        )
                        v.lay_actions.visibility = View.VISIBLE

                    } else {
                        v.recycler_purchase.visibility = View.GONE
                        //   v.txt_nodata.visibility = View.VISIBLE
                        v.lay_actions.visibility = View.GONE
                        Common.showToast(activity!!, "PO not found...")

                    }
                } else {
                    //   btnFilter.visibility = View.GONE
                    //    v.lay_actions.visibility = View.GONE
                    v.recycler_purchase.visibility = View.GONE
//v.txt_nodata.visibility = View.VISIBLE
                    v.lay_actions.visibility = View.GONE

                    Common.showToast(activity!!, "PO not found...")

                }
            },

            { error: VolleyError ->
                pd.dismiss()
                // btnFilter.visibility = View.GONE
                // v.lay_actions.visibility = View.GONE
                v.recycler_purchase.visibility = View.GONE
                v.txt_nodata.visibility = View.VISIBLE
                //  Common.showToast(activity!!, error.message.toString())

            }) {
        }

        postRequest.retryPolicy = DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(postRequest)

    }

    //============ GET POStatus =================
    private fun getPOStatus() {

        // val pd = ProgressDialog.show(activity!!, "", "Loading", false)

        val postRequest = object : StringRequest(
            Request.Method.GET, "http://suvidhaapi.suvidhacloud.com/api/PO/PoStatus", { response ->
                //  pd.dismiss()
                Log.e("POStatus Response", response)
                val gson = Gson()
                val reader = JsonReader(StringReader(response))
                reader.isLenient = true
                var rootPostatus  = gson.fromJson<POStatusRoot>(reader, POStatusRoot::class.java)

                if (rootPostatus != null) {
                    if (rootPostatus.table != null && rootPostatus.table.size > 0) {
                        for (i in 0 until rootPostatus.table.size) {
                            listStatus.add(rootPostatus.table[i].description)
                            listStatusCode.add(rootPostatus.table[i].code)
                        }
                        val adapterStatus = ArrayAdapter<String>(activity!!, R.layout.spinner_txt1, listStatus)
                        adapterStatus.setDropDownViewResource(R.layout.spinner_txt)
                        //  v.spin_postatus.adapter = adapterStatus
                        setSpinnerAdapter(v.spin_postatus, listStatus, "status")
                        /*   if(listPrepareId != null && listPrepareId.size > 0){
                               prepareId = listPrepareId[0]
                           }
                           else{
                               prepareId = ""
                           }
                           if(listStatusCode != null && listStatusCode.size > 0){
                               statusCode = listStatusCode[0]
                           }
                           else{
                               statusCode = ""
                           }

                           if(CommonUtils.getConnectivityStatusString(activity!!).equals("true")){
                               //getPO("1","10","",coid,boid,"",v.edt_srch_purchase.text.toString(),statusCode,prepareId)
                           }
                           else{
                               CommonUtils.openInternetDialog(activity!!)
                           }
   */
                    } else {

                    }
                } else {
                    Common.showToast(activity!!, "Failure")

                }
            },

            { error: VolleyError ->
                //   pd.dismiss()
                Common.showToast(activity!!, error.message.toString())

            }) {
        }

        postRequest.retryPolicy =
            DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(postRequest)

    }

    //============ GET PO Users =================
    private fun getPOUsers() {

        // val pd = ProgressDialog.show(activity!!, "", "Loading", false)

        val postRequest = object : StringRequest(
            Request.Method.GET, "http://suvidhaapi.suvidhacloud.com/api/PO/POPreparedByUsers", { response ->
                //    pd.dismiss()
                Log.e("POUser Response", response)
                val gson = Gson()
                val reader = JsonReader(StringReader(response))
                reader.isLenient = true
                var rootPoUser = gson.fromJson<POUserRoot>(reader, POUserRoot::class.java)

                if (rootPoUser != null) {
                    if (rootPoUser.table != null && rootPoUser.table.size > 0) {
                        for (i in 0 until rootPoUser.table.size) {
                            listPrepared.add(rootPoUser.table[i].username)
                            listPrepareId.add(rootPoUser.table[i].userid.toString())
                        }
                        val adapterPrepare = ArrayAdapter<String>(activity!!, R.layout.spinner_txt1, listPrepared)
                        adapterPrepare.setDropDownViewResource(R.layout.spinner_txt)
                        //v.spin_user.adapter = adapterPrepare
                        setSpinnerAdapter(v.spin_user, listPrepared, "user")
                        /*  if(listStatusCode != null && listStatusCode.size > 0){
                              statusCode = listStatusCode[0]
                          }
                          else{
                              statusCode = ""
                          }
                          if(listPrepareId != null && listPrepareId.size > 0){
                              prepareId = listPrepareId[0]
                          }
                          else{
                              prepareId = ""
                          }

                          if(CommonUtils.getConnectivityStatusString(activity!!).equals("true")){
                              getPO("1","10","",coid,boid,"",v.edt_srch_purchase.text.toString(),statusCode,prepareId)
                          }
                          else{
                              CommonUtils.openInternetDialog(activity!!)
                          }*/

                    } else {

                    }
                } else {
                    Common.showToast(activity!!, "Failure")

                }
            },

            { error: VolleyError ->
                //pd.dismiss()
                Common.showToast(activity!!, error.message.toString())

            }) {
        }

        postRequest.retryPolicy =
            DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(postRequest)

    }

}