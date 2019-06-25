package com.example.sqliteproject;

public class Contacts {
    private  String mphone;
    private  String mname;
    private  int mid;

    public Contacts( String mphone, String mname) {
        this.mphone = mphone;
        this.mname = mname;
    }

    public Contacts(int mid,String mphone, String mname) {
        this.mphone = mphone;
        this.mname = mname;
        this.mid = mid;
    }

    public String getMphone() {
        return mphone;
    }

    public String getMname() {
        return mname;
    }

    public int getMid() {
        return mid;
    }
}
