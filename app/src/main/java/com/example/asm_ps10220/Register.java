package com.example.asm_ps10220;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_ps10220.Database.DBLogin;

public class Register extends AppCompatActivity {

    EditText e1, e2, e3;
    Button b1,b2;
    DBLogin db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhxa();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                if (s1.equals("")||s2.equals("")||s3.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty", Toast.LENGTH_SHORT).show();
                }else {
                    if (s2.equals(s3)){
                        boolean chkemail = db.chkemail(s1);
                        if (chkemail==true){
                            Boolean insert = db.insert(s1,s2);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"Đăng ký thành công !", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"User đã tồn tại !",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"Password không khớp !",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public void anhxa(){
        db = new DBLogin(this);
        e1 = findViewById(R.id.etUser);
        e2 = findViewById(R.id.etPassword);
        e3 = findViewById(R.id.etRePassword);
        b1 = findViewById(R.id.btnRegister);
        b2 = findViewById(R.id.btn2);
    }
}
