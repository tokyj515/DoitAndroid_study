package org.techtown.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    //네트워킹

    EditText input1;
    TextView output1;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        input1 = findViewById(R.id.input1);
        output1 = findViewById(R.id.output1);

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String data = input1.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data); //소켓으로 전송하기 (스레드 사용, 인터넷 권한 필요, UI갱신을 위해 핸들러 사용하기)
                    }
                }).start();

            }
        });


        Button startServerButton = findViewById(R.id.startServerButton);
        startServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start();
            }
        });


    }



    public void println(final String data){ //UI에 출력하는 거라 핸들러를 써야하는데 쓰지 않아서 출력이 안되는 오류 발생
        handler.post(new Runnable() {
            @Override
            public void run() {
                output1.append(data+"\n");
            }
        });
    }



    public void startServer(){
        int port = 5001;

        try {
            ServerSocket server = new ServerSocket(port);

            while(true){
                Socket sock = server.accept();
                InetAddress clientHost = sock.getLocalAddress();
                int clientPort = sock.getPort();
                println("클라이언트 연결됨: "+clientHost+", "+clientPort);


                ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
                String input = (String) instream.readObject();
                println("데이터 받음: "+input);

                ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
                outstream.writeObject(input+" from server.");
                outstream.flush();
                println("데이터 보냄");

                sock.close();
            }

        } catch (Exception e){
            e.printStackTrace();
        }


    }

    public void send(String data){
        //클라이언트와 서버 통신
        //이더넷, 포트를 지정해서 주고 받음
        int port = 5001;

        try {
            Socket sock = new Socket("localhost", port);
            //정보를 주고 받을 ip주소 필요함 지금은 내 거로 받을 거라 localhost

            ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
            outstream.writeObject(data);
            outstream.flush();

            ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
            String input = (String) instream.readObject();

            sock.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


}