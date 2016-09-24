package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shuaxin.R;

import java.util.ArrayList;

/**
 * Created by 争志 on 2016/9/21.
 */
public class ListAdapter extends BaseAdapter {
    protected Context context;
    ArrayList<String> arrayList = new ArrayList<>();

    public ListAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }

    public void setList(ArrayList<String> list) {
        this.arrayList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arrayList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder _viewHolder = null;
        if (convertView == null) {
            _viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_view, null);
            _viewHolder.textview = (TextView) convertView.findViewById(R.id.textview);
            convertView.setTag(_viewHolder);
        } else {
            _viewHolder = (viewHolder) convertView.getTag();
        }
        String s = arrayList.get(position);
        if (!s.equals("")) {
            _viewHolder.textview.setText(s);
        }
        return convertView;
    }

    class viewHolder {
        private TextView textview;
    }
}
