package com.example.tqmin_000.da_cnpm.Controls;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tqmin_000.da_cnpm.Adapter.GioHangAD;
import com.example.tqmin_000.da_cnpm.Model.ConnectionClass;
import com.example.tqmin_000.da_cnpm.R;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
public class GioHang extends AppCompatActivity {
    ConnectionClass connectionClass;
    int IDUSER ;
    ArrayList<GioHang> giohang;
    ListView listgiohang;
    GioHangAD adgiohang;
    TextView tongtien;
    float Tong;
    Button btnmua;
    String notifi="Mua Hang Thanh Cong";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang);
        connectionClass=new ConnectionClass();
        giohang=new ArrayList<>();
        listgiohang=(ListView) findViewById(R.id.listgiohang);
        btnmua=(Button) findViewById(R.id.gh_btnmua);
        //Lay IDUSER
        Intent Prvintent=getIntent();
        Bundle Prvpacket=Prvintent.getBundleExtra("package");
        IDUSER=Prvpacket.getInt("IDuser");
        ///
        tongtien=(TextView) findViewById(R.id.gh_tt);
//        LayDuLieu();
//        adgiohang=new CatalogAdgiohang(GioHang.this,giohang,R.layout.dong_muahang);
//        listgiohang.setAdapter(adgiohang);
//        listgiohang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent chinhsuagiohang=new Intent(GioHang.this,Chinhsuagiohang.class);
//                Bundle bundle=new Bundle();
//                bundle.putInt("IDUSER",IDUSER);
//                bundle.putInt("IDSP",giohang.get(i).getIDSP());
//                bundle.putString("TENTUA",giohang.get(i).getTenSP());
//                bundle.putString("IMG",giohang.get(i).getImage());
//                bundle.putFloat("DG",giohang.get(i).getDongia());
//                bundle.putInt("SL",giohang.get(i).getSL());
//                chinhsuagiohang.putExtra("package",bundle);
//                startActivity(chinhsuagiohang);
//            }
//        });
        //
//        TongTien();
//        btnmua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TaoHoaDon();
//                for(Cataloggiohang sach:giohang){
//                    TaoChiTietHoaDon(sach.getIDSP(),sach.getDongia(),sach.getSL());
//                }
//                Toast.makeText(GioHang.this,notifi,Toast.LENGTH_SHORT).show();
//                XoaGioHang();
//            }
//        });

    }
    public void TaoChiTietHoaDon(int IDTUA,float dongia,int sl){
        Connection con=null;
        try{
            con=connectionClass.CONN();
            String query="ThemThonTinCTHD "+IDTUA+","+dongia+","+sl+"";
            PreparedStatement stm=con.prepareStatement(query);
            int i=stm.executeUpdate();
            con.close();
        }catch (Exception ex){
            Log.e("ERR",ex.toString());
        }
    }
    public void TaoHoaDon(){
        Connection con=null;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = df.format(c.getTime());
        try{
            con=connectionClass.CONN();
            String query="exec ThemThongTinHD "+IDUSER+",'"+formattedDate+"',"+Tong+"";
            PreparedStatement stm=con.prepareStatement(query);
            stm.executeUpdate();
            con.close();
        }catch (SQLException ex){
            notifi=ex.getMessage();
        }
    }
    public void TongTien(){
    Connection con=null;
    try{
        con=connectionClass.CONN();
        String query="select dbo.TinhTongTien("+IDUSER+")";
        PreparedStatement stm=con.prepareStatement(query);
        ResultSet rs=stm.executeQuery();
        while (rs.next()){
            Tong=rs.getFloat(1);
            tongtien.setText(""+Tong+"  đồng");
        }
        con.close();
    }catch (Exception ex){
        Log.e("ERR",ex.toString());
    }
}
//    public void LayDuLieu(){
//        Connection con=null;
//        try{
//            con=connectionClass.CONN();
//            String query="exec LayThongTinGioHang "+IDUSER+"";
//            PreparedStatement stm=con.prepareStatement(query);
//            ResultSet rs=stm.executeQuery();
//            while (rs.next()){
//                Cataloggiohang sach=new Cataloggiohang();
//                sach.setTenSP(rs.getString(1));
//                sach.setDongia(rs.getFloat(2));
//                sach.setSL(rs.getInt(3));
//                sach.setImage(rs.getString(4));
//                sach.setIDSP(rs.getInt(5));
//                giohang.add(sach);
//            }
//            con.close();
//        }catch (Exception ex){
//            Log.e("ERR",ex.toString());
//        }
//    }
    public void XoaGioHang(){
        Connection con=null;
        try{
            con=connectionClass.CONN();
            String query="exec XoaHetGioHang "+IDUSER+"";
            PreparedStatement stm=con.prepareStatement(query);
            int i=stm.executeUpdate();
            giohang.clear();
            adgiohang.notifyDataSetChanged();
            listgiohang.setAdapter(adgiohang);
            con.close();
        }catch (Exception ex){
            Log.e("ERR",ex.toString());
        }
    }
}
