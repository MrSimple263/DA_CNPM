package com.example.tqmin_000.da_cnpm.Model;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.example.tqmin_000.da_cnpm.R;

public class ConnectionClass extends AppCompatActivity{
    static String ip;
    static String classs = "net.sourceforge.jtds.jdbc.Driver";
    static String db = "DOAN-SACH";
    static String un;
    static String password;
    EditText ip1,name,pass;
    Button btnkn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ketnoi);
        ip1=(EditText) findViewById(R.id.kn_ip);
        name=(EditText) findViewById(R.id.kn_name);
        pass=(EditText) findViewById(R.id.kn_pass);
        btnkn=(Button) findViewById(R.id.kn_btn);
        btnkn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ip = ip1.getText().toString().trim();
                un =name.getText().toString().trim();
                password =pass.getText().toString().trim();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Connection conn = null;
                String ConnURL = null;
                try {

                    Class.forName(classs);
                    ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                            + "databaseName=" + db + ";user=" + un + ";password="
                            + password + ";";
                    conn = DriverManager.getConnection(ConnURL);
//                    Intent intent=new Intent(ConnectionClass.this,.class);
//                    startActivity(intent);
                } catch (SQLException se) {
                    Toast.makeText(ConnectionClass.this,se.getMessage(),Toast.LENGTH_SHORT).show();
                } catch (ClassNotFoundException e) {
                    Toast.makeText(ConnectionClass.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(ConnectionClass.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {

            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://192.168.1.4;"
                    + "databaseName=CNPM2;user=minh;password=123;";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }

        return conn;
    }
}
