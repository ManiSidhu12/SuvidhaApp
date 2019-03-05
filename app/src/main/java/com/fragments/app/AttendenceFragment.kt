package com.fragments.app

import android.Manifest
import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import android.content.IntentSender
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.LocationUtil.PermissionUtils
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.suvidha.app.LeaveApplication
import com.suvidha.app.R
import kotlinx.android.synthetic.main.attendence_screen.view.*
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AttendenceFragment : Fragment(), GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback, PermissionUtils.PermissionResultCallback {

    var listTimes = ArrayList<String>()
    private val PLAY_SERVICES_REQUEST = 1000
    private val REQUEST_CHECK_SETTINGS = 2000

    private var mLastLocation: Location? = null
    // Google client to interact with Google API

    lateinit var mGoogleApiClient: GoogleApiClient
    internal var latitude: Double = 0.toDouble()
    internal var longitude: Double = 0.toDouble()

    // list of permissions

    internal var permissions = ArrayList<String>()
    lateinit var permissionUtils: PermissionUtils

    internal var isPermissionGranted: Boolean = false

    override fun onConnected(p0: Bundle?) {
        getLocation()
    }

    override fun onConnectionSuspended(p0: Int) {
        mGoogleApiClient.connect()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    override fun PermissionGranted(request_code: Int) {
       // Log.i("PERMISSION Attendence", "GRANTED")
        isPermissionGranted = true
        mGoogleApiClient = GoogleApiClient.Builder(activity!!)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API).build()
        getLocation()
    }

    override fun PartialPermissionGranted(request_code: Int, granted_permissions: ArrayList<String>?) {
    }

    override fun PermissionDenied(request_code: Int) {
    }

    override fun NeverAskAgain(request_code: Int) {
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        permissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    lateinit var v : View
    lateinit var toolBar : Toolbar
    lateinit var btnFilter : ImageView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.attendence_screen,container,false)
        toolBar = activity!!.findViewById(R.id.toolbar)
        toolBar.title = "Attendence"
        btnFilter = toolBar.findViewById(R.id.img_filter)
        btnFilter.visibility = View.GONE

        permissionUtils = PermissionUtils(activity!!)

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)


        permissionUtils.check_permission(permissions, "Need GPS permission for getting your location", 1)
        Log.e("res",checkPlayServices().toString())

        mGoogleApiClient = GoogleApiClient.Builder(activity!!)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API).build()
        getLocation()

        getCurrentDate()
       /* if (mLastLocation != null) {
            latitude = mLastLocation!!.latitude
            longitude = mLastLocation!!.longitude
            Log.e("lat",""+latitude+"lat"+longitude)
            getAddress()

        } else {


           // Common.showToast(activity!!,"Couldn't get the location. Make sure location is enabled on the device")
        }*/
        if (checkPlayServices()) {

            // Building the GoogleApi client
            buildGoogleApiClient()
        }
        listTimes.add("Travel Start Time")
        listTimes.add("Duty On Time")
        listTimes.add("Duty Off Time")

        work()
        return v
    }
    private fun work(){
        val adapterTime = ArrayAdapter<String>(activity!!, R.layout.spinner_txt1, listTimes)
        adapterTime.setDropDownViewResource(R.layout.spinner_txt)
        v.spin_times.adapter = adapterTime

        v.spin_times.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if(position == 0){
                    v.lay_start.visibility = View.VISIBLE
                    v.lay_duty_on.visibility = View.GONE
                    v.lay_duty_off.visibility = View.GONE
                }
                else if(position == 1){
                    v.lay_start.visibility = View.VISIBLE
                    v.lay_duty_on.visibility = View.VISIBLE
                    v.lay_duty_off.visibility = View.GONE
                }
                else if(position == 2){
                    v.lay_start.visibility = View.GONE
                    v.lay_duty_on.visibility = View.VISIBLE
                    v.lay_duty_off.visibility = View.VISIBLE
                }
            }

        }
        v.btn_close_keyboard.setOnClickListener {
            val imm = activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.edt_remark_attendence.windowToken, 0)
        }
        v.rg.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.radio_leave){
                startActivity(Intent(activity!!,LeaveApplication::class.java))
                v.rg.check(R.id.radio_absent)


            }
        }
        v.leave_request.setOnClickListener {
            startActivity(Intent(activity!!,LeaveApplication::class.java))

        }

        v.lay_start_time.setOnClickListener {
            if(v.spin_times.selectedItem != null && v.spin_times.selectedItem.toString().equals("Travel Start Time")) {
                openTimePicker(v.txt_starttime)
            }
        }
        v.lay_duty_time.setOnClickListener {
            openTimePicker(v.txt_dutytime)
        }
        v.lay_dutyoff_time.setOnClickListener {
            openTimePicker(v.txt_dutyofftime)
        }
    }
    fun openTimePicker(txt : TextView){
        val c = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(activity, TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
            c.set(Calendar.HOUR_OF_DAY, hourOfDay)
            c.set(Calendar.MINUTE, minute)
            val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.time)
            Log.d("MainActivity", "Selected time is $time")
            txt.text = time
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true)
        timePickerDialog.show()

    }

    private fun getLocation() {

       // if (isPermissionGranted) {

            try {
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
                Log.e("mlast",mLastLocation.toString())
                if (mLastLocation != null) {
                    latitude = mLastLocation!!.latitude
                    longitude = mLastLocation!!.longitude
                    Log.e("lat",""+latitude+"lat"+longitude)
                    getAddress()

                }
            } catch (e: SecurityException) {
                e.printStackTrace()
            }

      //  }

    }

    fun getAddress(latitude: Double, longitude: Double): Address? {
        val geocoder: Geocoder
        val addresses: List<Address>
        geocoder = Geocoder(activity!!, Locale.getDefault())

        try {
            addresses = geocoder.getFromLocation(
                latitude,
                longitude,
                1
            ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            return addresses[0]

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null

    }

    fun getAddress() {

        val locationAddress = getAddress(latitude, longitude)

        if (locationAddress != null) {
            val address = locationAddress.getAddressLine(0)
            val address1 = locationAddress.getAddressLine(1)
            val city = locationAddress.locality
            val state = locationAddress.adminArea
            val country = locationAddress.countryName
            val postalCode = locationAddress.postalCode

            var currentLocation: String

            if (!TextUtils.isEmpty(address)) {
                currentLocation = address

                if (!TextUtils.isEmpty(address1))
                    currentLocation += "\n" + address1

                if (!TextUtils.isEmpty(city)) {
                    currentLocation += "\n" + city

                    if (!TextUtils.isEmpty(postalCode))
                        currentLocation += " - $postalCode"
                } else {
                    if (!TextUtils.isEmpty(postalCode))
                        currentLocation += "\n" + postalCode
                }

                if (!TextUtils.isEmpty(state))
                    currentLocation += "\n" + state

                if (!TextUtils.isEmpty(country))
                    currentLocation += "\n" + country

               // tvEmpty.setVisibility(View.GONE)
                v.txt_loc.setText(currentLocation)
                //tvAddress.setVisibility(View.VISIBLE)


            }

        }

    }

    @Synchronized
    protected fun buildGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(activity!!)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API).build()

        mGoogleApiClient.connect()

        val mLocationRequest = LocationRequest()
        mLocationRequest.interval = 10000
        mLocationRequest.fastestInterval = 5000
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(mLocationRequest)

        val result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build())

        result.setResultCallback { locationSettingsResult ->
            val status = locationSettingsResult.status
             Log.e("status",status.statusCode.toString())
              when (status.statusCode) {   LocationSettingsStatusCodes.SUCCESS ->
                    // All location settings are satisfied. The client can initialize location requests here
                    getLocation()
                LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    status.startResolutionForResult(activity!!, REQUEST_CHECK_SETTINGS)

                } catch (e: IntentSender.SendIntentException) {
                    // Ignore the error.
                }

                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                }
            }
        }


    }

    private fun checkPlayServices(): Boolean {

        val googleApiAvailability = GoogleApiAvailability.getInstance()

        val resultCode = googleApiAvailability.isGooglePlayServicesAvailable(activity!!)

        if (resultCode != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(resultCode)) {
                googleApiAvailability.getErrorDialog(
                    activity!!, resultCode,
                    PLAY_SERVICES_REQUEST
                ).show()
            } else {
                //Toast.makeText(activity!!,"This device is not supported.", Toast.LENGTH_LONG).show()
              //  finish()
            }
            return false
        }
        return true
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val states = LocationSettingsStates.fromIntent(data!!)
        when (requestCode) {
            REQUEST_CHECK_SETTINGS -> when (resultCode) {
                Activity.RESULT_OK ->
                    // All required changes were successfully made
                    getLocation()
                Activity.RESULT_CANCELED -> {
                }
                else -> {
                }
            }// The user was asked to change settings, but chose not to
        }
    }


     override fun onResume() {
        super.onResume()
        checkPlayServices()
         this.getLocation()

    }
private fun getCurrentDate(){
    val c = Calendar.getInstance().time
System.out.println("Current time => " + c)

val df =  SimpleDateFormat("dd-MMM-yyyy")
val formattedDate = df.format(c)

    v.txt_date_curr.text = formattedDate
}
}