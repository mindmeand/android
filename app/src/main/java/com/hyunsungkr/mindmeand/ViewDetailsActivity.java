package com.hyunsungkr.mindmeand;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunsungkr.mindmeand.model.UserHistory;

public class ViewDetailsActivity extends AppCompatActivity {

    TextView txtQuestion;
    TextView txtAnswer;
    ImageView imgBack;

    UserHistory userHistory;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        txtQuestion = findViewById(R.id.txtQuestion);
        txtAnswer = findViewById(R.id.txtAnswer);
        imgBack = findViewById(R.id.imgBack);

        userHistory = (UserHistory) getIntent().getSerializableExtra("userHistory");

        txtQuestion.setText(userHistory.getQuestion());
        txtAnswer.setText(userHistory.getAnswer());

        // 뒤로가기 처리
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }
}