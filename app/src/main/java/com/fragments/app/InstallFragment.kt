package com.fragments.app

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.adapter.app.DocumentDetailsAdapter
import com.adapter.app.InstallAdapter
import com.common.app.GlobalConstants
import com.common.app.Imageutils
import com.common.app.NothingSelectedSpinnerAdapter
import com.common.app.RecyclerItemClickListener
import com.suvidha.app.FileChoose
import com.suvidha.app.R
import ir.sohreco.androidfilechooser.ExternalStorageNotAvailableException
import ir.sohreco.androidfilechooser.FileChooser
import ir.sohreco.androidfilechooser.FileChooser.Builder
import kotlinx.android.synthetic.main.complaint_dialog.*
import kotlinx.android.synthetic.main.followupdetails_dialog.*
import kotlinx.android.synthetic.main.installation_screen.view.*
import kotlinx.android.synthetic.main.upload_document.*
import java.util.*

class InstallFragment : Fragment(), Imageutils.ImageAttachmentListener {
    private val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 13

    override fun image_attachment(from: Int, filename: String, file: Bitmap, uri: Uri?) {
        file_path = filename
        dialog2!!.no_file.text = file.toString()
        GlobalConstants.path = ""
    }

    var file_path: String = ""

    lateinit var v: View
    lateinit var toolBar: Toolbar
    lateinit var btnFilter: ImageView
    var listComplain = ArrayList<String>()
    var listPriority = ArrayList<String>()
    val priorArray = arrayOf("Cold", "Hot", "Warm")
    val deptArray = arrayOf("All", "Automation Dept.", "Design", "Development Dept.")
    lateinit var builder: AlertDialog.Builder

    lateinit var imageUtils: Imageutils
     var dialog2: Dialog ? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        v = inflater.inflate(R.layout.installation_screen, container, false)

        toolBar = activity!!.findViewById(R.id.toolbar)
        toolBar.title = "Installation & Services"
        btnFilter = toolBar.findViewById(R.id.img_filter)
        btnFilter.visibility = View.VISIBLE
        btnFilter.setImageResource(R.drawable.filter)
        btnFilter.setColorFilter(
            ContextCompat.getColor(activity!!, android.R.color.white),
            android.graphics.PorterDuff.Mode.SRC_IN
        )

        v.recycler_install.layoutManager = LinearLayoutManager(activity!!)
        v.recycler_install.adapter = InstallAdapter(activity!!)

        imageUtils = Imageutils(activity, this, true)

        listComplain.add("All")
        listComplain.add("Pending")
        listComplain.add("Under Follow Up")
        listComplain.add("Closed")
        listComplain.add("Canceled")

        listPriority.add("Attempt to Contact")
        listPriority.add("Cold")
        listPriority.add("Hot")
        listPriority.add("Warm")

        val adapterComplain = ArrayAdapter<String>(activity!!, R.layout.spinner_txt1, listComplain)
        adapterComplain.setDropDownViewResource(R.layout.spinner_txt)
        v.spin_complain_status.adapter = adapterComplain

        val adapterPriority = ArrayAdapter<String>(activity!!, R.layout.spinner_txt1, listPriority)
        adapterPriority.setDropDownViewResource(R.layout.spinner_txt)
        v.spin_priority.adapter = adapterPriority

        v.spin_priority.adapter = NothingSelectedSpinnerAdapter(adapterPriority, R.layout.selection, activity!!)

        GlobalConstants.path = ""

        work()

