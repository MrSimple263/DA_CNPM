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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tqmin_000.da_cnpm.Model.ConnectionClass;
import com.example.tqmin_000.da_cnpm.R;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Chinhsuagiohang extends AppCompatActivity{
    TextView tetua,sl,dongia;
    ImageView img;
    Button btnsua,btnxoa;
    int IDUSER;
    int IDSP;
    ConnectionClass connectionClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchinhsuagiohang);
        tetua=(TextView) findViewById(R.id.csgh_name);
        sl=(TextView) findViewById(R.id.csgh_sl);
        dongia=(TextView) findViewById(R.id.csgh_dongia);
        img=(ImageView) findViewById(R.id.csgh_img);
        btnsua=(Button) findViewById(R.id.csgh_btnsua);
        btnxoa=(Button) findViewById(R.id.csgh_btnxoa);
        Intent Prvintent=getIntent();
        connectionClass=new ConnectionClass();
        Bundle Prvpac=Prvintent.getBundleExtra("package");
        tetua.setText(Prvpac.getString("TENTUA"));
        sl.setText(""+Prvpac.getInt("SL"));
        dongia.setText(""+Prvpac.getFloat("DG"));
        if(Prvpac.getString("IMG")!=null) {
            byte[] decodeString = Base64.decode(Prvpac.getString("IMG"), Base64.DEFAULT);
            Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString,
                    0, decodeString.length);
            img.setImageBitmap(decodebitmap);
        }
        IDUSER=Prvpac.getInt("IDUSER");
        IDSP=Prvpac.getInt("IDSP");
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SuaGioHang();
            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XoaGioHang();
            }
        });

    }
    public void SuaGioHang(){
        Connection con=null;
        try{
            con=connectionClass.CONN();
            String query="exec UpdGioHang "+IDUSER+","+IDSP+","+Integer.parseInt(sl.getText().toString().trim())+"";
            PreparedStatement stm=con.prepareStatement(query);
            int i=stm.executeUpdate();
            Toast.makeText(Chinhsuagiohang.this,"Đa sữa đổi",Toast.LENGTH_SHORT).show();
            con.close();
        }catch (Exception ex){
            Log.e("Err",ex.toString());
        }
    }
    public  void XoaGioHang(){
        Connection con=null;
        try{
            con=connectionClass.CONN();
            String query="exec XoaGioHang "+IDUSER+","+IDSP+"";
            PreparedStatement stm=con.prepareStatement(query);
            int i=stm.executeUpdate();
            Toast.makeText(Chinhsuagiohang.this,"Đã xóa khỏi giỏ hang",Toast.LENGTH_SHORT).show();
            con.close();
        }catch (Exception ex){
            Log.e("Err",ex.toString());
        }
    }

}
