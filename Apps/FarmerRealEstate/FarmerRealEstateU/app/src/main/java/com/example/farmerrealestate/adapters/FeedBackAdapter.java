package com.example.farmerrealestate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerrealestate.R;
import com.example.farmerrealestate.data_models.FeedBackDataType;

import java.util.ArrayList;

public class FeedBackAdapter extends RecyclerView.Adapter<FeedBackAdapter.ViewHolder>{

    Context context;
    ArrayList<FeedBackDataType> ex;

    public FeedBackAdapter(Context context, ArrayList<FeedBackDataType> ex) {
        this.context = context;
        this.ex = ex;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(this.context).inflate(R.layout.layout_feedback_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FeedBackDataType dt = ex.get(position);

        holder.tvFeedBack.setText(dt.getFeedBack());
        holder.tvUserName.setText(dt.getUserName());

    }

    @Override
    public int getItemCount() {
        // Toast.makeText(context.getApplicationContext(), String.valueOf(ex.size()), Toast.LENGTH_LONG).show();
        return ex.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvID, tvFeedBack,tvUserName;

        public ViewHolder(View view) {
            super(view);

            tvID = (TextView) view.findViewById(R.id.tvID);
            tvFeedBack = (TextView) view.findViewById(R.id.tvFeedBack);
            tvUserName=(TextView)view.findViewById(R.id.tvUserName);
        }
    }
}
