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

import com.example.farmerrealestateo.FeedBackListActivity;
import com.example.farmerrealestateo.PropertiesEditActivity;
import com.example.farmerrealestateo.PropertiesImagesListActivity;
import com.example.farmerrealestateo.R;
import com.example.farmerrealestateo.data_models.PropertyDataType;

import java.util.ArrayList;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder>{


    Context context;
    ArrayList<PropertyDataType> ex;

    public PropertyAdapter(Context context, ArrayList<PropertyDataType> ex) {
        this.context = context;
        this.ex = ex;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(this.context).inflate(R.layout.layout_property_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PropertyDataType dt = ex.get(position);

        holder.tvID.setText(dt.getID());
        holder.tvCategory.setText(dt.getCategory());
        holder.tvStatus.setText(dt.getStatus());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PropertiesEditActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("ID", dt.getID());
                context.startActivity(i);
                //   Toast.makeText(context.getApplicationContext(), "Click on button", Toast.LENGTH_LONG).show();
            }
        });

        holder.btnImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PropertiesImagesListActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("ID", dt.getID());
                context.startActivity(i);
                //   Toast.makeText(context.getApplicationContext(), "Click on button", Toast.LENGTH_LONG).show();
            }
        });

        holder.btnFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FeedBackListActivity.class);
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
        Button btnEdit,btnImages,btnFeedBack;

        public ViewHolder(View view) {
            super(view);

            tvID = (TextView) view.findViewById(R.id.tvID);
            tvCategory = (TextView) view.findViewById(R.id.tvCategory);
            tvStatus=(TextView)view.findViewById(R.id.tvStatus);

            btnEdit = (Button) view.findViewById(R.id.btnEdit);
            btnImages=(Button) view.findViewById(R.id.btnImages);
            btnFeedBack=(Button) view.findViewById(R.id.btnFeedBack);

        }
    }
}
