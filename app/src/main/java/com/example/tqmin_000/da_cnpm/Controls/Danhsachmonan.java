package com.example.tqmin_000.da_cnpm.Controls;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.tqmin_000.da_cnpm.Adapter.MonAnAD;
import com.example.tqmin_000.da_cnpm.Model.ConnectionClass;
import com.example.tqmin_000.da_cnpm.Model.FOOD;
import com.example.tqmin_000.da_cnpm.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Danhsachmonan extends AppCompatActivity {
    EditText key;
    ImageButton brnsearch;
    ListView listView;
    ConnectionClass connectionClass=new ConnectionClass();
    MonAnAD monAnAD;
    ArrayList<FOOD> foods=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachmonan);
        key=(EditText) findViewById(R.id.editText);
        brnsearch=(ImageButton) findViewById(R.id.imageButton);
        Connection con=null;
        con=connectionClass.CONN();
        String query="exec LayHetThongTinFood";
        try{
            PreparedStatement stm=con.prepareStatement(query);
            ResultSet rs=stm.executeQuery();
            while (rs.next()){
                FOOD food=new FOOD();
                food.setIdfood(rs.getInt(1));
                food.setName(rs.getString(2));
                food.setDongia(rs.getFloat(3));
                food.setImg(rs.getString(4));
                food.setInfor(rs.getString(5));
                food.setGiamgia(rs.getFloat(6));
                food.setGiamoi();
                foods.add(food);
            }
        }catch (SQLException ex){
            Log.e("ERR",ex.getMessage());
        }
        //
        listView=(ListView) findViewById(R.id.listmonan);
        monAnAD=new MonAnAD(Danhsachmonan.this,R.layout.dongnoibat,foods);
        listView.setAdapter(monAnAD);
        ///
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Danhsachmonan.this,Chitietmonan.class);
                Bundle bundle=new Bundle();
                bundle.putString("ten",foods.get(i).getName());
                bundle.putString("infor",foods.get(i).getInfor());
                bundle.putString("img",foods.get(i).getImg());
                bundle.putFloat("gia",foods.get(i).getGiamoi());
                bundle.putInt("idmonan",foods.get(i).getIdfood());
                intent.putExtra("package",bundle);
                startActivity(intent);
            }
        });
    }
}
