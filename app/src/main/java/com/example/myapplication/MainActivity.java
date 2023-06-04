package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        requestQueue = Volley.newRequestQueue(MainActivity.this);


        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToServer();
            }
        });
    }

    private void sendDataToServer() {
        String url = "http://192.168.25.55/Singup2.php";

        EditText editTextUserID = findViewById(R.id.editTextUserID);
        EditText editTextUserPassword = findViewById(R.id.editTextUserPassword);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        EditText editTextUserSort = findViewById(R.id.editTextUserSort);

        final String userID = editTextUserID.getText().toString();
        final String userPassword = editTextUserPassword.getText().toString();
        final String email = editTextEmail.getText().toString();
        final String phoneNumber = editTextPhoneNumber.getText().toString();
        final String userSort = editTextUserSort.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        // 성공적으로 서버에 데이터를 전송한 후 수행할 작업을 여기에 추가
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                        // 요청 실패 시 수행할 작업을 여기에 추가
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userID", userID);
                params.put("userPassword", userPassword);
                params.put("email", email);
                params.put("phoneNumber", phoneNumber);
                params.put("userSort", userSort);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}