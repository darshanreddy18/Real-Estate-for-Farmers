package com.example.farmerrealestate.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerrealestate.ConnectionClass;
import com.example.farmerrealestate.FeedBackListActivity;
import com.example.farmerrealestate.PropertiesDetailsActivity;
import com.example.farmerrealestate.PropertiesImagesListActivity;
import com.example.farmerrealestate.PropertiesListActivity;
import com.example.farmerrealestate.R;
import com.example.farmerrealestate.data_models.PropertyDataType;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

        if(dt.getStatus().equals("Booked"))
            holder.btnBook.setVisibility(View.INVISIBLE);


        holder.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PropertiesDetailsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("ID", dt.getID());
                i.putExtra("Status", dt.getStatus());
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
                i.putExtra("Status", dt.getStatus());
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
                i.putExtra("Status", dt.getStatus());
                context.startActivity(i);
                //   Toast.makeText(context.getApplicationContext(), "Click on button", Toast.LENGTH_LONG).show();
            }
        });

        holder.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectionClass connectionClass = new ConnectionClass();

                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {
                        Toast.makeText(context, "No Internet", Toast.LENGTH_LONG).show();
                    } else {

                        //fetching value from shared preference
                        SharedPreferences sharedPreferences = context.getSharedPreferences("LK", 0);
                        String user_id = sharedPreferences.getString("user_id", "");

                        String query = "update tblProperties set UserID='" + user_id + "',Status='Booked' where ID='" + dt.getID() + "'";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query);

                        preparedStatement2.executeUpdate();

                        Toast.makeText(context, "Updated Successfully.", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(context, PropertiesListActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);

                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    Writer writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));

                    Toast.makeText(context, writer.toString(), Toast.LENGTH_LONG).show();
                }


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
        Button btnImages,btnFeedBack,btnInfo,btnBook;

        public ViewHolder(View view) {
            super(view);

            tvID = (TextView) view.findViewById(R.id.tvID);
            tvCategory = (TextView) view.findViewById(R.id.tvCategory);
            tvStatus=(TextView)view.findViewById(R.id.tvStatus);

            btnInfo=(Button) view.findViewById(R.id.btnInfo);
            btnImages=(Button) view.findViewById(R.id.btnImages);
            btnFeedBack=(Button) view.findViewById(R.id.btnFeedBack);
            btnBook=(Button) view.findViewById(R.id.btnBook);
        }
    }
}
