package com.adapter.app;

public class SimpleChild {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    private String cat_id;

    public SimpleChild(String titl, String id) {
        title = titl;
        cat_id = id;
    }


}
