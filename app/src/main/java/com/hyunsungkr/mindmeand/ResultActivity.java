package com.hyunsungkr.mindmeand;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyunsungkr.mindmeand.model.Consultation;

public class ResultActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtquestion;
    TextView txtanswer;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 화면 연결
        txtName = findViewById(R.id.txtName);
        txtquestion = findViewById(R.id.txtquestion);
        txtanswer = findViewById(R.id.txtanswer);
        imgBack = findViewById(R.id.imgBack);

        // 데이터 받아오기
        Consultation consultation = (Consultation) getIntent().getSerializableExtra("consultation");
        String name = getIntent().getStringExtra("name");

        // 셋팅
        txtName.setText(name);
        txtquestion.setText(consultation.getQuestion());
        txtanswer.setText(consultation.getAnswer());

        // X버튼 이벤트
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 뒤로가기
                onBackPressed();
            }
        });


    }
}