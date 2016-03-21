package com.silion.itheima.createxml;

/**
 * Created by silion on 2016/3/21.
 */
public class Sms {
    private String address;
    private String body;
    private long date;
    private int type;

    public Sms(String address, String body, long date, int type) {
        this.address = address;
        this.body = body;
        this.date = date;
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
