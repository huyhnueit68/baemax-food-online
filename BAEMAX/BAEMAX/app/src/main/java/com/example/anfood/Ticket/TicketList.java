package com.example.anfood.Ticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.anfood.Adapter.ListTicketAdapter;
import com.example.anfood.Menu.Menu;
import com.example.anfood.Model.Ticket;
import com.example.anfood.R;

import java.util.ArrayList;

public class TicketList extends AppCompatActivity {
Toolbar tb;
RecyclerView rv_listticket;
ArrayList<Ticket> dsticket;
public static String ticketdetails;
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Ticket ticket = dsticket.get(position);
            ticketdetails = ticket.getTitle();
            startActivity(new Intent(TicketList.this,VoucherDetails.class));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_list);
        //custom toolbar
        tb=findViewById(R.id.toolbar_ticket);
        tb.setNavigationIcon(R.drawable.ic_back);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Menu.class);
                startActivity(i);
            }
        });
        //custom toolbar
        //List ticket
        rv_listticket=findViewById(R.id.rv_listticket);
        Ticket tk = new Ticket("abc","Ưu đãi 35% tối đa 10.000đ","Hạn dùng 23 thg 12, 2019",true);
        Ticket tk2 = new Ticket("abc","Ưu đãi 15% tối đa 5.000đ","Hạn dùng 05 thg 2, 2019",true);
        Ticket tk3 = new Ticket("abc","Ưu đãi 5% tối đa 3.000đ","Hạn dùng 08thg 3, 2019",true);
        dsticket = new ArrayList<>();
        dsticket.add(tk); dsticket.add(tk2); dsticket.add(tk3);
        ListTicketAdapter adapter = new ListTicketAdapter(dsticket);
        rv_listticket.setLayoutManager(new LinearLayoutManager(this));
        rv_listticket.setAdapter(adapter);
       adapter.setOnItemClickListener(onItemClickListener);
        //List ticket



    }

}
