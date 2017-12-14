package com.example.tqmin_000.da_cnpm.Controls;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tqmin_000.da_cnpm.R;

/**
 * Created by tqmin_000 on 12/14/2017.
 */

public class News extends AppCompatActivity {
    TextView tile,noidung;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tintuc);
        tile=(TextView) findViewById(R.id.textView17);
        noidung=(TextView) findViewById(R.id.textView28);
        img=(ImageView) findViewById(R.id.imageView9);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("package");
        tile.setText(bundle.getString("tile"));
        noidung.setText(bundle.getString("infor"));
        String imgstring =bundle.getString("img");
        if(imgstring!=null) {
            byte[] decodeString = Base64.decode(imgstring, Base64.DEFAULT);
            Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString,
                    0, decodeString.length);
            img.setImageBitmap(decodebitmap);
        }
    }
}
