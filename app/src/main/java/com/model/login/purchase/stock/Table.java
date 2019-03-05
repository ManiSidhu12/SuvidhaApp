package com.model.login.purchase.stock;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

@SerializedName("itemid")
@Expose
private Integer itemid;
@SerializedName("itemcode")
@Expose
private String itemcode;
@SerializedName("itemname")
@Expose
private String itemname;
@SerializedName("oldcode")
@Expose
private String oldcode;
@SerializedName("itemcategory")
@Expose
private String itemcategory;
@SerializedName("itemcategoryid")
@Expose
private Integer itemcategoryid;
@SerializedName("uom")
@Expose
private String uom;
@SerializedName("unitid")
@Expose
private Integer unitid;
@SerializedName("openingstock")
@Expose
private Float openingstock;
@SerializedName("stockinhand")
@Expose
private Float stockinhand;
@SerializedName("receivedqty")
@Expose
private Float receivedqty;
@SerializedName("issueqty")
@Expose
private Float issueqty;
@SerializedName("underiqc")
@Expose
private Float underiqc;
@SerializedName("reservedstock")
@Expose
private Float reservedstock;
@SerializedName("minlevel")
@Expose
private Float minlevel;
@SerializedName("maxlevel")
@Expose
private Float maxlevel;
@SerializedName("reorderlevel")
@Expose
private Float reorderlevel;
@SerializedName("eoqlevel")
@Expose
private Float eoqlevel;
@SerializedName("totalindentqty")
@Expose
private Float totalindentqty;
@SerializedName("pendingpoqty")
@Expose
private Float pendingpoqty;
@SerializedName("rejectedbyiqc")
@Expose
private Float rejectedbyiqc;
@SerializedName("location")
@Expose
private String location;
@SerializedName("wipqty")
@Expose
private Float wipqty;
@SerializedName("igst")
@Expose
private Float igst;
@SerializedName("sgst")
@Expose
private Float sgst;
@SerializedName("cgst")
@Expose
private Float cgst;
@SerializedName("gstid")
@Expose
private Integer gstid;
@SerializedName("hsncode")
@Expose
private String hsncode;

public Integer getItemid() {
return itemid;
}

public void setItemid(Integer itemid) {
this.itemid = itemid;
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

public String getOldcode() {
return oldcode;
}

public void setOldcode(String oldcode) {
this.oldcode = oldcode;
}

public String getItemcategory() {
return itemcategory;
}

public void setItemcategory(String itemcategory) {
this.itemcategory = itemcategory;
}

public Integer getItemcategoryid() {
return itemcategoryid;
}

public void setItemcategoryid(Integer itemcategoryid) {
this.itemcategoryid = itemcategoryid;
}

public String getUom() {
return uom;
}

public void setUom(String uom) {
this.uom = uom;
}

public Integer getUnitid() {
return unitid;
}

public void setUnitid(Integer unitid) {
this.unitid = unitid;
}

public Float getOpeningstock() {
return openingstock;
}

public void setOpeningstock(Float openingstock) {
this.openingstock = openingstock;
}

public Float getStockinhand() {
return stockinhand;
}

public void setStockinhand(Float stockinhand) {
this.stockinhand = stockinhand;
}

public Float getReceivedqty() {
return receivedqty;
}

public void setReceivedqty(Float receivedqty) {
this.receivedqty = receivedqty;
}

public Float getIssueqty() {
return issueqty;
}

public void setIssueqty(Float issueqty) {
this.issueqty = issueqty;
}

public Float getUnderiqc() {
return underiqc;
}

public void setUnderiqc(Float underiqc) {
this.underiqc = underiqc;
}

public Float getReservedstock() {
return reservedstock;
}

public void setReservedstock(Float reservedstock) {
this.reservedstock = reservedstock;
}

public Float getMinlevel() {
return minlevel;
}

public void setMinlevel(Float minlevel) {
this.minlevel = minlevel;
}

public Float getMaxlevel() {
return maxlevel;
}

public void setMaxlevel(Float maxlevel) {
this.maxlevel = maxlevel;
}

public Float getReorderlevel() {
return reorderlevel;
}

public void setReorderlevel(Float reorderlevel) {
this.reorderlevel = reorderlevel;
}

public Float getEoqlevel() {
return eoqlevel;
}

public void setEoqlevel(Float eoqlevel) {
this.eoqlevel = eoqlevel;
}

public Float getTotalindentqty() {
return totalindentqty;
}

public void setTotalindentqty(Float totalindentqty) {
this.totalindentqty = totalindentqty;
}

public Float getPendingpoqty() {
return pendingpoqty;
}

public void setPendingpoqty(Float pendingpoqty) {
this.pendingpoqty = pendingpoqty;
}

public Float getRejectedbyiqc() {
return rejectedbyiqc;
}

public void setRejectedbyiqc(Float rejectedbyiqc) {
this.rejectedbyiqc = rejectedbyiqc;
}

public String getLocation() {
return location;
}

public void setLocation(String location) {
this.location = location;
}

public Float getWipqty() {
return wipqty;
}

public void setWipqty(Float wipqty) {
this.wipqty = wipqty;
}

public Float getIgst() {
return igst;
}

public void setIgst(Float igst) {
this.igst = igst;
}

public Float getSgst() {
return sgst;
}

public void setSgst(Float sgst) {
this.sgst = sgst;
}

public Float getCgst() {
return cgst;
}

public void setCgst(Float cgst) {
this.cgst = cgst;
}

public Integer getGstid() {
return gstid;
}

public void setGstid(Integer gstid) {
this.gstid = gstid;
}

public String getHsncode() {
return hsncode;
}

public void setHsncode(String hsncode) {
this.hsncode = hsncode;
}

}