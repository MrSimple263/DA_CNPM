package com.example.tqmin_000.da_cnpm.Controls;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.tqmin_000.da_cnpm.Model.ConnectionClass;
import com.example.tqmin_000.da_cnpm.Model.DocGhiFile;
import com.example.tqmin_000.da_cnpm.R;
import com.example.tqmin_000.da_cnpm.Model.LichSuclass;
import com.example.tqmin_000.da_cnpm.Adapter.LichSuAD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Lichsumuahang extends AppCompatActivity{
    ListView listView;
    ConnectionClass connectionClass;
    ArrayList<LichSuclass> arrayList;
    LichSuAD lichSuAD;
    int IDUSER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lichsumuahang);
        listView=(ListView) findViewById(R.id.ls_list);
        DocGhiFile docGhiFile=new DocGhiFile();
        IDUSER=Integer.parseInt(docGhiFile.docfile(Lichsumuahang.this).toString());
        connectionClass=new ConnectionClass();
        arrayList=new ArrayList<>();
        Connection con=null;
        try{
            con=connectionClass.CONN();
            String query="select * from LichSuMuaHang("+IDUSER+")";
            PreparedStatement stm=con.prepareStatement(query);
            ResultSet rs=stm.executeQuery();
            while (rs.next()){
                LichSuclass sp=new LichSuclass();
                sp.setTen(rs.getString(1));
                sp.setSl(rs.getInt(2));
                sp.setAnh(rs.getString(3));
                sp.setNgay(""+rs.getDate(4));
                arrayList.add(sp);
            }
        }catch (SQLException ex){

        }
        lichSuAD=new LichSuAD(Lichsumuahang.this,R.layout.dong_lichsu,arrayList);
        listView.setAdapter(lichSuAD);
    }
}
