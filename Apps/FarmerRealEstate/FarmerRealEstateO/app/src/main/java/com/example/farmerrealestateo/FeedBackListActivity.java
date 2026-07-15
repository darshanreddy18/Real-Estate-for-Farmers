package com.example.farmerrealestateo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.farmerrealestateo.adapters.FeedBackAdapter;
import com.example.farmerrealestateo.adapters.PropertyAdapter;
import com.example.farmerrealestateo.data_models.FeedBackDataType;
import com.example.farmerrealestateo.data_models.PropertyDataType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FeedBackListActivity extends NavigationDrawerBaseActivity {

    ConnectionClass connectionClass = new ConnectionClass();

    RecyclerView recyclerView;
    ResultSet rss;
    ArrayList<FeedBackDataType> ex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_list);

        super.OnCreateDrawer();

        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Feed Back List Page");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {

                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else
            {
                String ID=null;

                Intent intent = getIntent();
                if (intent != null) {
                    ID = intent.getExtras().getString("ID");
                } else {

                }

                String query="SELECT * from tblFeedBack where PropertyID='" + ID + "'";

                PreparedStatement stmt = conn.prepareStatement(query);
                rss = stmt.executeQuery();
                ex = new ArrayList<>();
                while(rss.next()) {
                    //Log.d("ResultSet", rs.getString("ID"));
                    FeedBackDataType dt = new FeedBackDataType();
                    dt.setID(rss.getString("ID").toString());
                    dt.setUserName(rss.getString("UserName").toString());
                    dt.setFeedBack(rss.getString("FeedBack").toString());
                    ex.add(dt);
                }
                FeedBackAdapter eadapters = new FeedBackAdapter(getApplicationContext(),ex);
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
        finish();
        startActivity(new Intent(getApplicationContext(),PropertiesListActivity.class));
    }
}