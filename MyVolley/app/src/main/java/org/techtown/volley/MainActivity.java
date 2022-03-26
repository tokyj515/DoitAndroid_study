package org.techtown.volley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    static RequestQueue requestQueue;

    RecyclerView recyclerView;
    MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlStr = editText.getText().toString();
                request(urlStr);
            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());


        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);

    }


    public void request(String urlStr){
        StringRequest request = new StringRequest(
                Request.Method.GET,
                urlStr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) { //volley에서 만든 스레드
                        println("응답-> "+response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러-> "+error.toString());
                    }
                }
        ) {
            @Nullable
            @Override //웹에서 서버로 데이터를 보낸 것을 처리하는 메소드
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };

        request.setShouldCache(false);
        requestQueue.add(request); //위에서 만든 요청 객체를 요청큐에 넣어줌
        println("요청 보냄");
    }

    public void processResponse(String response){
        Gson gson = new Gson();

        MovieList movieList = gson.fromJson(response, MovieList.class); //parsing
        println("영화 정보의 수: "+movieList.boxOfficeResult.dailyBoxOfficeList.size());

        for(int i=0; i<movieList.boxOfficeResult.dailyBoxOfficeList.size(); i++){
            Movie movie = movieList.boxOfficeResult.dailyBoxOfficeList.get(i);
            adapter.addItem(movie);
        }

        adapter.notifyDataSetChanged();
    }


    public void println(String data){
        textView.append(data + "\n");
    }


}