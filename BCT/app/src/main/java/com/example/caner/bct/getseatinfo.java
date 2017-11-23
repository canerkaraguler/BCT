package com.example.caner.bct;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caner on 03.06.2017.
 */

public class getseatinfo extends StringRequest {
    private static final String Login_Url="http://54.186.173.241/getseats.php";
    private Map<String,String> params;

    public getseatinfo( String cid,String sid, Response.Listener<String> listener){
        super(Request.Method.POST,Login_Url,listener,null);
        params =new HashMap<>();

        params.put("cid",cid);
        params.put("sid",sid);



        // params.put("Address",address);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

