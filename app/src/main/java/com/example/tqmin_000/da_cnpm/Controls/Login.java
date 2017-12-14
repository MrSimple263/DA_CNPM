package com.example.tqmin_000.da_cnpm.Controls;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tqmin_000.da_cnpm.Model.ConnectionClass;
import com.example.tqmin_000.da_cnpm.Model.DocGhiFile;
import com.example.tqmin_000.da_cnpm.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Login extends AppCompatActivity {
    TextView username,pass;
    boolean isuser=false;
    ConnectionClass connectionClass=new ConnectionClass();
    Button dangnhap,dangky;
    Connection con=null;
    int iduser=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username=(TextView) findViewById(R.id.login_username);
        pass=(TextView) findViewById(R.id.login_pass);
        isuser=Isuser(username.getText().toString().trim(),pass.getText().toString().trim());
        connectionClass= new ConnectionClass();
        dangnhap=(Button) findViewById(R.id.button);
        dangky=(Button) findViewById(R.id.button2);

        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isuser=Isuser(username.getText().toString().trim(),pass.getText().toString().trim());
                if(isuser==true){
                    DocGhiFile docGhiFile=new DocGhiFile(iduser);
                   docGhiFile.ghifile(Login.this);
                   Toast.makeText(Login.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                   finish();
                }else {
                    Toast.makeText(Login.this,"Ten tai khoan hoac mat khau khong dung",Toast.LENGTH_SHORT).show();
                }
            }
        });
       dangky.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(Login.this,Dangki.class);
               startActivity(intent);
           }
       });

    }
    public boolean Isuser(String username,String pass){
        con=connectionClass.CONN();
        try{
            String query="select dbo.checklogin('"+username+"','"+pass+"')";
            PreparedStatement stm=con.prepareStatement(query);
            ResultSet rs=stm.executeQuery();
            while (rs.next()){
                iduser=rs.getInt(1);
            }
        }
        catch (SQLException ex){

        }
        if(iduser !=-1){
            return true;
        }else {
            return  false;
        }
    }

}
