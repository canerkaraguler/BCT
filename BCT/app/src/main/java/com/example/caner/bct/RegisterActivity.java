package com.example.caner.bct;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;


public class RegisterActivity extends AppCompatActivity {
    private Uri file_uri1;
    private File file1;
    private String  photo_name;
    private String usern1,usern2;


    public boolean empty(EditText e){
        return e.getText().toString().trim().length() == 0;
    }

    public boolean emptyall(EditText a,EditText b,EditText c,EditText d,EditText e,EditText f){
        boolean x =true ;
        if (empty(a)) {

            x=false;
        }
        if (empty(b)) {
            x=false;
        }
        if (empty(c)) {
            x=false;

        }
        if (empty(d)) {
            x=false;

        }
        if (empty(e)) {

            x=false;
        }
        if (empty(f)) {
            x=false;

        }

        return x;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etUserNameR = (EditText) findViewById(R.id.etUsernameR);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etEmail = (EditText) findViewById(R.id.etEmailP);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);





            final Button bRegister = (Button) findViewById(R.id.bRegister);

            bRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {


                        final String username = etUserNameR.getText().toString();
                        final String name = etName.getText().toString();
                        final String email = etEmail.getText().toString();
                        final String password = etPassword.getText().toString();
                        final int age = Integer.parseInt(etAge.getText().toString());
                        final String passwordconf = etPasswordConfirm.getText().toString();

                        usern1="default";
                        usern2=username;

                        etUserNameR.setError(null);
                        etEmail.setError(null);
                        etAge.setError(null);
                        etPassword.setError(null);
                        etName.setError(null);
                        etPasswordConfirm.setError(null);


                       if(emptyall(etAge,etEmail,etName,etPassword,etPasswordConfirm,etUserNameR)) {


                           if (passwordconf.equals(password)) {

                               Response.Listener responseListener = new Response.Listener<String>() {

                                   @Override
                                   public void onResponse(String response) {
                                       try {
                                           JSONObject jsonResponse = new JSONObject(response);
                                           boolean succes = jsonResponse.getBoolean("success");

                                           if (succes) {
                                               Intent go_login = new Intent(RegisterActivity.this, LoginActivity.class);
                                               RegisterActivity.this.startActivity(go_login);
                                           } else {


                                               AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                               builder.setMessage("Username or E-mail is already used.")
                                                       .setNegativeButton("Retry", null)
                                                       .create()
                                                       .show();


                                           }
                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }
                                   }
                               };
                                getFileUri1();
                               RegisterRequest reg = new RegisterRequest(name, username, age, email, password, "FALSE", file_uri1.getPath(),photo_name,responseListener);
                               RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                               queue.add(reg);
                               etPasswordConfirm.setError(null);


                           } else {
                               etPasswordConfirm.setError("Wrong password");


                           }
                       }else {
                           if (empty(etUserNameR)) {
                               etUserNameR.setError("Username cannot be empty.");

                           }
                           if (empty(etEmail)) {
                               etEmail.setError("E-mail cannot be empty.");

                           }
                           if (empty(etAge)) {
                               etAge.setError("Age cannot be empty.");

                           }
                           if (empty(etPassword)) {
                               etPassword.setError("Password cannot be empty.");

                           }
                           if (empty(etName)) {
                               etName.setError("Name cannot be empty");

                           }
                           if (empty(etPasswordConfirm)) {
                               etPasswordConfirm.setError("Password cannot be empty");

                           }

                       }



                    }catch (Exception e){
                        if (empty(etUserNameR)) {
                            etUserNameR.setError("Username cannot be empty.");

                        }
                        if (empty(etEmail)) {
                            etEmail.setError("E-mail cannot be empty.");

                        }
                        if (empty(etAge)) {
                            etAge.setError("Age cannot be empty.");

                        }
                        if (empty(etPassword)) {
                            etPassword.setError("Password cannot be empty.");

                        }
                        if (empty(etName)) {
                            etName.setError("Name cannot be empty");

                        }
                        if (empty(etPasswordConfirm)) {
                            etPasswordConfirm.setError("Password cannot be empty");

                        }
                         /*AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("Please fill all .. ")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();*/
                    }
                }



            });
        }

    private void getFileUri1() {
        photo_name = usern2+".jpg";
        file1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + File.separator + ("default.jpg")
        );

        file_uri1 = Uri.fromFile(file1);
    }

}

