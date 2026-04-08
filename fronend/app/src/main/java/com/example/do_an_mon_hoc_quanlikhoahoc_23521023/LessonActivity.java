package com.example.do_an_mon_hoc_quanlikhoahoc_23521023;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class LessonActivity extends AppCompatActivity {

    TextView txtLessonTitle, txtObjective, txtContent, txtExample;
    ImageView imgExample;
    ImageButton btnBack, btnNext;

    int currentIndex;
    List<Lesson> lessonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        // ánh xạ view
        txtLessonTitle = findViewById(R.id.txtLessonTitle);
        txtObjective = findViewById(R.id.txtObjective);
        txtContent = findViewById(R.id.txtContent);
        txtExample = findViewById(R.id.txtExample);
        imgExample = findViewById(R.id.imgExample);

        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);

        // dữ liệu
        lessonList = LessonData.getAllLessons();

        currentIndex = getIntent().getIntExtra("index", 0);

        // tránh crash nếu index sai
        if (currentIndex < 0 || currentIndex >= lessonList.size()) {
            currentIndex = 0;
        }

        loadLesson();

        // NEXT
        btnNext.setOnClickListener(v -> {
            if (currentIndex < lessonList.size() - 1) {
                currentIndex++;
                loadLesson();
            }
        });

        // BACK
        btnBack.setOnClickListener(v -> {
            if (currentIndex == 0) {
                finish();
            } else {
                currentIndex--;
                loadLesson();
            }
        });
    }

    void loadLesson() {

        Lesson lesson = lessonList.get(currentIndex);

        // tiêu đề + progress
        txtLessonTitle.setText(
                (currentIndex + 1) + "/" + lessonList.size() + " - "
                        + lesson.getLesson() + ": " + lesson.getTitle()
        );

        txtObjective.setText(lesson.getObjective());
        txtContent.setText(lesson.getContent());
        txtExample.setText(lesson.getExample());

        imgExample.setImageResource(lesson.getImage());

        // animation nhẹ
        imgExample.setAlpha(0f);
        imgExample.animate().alpha(1f).setDuration(300);

        // BACK button
        if (currentIndex == 0) {
            btnBack.setVisibility(View.INVISIBLE);
        } else {
            btnBack.setVisibility(View.VISIBLE);
        }

        // NEXT button
        if (currentIndex == lessonList.size() - 1) {
            btnNext.setVisibility(View.INVISIBLE);
        } else {
            btnNext.setVisibility(View.VISIBLE);
        }
    }
}