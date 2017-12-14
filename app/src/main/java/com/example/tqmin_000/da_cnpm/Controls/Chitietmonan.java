package com.example.tqmin_000.da_cnpm.Controls;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tqmin_000.da_cnpm.Model.ConnectionClass;
import com.example.tqmin_000.da_cnpm.Model.DocGhiFile;
import com.example.tqmin_000.da_cnpm.Model.SAO;
import com.example.tqmin_000.da_cnpm.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by tqmin_000 on 12/14/2017.
 */

public class Chitietmonan extends AppCompatActivity {
    TextView ten,gia,infor,danhgia;
    ImageView img;
    ArrayList<SAO> saos=new ArrayList<>();
    SAO sao1,sao2,sao3,sao4,sao5;
    int SL,RATE;
    int idmonan;
    Button btn;
    EditText sol;
    ConnectionClass connectionClass=new ConnectionClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietmonan);
        ten=(TextView) findViewById(R.id.textView13);
        gia=(TextView) findViewById(R.id.textView15);
        infor=(TextView) findViewById(R.id.textView16);
        img=(ImageView) findViewById(R.id.imageView3);
        danhgia=(TextView) findViewById(R.id.sldanhgia);
        btn=(Button) findViewById(R.id.button4);
        sol=(EditText) findViewById(R.id.editText2);

        sao1=new SAO((ImageButton) findViewById(R.id.imageView4));
        sao2=new SAO((ImageButton) findViewById(R.id.imageView5));
        sao3=new SAO((ImageButton) findViewById(R.id.imageView6));
        sao4=new SAO((ImageButton) findViewById(R.id.imageView7));
        sao5=new SAO((ImageButton) findViewById(R.id.imageView8));
        saos.add(sao1);
        saos.add(sao2);
        saos.add(sao3);
        saos.add(sao4);
        saos.add(sao5);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("package");
         idmonan=bundle.getInt("idmonan");
        ten.setText(bundle.getString("ten"));
        infor.setText(bundle.getString("infor"));
        String imgstring=bundle.getString("img");
        gia.setText(""+bundle.getFloat("gia")+"đ");
        if(imgstring!=null) {
            byte[] decodeString = Base64.decode(imgstring, Base64.DEFAULT);
            Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString,
                    0, decodeString.length);
            img.setImageBitmap(decodebitmap);
        }
        ////danhgia
        danhgia();
        for(int i=0;i<RATE;i++){
            saos.get(i).getButton().setImageResource(R.drawable.btn_star_big_on);
        }
        danhgia.setText("có "+SL+" lượt đánh giá");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocGhiFile docGhiFile=new DocGhiFile();
                String iduser=docGhiFile.docfile(Chitietmonan.this).toString();
                if(iduser.equals("-1")){
                    Toast.makeText(Chitietmonan.this,"Hãy Dăng Nhâp",Toast.LENGTH_LONG).show();
                }else {
                    Connection con=null;
                    con=connectionClass.CONN();
                    String query="exec ThemVaoGioHang '"+iduser+"',"+idmonan+","+sol.getText().toString().trim()+"";
                    try{
                        PreparedStatement stm=con.prepareStatement(query);
                        stm.executeUpdate();
                        Toast.makeText(Chitietmonan.this,"Thêm Vào Giỏ Hang Thanh Công",Toast.LENGTH_LONG).show();
                    }catch (SQLException ex){
                        Log.e("ERR",ex.getMessage());
                    }
                }

            }
        });
    }
    public void danhgia(){
        Connection con=null;
        con=connectionClass.CONN();
        String query="exec LayThongDanhGia "+idmonan+"";
        try{
            PreparedStatement stm=con.prepareStatement(query);
            ResultSet rs=stm.executeQuery();
            while (rs.next()){
                SL=rs.getInt(1);
                RATE=rs.getInt(2);
            }
        }catch (SQLException ex){

        }
    }
}
