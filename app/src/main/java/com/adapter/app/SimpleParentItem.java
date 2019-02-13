package com.adapter.app;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

public class SimpleParentItem implements ParentListItem {

    private String itemName;
    private String itemId;
    private String orderId;
    private String itemCode;
    private String itemQuantity;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemRate() {
        return itemRate;
    }

    public void setItemRate(String itemRate) {
        this.itemRate = itemRate;
    }

    private String itemRate;
    private boolean mSolved;
    private List<SimpleChild> mChildItemList;

    public SimpleParentItem() {
    }




    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    @Override
    public List<SimpleChild> getChildItemList() {
        return mChildItemList;
    }

    public void setChildItemList(List<SimpleChild> list) {
        mChildItemList = list;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }


}
