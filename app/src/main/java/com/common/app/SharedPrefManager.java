package com.common.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import com.suvidha.app.Login;

public class SharedPrefManager {
 
    //the constants
    private static final String SHARED_PREF_NAME = "login";
    private static final String FNAME = "fname";
    private static final String LNAME = "lname";
    private static final String MNAME = "mname";
    private static final String USERNAME = "uname";
    private static final String EMAIL = "email";
    private static final String Designation = "designation";
    private static final String PASSWORD = "password";
    private static final String USER_ID = "user_id";
    private static final String PHN_NO = "phone";
    private static final String USER_IMG = "img";
    private static final String DEVICE_ID = "id";
    private static final String LOCATION = "loc";
    private static final String DOB = "dob";
    private static final String ABOUTME = "about";
    private static final String GENDER = "gender";
    private static final String UNIT = "unit";
    private static final String FY = "fy";
    private static final String CO_ID = "coid";
    private static final String BO_ID = "boid";
    private static final String Login_Response = "response";
    private static SharedPrefManager mInstance;




    private static Context mCtx;
 
    private SharedPrefManager(Context context) {
        mCtx = context;
    }
 
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }
 
    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(String id, String fname,String mname,String lnm,String email,String des,String coid) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, id);
        editor.putString(FNAME, fname);
        editor.putString(MNAME,mname);
        editor.putString(LNAME, lnm);
        editor.putString(USERNAME, fname+" "+lnm);
        editor.putString(EMAIL, email);
        editor.putString(Designation, des);
        editor.putString(CO_ID, coid);
        editor.apply();
    }
public void setContactInfo(String phn, String skype, String contry, String city, String adrs, String adrs1, String zip){
    SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(PHN_NO, phn);

    editor.apply();
}
    public void setLoginResponse(String response){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Login_Response, response);

        editor.apply();
    }
    public String getLogin_Response(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(Login_Response, null);
    }

    public void setUserID(String id){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, id);
        editor.apply();
    }
    public void setUserName(String nm){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, nm);
        editor.apply();
    }
    public void setPassword(String ps){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PASSWORD, ps);
        editor.apply();
    }
    public void setUnit(String unti){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(UNIT, unti);
        editor.apply();
    }
    public void setFY(String fy){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FY, fy);
        editor.apply();
    }
    public void setBoId(String id){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BO_ID, id);
        editor.apply();
    }

    public void setGender(String gender){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(GENDER, gender);
        editor.apply();
    }
    public String getUnit(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(UNIT, null);
    }
    public String getFY(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(FY, null);
    }
    public String getCoId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(CO_ID, null);
    }
    public String getBoId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(BO_ID, null);
    }
    public String getGender() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(GENDER, null);


    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(USER_ID, null) != null){

            return true;
        }
        else{

            return false;
        }

    }

    public String getAboutMe() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ABOUTME, null);


    }
 
    //this method will give the logged in user
    public String getUserEmail() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(EMAIL, null);


    }

    public String getName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

       return sharedPreferences.getString(USERNAME, null);
    }
    public String getPhnNo(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(PHN_NO, null);
    }
    public String getUserId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(USER_ID, null);
    }
    public String getPassword(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(PASSWORD, null);
    }
    public String getLocation(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(LOCATION, null);
    }
    public String getDob(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(DOB, null);
    }


    public String getDeviceToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
if(sharedPreferences.getString(DEVICE_ID,null) != null){
    return sharedPreferences.getString(DEVICE_ID,null);
}
return null;
    }
    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, Login.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        ((Activity)mCtx).finish();
    }

    public void setDeviceToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DEVICE_ID, token);
        editor.apply();
    }

}