package com.example.caner.bct;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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

public class TicketHistory extends AppCompatActivity {
    private CircleImageView navimg;
    private CircleImageView photo;
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle nToggle;
    private Toolbar mtoolbar;
    private Button btn;
    private ListView histlv;

    private ImageButton cambtn;
    private String encoded_string, image_name;
    private Bitmap bitmap;
    private TextView navname;

    private File file;
    private Uri file_uri;
    private  String usern,nameuser,starvalue;
    private int userage;

    final ArrayList<String> hist = new ArrayList<String>();
    final ArrayList<String> hist2 = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_history);

        mdrawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        nToggle =new ActionBarDrawerToggle(this,mdrawerLayout,R.string.open,R.string.close);
        mtoolbar=(Toolbar) findViewById(R.id.nav_action_bar);
        setSupportActionBar(mtoolbar);

        //View child = getLayoutInflater().inflate(R.layout.navigation_header, null);
        //mdrawerLayout.addView(child);

        mdrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        histlv =(ListView) findViewById(R.id.listw1);



        //navimg = (CircleImageView) findViewById(ivheaderimg);
        photo= (CircleImageView) findViewById(R.id.ivphotouser);
        Intent gintent=getIntent();
        usern=gintent.getStringExtra("username");
        nameuser=gintent.getStringExtra("name");
        userage=gintent.getIntExtra("age",-1);


        //final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2,android.R.id.text1, hist);
        //histlv.setAdapter(dataAdapter);
        //final ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2,android.R.id.text2, hist2);
        //histlv.setAdapter(dataAdapter2);
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, hist) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(hist.get(position));
                text2.setText(hist2.get(position));
                return view;
            }
        };
        histlv.setAdapter(adapter);


        Response.Listener<String> responselistener5 =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonresponse =new JSONObject(response);


                    JSONArray arr =jsonresponse.getJSONArray("success");

                    for(int i = 0;i<arr.length();i++){
                        hist.add(i,arr.getJSONObject(i).getString("movie_name").toString());
                        hist2.add(i,arr.getJSONObject(i).getString("star").toString());
                        adapter.notifyDataSetChanged();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        };
        getHistory login=new getHistory(usern,responselistener5);
        RequestQueue queue = Volley.newRequestQueue(TicketHistory.this);
        queue.add(login);

        histlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                final int k = position;

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(TicketHistory.this);
                alertDialog.setTitle("Evaluate Movie");
                alertDialog.setMessage("Evaluate between 1-10");

                final EditText input = new EditText(TicketHistory.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                //alertDialog.setIcon(R.drawable.key);



                    alertDialog.setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String a =input.getText().toString();
                                    Response.Listener<String> responselistener8 =new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                            try {
                                                JSONObject jsonresponse =new JSONObject(response);


                                                JSONArray arr =jsonresponse.getJSONArray("success");





                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }


                                        }
                                    };
                                    evaluate login=new evaluate(usern,a,hist.get(k).toString(),responselistener8);
                                    RequestQueue queue = Volley.newRequestQueue(TicketHistory.this);
                                    queue.add(login);

                                    Intent historyintent = new Intent(TicketHistory.this, TicketHistory.class);
                                    historyintent.putExtra("username",usern);
                                    historyintent.putExtra("name",nameuser);
                                    historyintent.putExtra("age",userage);
                                    finish();
                                    TicketHistory.this.startActivity(historyintent);

                                }
                            });


                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();
            }




        });





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
                        Intent profileIntent = new Intent(TicketHistory.this, UserProfile.class);
                        profileIntent.putExtra("username",usern);
                        profileIntent.putExtra("name",nameuser);
                        profileIntent.putExtra("age",userage);
                        finish();
                        TicketHistory.this.startActivity(profileIntent);
                        return true;
                    case R.id.item2:
                        Intent walletintent = new Intent(TicketHistory.this, wallet.class);
                        walletintent.putExtra("username",usern);
                        walletintent.putExtra("name",nameuser);
                        walletintent.putExtra("age",userage);
                        finish();
                        TicketHistory.this.startActivity(walletintent);
                        return true;
                    case R.id.item3:
                        Intent historyintent = new Intent(TicketHistory.this, TicketHistory.class);
                        historyintent.putExtra("username",usern);
                        historyintent.putExtra("name",nameuser);
                        historyintent.putExtra("age",userage);
                        finish();
                        TicketHistory.this.startActivity(historyintent);
                        // Handle logout click
                        return true;
                    case R.id.itembuy:
                        Intent buyintent =new Intent (TicketHistory.this,SelectCity.class);
                        buyintent.putExtra("username",usern);
                        buyintent.putExtra("name",nameuser);
                        buyintent.putExtra("age",userage);
                        finish();
                        TicketHistory.this.startActivity(buyintent);
                        return true;
                    case R.id.item5:
                        AlertDialog.Builder builder = new AlertDialog.Builder(TicketHistory.this);
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