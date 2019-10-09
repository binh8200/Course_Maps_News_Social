package com.example.asm_ps10220.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm_ps10220.Model.SinhVien;
import com.example.asm_ps10220.R;

import java.util.ArrayList;

public class SinhVienAdapter extends BaseAdapter {
    Context context;
    ArrayList<SinhVien> dulieu;

    public SinhVienAdapter(Context context, ArrayList<SinhVien> dulieu) {
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
            convertView=layoutInflater.inflate(R.layout.cell_student,null);
        }

        TextView tvstt =convertView.findViewById(R.id.tv_sttsv);
        TextView tvTenSV =convertView.findViewById(R.id.tv_tensv);
        TextView tvNgaySinh=convertView.findViewById(R.id.tv_ngaysinh);

        SinhVien sv =dulieu.get(position);
        tvstt.setText(position+1+"");
        tvTenSV.setText(sv.getTenSinhVien());
        tvNgaySinh.setText(sv.getNgaySinh());
        return convertView;
    }
}
