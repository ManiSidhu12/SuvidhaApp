package com.fragments.app

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.adapter.app.HRAdapter
import com.common.app.Common
import com.suvidha.app.NewConveyance
import com.suvidha.app.R
import kotlinx.android.synthetic.main.hr_screen.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

 class HRFragment : Fragment(){

    lateinit var v : View
    lateinit var toolBar : Toolbar
    lateinit var btnFilter : ImageView
    var listFilters = ArrayList<String>()

     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        v = inflater.inflate(R.layout.hr_screen,container,false)

        toolBar = activity!!.findViewById(R.id.toolbar)
        toolBar.title = "List Of Conveyance"
        btnFilter = toolBar.findViewById(R.id.img_filter)
        btnFilter.visibility = View.GONE
        btnFilter.setImageResource(R.drawable.filter)
        btnFilter.setColorFilter(ContextCompat.getColor(activity!!,android.R.color.white),android.graphics.PorterDuff.Mode.SRC_IN)

        listFilters.add("All")
        listFilters.add("Pending from User")
        listFilters.add("Pending from HOD")
        listFilters.add("Pending from Accounts")
        listFilters.add("Passed by Accounts")


        val adapterFilters = ArrayAdapter<String>(activity!!, R.layout.spinner_txt1, listFilters)
        adapterFilters.setDropDownViewResource(R.layout.spinner_txt)
        v.spin_filter.adapter = adapterFilters

        v.recycler_convo.layoutManager = LinearLayoutManager(activity!!)
        v.recycler_convo.adapter = HRAdapter(activity!!)

        work()


        return v
    }

    private fun work(){
        btnFilter.setOnClickListener {
            if(v.lay_filters_hr.visibility == View.VISIBLE){
                v.lay_filters_hr.visibility = View.GONE
                btnFilter.setImageResource(R.drawable.filter)
                btnFilter.setColorFilter(ContextCompat.getColor(activity!!,android.R.color.white),android.graphics.PorterDuff.Mode.SRC_IN
                )
            }

        }
        v.lay_from_date.setOnClickListener {
            showDatePicker(v.txt_from_date,"from","")
        }
        v.lay_to_date.setOnClickListener {
            showDatePicker(v.txt_to_date,"to",v.txt_from_date.text.toString())
        }
        v.btn_prepare.setOnClickListener {
            startActivity(Intent(activity!!,NewConveyance::class.java))
        }

    }
    private fun showDatePicker(txt : TextView,value : String,dateFrom : String) {
        val c = Calendar.getInstance()
        val mYear  = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay   = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener
         { view, year, monthOfYear, dayOfMonth ->

             if(value.equals("to")){
    if(checkDate(dateFrom,(monthOfYear + 1).toString() + "/" + dayOfMonth.toString()  + "/" + year)){
        txt.text =  (monthOfYear + 1).toString() + "/" + dayOfMonth.toString()  + "/" + year

    }
    else{
        Common.showToast(activity!!,"Please Select Valid Date...")
    }

}
            else{
    txt.text =  (monthOfYear + 1).toString() + "/" + dayOfMonth.toString()  + "/" + year

}
          }, mYear, mMonth, mDay)
        datePickerDialog.show()
    }
private fun checkDate(dateFrom : String,dateTo : String) : Boolean{
    var bool =  false
    val dateFormat = SimpleDateFormat("MM/dd/yyyy")
    try {
      val  convertedDate = dateFormat.parse(dateFrom)
      val  convertedDate2 = dateFormat.parse(dateTo)
        if(convertedDate2.after(convertedDate)){
         bool =  true
        } else {
           bool =  false
        }
    } catch (e: ParseException) {
        e.printStackTrace()
    }
return bool
}


}
