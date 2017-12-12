package com.example.tqmin_000.da_cnpm.Controls;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tqmin_000.da_cnpm.R;

public class Dangki extends AppCompatActivity {
    TextView username,pass,fullname,birthday,add,phone,email,infor;
    ImageView imageView;
    RadioGroup group=(RadioGroup) findViewById(R.id.sex);
    String sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userins);
        username=(TextView) findViewById(R.id.editText6);
        pass=(TextView) findViewById(R.id.editText8);
        fullname=(TextView) findViewById(R.id.editText7);
        birthday=(TextView) findViewById(R.id.editText9);
        add=(TextView) findViewById(R.id.editText10);
        phone=(TextView) findViewById(R.id.editText11);
        email=(TextView) findViewById(R.id.editText12);
        infor=(TextView) findViewById(R.id.editText13);
        int idchecked=group.getCheckedRadioButtonId();
        switch (idchecked){
            case R.id.radioButton2://nam
            {
                sex="1";
                break;
            }
            case R.id.radioButton://nu
            {
                sex="0";
                break;
            }
        }
        imageView=(ImageView) findViewById(R.id.imageView2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    public void chonanh(){

    }
}
