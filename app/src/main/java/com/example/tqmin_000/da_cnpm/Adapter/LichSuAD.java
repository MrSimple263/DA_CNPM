package com.example.tqmin_000.da_cnpm.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tqmin_000.da_cnpm.Model.LichSuclass;
import com.example.tqmin_000.da_cnpm.R;

import java.util.List;

public class LichSuAD extends BaseAdapter {

    Context context;
    int layout;
    List<LichSuclass> list;

    public LichSuAD(Context context, int layout, List<LichSuclass> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        //Anh xa
        TextView txtten = (TextView) view.findViewById(R.id.textView40);
        TextView sl = (TextView) view.findViewById(R.id.textView41);
        TextView ngay = (TextView) view.findViewById(R.id.textView42);
        ImageView imageView=(ImageView) view.findViewById(R.id.imageView3);
        ///
        txtten.setText(list.get(i).getTen());
        sl.setText(""+list.get(i).getSl());
        ngay.setText(list.get(i).getNgay());
        if(list.get(i).getAnh()!=null) {
            byte[] decodeString = Base64.decode(list.get(i).getAnh(), Base64.DEFAULT);
            Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString,
                    0, decodeString.length);
            imageView.setImageBitmap(decodebitmap);
        }

        return view;
    }
}
