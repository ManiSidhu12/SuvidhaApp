package com.model.login;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

public class SimpleParentItem implements ParentListItem {

    private String mTitle;
    private String mContent;
    private String id;
    private boolean mSolved;
    private List<SimpleChild> mChildItemList;

    public SimpleParentItem() {
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
