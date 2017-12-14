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

import com.example.tqmin_000.da_cnpm.Model.FOOD;
import com.example.tqmin_000.da_cnpm.R;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by tqmin_000 on 12/13/2017.
 */

public class MonAnAD extends BaseAdapter {
    Context context;
    int layout;
    List<FOOD> list;

    public MonAnAD(Context context, int layout, List<FOOD> list) {
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
        TextView ten=(TextView) view.findViewById(R.id.tenmonan_dong);
        TextView giacu=(TextView) view.findViewById(R.id.giacu_dong);
        TextView giamoi=(TextView) view.findViewById(R.id.giamoi_dong);
        ImageView img=(ImageView) view.findViewById(R.id.img_dong);
        ten.setText(list.get(i).getName());
        giacu.setText(""+list.get(i).getDongia());
        giamoi.setText(""+list.get(i).getGiamoi());
        if(list.get(i).getImg()!=null) {
            byte[] decodeString = Base64.decode(list.get(i).getImg(), Base64.DEFAULT);
            Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString,
                    0, decodeString.length);
            img.setImageBitmap(decodebitmap);
        }
        return view;
    }
}
