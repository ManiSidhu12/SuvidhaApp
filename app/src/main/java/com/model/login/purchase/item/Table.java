package com.model.login.purchase.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

@SerializedName("orderid")
@Expose
private Integer orderid;
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("itemcode")
@Expose
private String itemcode;
@SerializedName("itemname")
@Expose
private String itemname;
@SerializedName("orderedquantity")
@Expose
private Integer orderedquantity;
@SerializedName("itemrate")
@Expose
private Integer itemrate;

public Integer getOrderid() {
return orderid;
}

public void setOrderid(Integer orderid) {
this.orderid = orderid;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getItemcode() {
return itemcode;
}

public void setItemcode(String itemcode) {
this.itemcode = itemcode;
}

public String getItemname() {
return itemname;
}

public void setItemname(String itemname) {
this.itemname = itemname;
}

public Integer getOrderedquantity() {
return orderedquantity;
}

public void setOrderedquantity(Integer orderedquantity) {
this.orderedquantity = orderedquantity;
}

public Integer getItemrate() {
return itemrate;
}

public void setItemrate(Integer itemrate) {
this.itemrate = itemrate;
}

}