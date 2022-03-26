package org.techtown.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    PersonAdpater adpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //GridLayoutManager layoutManager = new GridLayoutManager(this, 2); //열 2개
        //recyclerView.setLayoutManager(layoutManager);


       adpater = new PersonAdpater();

        adpater.addItem(new Person("김민수", "010-1000-1000"));
        adpater.addItem(new Person("김하늘", "010-2000-2000"));
        adpater.addItem(new Person("홍길동", "010-3000-3000"));
        //코드로 임의로 추가하는 것이 아니라 사용자가 추가할 수 있게 또는 네트워킹을 통해 추가하기 등등

        recyclerView.setAdapter(adpater);

        adpater.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdpater.ViewHolder holder, View view, int position) {
               Person item = adpater.getItem(position);
               showToast("아이템 선택됨: "+item.getName());
            }
        });

    }

    public void showToast(String data){
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }


}