package com.hyunsungkr.mindmeand;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hyunsungkr.mindmeand.api.ConsultationApi;
import com.hyunsungkr.mindmeand.api.NetworkClient;
import com.hyunsungkr.mindmeand.api.UserApi;
import com.hyunsungkr.mindmeand.config.Config;
import com.hyunsungkr.mindmeand.model.Consultation;
import com.hyunsungkr.mindmeand.model.Res;
import com.hyunsungkr.mindmeand.model.User;
import com.hyunsungkr.mindmeand.model.UserMyInfoList;
import com.hyunsungkr.mindmeand.model.UserRes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextView txtName;
    Spinner spinner;
    EditText editCounsel;
    Button btnCounsel;
    String select;
    int intSelect;
    String counsel;
    String accessToken;
    LinearLayout mainLayout;
    LinearLayout loadingLayout;
    ImageView imgPerson;
    TextView txtExplanation;
    TextView txtCounselorName;
    String name;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        // 화면 연결
        txtName = rootView.findViewById(R.id.txtName);
        spinner = rootView.findViewById(R.id.spinner);
        editCounsel = rootView.findViewById(R.id.editCounsel);
        btnCounsel = rootView.findViewById(R.id.btnCounsel);
        mainLayout = rootView.findViewById(R.id.mainLayout);
        loadingLayout = rootView.findViewById(R.id.loadingLayout);
        imgPerson = rootView.findViewById(R.id.imgPerson);
        txtExplanation = rootView.findViewById(R.id.txtExplanation);
        txtCounselorName = rootView.findViewById(R.id.txtCounselorName);

        // 토큰 가져오기
        SharedPreferences sp = getActivity().getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);
        accessToken = "Bearer " + sp.getString(Config.ACCESS_TOKEN, "");

        // 유저 정보 가져와서 이름 셋팅하기
        getUserInfo();

        // 스피너 설정
        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.cancel_reasons, android.R.layout.simple_spinner_item);
        spinner.setAdapter(yearAdapter);

        // 상담하기 클릭
        btnCounsel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinner == null || spinner.getSelectedItem().toString().equals("상담가 선택")) {
                    Toast.makeText(getContext(), "상담가를 선택해주세요", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    select = spinner.getSelectedItem().toString();

                    if (select.equals("유능하고 친절한 상담가 왕현성")) {
                        intSelect = 0;
                    } else if (select.equals("객관적이고 냉철한 상담가 백민우")) {
                        intSelect = 1;
                    } else if (select.equals("편안한 친구같은 상담가 윤지수"))
                        intSelect = 2;
                }

                // 상담 내용 가져오기
                counsel = editCounsel.getText().toString().trim();

                // 상담가와 상담 내용 전달할 API 호출
                setCounsel();

            }
        });

        return rootView;

    }

    private void getUserInfo() {
        Retrofit retrofit = NetworkClient.getRetrofitClient(getContext());
        UserApi api = retrofit.create(UserApi.class);

        Call<UserMyInfoList> call = api.userInfo(accessToken);
        call.enqueue(new Callback<UserMyInfoList>() {
            @Override
            public void onResponse(Call<UserMyInfoList> call, Response<UserMyInfoList> response) {

                if (response.isSuccessful()) {
                    // 완료시 실행할 코드
                    UserMyInfoList userMyInfoList = response.body();
                    User user = userMyInfoList.getUser();
                    name = user.getName();
                    txtName.setText(name);

                }
            }

            @Override
            public void onFailure(Call<UserMyInfoList> call, Throwable t) {
            }

        });

    }

    private void setCounsel() {
        // 메인 레이아웃 숨기고 로딩 레이아웃 보여주기
        mainLayout.setVisibility(View.GONE);

        // 상담가 프로필 셋팅
        // todo 이미지 셋팅
        if (intSelect == 0) {
            Glide.with(getActivity()).load(R.drawable.p0).into(imgPerson);
            txtExplanation.setText("유능하고 친절한 상담가");
            txtCounselorName.setText("왕현성");
        } else if (intSelect == 1) {
            Glide.with(getActivity()).load(R.drawable.p1).into(imgPerson);
            txtExplanation.setText("객관적이고 냉철한 상담가");
            txtCounselorName.setText("백민우");
        } else if (intSelect == 2) {
            Glide.with(getActivity()).load(R.drawable.p2).into(imgPerson);
            txtExplanation.setText("편안한 친구같은 상담가");
            txtCounselorName.setText("윤지수");
        }

        loadingLayout.setVisibility(View.VISIBLE);

        Retrofit retrofit = NetworkClient.getRetrofitClient(getContext());
        ConsultationApi api = retrofit.create(ConsultationApi.class);

        Consultation setConsultation = new Consultation(intSelect, counsel);

        Call<Consultation> call = api.consultation(accessToken, setConsultation);
        call.enqueue(new Callback<Consultation>() {
            @Override
            public void onResponse(Call<Consultation> call, Response<Consultation> response) {

                if (response.isSuccessful()) {
                    Consultation consultation = response.body();
                    // 완료시 실행할 코드
                    Intent intent = new Intent(getActivity(), ResultActivity.class);
                    intent.putExtra("consultation", consultation);
                    intent.putExtra("name", name);
                    startActivity(intent);

                    ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(getContext(),
                            R.array.cancel_reasons, android.R.layout.simple_spinner_item);
                    spinner.setAdapter(yearAdapter);
                    editCounsel.setText("");
                    loadingLayout.setVisibility(View.GONE);
                    mainLayout.setVisibility(View.VISIBLE);


                }
            }

            @Override
            public void onFailure(Call<Consultation> call, Throwable t) {
            }

        });
    }
}