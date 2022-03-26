package org.techtown.provider;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                insertPerson();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryPerson();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePerson();
            }
        });


        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePerson();
            }
        });
    }

   // @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertPerson(){
        println("insertPerson 호출됨");

        String uriStr = "content://org.techtown.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriStr);

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        String[] columns = cursor.getColumnNames();

        for(int i=0; i<columns.length; i++){
            println("#"+i+": "+columns[i]);
        }

        ContentValues values = new ContentValues();
        values.put("name", "john");
        values.put("age", 20);
        values.put("mobile", "101-1000-1000");

        uri = getContentResolver().insert(uri, values);
        println("insert 결과: "+uri.toString());

    }


    public void queryPerson(){
        try {
            String uriStr = "content://org.techtown.provider/person";
            Uri uri = new Uri.Builder().build().parse(uriStr);

            String[] columns = new String[]{"name", "age", "mobile"};
            Cursor cursor = getContentResolver().query(uri, columns, null, null, "name ASC");

            println("query 결과: " + cursor.getCount());

            int index = 0;
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(columns[0]));
                @SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex(columns[1]));
                @SuppressLint("Range") String mobile = cursor.getString(cursor.getColumnIndex(columns[2]));

                println("#" + index + "-> " + name + ", " + age + ", " + mobile);

                index++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updatePerson(){
        String uriStr = "content://org.techtown.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriStr);

        String seletion = "mobile = ?";
        String[] seletionArgs = new String[] {"010-1000-1000"};
        ContentValues updateValues = new ContentValues();
        updateValues.put("mobile", "010-2000-2000");

        int count = getContentResolver().update(uri, updateValues, seletion, seletionArgs);

        println("update 결과: " + count);
    }


    public void deletePerson(){
        String uriStr = "content://org.techtown.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriStr);

        String seletion = "mobile = ?";
        String[] seletionArgs = new String[] {"john"};

        int count = getContentResolver().delete(uri, seletion, seletionArgs);
        println("delete 결과: " + count);

    }

    public void println(String data){
        textView.append(data+"\n");
    }
}