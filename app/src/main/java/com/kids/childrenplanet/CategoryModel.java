package com.kids.childrenplanet;

public class CategoryModel {

    private String catimage;
    private String catname;

    public CategoryModel() {
    }

    public CategoryModel(String catimage, String catname) {
        this.catimage = catimage;
        this.catname = catname;
    }

    public String getCatimage() {
        return catimage;
    }

    public void setCatimage(String catimage) {
        this.catimage = catimage;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }
}
