package com.example.tqmin_000.da_cnpm.Controls;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tqmin_000.da_cnpm.R;

import java.io.FileOutputStream;


public class Login extends AppCompatActivity {
    TextView username,pass;
    boolean isuser=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username=(TextView) findViewById(R.id.login_username);
        pass=(TextView) findViewById(R.id.login_pass);
        isuser=Isuser(username.getText().toString().trim(),pass.getText().toString().trim());
        if(isuser==true){
            finish();
            Ghivaofiletxt(username.getText().toString().trim(),pass.getText().toString().trim());
        }

    }
    public void Ghivaofiletxt(String username,String pass){
        String FILE_NAME="user.txt";
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(username.trim().getBytes());
            fos.write(pass.trim().getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public boolean Isuser(String username,String pass){
        return true;
    }
}
