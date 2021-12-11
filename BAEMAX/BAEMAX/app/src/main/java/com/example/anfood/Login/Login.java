package com.example.anfood.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anfood.Login.Premire_Tutorial.Premire;
import com.example.anfood.R;

public class Login extends AppCompatActivity {
Button btn_dangnhap,btn_dangky;
TextView tv_quenmk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_dangnhap=findViewById(R.id.btn_dangnhap);
        btn_dangky =findViewById(R.id.btn_dangky);
        tv_quenmk=findViewById(R.id.tv_quenmatkhau);
        btn_dangnhap=findViewById(R.id.btn_dangnhap);
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
        tv_quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, GetPassword.class);
                startActivity(i);
            }
        });
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Premire.class);
                startActivity(i);
            }
        });
    }
}
