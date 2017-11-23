package com.example.caner.bct;

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
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

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
import java.io.InterruptedIOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SelectSeat extends AppCompatActivity {



        private SeatBttn sb;

        private CircleImageView navimg;
        private CircleImageView photo;
        private DrawerLayout mdrawerLayout;
        private ActionBarDrawerToggle nToggle;
        private Toolbar mtoolbar;
        private Button btn;
        private ToggleButton s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,s31,s32,s33,s34,s35,s36,s37,s38,s39,s40,s41,s42,s43,s44,s45,s46,s47,s48,s49,s50,s51,s52,s53,s54,s55,s56,s57,s58;

        private ImageButton cambtn;
        private String encoded_string, image_name,cid,sid;
        private Bitmap bitmap;
        private TextView navname,selectedseats;

        private File file;
        private Uri file_uri;
        private  String usern,nameuser,mname;
        private int userage;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_select_seat);

            final Button bLogin1 =(Button) findViewById(R.id.bnext);





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
            cid=gintent.getStringExtra("cid");
            mname=gintent.getStringExtra("movieName");
            sid=gintent.getStringExtra("sid");
            userage=gintent.getIntExtra("age",-1);
            final ArrayList<String> ss =new ArrayList<>();
           final ArrayList<ToggleButton> arr =new ArrayList<>();

            s1=(ToggleButton) findViewById(R.id.s1); arr.add(s1);
            s2=(ToggleButton) findViewById(R.id.s2); arr.add(s2);
            s3=(ToggleButton) findViewById(R.id.s3); arr.add(s3);
            s4=(ToggleButton) findViewById(R.id.s4); arr.add(s4);
            s5=(ToggleButton) findViewById(R.id.s5); arr.add(s5);
            s6=(ToggleButton) findViewById(R.id.s6); arr.add(s6);
            s7=(ToggleButton) findViewById(R.id.s7); arr.add(s7);
            s8=(ToggleButton) findViewById(R.id.s8); arr.add(s8);
            s9=(ToggleButton) findViewById(R.id.s9); arr.add(s9);
            s10=(ToggleButton) findViewById(R.id.s10); arr.add(s10);
            s11=(ToggleButton) findViewById(R.id.s11); arr.add(s11);
            s12=(ToggleButton) findViewById(R.id.s12); arr.add(s12);
            s13=(ToggleButton) findViewById(R.id.s13); arr.add(s13);
            s14=(ToggleButton) findViewById(R.id.s14); arr.add(s14);
            s15=(ToggleButton) findViewById(R.id.s15); arr.add(s15);
            s16=(ToggleButton) findViewById(R.id.s16); arr.add(s16);
            s17=(ToggleButton) findViewById(R.id.s17); arr.add(s17);
            s18=(ToggleButton) findViewById(R.id.s18); arr.add(s18);
            s19=(ToggleButton) findViewById(R.id.s19); arr.add(s19);
            s20=(ToggleButton) findViewById(R.id.s20); arr.add(s20);
            s21=(ToggleButton) findViewById(R.id.s21); arr.add(s21);
            s22=(ToggleButton) findViewById(R.id.s22); arr.add(s22);
            s23=(ToggleButton) findViewById(R.id.s23); arr.add(s23);
            s24=(ToggleButton) findViewById(R.id.s24); arr.add(s24);
            s25=(ToggleButton) findViewById(R.id.s25); arr.add(s25);
            s26=(ToggleButton) findViewById(R.id.s26); arr.add(s26);
            s27=(ToggleButton) findViewById(R.id.s27); arr.add(s27);
            s28=(ToggleButton) findViewById(R.id.s28); arr.add(s28);
            s29=(ToggleButton) findViewById(R.id.s29); arr.add(s29);
            s30=(ToggleButton) findViewById(R.id.s30); arr.add(s30);
            s31=(ToggleButton) findViewById(R.id.s31); arr.add(s31);
            s32=(ToggleButton) findViewById(R.id.s32); arr.add(s32);
            s33=(ToggleButton) findViewById(R.id.s33); arr.add(s33);
            s34=(ToggleButton) findViewById(R.id.s34); arr.add(s34);
            s35=(ToggleButton) findViewById(R.id.s35); arr.add(s35);
            s36=(ToggleButton) findViewById(R.id.s36); arr.add(s36);
            s37=(ToggleButton) findViewById(R.id.s37); arr.add(s37);
            s38=(ToggleButton) findViewById(R.id.s38); arr.add(s38);
            s39=(ToggleButton) findViewById(R.id.s39); arr.add(s39);
            s40=(ToggleButton) findViewById(R.id.s40); arr.add(s40);
            s41=(ToggleButton) findViewById(R.id.s41); arr.add(s41);
            s42=(ToggleButton) findViewById(R.id.s42); arr.add(s42);
            s43=(ToggleButton) findViewById(R.id.s43); arr.add(s43);
            s44=(ToggleButton) findViewById(R.id.s44); arr.add(s44);
            s45=(ToggleButton) findViewById(R.id.s45); arr.add(s45);
            s46=(ToggleButton) findViewById(R.id.s46); arr.add(s46);
            s47=(ToggleButton) findViewById(R.id.s47); arr.add(s47);
            s48=(ToggleButton) findViewById(R.id.s48); arr.add(s48);
            s49=(ToggleButton) findViewById(R.id.s49); arr.add(s49);
            s50=(ToggleButton) findViewById(R.id.s50); arr.add(s50);
            s51=(ToggleButton) findViewById(R.id.s51); arr.add(s51);
            s52=(ToggleButton) findViewById(R.id.s52); arr.add(s52);
            s53=(ToggleButton) findViewById(R.id.s53); arr.add(s53);
            s54=(ToggleButton) findViewById(R.id.s54); arr.add(s54);
            s55=(ToggleButton) findViewById(R.id.s55); arr.add(s55);
            s56=(ToggleButton) findViewById(R.id.s56); arr.add(s56);
            s57=(ToggleButton) findViewById(R.id.s57); arr.add(s57);
            s58=(ToggleButton) findViewById(R.id.s58); arr.add(s58);


           selectedseats=(TextView) findViewById(R.id.tvselectedseats);





            for( int i=0;i<arr.size();i++){
               if (i>=0 && i<=11 ) {
                   final int kl =i;

                   arr.get(i).setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           String s;

                           s = "A-" + (kl + 1)+"/ ";

                        if(arr.get(kl).isChecked()) {

                            ss.add(s);
                        }else{
                            ss.remove(s);
                            ss.add("");
                        }

                       }
                   });

               }else if(i>=12 && i<=21){
                   final int kl =i;

                   arr.get(i).setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           String s;

                           s = "B-" + (kl-11)+"/ ";

                           if(arr.get(kl).isChecked()) {

                               ss.add(s);
                           }else{
                               ss.remove(s);
                               ss.add("");
                           }

                       }
                   });
               }else if(i>=22 && i<=30){
                   final int kl =i;

                   arr.get(i).setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           String s;

                           s = "C-" + (kl-21)+"/ ";

                           if(arr.get(kl).isChecked()) {

                               ss.add(s);
                           }else{
                               ss.remove(s);
                               ss.add("");
                           }

                       }
                   });

               }else if(i>=31 && i<=39){
                   final int kl =i;

                   arr.get(i).setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           String s;

                           s = "D-" + (kl-30)+"/ ";

                           if(arr.get(kl).isChecked()) {

                               ss.add(s);
                           }else{
                               ss.remove(s);
                               ss.add("");
                           }

                       }
                   });

               }else if(i>=40 && i<=48){
                   final int kl =i;

                   arr.get(i).setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           String s;

                           s = "E-" + (kl-39)+"/ ";

                           if(arr.get(kl).isChecked()) {

                               ss.add(s);
                           }else{
                               ss.remove(s);
                               ss.add("");
                           }

                       }
                   });

               }else if(i>=49 && i<=57){
                   final int kl =i;

                   arr.get(i).setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           String s;

                           s = "F-" + (kl-48)+"/ ";

                           if(arr.get(kl).isChecked()) {

                               ss.add(s);
                           }else{
                               ss.remove(s);
                               ss.add("");
                           }

                       }
                   });

               }



            }


            final Thread t = new Thread(){
                @Override
                public void run() {
                    try {
                        while(!isInterrupted()){
                            Thread.sleep(500);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String x="Selected Seats :";
                                    for(int i=0;i<ss.size();i++){

                                            Log.d("notempty",ss.get(i));
                                            x = x +  ss.get(i) ;
                                            selectedseats.setText(x);



                                    }
                                }
                            });
                        }

                    }catch(Exception e){

                    }

                }

            };
            t.start();

            bLogin1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent profileIntent = new Intent(SelectSeat.this, PaymentActivity.class);
                    profileIntent.putExtra("username",usern);
                    profileIntent.putExtra("name",nameuser);
                    profileIntent.putExtra("age",userage);
                    profileIntent.putExtra("movieName",mname);
                    profileIntent.putExtra("seats",selectedseats.getText().toString());
                    finish();
                    t.interrupt();
                    SelectSeat.this.startActivity(profileIntent);
                }
            });





            final Response.Listener<String> responselistenercinema =new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonresponse =new JSONObject(response);


                        JSONArray arr1 =jsonresponse.getJSONArray("success");
                        int k =0;

                        for(int i =0; i<arr1.length();i++) {
                            JSONObject rec = arr1.getJSONObject(i);
                            String s = rec.toString();
                            String[] sarr = s.split("[!]+");

                            for (int j = 0; j < sarr.length; j++) {
                               if(j%2 !=0 ) {

                                   if (sarr[j].equals("empty")) {
                                       Log.d("asda", sarr[j]);
                                       arr.get(k).setBackgroundResource(R.drawable.select_button);
                                       k++;
                                   } else {
                                       Log.d("aqwerqwe", sarr[j]);
                                       arr.get(k).setBackgroundResource(R.drawable.select_button_full);
                                       arr.get(k).setEnabled(false);
                                       k++;
                                   }
                               }
                            }

                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            };
            getseatinfo logincinema=new getseatinfo(cid,sid,responselistenercinema);
            RequestQueue queuecinema = Volley.newRequestQueue(SelectSeat.this);
            queuecinema.add(logincinema);






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
                            Intent profileIntent = new Intent(SelectSeat.this, UserProfile.class);
                            profileIntent.putExtra("username",usern);
                            profileIntent.putExtra("name",nameuser);
                            profileIntent.putExtra("age",userage);
                            finish();
                            SelectSeat.this.startActivity(profileIntent);
                            return true;
                        case R.id.item2:
                            Intent walletintent = new Intent(SelectSeat.this, wallet.class);
                            walletintent.putExtra("username",usern);
                            walletintent.putExtra("name",nameuser);
                            walletintent.putExtra("age",userage);
                            finish();
                            SelectSeat.this.startActivity(walletintent);
                            return true;
                        case R.id.item3:
                            Intent historyintent = new Intent(SelectSeat.this, TicketHistory.class);
                            historyintent.putExtra("username",usern);
                            historyintent.putExtra("name",nameuser);
                            historyintent.putExtra("age",userage);
                            finish();
                            SelectSeat.this.startActivity(historyintent);
                            return true;
                        case R.id.itembuy:
                            Intent buyintent =new Intent (SelectSeat.this,SelectCity.class);
                            buyintent.putExtra("username",usern);
                            buyintent.putExtra("name",nameuser);
                            buyintent.putExtra("age",userage);
                            finish();
                            SelectSeat.this.startActivity(buyintent);

                            return true;
                        case R.id.item5:
                            AlertDialog.Builder builder = new AlertDialog.Builder(SelectSeat.this);
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



        public void setSeat(boolean x){
             sb.setSeatFull(x);
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




