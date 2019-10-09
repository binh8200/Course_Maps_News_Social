package com.example.asm_ps10220.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.asm_ps10220.Adapter.LopAdapter;
import com.example.asm_ps10220.DAO.LopDAO;
import com.example.asm_ps10220.Model.Lop;
import com.example.asm_ps10220.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentAddClass extends Fragment {

    FloatingActionButton fabAddClass;
    LopDAO lopDAO;
    LopAdapter lopAdapter;
    ArrayList<Lop> list_lop;
    ListView lvClass;
    Button btnXoa;
    TextView tvXoa;


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addclass, container, false);
        //-----Ánh xạ-----
        lvClass = view.findViewById(R.id.lvClass);
        btnXoa = view.findViewById(R.id.btn_xoa);
        tvXoa = view.findViewById(R.id.tv_xoa);

        list_lop = new ArrayList<>();
        lopDAO = new LopDAO(getContext());
        list_lop = lopDAO.DSLop();
        lopAdapter = new LopAdapter(getContext(),list_lop);
        lvClass.setAdapter(lopAdapter);

        lvClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lop lop = list_lop.get(position);
                tvXoa.setText(lop.getMaLop());
            }
        });

        //-----xoa-----
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lop lop = new Lop();
                lop.setMaLop(tvXoa.getText().toString());
                if (lopDAO.XoaLop(lop) == 1){
                    list_lop = lopDAO.DSLop();
                    lopAdapter = new LopAdapter(getContext(),list_lop);
                    lvClass.setAdapter(lopAdapter);
                    Toast.makeText(getContext(),"Xóa Thành Công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Xóa Không Thanh Công", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //-----add-----
        fabAddClass = view.findViewById(R.id.FabAddClass);
        fabAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lopDAO = new LopDAO(getContext());
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                //-----gán layout-----
                LayoutInflater inflater = getActivity().getLayoutInflater();
                v = inflater.inflate(R.layout.add_class, null);
                alertDialog.setView(v);
                //-----ánh xạ-----
                final EditText edMaLop=(EditText)v.findViewById(R.id.edt_nhapmalop);
                final EditText edTenLop = (EditText) v.findViewById(R.id.edt_nhaptenlop);
                //-----thêm-----
                alertDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            boolean error = true;
                            if (edTenLop.getText().toString().equals("") ||edMaLop.getText().toString()==null){
                                Toast.makeText(getContext(), "Phải nhập đủ thông tin !",Toast.LENGTH_SHORT).show();
                                error = false;
                            }
                            if(error==true){
                                Lop lop = new Lop();
                                lop.setMaLop(edMaLop.getText().toString());
                                lop.setTenLop(edTenLop.getText().toString());

                                if (lopDAO.ThemLop(lop) == -1 ) {
                                    Toast.makeText(getContext(), "Thêm Không Thành Công",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            Toast.makeText(getContext(),"Thêm Thành Công",Toast.LENGTH_SHORT).show();
                            list_lop = lopDAO.DSLop();
                            lopAdapter = new LopAdapter(getActivity(),list_lop);
                            lvClass.setAdapter(lopAdapter);

                        } catch (NullPointerException npe) {
                            Toast.makeText(getContext(), "Lỗi không xát định !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //-----cancel-----
                alertDialog.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });

        return view;
    }
}
