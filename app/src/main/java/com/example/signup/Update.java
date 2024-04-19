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

public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Button button = (Button) findViewById(R.id.btn_next2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendelete();
            }
        });

        EditText txt_id=findViewById(R.id.txt_id);
        EditText txt_uname=findViewById(R.id.txt_uname);
        EditText txt_pword=findViewById(R.id.txt_pword);
        EditText txt_email=findViewById(R.id.txt_email);
        Button btn_update=findViewById(R.id.btn_update);

        //add btn_save onClickListener

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=txt_id.getText().toString();
                String uname=txt_uname.getText().toString();
                String pword=txt_pword.getText().toString();
                String email=txt_email.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://192.168.1.71/android_crud/update.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Updated")){
                                    Toast.makeText(Update.this, "Updated", Toast.LENGTH_SHORT).show();
                                } else{
                                    Toast.makeText(Update.this, "Failed to Update", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.e("Error",error.getLocalized)
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("id", id);
                        paramV.put("uname", uname);
                        paramV.put("pword", pword);
                        paramV.put("email", email);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }

    private void opendelete() {
        Intent i = new Intent(this, Delete.class);
        startActivity(i);
    }

}