package com.example.caner.bct;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SelectCity extends AppCompatActivity {

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
    private  String usern,nameuser,itemCity,itemCinema,itemMovie,itemCinemaId,salonid,salonid2;
    private int userage;
    private Spinner sip,sipCinema,sipMovie;



    final ArrayList<String> cities = new ArrayList<String>();
    final ArrayList<String> cinemas = new ArrayList<String>();
    final ArrayList<String> movies = new ArrayList<String>();
    final ArrayList<String> cinemaId = new ArrayList<String>();
    final ArrayList<String> salid = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        mdrawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        nToggle =new ActionBarDrawerToggle(this,mdrawerLayout,R.string.open,R.string.close);
        mtoolbar=(Toolbar) findViewById(R.id.nav_action_bar);
        setSupportActionBar(mtoolbar);

        //View child = getLayoutInflater().inflate(R.layout.navigation_header, null);
        //mdrawerLayout.addView(child);

        mdrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        //navimg = (CircleImageView) findViewById(ivheaderimg);
        photo= (CircleImageView) findViewById(R.id.ivphotouser);
        Intent gintent=getIntent();
        usern=gintent.getStringExtra("username");
        nameuser=gintent.getStringExtra("name");
        userage=gintent.getIntExtra("age",-1);




        sip = (Spinner) findViewById(R.id.spinnerCity);
        sipCinema = (Spinner) findViewById(R.id.spinnerCinema);
        sipMovie = (Spinner) findViewById(R.id.spinnerMovie);
        btn=(Button) findViewById(R.id.goSelectSeat);


        new DownloadImage(usern).execute();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        // navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
/*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        navimg = (CircleImageView) header.findViewById(R.id.ivheaderimg);
        navname =(TextView) header.findViewById(R.id.tvUsernameMenu);

        navname.setText(nameuser+"/"+userage);






        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ArrayAdapter<String> dataAdapterCinema = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cinemas);
        dataAdapterCinema.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ArrayAdapter<String> dataAdapterMovie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, movies);
        dataAdapterMovie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sip.setAdapter(dataAdapter);
        sipCinema.setAdapter(dataAdapterCinema);
        sipMovie.setAdapter(dataAdapterMovie);






        Response.Listener<String> responselistener =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonresponse =new JSONObject(response);


                    JSONArray arr =jsonresponse.getJSONArray("success");

                    for(int i = 0;i<arr.length();i++){
                        cities.add(i,arr.getJSONObject(i).getString("name").toString());

                        dataAdapter.notifyDataSetChanged();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        };
        getcity login=new getcity(responselistener);
        RequestQueue queue = Volley.newRequestQueue(SelectCity.this);
        queue.add(login);



        final Response.Listener<String> responselistenercinema =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonresponse =new JSONObject(response);


                    JSONArray arr =jsonresponse.getJSONArray("success");

                    for(int i = 0;i<arr.length();i++){
                        cinemas.add(i,arr.getJSONObject(i).getString("cname").toString());
                        cinemaId.add(i,arr.getJSONObject(i).getString("id").toString());
                        dataAdapterCinema.notifyDataSetChanged();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        };
        getcinema logincinema=new getcinema(itemCity,responselistenercinema);
        RequestQueue queuecinema = Volley.newRequestQueue(SelectCity.this);
        queuecinema.add(logincinema);

        final Response.Listener<String> responselistenermovie =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonresponse =new JSONObject(response);


                    JSONArray arr =jsonresponse.getJSONArray("success");

                    for(int i = 0;i<arr.length();i++){
                        salonid=arr.getJSONObject(i).getString("salonId");
                        movies.add(i,arr.getJSONObject(i).getString("movieName")+"  Salon:"+salonid);
                        salid.add(salonid);
                        dataAdapterMovie.notifyDataSetChanged();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        };
        getmovie loginmovie=new getmovie(Integer.toString(0),responselistenermovie);
        RequestQueue queuemovie = Volley.newRequestQueue(SelectCity.this);
        queuemovie.add(loginmovie);

        sip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemCity = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + itemCity, Toast.LENGTH_LONG).show();



                getcinema logincinema=new getcinema(itemCity,responselistenercinema);
                RequestQueue queuecinema = Volley.newRequestQueue(SelectCity.this);
                queuecinema.add(logincinema);

                sipCinema.setAdapter(dataAdapterCinema);
                dataAdapterCinema.notifyDataSetChanged();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        sipCinema.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemCinema = parent.getItemAtPosition(position).toString();

                for(int i=0;i<cinemas.size();i++){
                    if(cinemas.get(i).equals(itemCinema)){
                        itemCinemaId=cinemaId.get(i);
                    }
                }
               // Toast.makeText(parent.getContext(), "id: " + itemCinemaId, Toast.LENGTH_LONG).show();

                getmovie loginmovie=new getmovie(itemCinemaId,responselistenermovie);
                RequestQueue queuemovie = Volley.newRequestQueue(SelectCity.this);
                queuemovie.add(loginmovie);


                sipMovie.setAdapter(dataAdapterMovie);
                dataAdapterMovie.notifyDataSetChanged();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sipMovie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemMovie = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
               // Toast.makeText(parent.getContext(), "Selected: " + itemMovie, Toast.LENGTH_LONG).show();
                for(int i=0;i<movies.size();i++){
                    if(movies.get(i).equals(itemMovie)){
                        salonid2=salid.get(i);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.item1:
                        Intent profileIntent = new Intent(SelectCity.this, UserProfile.class);
                        profileIntent.putExtra("username",usern);
                        profileIntent.putExtra("name",nameuser);
                        profileIntent.putExtra("age",userage);
                        finish();
                        SelectCity.this.startActivity(profileIntent);
                        return true;
                    case R.id.item2:
                        Intent walletintent = new Intent(SelectCity.this, wallet.class);
                        walletintent.putExtra("username",usern);
                        walletintent.putExtra("name",nameuser);
                        walletintent.putExtra("age",userage);
                        finish();
                        SelectCity.this.startActivity(walletintent);
                        return true;
                    case R.id.item3:
                        Intent historyintent = new Intent(SelectCity.this, TicketHistory.class);
                        historyintent.putExtra("username",usern);
                        historyintent.putExtra("name",nameuser);
                        historyintent.putExtra("age",userage);
                        finish();
                        SelectCity.this.startActivity(historyintent);
                        return true;
                    case R.id.itembuy:
                        Intent buyintent =new Intent (SelectCity.this,SelectCity.class);
                        buyintent.putExtra("username",usern);
                        buyintent.putExtra("name",nameuser);
                        buyintent.putExtra("age",userage);
                        finish();
                        SelectCity.this.startActivity(buyintent);
                        return true;
                    case R.id.item5:
                        AlertDialog.Builder builder = new AlertDialog.Builder(SelectCity.this);
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


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent profileIntent = new Intent(SelectCity.this, SelectSeat.class);
                profileIntent.putExtra("username",usern);
                profileIntent.putExtra("name",nameuser);
                profileIntent.putExtra("age",userage);
                profileIntent.putExtra("movieName",itemMovie);

                profileIntent.putExtra("cid",itemCinemaId);
                profileIntent.putExtra("sid",salonid2);
                finish();
                SelectCity.this.startActivity(profileIntent);
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




    private class DownloadImage extends AsyncTask<Void,Void,Bitmap>{

        String name;

        public DownloadImage(String name){
            this.name=name;

        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap !=null){
                //photo.setImageBitmap(bitmap);
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