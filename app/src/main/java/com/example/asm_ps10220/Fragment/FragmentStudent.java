package com.example.asm_ps10220.Fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.asm_ps10220.Adapter.SinhVienAdapter;
import com.example.asm_ps10220.DAO.LopDAO;
import com.example.asm_ps10220.DAO.SinhVienDAO;
import com.example.asm_ps10220.Model.Lop;
import com.example.asm_ps10220.Model.SinhVien;
import com.example.asm_ps10220.R;

import java.util.ArrayList;
import java.util.Calendar;

public class FragmentStudent extends Fragment {
    EditText edTensinhvien;
    TextView tvNgaysinh;
    Spinner spinner;
    Button btnThemsv, btnXoasv, btnNew;
    SinhVienDAO svDAO;
    LopDAO lopDAO;
    ArrayList<SinhVien> list = new ArrayList<>();
    ArrayList<Lop> listlop = new ArrayList<>();
    ListView lvSinhVien;
    SpinnerAdapter spinnerAdapter;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    static final String TAG="QLSV";
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, container, false);
        //-----anhxa-----
        edTensinhvien = view.findViewById(R.id.edt_tensinhvien);
        tvNgaysinh = view.findViewById(R.id.tv_ngaysinh);
        spinner = view.findViewById(R.id.spinner);
        btnThemsv = view.findViewById(R.id.btn_themsv);
        btnXoasv = view.findViewById(R.id.btn_xoasv);
        lvSinhVien = view.findViewById(R.id.lv_sinhvien);
        btnNew = view.findViewById(R.id.btn_new);

        tvNgaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog= new DatePickerDialog(
                        getContext(),android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                Log.d(TAG,"onDateSet:mm/dd/yyyy: "+dayOfMonth+"/"+month+"/"+year);
                String date = "   "+dayOfMonth +"/"+month+"/"+year;
                tvNgaysinh.setText(date);
            }
        } ;

        list = new ArrayList<>();
        svDAO = new SinhVienDAO(getContext());
        lopDAO = new LopDAO(getContext());

        //-----lấy mã lớp cho spinner
        listlop = (ArrayList<Lop>) lopDAO.getMaLop();
        spinnerAdapter = new com.example.asm_ps10220.Adapter.SpinnerAdapter(getContext(),listlop);
        spinner.setAdapter(spinnerAdapter);

        //-----click listview-----
        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SinhVien sinhVien = list.get(position);
                edTensinhvien.setText(sinhVien.getTenSinhVien());
                tvNgaysinh.setText(sinhVien.getNgaySinh());
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String maLop = listlop.get(position).getMaLop();
                Toast.makeText(getContext(), maLop, Toast.LENGTH_SHORT).show();
                list= svDAO.xemQLSV(maLop);
                SinhVienAdapter adaptersv = new SinhVienAdapter(getContext(), list);
                lvSinhVien.setAdapter(adaptersv);

                btnThemsv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean error = true;
                        if (edTensinhvien.getText().toString().equals("") || edTensinhvien.getText().toString()== null){
                            Toast.makeText(getContext(), "Phải nhập đủ thông tin !",Toast.LENGTH_SHORT).show();
                            error = false;
                        }else if(tvNgaysinh.getText().toString().equals("") || tvNgaysinh.getText().toString()==null){
                            Toast.makeText(getContext(), "Phải nhập đủ thông tin !",Toast.LENGTH_SHORT).show();
                            error = false;
                        }
                        if(error==true){
                            SinhVien sv = new SinhVien();
                            sv.setTenSinhVien(edTensinhvien.getText().toString());
                            sv.setNgaySinh(tvNgaysinh.getText().toString());
                            sv.setMaLop(maLop);

                            if (svDAO.ThemSinhVien(sv)==-1){
                                Toast.makeText(getContext(),"Thêm không thành công",Toast.LENGTH_SHORT).show();
                                return;
                            }else {
                                Toast.makeText(getContext(),"Thêm thành công.",Toast.LENGTH_SHORT).show();}
                        }

                        list= svDAO.xemQLSV(maLop);
                        SinhVienAdapter adaptersv = new SinhVienAdapter(getContext(), list);
                        lvSinhVien.setAdapter(adaptersv);
                    }
                });

                btnXoasv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SinhVien sv = new SinhVien();
                        sv.setTenSinhVien(edTensinhvien.getText().toString());
                        sv.setNgaySinh(tvNgaysinh.getText().toString());
                        if (svDAO.XoaSinhVien(sv) ==-1){
                            Toast.makeText(getContext(),"Xóa không thành công.",Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            Toast.makeText(getContext(),"Xóa thành công.",Toast.LENGTH_SHORT).show();}
                        list= svDAO.xemQLSV(maLop);
                        SinhVienAdapter adaptersv = new SinhVienAdapter(getContext(), list);
                        lvSinhVien.setAdapter(adaptersv);
                    }
                });

                btnNew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edTensinhvien.setText("");
                        tvNgaysinh.setText("");
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }
}
