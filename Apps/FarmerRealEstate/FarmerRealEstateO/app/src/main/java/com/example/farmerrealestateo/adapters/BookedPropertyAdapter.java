package com.example.farmerrealestateo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.farmerrealestateo.PropertiesBookedDetailsActivity;
import com.example.farmerrealestateo.R;
import com.example.farmerrealestateo.data_models.BookedPropertyDataType;


import java.util.ArrayList;

public class BookedPropertyAdapter extends RecyclerView.Adapter<BookedPropertyAdapter.ViewHolder>{


    Context context;
    ArrayList<BookedPropertyDataType> ex;

    public BookedPropertyAdapter(Context context, ArrayList<BookedPropertyDataType> ex) {
        this.context = context;
        this.ex = ex;
    }


    @NonNull
    @Override
    public BookedPropertyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(this.context).inflate(R.layout.layout_bookedproperty_item, parent, false);
        return new BookedPropertyAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookedPropertyAdapter.ViewHolder holder, int position) {
        final BookedPropertyDataType dt = ex.get(position);

        holder.tvID.setText(dt.getID());
        holder.tvCategory.setText(dt.getCategory());
        holder.tvStatus.setText(dt.getStatus());

       holder.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PropertiesBookedDetailsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("ID", dt.getID());
                context.startActivity(i);
                //   Toast.makeText(context.getApplicationContext(), "Click on button", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        // Toast.makeText(context.getApplicationContext(), String.valueOf(ex.size()), Toast.LENGTH_LONG).show();
        return ex.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvID, tvCategory,tvStatus;
        Button btnInfo;

        public ViewHolder(View view) {
            super(view);

            tvID = (TextView) view.findViewById(R.id.tvID);
            tvCategory = (TextView) view.findViewById(R.id.tvCategory);
            tvStatus=(TextView)view.findViewById(R.id.tvStatus);

            btnInfo = (Button) view.findViewById(R.id.btnInfo);

        }
    }
}
