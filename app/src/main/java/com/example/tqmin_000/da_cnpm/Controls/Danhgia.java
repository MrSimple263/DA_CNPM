package com.example.tqmin_000.da_cnpm.Controls;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tqmin_000.da_cnpm.Model.SAO;
import com.example.tqmin_000.da_cnpm.R;
import java.util.ArrayList;
public class Danhgia extends AppCompatActivity {
    TextView ten,dongia;
    SAO sao1,sao2,sao3,sao4,sao5;
    Button danhgia;
    ImageView img;
    ArrayList<SAO> listsao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhgia);
        listsao=new ArrayList<>();
        ten=(TextView) findViewById(R.id.danhgia_ten);
        dongia=(TextView) findViewById(R.id.danhgia_gia);
        sao1=new SAO((ImageButton) findViewById(R.id.danhgia_sao1));
        sao2=new SAO((ImageButton) findViewById(R.id.danhgia_sao2));
        sao3=new SAO((ImageButton) findViewById(R.id.danhgia_sao3));
        sao4=new SAO((ImageButton) findViewById(R.id.danhgia_sao4));
        sao5=new SAO((ImageButton) findViewById(R.id.danhgia_sao5));
        img=(ImageView) findViewById(R.id.danhgia_img);
        listsao.add(sao1);
        listsao.add(sao2);
        listsao.add(sao3);
        listsao.add(sao4);
        listsao.add(sao5);
        for(SAO sao:listsao){
            sao.getButton().setImageResource(R.drawable.btn_star_big_off);
        }
        danhgia=(Button) findViewById(R.id.danhgia_btn);
        sao1.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sao1.getTrangthai()==0){
                    sao1.getButton().setImageResource(R.drawable.btn_star_big_on);
                    sao1.setTrangthai(1);
                }else{
                    for(int i=0;i<5;i++){
                        listsao.get(i).getButton().setImageResource(R.drawable.btn_star_big_off);
                        listsao.get(i).setTrangthai(0);
                    }
                }

            }
        });
        sao2.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sao2.getTrangthai()==0){
                    for(int i=0;i<2;i++){
                        listsao.get(i).getButton().setImageResource(R.drawable.btn_star_big_on);
                        listsao.get(i).setTrangthai(1);
                    }
                }else {
                    for(int i=1;i<5;i++) {
                        listsao.get(i).getButton().setImageResource(R.drawable.btn_star_big_off);
                        listsao.get(i).setTrangthai(0);
                    }
                }
            }
        });
        sao3.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sao3.getTrangthai()==0){
                    for(int i=0;i<3;i++){
                        listsao.get(i).getButton().setImageResource(R.drawable.btn_star_big_on);
                        listsao.get(i).setTrangthai(1);
                    }
                }else {
                    for(int i=2;i<5;i++) {
                        listsao.get(i).getButton().setImageResource(R.drawable.btn_star_big_off);
                        listsao.get(i).setTrangthai(0);
                    }
                }
            }
        });
        sao4.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sao4.getTrangthai()==0){
                    for(int i=0;i<4;i++){
                        listsao.get(i).getButton().setImageResource(R.drawable.btn_star_big_on);
                        listsao.get(i).setTrangthai(1);
                    }
                }else {
                    for(int i=3;i<5;i++) {
                        listsao.get(i).getButton().setImageResource(R.drawable.btn_star_big_off);
                        listsao.get(i).setTrangthai(0);
                    }
                }
            }
        });
        sao5.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sao5.getTrangthai()==0){
                    for(int i=0;i<5;i++){
                        listsao.get(i).getButton().setImageResource(R.drawable.btn_star_big_on);
                        listsao.get(i).setTrangthai(1);
                    }
                }else {
                        sao5.getButton().setImageResource(R.drawable.btn_star_big_off);
                        sao5.setTrangthai(0);
                    }
                }
        });
    }
}
