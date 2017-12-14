package com.example.tqmin_000.da_cnpm.Controls;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tqmin_000.da_cnpm.Adapter.MonAnAD;
import com.example.tqmin_000.da_cnpm.Model.ConnectionClass;
import com.example.tqmin_000.da_cnpm.Model.DocGhiFile;
import com.example.tqmin_000.da_cnpm.Model.FOOD;
import com.example.tqmin_000.da_cnpm.Model.NEWS;
import com.example.tqmin_000.da_cnpm.Model.USER;
import com.example.tqmin_000.da_cnpm.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        ArrayList<FOOD> foods=new ArrayList<>();
        ConnectionClass connectionClass=new ConnectionClass();
        ListView listView;
        MonAnAD monAnAD;
        ImageView tintuc;
        USER user=new USER();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //load MON ĂN
        listView=(ListView) findViewById(R.id.list_noibat);
        Loadthucan();
        monAnAD=new MonAnAD(MainActivity.this,R.layout.dongnoibat,foods);
        listView.setAdapter(monAnAD);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this,Chitietmonan.class);
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
        //Load Tin Tuc
        tintuc =(ImageView) findViewById(R.id.img_tintuc_tt);
        final NEWS news=new NEWS();
        Loadtintuc(news);
        if(news.getImg()!=null) {
            byte[] decodeString = Base64.decode(news.getImg(), Base64.DEFAULT);
            Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString,
                    0, decodeString.length);
            tintuc.setImageBitmap(decodebitmap);
        }
        tintuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,News.class);
                Bundle bundle=new Bundle();
                bundle.putString("tile",news.getTile());
                bundle.putString("infor",news.getNoidung());
                bundle.putString("img",news.getImg());
                intent.putExtra("package",bundle);
                startActivity(intent);
            }
        });
        /// Kiem tra có Dang nhap trươc chua
        DocGhiFile docGhiFile=new DocGhiFile();
        String iduser=docGhiFile.docfile(MainActivity.this).toString();
//        Log.e("ERR",iduser);
            Connection con=null;
            con=connectionClass.CONN();
            String query="select * from USERS where IDUSER='"+iduser+"'";
            if(!iduser.equals(-1)) {
                try {
                    PreparedStatement stm = con.prepareStatement(query);
                    ResultSet rs = stm.executeQuery();
                    while (rs.next()) {
                        user.setIduser(rs.getInt(1));
                        user.setUsername(rs.getString(2));
                        user.setPass(rs.getString(3));
                        user.setFullname(rs.getString(4));
                        user.setEmail(rs.getString(5));
                        user.setDiachi(rs.getString(6));
                        user.setSdt(rs.getString(7));
                        user.setImg(rs.getString(8));
                        user.setRole(rs.getString(9));
                        user.setTinhtrang(rs.getString(10));
                        user.setSex(rs.getString(11));
                        Log.e("ERR",user.getFullname());
                    }
                } catch (SQLException ex) {
                    Log.e("ERR", ex.getMessage());
                }
            }
        ///gan cac gia tri personal
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        TextView Ten=(TextView)header.findViewById(R.id.tenper);
        ImageView imgdaidien=(ImageView) header.findViewById(R.id.img_personnal);
        Ten.setText(user.getFullname());
        if(user.getImg()!=null) {
            byte[] decodeString = Base64.decode(user.getImg(), Base64.DEFAULT);
            Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString,
                    0, decodeString.length);
            imgdaidien.setImageBitmap(decodebitmap);
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cart) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_monan:{
                Intent intent =new Intent(MainActivity.this,Danhsachmonan.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_danhmuc:{
                break;
            }
            case R.id.nav_lichsu:{
                break;
            }
            case R.id.nav_login:{
                Intent intent =new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                break;
            }
            case  R.id.nav_logout:{
                DocGhiFile docGhiFile=new DocGhiFile();
                docGhiFile.ghifile(MainActivity.this);
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);
                View header=navigationView.getHeaderView(0);
                TextView Ten=(TextView)header.findViewById(R.id.tenper);
                ImageView imgdaidien=(ImageView) header.findViewById(R.id.img_personnal);
                Ten.setText(null);
                imgdaidien.setImageResource(R.mipmap.ic_launcher);
                break;
            }
            case R.id.nav_about:{
                break;
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void Loadthucan(){
        Connection con=null;
        con=connectionClass.CONN();
        String query="exec LayThongTinfood";
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
                food.setGiamgia(rs.getFloat(7));
                food.setGiamoi();
                foods.add(food);
            }
        }catch (SQLException ex){
            Log.e("ERR",ex.getMessage());
        }
    }
    public void Loadtintuc(NEWS news){
        Connection con=null;
        con=connectionClass.CONN();
        String query="exec LayThongTinTinTuc";
        try{
            PreparedStatement stm=con.prepareStatement(query);
            ResultSet rs=stm.executeQuery();
            while (rs.next()){
                news.setIdnews(rs.getInt(1));
                news.setTile(rs.getString(2));
                news.setNoidung(rs.getString(3));
                news.setImg(rs.getString(4));
            }
        }catch (SQLException ex){
            Log.e("ERR",ex.getMessage());
        }
    }
}
