package ru.dvteam.itcollabhub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Purposes extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_purposes, container, false);

        ImageView addPurp = v.findViewById(R.id.add_but);
        EditText purp1 = v.findViewById(R.id.name_friend);
        EditText purp2 = v.findViewById(R.id.name_friend2);

        CreateProject createProject = (CreateProject) getActivity();
        int score = createProject.getScore();

        if(score < 100){
            addPurp.setBackgroundResource(R.drawable.ad);
        }
        else if(score < 300){
            addPurp.setBackgroundResource(R.drawable.green_add);
        }
        else if(score < 1000){
            addPurp.setBackgroundResource(R.drawable.brown_add);
        }
        else if(score < 2500){
            addPurp.setBackgroundResource(R.drawable.light_gray_add);
        }
        else if(score < 7000){
            addPurp.setBackgroundResource(R.drawable.ohra_add);
        }
        else if(score < 17000){
            addPurp.setBackgroundResource(R.drawable.red_add);
        }
        else if(score < 30000){
            addPurp.setBackgroundResource(R.drawable.brown_add);
        }
        else if(score < 50000){
            addPurp.setBackgroundResource(R.drawable.violete_add);
        }
        else{
            addPurp.setBackgroundResource(R.drawable.blue_green_add);
        }

        addPurp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String purpName = purp1.getText().toString();
                String purp = purp2.getText().toString();
                if(purpName.isEmpty()){
                    Toast.makeText(createProject, "Нет названия", Toast.LENGTH_SHORT).show();
                }
                else if(purp.isEmpty()){
                    Toast.makeText(createProject, "Нет описания", Toast.LENGTH_SHORT).show();
                }
                else {
                    CreateProject createProject = (CreateProject) getActivity();
                    purp1.setText("");
                    purp2.setText("");
                    Toast.makeText(v.getContext(), "Цель добавлена", Toast.LENGTH_SHORT).show();
                    createProject.setPurp(purpName, purp);
                }
            }
        });

        return v;
    }
}