package com.example.caner.bct;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caner on 22.05.2017.
 */

public class adrget extends StringRequest{
    private static final String Login_Url="http://54.186.173.241/editprofile.php";
    private Map<String,String> params;

    public adrget( String username, Response.Listener<String> listener){
        super(Request.Method.POST,Login_Url,listener,null);
        params =new HashMap<>();

        params.put("username",username);

       // params.put("Address",address);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

