package com.example.tqmin_000.da_cnpm.Controls;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tqmin_000.da_cnpm.Model.ConnectionClass;
import com.example.tqmin_000.da_cnpm.Model.DocGhiFile;
import com.example.tqmin_000.da_cnpm.Model.USER;
import com.example.tqmin_000.da_cnpm.R;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Chinhsuathongtin extends AppCompatActivity {
    TextView username,pass,fullname,add,phone,email,infor;
    ImageView imageView;
    RadioGroup group;
    Button dangky;
    String sex="";
    byte[] byteArray;
    String encodedImage=null;
    RadioButton radioButton1;
    RadioButton radioButton2;
    ConnectionClass connectionClass=new ConnectionClass();
    USER user=new USER();
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chinhsuathongtin);
        username=(TextView) findViewById(R.id.editText6);
        pass=(TextView) findViewById(R.id.editText8);
        fullname=(TextView) findViewById(R.id.editText7);
        add=(TextView) findViewById(R.id.editText10);
        phone=(TextView) findViewById(R.id.editText11);
        email=(TextView) findViewById(R.id.editText12);
        group=(RadioGroup) findViewById(R.id.sex);
         radioButton1=(RadioButton) findViewById(R.id.radioButton2);
        radioButton2=(RadioButton) findViewById(R.id.radioButton);
        dangky=(Button) findViewById(R.id.button8);
        imageView=(ImageView) findViewById(R.id.imageView2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonanh();
            }
        });
        loadThongTin();
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!id.equals(-1)){
                Connection con=null;
                con=connectionClass.CONN();
                int idchecked=group.getCheckedRadioButtonId();
                if(idchecked==R.id.radioButton)
                    sex="0";
                else sex="1";

                String query="exec user_update '"+id+"','"+username.getText().toString().trim()+"'," +
                        "'"+pass.getText().toString().trim()+"'" +
                        ",N'"+fullname.getText().toString().trim()+"'," +
                        "'"+email.getText().toString().trim()+"'" +
                        ",N'"+add.getText().toString().trim()+"'," +
                        "'"+phone.getText().toString().trim()+"'," +
                        "'"+encodedImage+"','0','1','"+sex+"'";
                try{
                    PreparedStatement stm=con.prepareStatement(query);
                    stm.executeUpdate();
                    Toast.makeText(Chinhsuathongtin.this,"Đăng Kí Thành Công",Toast.LENGTH_SHORT).show();
                }catch (SQLException ex){
                    Log.e("ERR",ex.getMessage());
                }
            }
            }
        });
    }
    public void loadThongTin(){
        DocGhiFile docGhiFile=new DocGhiFile();
        String iduser=docGhiFile.docfile(Chinhsuathongtin.this).toString();
        id=iduser;
        if(!iduser.equals(-1)){
            Connection con = null;
            con = connectionClass.CONN();
            String query = "select * from USERS where IDUSER='" + iduser + "'";
            if (!iduser.equals(-1)) {
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
                        Log.e("ERR", user.getFullname());
                    }
                    fullname.setText(user.getFullname());
                    username.setText(user.getUsername());
                    pass.setText(user.getPass());
                    add.setText(user.getDiachi());
                    phone.setText(user.getSdt());
                    email.setText(user.getEmail());
                    if(user.getSex().equals(1)){
                        radioButton2.setChecked(true);
                    }else radioButton1.setChecked(true);
                    if(user.getImg()!=null) {
                        byte[] decodeString = Base64.decode(user.getImg(), Base64.DEFAULT);
                        Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString,
                                0, decodeString.length);
                        imageView.setImageBitmap(decodebitmap);
                    }
                } catch (SQLException ex) {
                    Log.e("ERR", ex.getMessage());
                }
        }
    }
    }
    public void chonanh(){
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)
                && !Environment.getExternalStorageState().equals(
                Environment.MEDIA_CHECKING)) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1);

        } else {
            Toast.makeText(Chinhsuathongtin.this,"No activity found to perform this task",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap originBitmap = null;
            Uri selectedImage = data.getData();
            InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(
                        selectedImage);
                originBitmap = BitmapFactory.decodeStream(imageStream);
            } catch (FileNotFoundException e) {
            }
            if (originBitmap != null) {
                this.imageView.setImageBitmap(originBitmap);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                originBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();

                encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
            }
        } else {
            Toast.makeText(Chinhsuathongtin.this,"There's an error if this code doesn't work" +
                    ", thats all I know",Toast.LENGTH_SHORT).show();

        }
    }
}
