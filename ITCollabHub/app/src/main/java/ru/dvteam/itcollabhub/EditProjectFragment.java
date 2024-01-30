package ru.dvteam.itcollabhub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProjectFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_project, container, false);

        EditText prDesc = v.findViewById(R.id.projectDescription);
        EditText tg = v.findViewById(R.id.tg);
        EditText vk = v.findViewById(R.id.vk);
        EditText web = v.findViewById(R.id.web);
        Button btn = v.findViewById(R.id.button2);

        EditProject editProject = (EditProject) getActivity();
        assert editProject != null;
        String description = editProject.getDescription();
        //Toast.makeText(editProject, description, Toast.LENGTH_SHORT).show();
        prDesc.setHint(description);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prDesc.clearFocus();
                tg.clearFocus();
                vk.clearFocus();
                web.clearFocus();
                editProject.confirm();
            }
        });

        return v;
    }
}