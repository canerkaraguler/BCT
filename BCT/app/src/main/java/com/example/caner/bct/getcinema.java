package com.example.caner.bct;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caner on 23.05.2017.
 */

public class getcinema extends StringRequest{
    private static final String Login_Url="http://54.186.173.241/getcinemas.php";
    private Map<String,String> params;

    public getcinema( String name,  Response.Listener<String> listener){
        super(Request.Method.POST,Login_Url,listener,null);
        params =new HashMap<>();

        params.put("name",name);




    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

