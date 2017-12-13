package com.example.tqmin_000.da_cnpm.Model;

import android.widget.ImageButton;

/**
 * Created by tqmin_000 on 12/13/2017.
 */

public class SAO {
    int trangthai=0;
    ImageButton button;
    public SAO(ImageButton button){
        this.button=button;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public ImageButton getButton() {
        return button;
    }

    public void setButton(ImageButton button) {
        this.button = button;
    }
}
