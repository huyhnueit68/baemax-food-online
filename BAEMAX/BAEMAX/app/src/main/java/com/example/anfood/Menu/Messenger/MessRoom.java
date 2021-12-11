package com.example.anfood.Menu.Messenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.anfood.Menu.Menu;
import com.example.anfood.R;

import static com.example.anfood.Menu.Messenger.TinNhanFragment.username;

public class MessRoom extends AppCompatActivity {
Toolbar tb;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_room);
        tb=findViewById(R.id.tb_mess);
        tb.setNavigationIcon(R.drawable.ic_back);
        tb.setTitle(username+"");
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Menu.class);
                startActivity(i);
            }
        });
    }
}
