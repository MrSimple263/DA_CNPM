package com.example.tqmin_000.da_cnpm.Model;

/**
 * Created by tqmin_000 on 12/9/2017.
 */

public class FOOD {
    int idfood;
    String name;
    int idmenu;
    float dongia;
    String img;
    String infor;
    float giamgia=0;
    float giamoi;

    public FOOD() {
    }

    public int getIdfood() {
        return idfood;
    }

    public void setIdfood(int idfood) {
        this.idfood = idfood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    public float getDongia() {
        return dongia;
    }

    public void setDongia(float dongia) {
        this.dongia = dongia;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public float getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(float giamgia) {
        this.giamgia = giamgia;
    }

    public float getGiamoi() {
        return giamoi;
    }

    public void setGiamoi() {
        this.giamoi =dongia*(1-giamgia/100);
    }
}
