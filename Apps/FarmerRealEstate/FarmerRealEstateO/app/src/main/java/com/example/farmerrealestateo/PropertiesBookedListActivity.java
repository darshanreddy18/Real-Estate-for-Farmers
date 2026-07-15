package com.example.farmerrealestateo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.farmerrealestateo.adapters.BookedPropertyAdapter;
import com.example.farmerrealestateo.adapters.PropertyAdapter;
import com.example.farmerrealestateo.data_models.BookedPropertyDataType;
import com.example.farmerrealestateo.data_models.PropertyDataType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PropertiesBookedListActivity extends NavigationDrawerBaseActivity {

    ConnectionClass connectionClass = new ConnectionClass();

    RecyclerView recyclerView;
    ResultSet rss;
    ArrayList<BookedPropertyDataType> ex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_booked_list);

        super.OnCreateDrawer();

        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Property List Page");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        //fetching value from shared preference
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LK", 0);
        String user_id = sharedPreferences.getString("user_id", "");

        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {

                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else
            {
                String query="SELECT * from tblProperties where OwnerID='" + user_id + "' and (Status='Booked' or Status='Accepted')";

                PreparedStatement stmt = conn.prepareStatement(query);
                rss = stmt.executeQuery();
                ex = new ArrayList<>();
                while(rss.next()) {
                    //Log.d("ResultSet", rs.getString("ID"));
                    BookedPropertyDataType dt = new BookedPropertyDataType();
                    dt.setID(rss.getString("ID").toString());
                    dt.setCategory(rss.getString("Category").toString());
                    dt.setStatus(rss.getString("Status").toString());
                    ex.add(dt);
                }
                BookedPropertyAdapter eadapters = new BookedPropertyAdapter(getApplicationContext(),ex);
                recyclerView.setAdapter(eadapters);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));

            Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onBackPressed() {
        // do something on back.
        Intent intent=new Intent(PropertiesBookedListActivity.this,HomeActivity.class);
        finish();
        startActivity(intent);
    }
}