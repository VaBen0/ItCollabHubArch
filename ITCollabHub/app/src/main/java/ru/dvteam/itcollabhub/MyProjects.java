package ru.dvteam.itcollabhub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MyProjects extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_projects, container, false);

        LinearLayout main = v.findViewById(R.id.main_layout);

        for (int i = 0; i < 5; i++) {
            View custom = inflater.inflate(R.layout.project_window, null);

            main.addView(custom);
        }
        View empty = inflater.inflate(R.layout.emty_obj, null);
        main.addView(empty);

        Toast.makeText(v.getContext(), "lol", Toast.LENGTH_SHORT).show();

        return v;
    }
}