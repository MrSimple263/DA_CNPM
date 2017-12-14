package com.example.tqmin_000.da_cnpm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tqmin_000.da_cnpm.Model.MENU;
import com.example.tqmin_000.da_cnpm.R;

import java.util.List;

/**
 * Created by tqmin_000 on 12/13/2017.
 */

public class DanhMucAD extends BaseAdapter {
    Context context;
    int layout;
    List<MENU> list;

    public DanhMucAD(Context context, int layout, List<MENU> list) {
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
        TextView ten=(TextView) view.findViewById(R.id.textView32);
        TextView SL=(TextView) view.findViewById(R.id.textView34);
        ten.setText(list.get(i).getName());
        ten.setText(list.get(i).getName());
        SL.setText(""+list.get(i).getSL()+" món");
        return view;
    }
}
