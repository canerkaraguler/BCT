package com.example.caner.bct;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caner on 01.06.2017.
 */

public class deleteCard extends StringRequest {
    private static final String Register_Url="http://54.186.173.241/deletecard.php";
    private Map<String,String> params;

    public deleteCard(String cardno,String username, Response.Listener<String> listener){
        super(Request.Method.POST,Register_Url,listener,null);
        params =new HashMap<>();

        params.put("cardNo",cardno);
        params.put("userName",username);



    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

