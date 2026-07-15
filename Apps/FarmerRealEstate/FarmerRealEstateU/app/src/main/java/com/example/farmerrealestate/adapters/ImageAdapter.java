package com.example.farmerrealestate.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerrealestate.ConnectionClass;
import com.example.farmerrealestate.R;
import com.example.farmerrealestate.data_models.ImageDataType;


import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{

    Context context;
    ArrayList<ImageDataType> ex;

    public ImageAdapter(Context context, ArrayList<ImageDataType> ex) {
        this.context = context;
        this.ex = ex;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(this.context).inflate(R.layout.layout_propertyimage_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ImageDataType dt = ex.get(position);

        holder.tvID.setText(dt.getID());
        holder.tvPropertyID.setText(dt.getPropertyID());
        holder.tvSubject.setText(dt.getSubject());
        holder.tvDescription.setText(dt.getDescription());

        byte[] decodeString = Base64.decode(dt.getImage(), Base64.DEFAULT);
        Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        holder.imageView.setImageBitmap(decodebitmap);
    }

    @Override
    public int getItemCount() {
        // Toast.makeText(context.getApplicationContext(), String.valueOf(ex.size()), Toast.LENGTH_LONG).show();
        return ex.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvID, tvPropertyID,tvSubject,tvDescription;
        Button btnDelete;

        ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            tvID = (TextView) view.findViewById(R.id.tvID);
            tvPropertyID = (TextView) view.findViewById(R.id.tvPropertyID);
            tvSubject=(TextView)view.findViewById(R.id.tvSubject);
            tvDescription=(TextView)view.findViewById(R.id.tvDescription);
            imageView=(ImageView) view.findViewById(R.id.imageView);
        }
    }
}
