package com.example.anfood.Ticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.anfood.R;

import static com.example.anfood.Ticket.TicketList.ticketdetails;

public class VoucherDetails extends AppCompatActivity {
Toolbar tb;
TextView tv_ticket_details_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_details);
        //custom toolbar
        tb=findViewById(R.id.toolbar_ticket_details);
        tb.setNavigationIcon(R.drawable.ic_back);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VoucherDetails.this,TicketList.class));
            }
        });
        tb.setTitle(ticketdetails+"");
        //custom toolbar
        tv_ticket_details_title=findViewById(R.id.tv_ticket_details_title);
        tv_ticket_details_title.setText(ticketdetails+"");
    }
}
