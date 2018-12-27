package com.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table2 {

@SerializedName("fyid")
@Expose
private Integer fyid;
@SerializedName("fyname")
@Expose
private String fyname;
@SerializedName("fystartdate")
@Expose
private String fystartdate;
@SerializedName("fyenddate")
@Expose
private String fyenddate;

public Integer getFyid() {
return fyid;
}

public void setFyid(Integer fyid) {
this.fyid = fyid;
}

public String getFyname() {
return fyname;
}

public void setFyname(String fyname) {
this.fyname = fyname;
}

public String getFystartdate() {
return fystartdate;
}

public void setFystartdate(String fystartdate) {
this.fystartdate = fystartdate;
}

public String getFyenddate() {
return fyenddate;
}

public void setFyenddate(String fyenddate) {
this.fyenddate = fyenddate;
}

}