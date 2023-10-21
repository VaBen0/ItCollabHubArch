package ru.dvteam.itcollabhub;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class confirm_code extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String User_Mail = null;
        String User_Name = null;
        String User_Pass = null;

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            User_Mail = bundle.getString("User_Mail", "Default");
            User_Name = bundle.getString("User_Name", "Default");
            User_Pass = bundle.getString("User_Pass", "Default");
        }

        View inflatedView = inflater.inflate(R.layout.fragment_confirm_code, container, false);

        Button conf = inflatedView.findViewById(R.id.confirmBut);
        EditText User_code = inflatedView.findViewById(R.id.code);

        User_code.setText(User_Mail);

        String finalUser_Mail = User_Mail;
        String finalUser_Name = User_Name;
        String finalUser_Pass = User_Pass;

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res = Send_Post(finalUser_Mail, null, null, null, "CheckerCode");
                if(res.equals("Проверка почты прошла успешно")){
                    res = Send_Post(finalUser_Mail, User_code.getText().toString(), finalUser_Name, finalUser_Pass, "RegNewUser");
                    if(res.equals("Успешная регистрация")) {
                        Navigation.findNavController(v).navigate(R.id.confirm_code2);
                    }
                }
                else{
                    User_code.setText("Код неверный");
                    return;
                }
            }
        });

        return inflater.inflate(R.layout.fragment_confirm_code, container, false);
    }

    public String Send_Post(String mail_text, String code, String name, String pass, String req){
        final String[] res = new String[1];
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://serveritcollabhub.development-team.ru/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                res[0] = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("Request", req);
                map.put("UserMail", mail_text);
                map.put("UserCode", code);
                map.put("UserName", name);
                map.put("UserPass", pass);

                return map;
            }
        };
        requestQueue.add(stringRequest);
        return res[0];
    }
}