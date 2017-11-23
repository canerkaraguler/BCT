package com.example.caner.bct;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUserNameL =(EditText) findViewById(R.id.etUserNameL);
        final EditText etPasswordL =(EditText) findViewById(R.id.etPasswordL);
        final Button bLogin =(Button) findViewById(R.id.bLogin);
        final TextView tvRegisterHere =(TextView) findViewById(R.id.tvRegisterHere);
        tvRegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = etUserNameL.getText().toString();
                final String password = etPasswordL.getText().toString();

                Response.Listener<String> responselistener =new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonresponse =new JSONObject(response);
                            boolean success =jsonresponse.getBoolean("success");
                            if(success){
                                String is_admin =jsonresponse.getString("is_admin");
                                String name = jsonresponse.getString("name");
                                int age = jsonresponse.getInt("age");
                                String username =jsonresponse.getString("username");
                                if(is_admin.equals("TRUE")){
                                    Intent adminIntent = new Intent(LoginActivity.this, AdminActivity.class);
                                    adminIntent.putExtra("username",username);
                                    adminIntent.putExtra("name",name);
                                    adminIntent.putExtra("age",age);

                                    LoginActivity.this.startActivity(adminIntent);

                                }else{
                                    Intent userIntent = new Intent(LoginActivity.this, UserProfile.class);
                                    userIntent.putExtra("username",username);
                                    userIntent.putExtra("name",name);
                                    userIntent.putExtra("age",age);

                                    LoginActivity.this.startActivity(userIntent);
                                }

                            }else {

                                if (etUserNameL.getText().toString().trim().length() == 0 || etPasswordL.getText().toString().trim().length() == 0) {
                                    if (etUserNameL.getText().toString().trim().length() == 0) {
                                        etUserNameL.setError("Username cannot be empty");
                                    }
                                    if (etPasswordL.getText().toString().trim().length() == 0) {
                                        etPasswordL.setError("Password cannot be empty");
                                    }
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    builder.setMessage("Username or Password is wrong")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                };
                LoginRequest login=new LoginRequest(username,password,responselistener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(login);


            }
        });


    }
}
