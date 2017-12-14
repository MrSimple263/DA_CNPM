package com.example.tqmin_000.da_cnpm.Model;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class DocGhiFile {
    int id=-1;
    public DocGhiFile(int id) {
        this.id = id;
    }
    public  DocGhiFile(){

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public StringBuffer docfile(Context context){
        StringBuffer text = new StringBuffer();
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(context.openFileInput("user.txt")));
            String line;

            while ((line = bReader.readLine()) != null) {
                text.append(line);
            }
        } catch (IOException e) {
            text.append("-1");
            e.printStackTrace();
        }
        return text;
    }
    public void ghifile(Context context){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("user.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(""+id+"\n");
            outputStreamWriter.close();
            Log.e("Thanh cong", "Ghi file thanh cong");
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
