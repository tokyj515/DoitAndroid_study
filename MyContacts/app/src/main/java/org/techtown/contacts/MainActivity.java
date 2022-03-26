package org.techtown.contacts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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
            @Override
            public void onClick(View view) {
                selectContacts();
            }
        });

    }

    public void selectContacts(){
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(resultCode == 101){
                Uri contacksUri = data.getData();
                String id = contacksUri.getLastPathSegment();

                getContacts(id);
            }
        }
    }

    @SuppressLint("Range")
    public void getContacts(String id){
        Cursor cursor = null;
        String name  = "";

        try {
            cursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                    null, ContactsContract.Data.CONTACT_ID + "=?",
                    new String[]{id}, null);

            if (cursor.moveToFirst()) {
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                println("name: " + name);

                String columns[] = cursor.getColumnNames();
                for (String column : columns) {
                    int index = cursor.getColumnIndex(column);
                    String columnOutput = ("#" + index + "-> [" + column + "] " + cursor.getString(index));
                    println(columnOutput);
                }
            }
            cursor.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void println(String data){
        textView.append(data+"\n");
    }
}