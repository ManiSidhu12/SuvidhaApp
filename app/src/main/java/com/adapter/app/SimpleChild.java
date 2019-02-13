package com.adapter.app;

public class SimpleChild {



    private String id;
    private String orderid;
    private String itemid;
    private String remarks;
    private String deliverydate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
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

    public String getRequiredqty() {
        return requiredqty;
    }

    public void setRequiredqty(String requiredqty) {
        this.requiredqty = requiredqty;
    }

    public String getReceivedqty() {
        return receivedqty;
    }

    public void setReceivedqty(String receivedqty) {
        this.receivedqty = receivedqty;
    }

    public String getReceivedon() {
        return receivedon;
    }

    public void setReceivedon(String receivedon) {
        this.receivedon = receivedon;
    }

    private String requiredqty;
    private String receivedqty;
    private String receivedon;

    public SimpleChild(String id, String order_id,String item_id,String remark,String del_date,String req_qty,String rec_qty,String rec_on) {
        this.id = id;
        orderid = order_id;
        itemid = item_id;
        remarks = remark;
        deliverydate = del_date;
        requiredqty = req_qty;
        receivedqty = rec_qty;
        receivedon = rec_on;
    }


}
