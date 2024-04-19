package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Delete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Button button=(Button) findViewById(R.id.btn_next3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openRetrieve();}
        });

        EditText txt_id=findViewById(R.id.txt_id);
        Button btn_delete=findViewById(R.id.btn_delete);

        //add btn_save onClickListener

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = txt_id.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.1.71/android_crud/delete.php"; // Update the server address

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Deleted")) {
                                    Toast.makeText(Delete.this, "Deleted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Delete.this, "Failed to Delete", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("id", id);
                        return params;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }

    private void openRetrieve() {
        Intent intent = new Intent(this,Retrieve.class);
        startActivity(intent);
    }
}