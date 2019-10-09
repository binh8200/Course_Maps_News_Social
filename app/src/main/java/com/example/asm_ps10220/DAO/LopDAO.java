package com.example.asm_ps10220.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_ps10220.Database.Database;
import com.example.asm_ps10220.Model.Lop;

import java.util.ArrayList;
import java.util.List;

public class LopDAO {
    SQLiteDatabase db;

    public LopDAO(Context context){
        Database dbHelper = new Database(context);
        db=dbHelper.getWritableDatabase();
    }

    public ArrayList<Lop> DSLop(){
        ArrayList<Lop> xemdanhsach=new ArrayList<>();
        Cursor c=db.query("LOPHOC",null,null,null,null,null,null);
        while (c.moveToNext())
        {
            Lop lop =new Lop();
            lop.setMaLop(c.getString(c.getColumnIndex("MALOP")));
            lop.setTenLop(c.getString(c.getColumnIndex("TENLOP")));

            xemdanhsach.add(lop);
        }
        return xemdanhsach;
    }

    public long ThemLop(Lop lh)
    {
        ContentValues values = new ContentValues();
        values.put("MALOP",lh.getMaLop());
        values.put("TENLOP",lh.getTenLop());

        return db.insert("LOPHOC", null,values);

    }

    public  int XoaLop(Lop lh){
        return  db.delete("LOPHOC","MALOP=?",new String[]{lh.getMaLop()});
    }

    public List<Lop> getMaLop(){
        List<Lop> dsLop = new ArrayList<>();
        String sql2 = "SELECT MALOP, TENLOP FROM LOPHOC";
        Cursor c = db.rawQuery(sql2, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Lop malophoc = new Lop();
            malophoc.setMaLop(c.getString(c.getColumnIndex("MALOP")));
            malophoc.setTenLop(c.getString(c.getColumnIndex("TENLOP")));
            dsLop.add(malophoc);
            c.moveToNext();
        }
        c.close();
        return dsLop;
    }
}
