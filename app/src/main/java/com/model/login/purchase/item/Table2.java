package com.model.login.purchase.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table2 {

@SerializedName("itemid")
@Expose
private Integer itemid;
@SerializedName("name")
@Expose
private String name;
@SerializedName("suplitemrate")
@Expose
private Integer suplitemrate;
@SerializedName("lastpurchaserate")
@Expose
private Object lastpurchaserate;

public Integer getItemid() {
return itemid;
}

public void setItemid(Integer itemid) {
this.itemid = itemid;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Integer getSuplitemrate() {
return suplitemrate;
}

public void setSuplitemrate(Integer suplitemrate) {
this.suplitemrate = suplitemrate;
}

public Object getLastpurchaserate() {
return lastpurchaserate;
}

public void setLastpurchaserate(Object lastpurchaserate) {
this.lastpurchaserate = lastpurchaserate;
}

}