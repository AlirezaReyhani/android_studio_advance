package com.example.recycler;

public class MyListData {
    String title;
    int imageid;



    public MyListData(String title,int imageid){
        this.title=title;
        this.imageid=imageid;
    }

    public int getImageid() {
        return imageid;
    }

    public String getTitle() {
        return title;
    }
}
