package com.hyunsungkr.mindmeand;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hyunsungkr.mindmeand.api.NetworkClient;
import com.hyunsungkr.mindmeand.api.UserApi;
import com.hyunsungkr.mindmeand.config.Config;
import com.hyunsungkr.mindmeand.model.Res;
import com.hyunsungkr.mindmeand.model.User;
import com.hyunsungkr.mindmeand.model.UserMyInfoList;
import com.hyunsungkr.mindmeand.model.UserRes;

import java.util.ArrayList;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangeMyInfoActivity extends AppCompatActivity {

    ImageView imgBack;
    EditText editEmail;
    EditText editName;
    EditText editBirthday;
    Button btnSave;

    String accessToken;

    String email;
    String birthday;
    String name;
    ArrayList<User> mypageList = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_my_info);

        imgBack = findViewById(R.id.imgBack);
        editEmail = findViewById(R.id.editEmail);
        editName = findViewById(R.id.editName);
        editBirthday = findViewById(R.id.editBirthday);
        btnSave = findViewById(R.id.btnSave);

        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);
        accessToken = "Bearer " + sp.getString(Config.ACCESS_TOKEN, "");


        // 뒤로가기 클릭 리스너
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        // 수정버튼 클릭 리스너
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editEmail.getText().toString().trim();
                name = editName.getText().toString().trim();
                birthday = editBirthday.getText().toString().trim();

                Pattern pattern = Patterns.EMAIL_ADDRESS;
                if (pattern.matcher(email).matches() == false) {
                    Toast.makeText(ChangeMyInfoActivity.this, "이메일 형식이 올바르지 않습니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                Retrofit retrofit = NetworkClient.getRetrofitClient(ChangeMyInfoActivity.this);
                UserApi api = retrofit.create(UserApi.class);

                User user = new User(name, birthday,email);

                Call<Res> call = api.infoChange(accessToken,user);
                call.enqueue(new Callback<Res>() {
                    @Override
                    public void onResponse(Call<Res> call, Response<Res> response) {

                        if (response.isSuccessful()){
                            Toast.makeText(ChangeMyInfoActivity.this, "정상 수정되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();




                        }
                    }

                    @Override
                    public void onFailure(Call<Res> call, Throwable t) {

                        Toast.makeText(ChangeMyInfoActivity.this, "정상적으로 처리되지 않았습니다", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });



            }
        });

        getNetworkData();
    }
    void getNetworkData(){



        Retrofit retrofit = NetworkClient.getRetrofitClient(ChangeMyInfoActivity.this);
        UserApi api = retrofit.create(UserApi.class);

        // 헤더에 들어갈 억세스토큰 가져오기

        Call<UserMyInfoList> call = api.getMyinfo(accessToken);
        call.enqueue(new Callback<UserMyInfoList>() {
            @Override
            public void onResponse(Call<UserMyInfoList> call, Response<UserMyInfoList> response) {
                if(response.isSuccessful()){



                    // add the retrieved items to the list
                    mypageList.add(response.body().getUser());


                    editBirthday.setText(mypageList.get(0).getBirthDate());
                    editEmail.setText(mypageList.get(0).getEmail());
                    editName.setText(mypageList.get(0).getName());





                }
            }

            @Override
            public void onFailure(Call<UserMyInfoList> call, Throwable t) {

            }
        });
    }

}