package com.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

@SerializedName("coid")
@Expose
private Integer coid;
@SerializedName("boid")
@Expose
private Integer boid;
@SerializedName("userid")
@Expose
private Integer userid;
@SerializedName("firstname")
@Expose
private String firstname;
@SerializedName("middlename")
@Expose
private String middlename;
@SerializedName("lastname")
@Expose
private String lastname;
@SerializedName("aliasname")
@Expose
private String aliasname;
@SerializedName("designation")
@Expose
private String designation;
@SerializedName("emailid")
@Expose
private String emailid;
@SerializedName("useraccesstoken")
@Expose
private String useraccesstoken;
@SerializedName("lastlogindate")
@Expose
private String lastlogindate;

public Integer getCoid() {
return coid;
}

public void setCoid(Integer coid) {
this.coid = coid;
}

public Integer getBoid() {
return boid;
}

public void setBoid(Integer boid) {
this.boid = boid;
}

public Integer getUserid() {
return userid;
}

public void setUserid(Integer userid) {
this.userid = userid;
}

public String getFirstname() {
return firstname;
}

public void setFirstname(String firstname) {
this.firstname = firstname;
}

public String getMiddlename() {
return middlename;
}

public void setMiddlename(String middlename) {
this.middlename = middlename;
}

public String getLastname() {
return lastname;
}

public void setLastname(String lastname) {
this.lastname = lastname;
}

public String getAliasname() {
return aliasname;
}

public void setAliasname(String aliasname) {
this.aliasname = aliasname;
}

public String getDesignation() {
return designation;
}

public void setDesignation(String designation) {
this.designation = designation;
}

public String getEmailid() {
return emailid;
}

public void setEmailid(String emailid) {
this.emailid = emailid;
}

public String getUseraccesstoken() {
return useraccesstoken;
}

public void setUseraccesstoken(String useraccesstoken) {
this.useraccesstoken = useraccesstoken;
}

public String getLastlogindate() {
return lastlogindate;
}

public void setLastlogindate(String lastlogindate) {
this.lastlogindate = lastlogindate;
}

}