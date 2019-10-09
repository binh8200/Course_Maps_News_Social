package com.example.asm_ps10220;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.asm_ps10220.Fragment.FragmentCourse;
import com.example.asm_ps10220.Fragment.FragmentGioiThieu;
import com.example.asm_ps10220.Fragment.FragmentMaps;
import com.example.asm_ps10220.Fragment.FragmentNews;
import com.example.asm_ps10220.Fragment.FragmentSocial;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-----toolbar-----
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //-----DrawerLayout-----
        drawerLayout = findViewById(R.id.activity_main_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.left_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //-----mở app vào fragment thống kê đầu tiên-----
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_layout, new FragmentCourse()).commit();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();
        if (id == R.id.left_nav_course) {
            fragmentTransaction.replace(R.id.frame_layout, new FragmentCourse()).commit();
        } else if (id == R.id.left_nav_maps) {
            fragmentTransaction.replace(R.id.frame_layout, new FragmentMaps()).commit();
        } else if (id == R.id.left_nav_news) {
            fragmentTransaction.replace(R.id.frame_layout, new FragmentNews()).commit();
        } else if (id == R.id.left_nav_social) {
            fragmentTransaction.replace(R.id.frame_layout, new FragmentSocial()).commit();
        } else if (id == R.id.left_nav_gioithieu) {
            fragmentTransaction.replace(R.id.frame_layout, new FragmentGioiThieu()).commit();
        } else if (id == R.id.left_nav_thoat) {
            finish();
        }

        drawerLayout = findViewById(R.id.activity_main_drawer);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
