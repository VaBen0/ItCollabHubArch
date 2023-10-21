package ru.dvteam.itcollabhub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class Registration extends Fragment {

    private EditText name_text;
    private Button button_super;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_registration, container, false);

        name_text = inflatedView.findViewById(R.id.nameu);
        EditText mail_text = inflatedView.findViewById(R.id.mailu);
        EditText pass_text = inflatedView.findViewById(R.id.passu);
        EditText passAgain_text = inflatedView.findViewById(R.id.passuagain);
        Button button_super = inflatedView.findViewById(R.id.Reg);

        Fragment fragment = new Fragment();

        Bundle bundle = new Bundle();
        bundle.putString("User_Name", name_text.getText().toString());
        bundle.putString("User_Mail", mail_text.getText().toString());
        bundle.putString("User_Pass", pass_text.getText().toString());
        fragment.setArguments(bundle);

        button_super.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passAgain_text.getText().toString().equals(pass_text.getText().toString())) {
                    postData(mail_text.getText().toString());
                    if (name_text.getText().toString().equals("Код отправлен")) {
                        Navigation.findNavController(v).navigate(R.id.confirm_code2);
                    }
                } else {
                    passAgain_text.setText(null);
                    passAgain_text.setHint("Пароли не совпадают");
                    return;
                }
            }
        });

        return inflatedView;
    }

    public void postData(String mail) {
        button_super.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://serveritcollabhub.development-team.ru/", new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        name_text.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    public Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();

                        map.put("Request", "PostToNewUserCode");
                        map.put("UserMail", mail);

                        return map;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }
}

