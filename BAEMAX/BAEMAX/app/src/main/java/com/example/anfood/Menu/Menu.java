package com.example.anfood.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.anfood.Menu.Messenger.TinNhanFragment;
import com.example.anfood.Menu.Personal.CaNhanFragment;
import com.example.anfood.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu extends AppCompatActivity {
    private ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Fragment fragment;
        fragment = new HomeFragment();
        loadFragment(fragment);


    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.icon_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.icon_hoadon:
                    fragment = new HoaDonFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.icon_nhantin:
                    fragment = new TinNhanFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.icon_canhan:
                    fragment = new CaNhanFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
