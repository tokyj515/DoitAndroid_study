package org.techtown.snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "스낵바입니다.", Snackbar.LENGTH_LONG).show();
            }
        });


        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage();
            }
        });
    }

    public void showMessage(){
        AlertDialog.Builder bulider = new AlertDialog.Builder(this);
        bulider.setTitle("안내");
        bulider.setMessage("종료하시겠습니까?");
        bulider.setIcon(android.R.drawable.ic_dialog_alert);

        bulider.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "예 버튼 눌림", Toast.LENGTH_LONG).show();
            }
        });

        bulider.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "아니오 버튼 눌림", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog dialog = bulider.create();
        dialog.show();
    }

}