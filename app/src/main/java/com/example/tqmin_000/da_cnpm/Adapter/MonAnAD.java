package com.example.tqmin_000.da_cnpm.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tqmin_000.da_cnpm.Model.FOOD;

import java.util.List;

/**
 * Created by tqmin_000 on 12/13/2017.
 */

public class MonAnAD extends BaseAdapter {
    Context context;
    int layout;
    List<FOOD> list;
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
        return null;
    }
}
