package com.androidaura.hw3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomList extends BaseAdapter {

    private final Context context;
    private ArrayList<Student> dataSet;


    public CustomList(ArrayList<Student> data , Context context) {
        this.context = context;
        dataSet = data;

    }

    static class ViewHolder {

        TextView fname, lname;
        LinearLayout ll_customlist;

    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View view, final ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if (view == null) {
            view = inflater.inflate(R.layout.layout_customlist, null);
            holder = new ViewHolder();
            holder.fname = (TextView) view.findViewById(R.id.fname);
            holder.lname = (TextView) view.findViewById(R.id.lname);
            holder.ll_customlist = (LinearLayout) view.findViewById(R.id.ll_customlist);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

            Log.d("vt","fname "+dataSet.get(position).getFname());
          holder.fname.setText(dataSet.get(position).getFname());
          holder.lname.setText(dataSet.get(position).getLname());

        holder.ll_customlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), StudentDetails.class);
                i.putExtra("cwid",dataSet.get(position).getCWID());
                i.putExtra("fname",dataSet.get(position).getFname());
                i.putExtra("lname",dataSet.get(position).getLname());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });

        return view;

    }
}