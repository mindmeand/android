package com.hyunsungkr.mindmeand;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hyunsungkr.mindmeand.api.NetworkClient;
import com.hyunsungkr.mindmeand.api.UserApi;
import com.hyunsungkr.mindmeand.config.Config;
import com.hyunsungkr.mindmeand.model.User;
import com.hyunsungkr.mindmeand.model.UserRes;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    ImageView imgBack;
    EditText editName;
    EditText editEmail;
    EditText editBirthday;
    EditText editPassword;
    EditText editPasswordCk;
    Button btnRegister;

    String name;

    String email = "";
    String password;
    String passwordCk;
    String birthday;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        imgBack = findViewById(R.id.imgBack);
        editName = findViewById(R.id.editName);
        editBirthday = findViewById(R.id.editBirthday);
        editPassword = findViewById(R.id.editPassword);
        editPasswordCk = findViewById(R.id.editPasswordCk);
        btnRegister = findViewById(R.id.btnRegister);
        editEmail = findViewById(R.id.editEmail);


        // 백버튼 클릭 리스너
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        // 가입버튼 클릭 리스너
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editName.getText().toString().trim();
                email = editEmail.getText().toString().trim();
                password = editPassword.getText().toString().trim();
                passwordCk = editPasswordCk.getText().toString().trim();
                birthday = editBirthday.getText().toString().trim();

                Pattern pattern = Patterns.EMAIL_ADDRESS;
                if (pattern.matcher(name).matches() == false) {
                    Toast.makeText(RegisterActivity.this, "이메일 형식이 올바르지 않습니다", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (password.length() < 5 || password.length() > 20) {
                    Toast.makeText(RegisterActivity.this, "비밀번호 길이를 확인하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password != passwordCk){

                    Toast.makeText(RegisterActivity.this, "비밀번호와 비밀번호확인이 다릅니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                register();





            }
        });



    }

    public void register(){

        Retrofit retrofit = NetworkClient.getRetrofitClient(RegisterActivity.this);
        UserApi api = retrofit.create(UserApi.class);

        User user = new User(name,birthday,email,password);

        Call<UserRes> call = api.register(user);

        call.enqueue(new Callback<UserRes>() {
            @Override
            public void onResponse(Call<UserRes> call, Response<UserRes> response) {

                if(response.isSuccessful()){


                    UserRes res = response.body();


                    // 억세스토큰은 api 호출할때마다 헤더에서 사용하므로
                    // 회원가입이나 로그인이 끝나면 파일로 꼭 저장해놔야한다
                    SharedPreferences sp = getApplication().getSharedPreferences(Config.PREFERENCE_NAME,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(Config.ACCESS_TOKEN, res.getAccess_token());
                    editor.apply();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(RegisterActivity.this, "정상적으로 처리되지 않았습니다", Toast.LENGTH_SHORT).show();
                    return;
                }


            }

            @Override
            public void onFailure(Call<UserRes> call, Throwable t) {

                Toast.makeText(RegisterActivity.this, "정상적으로 처리되지 않았습니다", Toast.LENGTH_SHORT).show();
                return;
            }
        });

    }


}