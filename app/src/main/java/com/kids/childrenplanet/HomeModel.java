package com.kids.childrenplanet;

public class HomeModel {

    private  int catimage;
    private  String catname;

    public HomeModel(int catimage, String catname) {
        this.catimage = catimage;
        this.catname = catname;
    }

    public int getCatimage() {
        return catimage;
    }

    public void setCatimage(int catimage) {
        this.catimage = catimage;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }
}
