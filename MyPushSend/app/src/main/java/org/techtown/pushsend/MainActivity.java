package org.techtown.pushsend;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText input1;
    TextView output1;

    static RequestQueue requestQueue;
    static String regId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.input1);
        output1 = findViewById(R.id.output1);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = input1.getText().toString();
                send(input);
            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());

    }


    public void send(String input){
        JSONObject requestData = new JSONObject();
        try {
            requestData.put("priority", "high");

            JSONObject dataObj = new JSONObject();
            dataObj.put("contents", input);
            requestData.put("data", dataObj);

            JSONArray idArray = new JSONArray();
            idArray.put(0, regId);
            requestData.put("registration_ids", idArray);


        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "https://fcm.googleapis.com/fcm/send",
                requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization",
                        "key=AAAA9OwE55Y:APA91bHyWYY1SQw9TAUrslbc9kchhFQV5bI-KNiGHnd0J0gclXad2PU-Blar1HRXsWiEnPtDeHL1tbPj2eALDxMlXICvI_dcZ7dEwQhRpNdbPMnFy_tBg1lVupk7YVTFtLgPDx8f1zQu");
                return headers;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return super.getParams();
            }
        };

        request.setShouldCache(false);
        requestQueue.add(request);
        println("요청보냄");

    }

    public void println(String message){
        output1.append(message+"\n");
    }
}