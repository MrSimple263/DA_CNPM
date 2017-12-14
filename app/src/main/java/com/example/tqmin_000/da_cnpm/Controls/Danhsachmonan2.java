package com.example.tqmin_000.da_cnpm.Controls;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tqmin_000.da_cnpm.Adapter.MonAnAD;
import com.example.tqmin_000.da_cnpm.Model.ConnectionClass;
import com.example.tqmin_000.da_cnpm.Model.FOOD;
import com.example.tqmin_000.da_cnpm.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Danhsachmonan2 extends AppCompatActivity {
    EditText key;
    ListView listView;
    TextView ten;
    ConnectionClass connectionClass=new ConnectionClass();
    MonAnAD monAnAD;
    ArrayList<FOOD> foods=new ArrayList<>();
    ArrayList<FOOD> foods2=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachmonantheonhom);
        key=(EditText) findViewById(R.id.editText3);
        listView=(ListView) findViewById(R.id.list_monan2);
        ten=(TextView) findViewById(R.id.textView35);
        Intent intent=getIntent();
        Bundle bundle=new Bundle();
        bundle=intent.getBundleExtra("package");
        int id=bundle.getInt("idmenu");
        ten.setText(bundle.getString("namemenu"));
        Connection con=null;
        con=connectionClass.CONN();
        String query="exec LayHetThongTinFoodTheoidmenu "+id+"";
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
        monAnAD=new MonAnAD(Danhsachmonan2.this,R.layout.dongnoibat,foods);
        listView.setAdapter(monAnAD);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Danhsachmonan2.this,Chitietmonan.class);
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
        ///
        key.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                foods2.clear();
                if(key.getText().toString().trim()==""){
                    foods2=foods;
                    monAnAD=new MonAnAD(Danhsachmonan2.this,R.layout.dongnoibat,foods2);
                    listView.setAdapter(monAnAD);
                }else {
                    for (FOOD food:foods){
                        if(food.getName().toLowerCase().contains(key.getText().toString().toLowerCase()))
                            foods2.add(food);
                    }
                    monAnAD=new MonAnAD(Danhsachmonan2.this,R.layout.dongnoibat,foods2);
                    listView.setAdapter(monAnAD);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}
