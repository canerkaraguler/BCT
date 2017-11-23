package com.example.caner.bct;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class edit_profile extends AppCompatActivity {

    private CircleImageView navimg;
    private CircleImageView photo;
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle nToggle;
    private Toolbar mtoolbar;
    private Button btn;

    private ImageButton cambtn;
    private String encoded_string, image_name;
    private Bitmap bitmap;
    private TextView navname;


    private File file;
    private Uri file_uri;
    private  String usern,nameuser,address1;
    private int userage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);



        //LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View view = layoutInflater.inflate(R.layout.navigation_header, null  );

       // View inflatedView = getLayoutInflater().inflate(R.layout.navigation_header, null);

        mdrawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        nToggle =new ActionBarDrawerToggle(this,mdrawerLayout,R.string.open,R.string.close);
        mtoolbar=(Toolbar) findViewById(R.id.nav_action_bar);
        setSupportActionBar(mtoolbar);

        //View child = getLayoutInflater().inflate(R.layout.navigation_header, null);
        //mdrawerLayout.addView(child);

        final EditText adrs= (EditText) findViewById(R.id.etadr);



        mdrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final TextView tvname = (TextView) findViewById(R.id.tveditusern);

        //navimg = (CircleImageView) findViewById(ivheaderimg);
        photo= (CircleImageView) findViewById(R.id.ivphotouser);
        Intent gintent=getIntent();
         usern=gintent.getStringExtra("username");
        nameuser=gintent.getStringExtra("name");
        userage=gintent.getIntExtra("age",-1);


        tvname.setText(nameuser+"/"+userage);




        new DownloadImage(usern).execute();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
       // navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
/*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        navimg = (CircleImageView) header.findViewById(R.id.ivheaderimg);
        navname =(TextView) header.findViewById(R.id.tvUsernameMenu);

        navname.setText(nameuser+"/"+userage);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.item1:
                        Intent profileIntent = new Intent(edit_profile.this, UserProfile.class);
                        profileIntent.putExtra("username",usern);
                        profileIntent.putExtra("name",nameuser);
                        profileIntent.putExtra("age",userage);
                        finish();
                        edit_profile.this.startActivity(profileIntent);
                    case R.id.item2:
                        Intent walletintent = new Intent(edit_profile.this, wallet.class);
                        walletintent.putExtra("username",usern);
                        walletintent.putExtra("name",nameuser);
                        walletintent.putExtra("age",userage);
                        finish();
                        edit_profile.this.startActivity(walletintent);
                        return true;
                    case R.id.item3:
                        Intent historyintent = new Intent(edit_profile.this, TicketHistory.class);
                        historyintent.putExtra("username",usern);
                        historyintent.putExtra("name",nameuser);
                        historyintent.putExtra("age",userage);
                        finish();
                        edit_profile.this.startActivity(historyintent);
                        return true;
                    case R.id.itembuy:
                        Intent buyintent =new Intent (edit_profile.this,SelectCity.class);
                        buyintent.putExtra("username",usern);
                        buyintent.putExtra("name",nameuser);
                        buyintent.putExtra("age",userage);
                        finish();
                        edit_profile.this.startActivity(buyintent);
                        return true;
                    case R.id.item5:
                        AlertDialog.Builder builder = new AlertDialog.Builder(edit_profile.this);
                        builder.setMessage("Caner Karaguler:\ncaner.karaguler@gmail.com\n\nBetul Tekne:\nbetull.tekkne@gmail.com ")
                                .setNegativeButton("Ok", null)
                                .create()
                                .show();
                        return true;
                    case R.id.item6:
                        finish();
                        System.exit(0);
                        return true;
                    default:
                        return false;
                }
            }
        });





        btn =(Button) findViewById(R.id.button6);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    address1=adrs.getText().toString();

                }catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(edit_profile.this);
                    builder.setMessage("asdqweq")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();


                }

                Response.Listener responseListener1 = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean succes = jsonResponse.getBoolean("success");

                            if (succes) {
                                Intent go_login = new Intent(edit_profile.this, UserProfile.class);
                                go_login.putExtra("username",usern);
                                go_login.putExtra("name",nameuser);
                                go_login.putExtra("age",userage);
                                finish();
                                edit_profile.this.startActivity(go_login);
                            } else {


                                AlertDialog.Builder builder = new AlertDialog.Builder(edit_profile.this);
                                builder.setMessage("asdqweq")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                adrset reg = new adrset(usern, address1,responseListener1);
                RequestQueue queue = Volley.newRequestQueue(edit_profile.this);
                queue.add(reg);

            }
        });




        cambtn = (ImageButton) findViewById(R.id.camerabtn);
        cambtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                getFileUri();



                i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, file_uri);

                startActivityForResult(i, 10);






            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(nToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void getFileUri() {
        image_name = usern+".jpg";
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + File.separator + image_name
        );

        file_uri = Uri.fromFile(file);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {




             if(requestCode == 10 && resultCode == RESULT_OK){
                new Encode_image().execute();

            }


    }

    private class Encode_image extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {


            bitmap = BitmapFactory.decodeFile(file_uri.getPath());



            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, stream);
            bitmap.recycle();

            byte[] array = stream.toByteArray();
            encoded_string = Base64.encodeToString(array, 0);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

                makeRequest();


        }
    }

    private void makeRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, "http://54.186.173.241/image.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("encoded_string",encoded_string);
                map.put("image_name",image_name);

                return map;
            }
        };
        requestQueue.add(request);
        new DownloadImage(usern).execute();

        finish();startActivity(getIntent());

    }

    private class DownloadImage extends AsyncTask<Void,Void,Bitmap>{

        String name;

        public DownloadImage(String name){
            this.name=name;

        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap !=null){
                photo.setImageBitmap(bitmap);
              // navimg.setImageBitmap(bitmap);
                navimg.setImageBitmap(bitmap);

            }
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            String url = "http://54.186.173.241/images/" + name + ".jpg";

                try {
                    URLConnection urlcon = new URL(url).openConnection();
                    urlcon.setConnectTimeout(1000 * 30);
                    urlcon.setReadTimeout(1000 * 30);

                    return BitmapFactory.decodeStream((InputStream) urlcon.getContent(), null, null);


                } catch (Exception e) {

                    String url1 ="http://54.186.173.241/images/" + "default" + ".jpg";
                    URLConnection urlcon1 = null;
                    try {
                        urlcon1 = new URL(url1).openConnection();

                    urlcon1.setConnectTimeout(1000 * 30);
                    urlcon1.setReadTimeout(1000 * 30);

                    return BitmapFactory.decodeStream((InputStream) urlcon1.getContent(), null, null);

                    } catch (IOException e1) {
                        e1.printStackTrace();
                        return  null;
                    }
                }

            }

    }


}
