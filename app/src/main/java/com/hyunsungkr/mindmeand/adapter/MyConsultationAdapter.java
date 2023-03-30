package com.hyunsungkr.mindmeand.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hyunsungkr.mindmeand.R;
import com.hyunsungkr.mindmeand.model.UserHistory;
import com.hyunsungkr.mindmeand.model.UserRes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MyConsultationAdapter extends RecyclerView.Adapter<MyConsultationAdapter.ViewHolder> {

    Context context;
    ArrayList<UserHistory> consultationList;

    public interface OnItemClickListener {
        void onCardViewClick(int index);
        void deleteProcess(int index);
    }
    public OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public MyConsultationAdapter(Context context, ArrayList<UserHistory> consultationList) {
        this.context = context;
        this.consultationList = consultationList;
    }

    @NonNull
    @Override
    public MyConsultationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.consultation_row, parent, false);
        return new MyConsultationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyConsultationAdapter.ViewHolder holder, int position) {

        UserHistory consultation = consultationList.get(position);

        holder.txtQuestion.setText(consultation.getQuestion());
        String originalDateString = consultation.getCreatedAt();
        SimpleDateFormat originalDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        Date originalDate = null;
        try {
            originalDate = originalDateFormat.parse(originalDateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        SimpleDateFormat targetDateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        String targetDateString = targetDateFormat.format(originalDate);

        holder.txtDate.setText(targetDateString);

    }

    @Override
    public int getItemCount() {
        return consultationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtDate;
        TextView txtQuestion;
        ImageView imgDelete;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDate = itemView.findViewById(R.id.txtDate);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    listener.onCardViewClick(index);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("삭제");
                    builder.setMessage("정말 삭제하시겠습니까?");
                    builder.setNegativeButton("아니오", null);
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // todo: 삭제하는 함수 호출
                            int index = getAdapterPosition();
                            listener.deleteProcess(index);



                        }
                    });
                    builder.show();
                }

            });


        }
    }
}
