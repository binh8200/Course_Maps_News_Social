package com.example.asm_ps10220;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_ps10220.Database.DBLogin;

public class Login extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView tvRegister;
    CheckBox chkluuthongtin;
    DBLogin db;
    SharedPreferences luutru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        luutru = getSharedPreferences("myfile", Context.MODE_PRIVATE);
        //-----lưu user and password-----
        Boolean luuthongtin = luutru.getBoolean("save_information", false);
        if (luuthongtin) {
            edtUsername.setText(luutru.getString("username", ""));
            edtPassword.setText(luutru.getString("password", ""));
            chkluuthongtin.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usename = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                Boolean chkemailpass = db.emailandpassword(usename, password);
                if (chkemailpass == true) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = luutru.edit();
                    if (chkluuthongtin.isChecked()) {
                        editor.putString("username", usename);
                        editor.putString("password", password);
                    }
                    editor.putBoolean("save_information", chkluuthongtin.isChecked());
                    editor.commit();
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Sai user hoặc password !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void anhxa() {
        db = new DBLogin(this);
        edtUsername = (EditText) findViewById(R.id.edituser);
        edtPassword = (EditText) findViewById(R.id.editpass);
        tvRegister = (TextView) findViewById(R.id.register);
        chkluuthongtin = (CheckBox) findViewById(R.id.check);
        btnLogin = (Button) findViewById(R.id.bntlogin);
    }
}
