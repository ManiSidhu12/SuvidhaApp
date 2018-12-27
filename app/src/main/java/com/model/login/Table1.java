package com.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table1 {

@SerializedName("coid")
@Expose
private Integer coid;
@SerializedName("boid")
@Expose
private Integer boid;
@SerializedName("unitname")
@Expose
private String unitname;
@SerializedName("unitaddress")
@Expose
private String unitaddress;
@SerializedName("city")
@Expose
private String city;
@SerializedName("state")
@Expose
private String state;
@SerializedName("country")
@Expose
private String country;

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

public String getUnitname() {
return unitname;
}

public void setUnitname(String unitname) {
this.unitname = unitname;
}

public String getUnitaddress() {
return unitaddress;
}

public void setUnitaddress(String unitaddress) {
this.unitaddress = unitaddress;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getState() {
return state;
}

public void setState(String state) {
this.state = state;
}

public String getCountry() {
return country;
}

public void setCountry(String country) {
this.country = country;
}

}