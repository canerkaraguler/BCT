package com.example.caner.bct;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caner on 24.05.2017.
 */

public class getmovie extends StringRequest {
    private static final String Login_Url="http://54.186.173.241/getmovie.php";
    private Map<String,String> params;

    public getmovie( String cinemaId,  Response.Listener<String> listener){
        super(Request.Method.POST,Login_Url,listener,null);
        params =new HashMap<>();

        params.put("cinemaId",cinemaId);




    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}