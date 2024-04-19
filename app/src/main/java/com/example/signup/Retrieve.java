package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Retrieve extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);


        EditText txt_id=findViewById(R.id.txt_id);

        TextView lbl_uname=findViewById(R.id.lbl_uname);
        TextView lbl_pword=findViewById(R.id.lbl_pword);
        TextView lbl_email=findViewById(R.id.lbl_email);
        Button btn_retrieve=findViewById(R.id.btn_retrieve);
        TextView logError = findViewById(R.id.logError);

        btn_retrieve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String id=txt_id.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://192.168.1.71/android_crud/retrieve.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONArray jsonArray = new JSONArray(response);
//                                    lbl_uname.setText("test hehehe");

                                    for (int i=0; i<jsonArray.length(); i++){
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                        logError.setText(jsonObject.toString());

                                        String user_name = jsonObject.getString("user_name");
                                        String user_pass=jsonObject.getString("user_pword");
                                        String user_email=jsonObject.getString("user_email");
                                        lbl_uname.setText("Username: "+ user_name);
                                        lbl_pword.setText("Password: "+ user_pass);
                                        lbl_email.setText("Email: "+ user_email);

                                    }
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.e("Error",error.getLocalized)
//                        logError.setText(error.toString());
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("id", id);
//                        paramV.put("uname", uname);
//                        paramV.put("pword", pword);
//                        paramV.put("email", email);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }
}