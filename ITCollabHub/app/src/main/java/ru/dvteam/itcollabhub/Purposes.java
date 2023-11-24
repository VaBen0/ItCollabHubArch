package ru.dvteam.itcollabhub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Purposes extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_purposes, container, false);

        ImageView addPurp = v.findViewById(R.id.add_but);
        EditText purp1 = v.findViewById(R.id.name_friend);
        EditText purp2 = v.findViewById(R.id.name_friend2);

        addPurp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String purpName = purp1.getText().toString();
                String purp = purp2.getText().toString();
                CreateProject createProject = (CreateProject) getActivity();
                createProject.setPurp(purpName, purp);
            }
        });

        return v;
    }
}