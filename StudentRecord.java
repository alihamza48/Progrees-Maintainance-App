package com.example.hifzstudents;

public class StudentRecord {
    private String id;
    private String name;
    private String date;
    private String sabaq;
    private String sabaqi;
    private String manzil;


    public StudentRecord(String id, String name,String date, String sabaq, String sabaqi, String manzil) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.sabaq = sabaq;
        this.sabaqi = sabaqi;
        this.manzil = manzil;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSabaq() {
        return sabaq;
    }

    public void setSabaq(String sabaq) {
        this.sabaq = sabaq;
    }

    public String getSabaqi() {
        return sabaqi;
    }

    public void setSabaqi(String sabaqi) {
        this.sabaqi = sabaqi;
    }

    public String getManzil() {
        return manzil;
    }

    public void setManzil(String manzil) {
        this.manzil = manzil;
    }
}
