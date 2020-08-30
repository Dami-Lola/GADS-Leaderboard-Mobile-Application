package com.damio.gadsleaderboard;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.damio.gadsleaderboard.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(),getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(   new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent submitProject = new Intent(getApplicationContext(), SubmitProjectActivity.class);
                startActivity(submitProject);
            }
        });

    }
}