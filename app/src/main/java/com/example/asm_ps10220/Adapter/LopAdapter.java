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

public class LopAdapter extends BaseAdapter {
    Context context;
    ArrayList<Lop> dulieu;

    public LopAdapter(Context context, ArrayList<Lop> dulieu) {
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
            convertView=layoutInflater.inflate(R.layout.cell_class,null);
        }

        TextView tvSTT =convertView.findViewById(R.id.tv_stt);
        TextView tvMaLop =convertView.findViewById(R.id.tv_malop);
        TextView tvTenLop=convertView.findViewById(R.id.tv_tenlop);

        Lop w=dulieu.get(position);
        tvSTT.setText(position+1+"");
        tvMaLop.setText(w.getMaLop());
        tvTenLop.setText(w.getTenLop());
        return convertView;
    }
}
