package ru.dvteam.itcollabhub;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.zip.Inflater;

public class Rating extends Fragment {
    private int max, min;
    private int selectedColor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rating, container, false);
        TextView percents = v.findViewById(R.id.percents);
        ProgressBar lvl = v.findViewById(R.id.lvl);
        TextView statusView = v.findViewById(R.id.status);
        TextView nextScore = v.findViewById(R.id.nextScore);
        TextView nextStatus = v.findViewById(R.id.nextstatus);

        int score = getArguments().getInt("score");
        String status = getArguments().getString("status");

        if(score < 100){
            selectedColor = Color.parseColor("#B20000FF");
            nextScore.setTextColor(selectedColor);
            nextStatus.setTextColor(selectedColor);
            Drawable progressDrawable = getResources().getDrawable(R.drawable.circular_progress_bar_green);
            lvl.setBackgroundResource(R.drawable.progress_circle_blue);
            lvl.setProgressDrawable(progressDrawable);
            max = 100;
            min = 0;
        }
        else if(score < 300){
            selectedColor = Color.parseColor("#FFCC7722");
            nextScore.setTextColor(selectedColor);
            nextStatus.setTextColor(selectedColor);
            Drawable progressDrawable = getResources().getDrawable(R.drawable.circular_progress_bar_brown);
            lvl.setBackgroundResource(R.drawable.progress_circle_green);
            lvl.setProgressDrawable(progressDrawable);
            max = 300;
            min = 100;
        }
        else if(score < 1000){
            selectedColor = Color.parseColor("#B2B5B5B5");
            nextScore.setTextColor(selectedColor);
            nextStatus.setTextColor(selectedColor);
            Drawable progressDrawable = getResources().getDrawable(R.drawable.circular_progress_bar_light_gray);
            lvl.setBackgroundResource(R.drawable.progress_circle_brown);
            lvl.setProgressDrawable(progressDrawable);
            max = 1000;
            min = 300;
        }
        else if(score < 2500){
            selectedColor = Color.parseColor("#FFE8AA0E");
            nextScore.setTextColor(selectedColor);
            nextStatus.setTextColor(selectedColor);
            Drawable progressDrawable = getResources().getDrawable(R.drawable.circular_progress_bar_ohra);
            lvl.setBackgroundResource(R.drawable.progress_circle_light_gray);
            lvl.setProgressDrawable(progressDrawable);
            max = 2500;
            min = 1000;
        }
        else if(score < 7000){
            selectedColor = Color.parseColor("#FF0000");
            nextScore.setTextColor(selectedColor);
            nextStatus.setTextColor(selectedColor);
            Drawable progressDrawable = getResources().getDrawable(R.drawable.circular_progress_bar_red);
            lvl.setBackgroundResource(R.drawable.progress_circle_ohra);
            lvl.setProgressDrawable(progressDrawable);
            max = 7000;
            min = 1000;
        }
        else if(score < 17000){
            selectedColor = Color.parseColor("#FFCC7722");
            nextScore.setTextColor(selectedColor);
            nextStatus.setTextColor(selectedColor);
            Drawable progressDrawable = getResources().getDrawable(R.drawable.circular_progress_bar_orange);
            lvl.setBackgroundResource(R.drawable.progress_circle_red);
            lvl.setProgressDrawable(progressDrawable);
            max = 17000;
            min = 7000;
        }
        else if(score < 30000){
            selectedColor = Color.parseColor("#4F0070");
            nextScore.setTextColor(selectedColor);
            nextStatus.setTextColor(selectedColor);
            Drawable progressDrawable = getResources().getDrawable(R.drawable.circular_progress_bar_violete);
            lvl.setBackgroundResource(R.drawable.progress_circle_orange);
            lvl.setProgressDrawable(progressDrawable);
            max = 30000;
            min = 17000;
        }
        else if(score < 50000){
            selectedColor = Color.parseColor("#FF00C6A2");
            nextScore.setTextColor(selectedColor);
            nextStatus.setTextColor(selectedColor);
            Drawable progressDrawable = getResources().getDrawable(R.drawable.circular_progress_bar_blue_green);
            lvl.setBackgroundResource(R.drawable.progress_circle_violete);
            lvl.setProgressDrawable(progressDrawable);
            max = 50000;
            min = 30000;
        }
        else{
            selectedColor = Color.parseColor("#FF00C6A2");
            nextScore.setTextColor(selectedColor);
            nextStatus.setTextColor(selectedColor);
            Drawable progressDrawable = getResources().getDrawable(R.drawable.circular_progress_bar_blue_green);
            lvl.setBackgroundResource(R.drawable.progress_circle_blue_green);
            lvl.setProgressDrawable(progressDrawable);
            max = 50000;
            min = 0;
        }
        String res = "";
        String chast = "До следующей цели: " + (max - score);
        float med = (float)(score - min)/(float)(max - min);
        if(med <= 0.99) {
            res = new DecimalFormat("#0.0%").format(med);
        }
        else {res = "99.9%";}
        statusView.setText(status);
        percents.setText(res);
        nextScore.setText(chast);

        lvl.setMax(max - min);
        lvl.setProgress(score - min);
        return v;
    }
}