package com.example.tqmin_000.da_cnpm.Controls;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tqmin_000.da_cnpm.Adapter.DanhMucAD;
import com.example.tqmin_000.da_cnpm.Model.ConnectionClass;
import com.example.tqmin_000.da_cnpm.Model.FOOD;
import com.example.tqmin_000.da_cnpm.Model.MENU;
import com.example.tqmin_000.da_cnpm.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by tqmin_000 on 12/14/2017.
 */

public class Danhsachmenu extends AppCompatActivity {
    ListView listView;
    ConnectionClass connectionClass=new ConnectionClass();
    ArrayList<MENU> menus=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachmenu);
        listView = (ListView) findViewById(R.id.list_danhmuc);
        Connection con=null;
        con=connectionClass.CONN();
        String query="select  MENU.IDMENU,NAME,SL " +
                "from (MENU inner join (select Count(IDMENU) as SL,IDMENU\n" +
                "from FOOD Group by IDMENU) as MENU1 on MENU1.IDMENU=MENU.IDMENU) ";
        try{
            PreparedStatement stm=con.prepareStatement(query);
            ResultSet rs=stm.executeQuery();
            while (rs.next()){
                MENU menu=new MENU();
                menu.setIdmenu(rs.getInt(1));
                menu.setName(rs.getString(2));
                menu.setSL(rs.getInt(3));
                menus.add(menu);
            }
        }catch (SQLException ex){
            Log.e("ERR",ex.getMessage());
        }
        DanhMucAD danhMucAD=new DanhMucAD(Danhsachmenu.this,R.layout.dong_danhmuc,menus);
        listView.setAdapter(danhMucAD);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Danhsachmenu.this,Danhsachmonan2.class);
                Bundle bundle=new Bundle();
                bundle.putInt("idmenu",menus.get(i).getIdmenu());
                bundle.putString("namemenu",menus.get(i).getName());
                intent.putExtra("package",bundle);
                startActivity(intent);
            }
        });
    }
}
