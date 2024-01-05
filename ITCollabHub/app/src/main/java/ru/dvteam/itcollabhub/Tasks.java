package ru.dvteam.itcollabhub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Tasks extends Fragment {

    private int countTasks = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tasks, container, false);

        ImageView addTask = v.findViewById(R.id.add_but);
        EditText task1 = v.findViewById(R.id.name_friend);
        EditText task2 = v.findViewById(R.id.name_friend2);

        CreateProject createProject = (CreateProject) getActivity();
        int score = createProject.getScore();

        if(score < 100){
            addTask.setBackgroundResource(R.drawable.ad);
        }
        else if(score < 300){
            addTask.setBackgroundResource(R.drawable.green_add);
        }
        else if(score < 1000){
            addTask.setBackgroundResource(R.drawable.brown_add);
        }
        else if(score < 2500){
            addTask.setBackgroundResource(R.drawable.light_gray_add);
        }
        else if(score < 7000){
            addTask.setBackgroundResource(R.drawable.ohra_add);
        }
        else if(score < 17000){
            addTask.setBackgroundResource(R.drawable.red_add);
        }
        else if(score < 30000){
            addTask.setBackgroundResource(R.drawable.brown_add);
        }
        else if(score < 50000){
            addTask.setBackgroundResource(R.drawable.violete_add);
        }
        else{
            addTask.setBackgroundResource(R.drawable.blue_green_add);
        }

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = task1.getText().toString();
                String task = task2.getText().toString();
                if(countTasks == 30){
                    Toast.makeText(createProject, "Вы достигли предела", Toast.LENGTH_SHORT).show();
                }
                if(taskName.isEmpty()){
                    Toast.makeText(createProject, "Нет названия", Toast.LENGTH_SHORT).show();
                }
                else if(task.isEmpty()){
                    Toast.makeText(createProject, "Нет описания", Toast.LENGTH_SHORT).show();
                }
                else {
                    task1.setText("");
                    task2.setText("");
                    Toast.makeText(v.getContext(), "Задача добавлена", Toast.LENGTH_SHORT).show();
                    CreateProject createProject = (CreateProject) getActivity();
                    createProject.setTask(taskName, task);
                    countTasks++;
                }
            }
        });

        return v;
    }
}