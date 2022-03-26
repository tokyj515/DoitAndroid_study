package org.techtown.tab;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    //탭 버튼 세개가 보이고 그 버튼을 누르면 각 프래그먼트가 보이게 하는 앱

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
        //화면에 fragment1이 제일 먼저 보이도록 함


        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("통화기록"));
        tabs.addTab(tabs.newTab().setText("스팸기록"));
        tabs.addTab(tabs.newTab().setText("연락처"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) { //탭 선택
                int position = tab.getPosition();

                if(position == 0){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                }
                else if(position == 1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                }
                else if(position == 2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { //

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

}