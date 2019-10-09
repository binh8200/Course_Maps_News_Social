package com.example.asm_ps10220.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.asm_ps10220.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentCourse extends Fragment {


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        BottomNavigationView bottomNav = view.findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout1,
                new FragmentAddClass()).commit();
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_addclass:
                            selectedFragment = new FragmentAddClass();
                            break;

                        case R.id.nav_student:
                            selectedFragment = new FragmentStudent();
                            break;
                    }

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout1,
                            selectedFragment).commit();
                    return true;
                }
            };
}
