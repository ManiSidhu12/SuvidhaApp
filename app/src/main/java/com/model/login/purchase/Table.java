package com.model.login.purchase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("orderdate")
@Expose
private String orderdate;
@SerializedName("orderno")
@Expose
private Integer orderno;
@SerializedName("name")
@Expose
private String name;
@SerializedName("email")
@Expose
private String email;
@SerializedName("typeoforder")
@Expose
private String typeoforder;
@SerializedName("status")
@Expose
private String status = "0";

    public String getChkStatus() {
        return chkStatus;
    }

    public void setChkStatus(String chkStatus) {
        this.chkStatus = chkStatus;
    }

    private String chkStatus;
@SerializedName("sectionid")
@Expose
private Integer sectionid;
@SerializedName("section")
@Expose
private String section;
@SerializedName("deptname")
@Expose
private String deptname;
@SerializedName("sentforApproval")
@Expose
private Object sentforApproval;
@SerializedName("ARH")
@Expose
private String aRH;
@SerializedName("confirmation")
@Expose
private String confirmation;
@SerializedName("Supplierorvendor")
@Expose
private Object supplierorvendor;
@SerializedName("deptid")
@Expose
private Integer deptid;
@SerializedName("mailsentdate")
@Expose
private Object mailsentdate;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getOrderdate() {
return orderdate;
}

public void setOrderdate(String orderdate) {
this.orderdate = orderdate;
}

public Integer getOrderno() {
return orderno;
}

public void setOrderno(Integer orderno) {
this.orderno = orderno;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getTypeoforder() {
return typeoforder;
}

public void setTypeoforder(String typeoforder) {
this.typeoforder = typeoforder;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public Integer getSectionid() {
return sectionid;
}

public void setSectionid(Integer sectionid) {
this.sectionid = sectionid;
}

public String getSection() {
return section;
}

public void setSection(String section) {
this.section = section;
}

public String getDeptname() {
return deptname;
}

public void setDeptname(String deptname) {
this.deptname = deptname;
}

public Object getSentforApproval() {
return sentforApproval;
}

public void setSentforApproval(Object sentforApproval) {
this.sentforApproval = sentforApproval;
}

public String getARH() {
return aRH;
}

public void setARH(String aRH) {
this.aRH = aRH;
}

public String getConfirmation() {
return confirmation;
}

public void setConfirmation(String confirmation) {
this.confirmation = confirmation;
}

public Object getSupplierorvendor() {
return supplierorvendor;
}

public void setSupplierorvendor(Object supplierorvendor) {
this.supplierorvendor = supplierorvendor;
}

public Integer getDeptid() {
return deptid;
}

public void setDeptid(Integer deptid) {
this.deptid = deptid;
}

public Object getMailsentdate() {
return mailsentdate;
}

public void setMailsentdate(Object mailsentdate) {
this.mailsentdate = mailsentdate;
}

}