        return v

    }

    private fun work() {
        //====== On Click On Drawer Recycler View to change Fragments ====================
        v.recycler_install.addOnItemTouchListener(
            RecyclerItemClickListener(
                activity!!,
                v.recycler_install,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        openDialog(activity!!)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                    }

                })
        )

        btnFilter.setOnClickListener {
            if (v.lay_filters_install.visibility == View.VISIBLE) {
                v.lay_filters_install.visibility = View.GONE
                btnFilter.setImageResource(R.drawable.filter)
                btnFilter.setColorFilter(
                    ContextCompat.getColor(activity!!, android.R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
            } else {
                v.lay_filters_install.visibility = View.VISIBLE
                btnFilter.setImageResource(R.drawable.filledfilter)
                btnFilter.setColorFilter(
                    ContextCompat.getColor(activity!!, android.R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    private fun openDialog(ctx: Context) {

        val dialog = Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.setContentView(R.layout.complaint_dialog)
        dialog.show()

        dialog.btn_close.setOnClickListener {
            dialog.dismiss()
        }
        dialog.followup.setOnClickListener {
            openDialogFollow(ctx, "follow")
        }
        dialog.forward.setOnClickListener {
            openDialogFollow(ctx, "forward")
        }
        dialog.upload.setOnClickListener {
            openDialogUpload(ctx)
        }
    }

    private fun openDialogFollow(ctx: Context, type: String) {

        val dialog1 = Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar)
        dialog1.setContentView(R.layout.followupdetails_dialog)

        if (type.equals("follow")) {
            dialog1.txt_title.text = "FollowUp Details"
            dialog1.lay_prev.visibility = View.VISIBLE
            dialog1.lay_depart.visibility = View.GONE
            dialog1.lay_prior.visibility = View.VISIBLE

            val adapPriority = ArrayAdapter(ctx, R.layout.spinner_txt1, priorArray)
            adapPriority.setDropDownViewResource(R.layout.spinner_txt)
            dialog1.spin_prior.adapter = adapPriority
            dialog1.spin_prior.adapter = NothingSelectedSpinnerAdapter(adapPriority, R.layout.selection, ctx)
        } else {
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
            showDatePicker(ctx, dialog1.txt_date_followup)
        }
    }

    private fun openDialogUpload(ctx: Context) {

        dialog2 = Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar)
        dialog2!!.setContentView(R.layout.upload_document)
        dialog2!!.recycler_details.layoutManager = LinearLayoutManager(ctx)
        dialog2!!.recycler_details.adapter = DocumentDetailsAdapter(ctx)
        dialog2!!.show()
        dialog2!!.btn_close_upload.setOnClickListener {
            dialog2!!.dismiss()
        }
        dialog2!!.lay_browse.setOnClickListener {
            openPickerAlert()
        }

    }

    private fun showDatePicker(ctx: Context, txt: TextView) {
        val c = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(ctx, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                txt.text = (monthOfYear + 1).toString() + "/" + dayOfMonth.toString() + "/" + year

            }, mYear, mMonth, mDay)
        datePickerDialog.show()
    }

    //=========== Open Alert==============
    fun openPickerAlert() {

        val items = arrayOf<CharSequence>("Take Photo", "Choose Photo from Gallery", "Choose File", "Cancel")
        builder = AlertDialog.Builder(activity)
        builder.setTitle("Upload File!")

        builder.setItems(items, DialogInterface.OnClickListener { dialog, item ->
            if (items[item] == "Take Photo") {
                imageUtils.launchCamera(1)

            } else if (items[item] == "Choose Photo from Gallery") {
                imageUtils.launchGallery(1)

            } else if (items[item].equals("Choose File")) {
                val permissionCheck = ContextCompat.checkSelfPermission(
                    activity!!,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                        activity!!,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE
                    )
                } else {
                   // addFileChooserFragment()
                   dialog.dismiss()
                    startActivity(Intent(activity!!,FileChoose::class.java))
                }
//pickDoc()
            } else if (items[item] == "Cancel") {
                dialog.dismiss()
            }
        })
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageUtils.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
//            FilePickerConst.REQUEST_CODE_DOC ->
//                if (resultCode == Activity.RESULT_OK && data != null) {
//                    docPaths = ArrayList()
//                    docPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS))
//                }
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        Log.e("Fragment", "onRequestPermissionsResult: $requestCode")
        imageUtils.request_permission_result(requestCode, permissions, grantResults)
        //EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
        if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                addFileChooserFragment()
            }
        }
    }

    private fun addFileChooserFragment() {
        Log.e("akkk","hhjkk")
        val builder = Builder(
            FileChooser.ChooserType.FILE_CHOOSER,
            FileChooser.ChooserListener { path -> Toast.makeText(activity!!, path, Toast.LENGTH_SHORT).show() })
        try {
          activity!!.supportFragmentManager!!.beginTransaction()
                .add(R.id.frame, builder.build())
                .commit()
        } catch (e: ExternalStorageNotAvailableException) {
            Toast.makeText(
                activity!!, "There is no external storage available on this device.",
                Toast.LENGTH_SHORT
            ).show()
            e.printStackTrace()
        }

    }



    override fun onResume() {
        super.onResume()
        if(dialog2 !=null) {
            dialog2!!.no_file.text = GlobalConstants.path
        }
    }
}
