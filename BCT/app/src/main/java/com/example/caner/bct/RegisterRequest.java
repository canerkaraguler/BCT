package com.example.caner.bct;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caner on 16.05.2017.
 */

public class RegisterRequest extends StringRequest {
    private static final String Register_Url="http://54.186.173.241/Register.php";
    private Map<String,String>params;

    public RegisterRequest(String name, String username, int age, String email, String password, String is_admin ,String photo_default, String photo_name, Response.Listener<String> listener){
        super(Method.POST,Register_Url,listener,null);
        params =new HashMap<>();

        params.put("username",username);
        params.put("email",email);
        params.put("password",password);
        params.put("name",name);
        params.put("age",age+"");
        params.put("is_admin",is_admin);
        params.put("photo_default",photo_default);
        params.put("photo_name",photo_name);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
