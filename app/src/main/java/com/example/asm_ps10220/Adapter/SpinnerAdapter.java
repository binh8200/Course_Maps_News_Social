package com.example.asm_ps10220.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm_ps10220.Model.Lop;
import com.example.asm_ps10220.R;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<Lop> dulieu;
    public SpinnerAdapter(Context context, ArrayList<Lop> dulieu){
        this.context = context;
        this.dulieu = dulieu;
    }

    @Override
    public int getCount() {
        return dulieu.size();
    }

    @Override
    public Object getItem(int position) {
        return dulieu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.cell_spinner,null);
        }


        TextView tvspinner =convertView.findViewById(R.id.tv_spinner);



        Lop lop =dulieu.get(position);
        tvspinner.setText(lop.getMaLop() );


        return convertView;
    }
}
