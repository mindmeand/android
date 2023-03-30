package com.hyunsungkr.mindmeand;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyunsungkr.mindmeand.adapter.MyConsultationAdapter;
import com.hyunsungkr.mindmeand.api.ConsultationApi;
import com.hyunsungkr.mindmeand.api.NetworkClient;
import com.hyunsungkr.mindmeand.api.UserApi;
import com.hyunsungkr.mindmeand.config.Config;
import com.hyunsungkr.mindmeand.model.Consultation;
import com.hyunsungkr.mindmeand.model.Res;
import com.hyunsungkr.mindmeand.model.User;
import com.hyunsungkr.mindmeand.model.UserHistory;
import com.hyunsungkr.mindmeand.model.UserHistoryList;
import com.hyunsungkr.mindmeand.model.UserMyInfoList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyInfoFragment newInstance(String param1, String param2) {
        MyInfoFragment fragment = new MyInfoFragment();
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
    private boolean isLoading = false;
    TextView txtName;
    TextView txtBirthDate;
    TextView txtEmail;
    LinearLayout linearUpdate;

    // 페이징 처리를 위한 변수
    int count = 0;
    int offset = 0;
    int limit = 5;

    private int visibleThreshold = 5; // default value

    ArrayList<User> mypageList = new ArrayList<>();

    RecyclerView recyclerView;
    MyConsultationAdapter adapter;
    ArrayList<UserHistory> consultationList = new ArrayList<>();

    private int deleteIndex;
    private UserHistory selectedConsultation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_info, container, false);
        txtName = rootView.findViewById(R.id.txtName);
        txtBirthDate = rootView.findViewById(R.id.txtBirthDate);
        txtEmail = rootView.findViewById(R.id.txtEmail);
        linearUpdate = rootView.findViewById(R.id.linearUpdate);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));





        linearUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ChangeMyInfoActivity.class);
                startActivity(intent);
            }
        });



        return rootView;

    }

    public void onResume(){
        super.onResume();
        getNetworkData();
        getConsultationData();
    }




    void getNetworkData(){



        Retrofit retrofit = NetworkClient.getRetrofitClient(getActivity());
        UserApi api = retrofit.create(UserApi.class);

        // 헤더에 들어갈 억세스토큰 가져오기
        SharedPreferences sp = getActivity().getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);
        String accessToken = "Bearer " + sp.getString(Config.ACCESS_TOKEN, "");

        Call<UserMyInfoList> call = api.getMyinfo(accessToken);
        call.enqueue(new Callback<UserMyInfoList>() {
            @Override
            public void onResponse(Call<UserMyInfoList> call, Response<UserMyInfoList> response) {
                if(response.isSuccessful()){



                    // add the retrieved items to the list
                    mypageList.add(response.body().getUser());


                    txtBirthDate.setText(mypageList.get(0).getBirthDate());
                    txtEmail.setText(mypageList.get(0).getEmail());
                    txtName.setText(mypageList.get(0).getName());





                }
            }

            @Override
            public void onFailure(Call<UserMyInfoList> call, Throwable t) {

            }
        });
    } 

    void getConsultationData(){

        Retrofit retrofit = NetworkClient.getRetrofitClient(getActivity());
        ConsultationApi api = retrofit.create(ConsultationApi.class);

        // 헤더에 들어갈 억세스토큰 가져오기
        SharedPreferences sp = getActivity().getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);
        String accessToken = "Bearer " + sp.getString(Config.ACCESS_TOKEN, "");

        Call<UserHistoryList> call = api.getMyConsultation(accessToken);
        call.enqueue(new Callback<UserHistoryList>() {
            @Override
            public void onResponse(Call<UserHistoryList> call, Response<UserHistoryList> response) {
                if(response.isSuccessful()){
                    consultationList.clear();
                    consultationList.addAll(response.body().getResult());

                    adapter = new MyConsultationAdapter(getActivity(),consultationList);

                    adapter.setOnItemClickListener(new MyConsultationAdapter.OnItemClickListener() {
                        @Override
                        public void onCardViewClick(int index) {
                            UserHistory userHistory = consultationList.get(index);
                            Intent intent = new Intent(getActivity(),ViewDetailsActivity.class);
                            intent.putExtra("userHistory",userHistory);
                            startActivity(intent);
                        }

                        @Override
                        public void deleteProcess(int index) {
                            MyInfoFragment.this.deleteProcess(index);
                        }
                    });

                    recyclerView.setAdapter(adapter);


                }else{
                    return;
                }
            }

            @Override
            public void onFailure(Call<UserHistoryList> call, Throwable t) {

            }
        });




    }

    public void deleteProcess(int index){
        selectedConsultation = consultationList.get(index);
        Retrofit retrofit = NetworkClient.getRetrofitClient(getActivity());
        ConsultationApi api = retrofit.create(ConsultationApi.class);

        // 헤더에 들어갈 억세스토큰 가져오기
        SharedPreferences sp = getActivity().getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);
        String accessToken = "Bearer " + sp.getString(Config.ACCESS_TOKEN, "");

        Call<Res> call = api.deleteConsultation(accessToken,selectedConsultation.getId());

        call.enqueue(new Callback<Res>() {
            @Override
            public void onResponse(Call<Res> call, Response<Res> response) {
                if(response.isSuccessful()){
                    consultationList.remove(selectedConsultation);
                    adapter.notifyDataSetChanged();
                }else{
                    return;
                }
            }

            @Override
            public void onFailure(Call<Res> call, Throwable t) {

            }
        });



    }




}