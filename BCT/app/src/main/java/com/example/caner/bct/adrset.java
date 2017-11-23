package com.example.caner.bct;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caner on 22.05.2017.
 */

public class adrset extends StringRequest{
    private static final String Register_Url="http://54.186.173.241/editadr.php";
    private Map<String,String> params;

    public adrset(String username, String address, Response.Listener<String> listener){
        super(Request.Method.POST,Register_Url,listener,null);
        params =new HashMap<>();

        params.put("username",username);
        params.put("Address",address);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


