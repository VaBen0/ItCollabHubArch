package ru.dvteam.itcollabhub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountLinks extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account_links, container, false);

        EditProfile editProfile = (EditProfile) getActivity();
        String mail = editProfile.getMail();

        EditText tg_link = v.findViewById(R.id.tg);
        EditText vk_link = v.findViewById(R.id.vk);
        EditText web_link = v.findViewById(R.id.web);
        Button save = v.findViewById(R.id.update);

        PostDatas post = new PostDatas();
        post.postDataGetLinks("GetAllLinks", mail, new CallBackInt4() {
            @Override
            public void invoke(String tg, String vk, String web) {
                if(tg.equals("null")){
                    tg_link.setHint("Ваш ник в Телеграмм");
                }else{
                    tg_link.setHint(tg);
                }
                if(vk.equals("null")){
                    vk_link.setHint("Ваш ник в Вк");
                }else{
                    vk_link.setHint(vk);
                }
                if(web.equals("null")){
                    web_link.setHint("Ссылка на ваш сайт");
                }else{
                    web_link.setHint(web);
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tg_link.getText().toString().isEmpty()){
                    PostDatas post = new PostDatas();
                    post.postDataSendLink("SendUserLinkTg", mail, tg_link.getText().toString());
                }
                if(!vk_link.getText().toString().isEmpty()){
                    PostDatas post = new PostDatas();
                    post.postDataSendLink("SendUserLinkVk", mail, vk_link.getText().toString());
                }
                if(!web_link.getText().toString().isEmpty()){
                    PostDatas post = new PostDatas();
                    post.postDataSendLink("SendUserLinkWeb", mail, web_link.getText().toString());
                }
            }
        });

        return v;
    }

}