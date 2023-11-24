package ru.dvteam.itcollabhub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class Tasks extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tasks, container, false);

        ImageView addTask = v.findViewById(R.id.add_but);
        EditText task1 = v.findViewById(R.id.name_friend);
        EditText task2 = v.findViewById(R.id.name_friend2);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String purpName = task1.getText().toString();
                String purp = task2.getText().toString();
                CreateProject createProject = (CreateProject) getActivity();
                createProject.setTask(purpName, purp);
            }
        });

        return v;
    }
}