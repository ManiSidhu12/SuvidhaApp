package com.model.login.purchase.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table1 {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("orderid")
@Expose
private Integer orderid;
@SerializedName("itemid")
@Expose
private Integer itemid;
@SerializedName("remarks")
@Expose
private String remarks;
@SerializedName("deliverydate")
@Expose
private String deliverydate;
@SerializedName("requiredqty")
@Expose
private Integer requiredqty;
@SerializedName("receivedqty")
@Expose
private Integer receivedqty;
@SerializedName("receivedon")
@Expose
private String receivedon;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getOrderid() {
return orderid;
}

public void setOrderid(Integer orderid) {
this.orderid = orderid;
}

public Integer getItemid() {
return itemid;
}

public void setItemid(Integer itemid) {
this.itemid = itemid;
}

public String getRemarks() {
return remarks;
}

public void setRemarks(String remarks) {
this.remarks = remarks;
}

public String getDeliverydate() {
return deliverydate;
}

public void setDeliverydate(String deliverydate) {
this.deliverydate = deliverydate;
}

public Integer getRequiredqty() {
return requiredqty;
}

public void setRequiredqty(Integer requiredqty) {
this.requiredqty = requiredqty;
}

public Integer getReceivedqty() {
return receivedqty;
}

public void setReceivedqty(Integer receivedqty) {
this.receivedqty = receivedqty;
}

public String getReceivedon() {
return receivedon;
}

public void setReceivedon(String receivedon) {
this.receivedon = receivedon;
}

}