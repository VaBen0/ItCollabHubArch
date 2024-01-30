package ru.dvteam.itcollabhub;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ParticipantEditProject extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_participant_edit_project, container, false);

        Button add = v.findViewById(R.id.textView2);

        EditProject editProject = (EditProject) getActivity();
        assert editProject != null;
        int score = editProject.getScore();

        if(score < 100){
            add.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.blue));
        }
        else if(score < 300){
            add.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.green));
        }
        else if(score < 1000){
            add.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.brown));
        }
        else if(score < 2500){
            add.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.light_gray));
        }
        else if(score < 7000){
            add.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.ohra));
        }
        else if(score < 17000){
            add.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.red));
        }
        else if(score < 30000){
            add.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.orange));
        }
        else if(score < 50000){
            add.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.violete));
        }
        else{
            add.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), R.color.main_green));
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddParticipant.class);
                startActivity(intent);
            }
        });
        return v;
    }
}