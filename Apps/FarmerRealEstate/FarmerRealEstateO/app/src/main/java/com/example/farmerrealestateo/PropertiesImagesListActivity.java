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
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmerrealestateo.adapters.ImageAdapter;
import com.example.farmerrealestateo.data_models.ImageDataType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PropertiesImagesListActivity extends NavigationDrawerBaseActivity {

    ConnectionClass connectionClass = new ConnectionClass();

    RecyclerView recyclerView;
    ResultSet rss;
    ArrayList<ImageDataType> ex;

    Button btnAddImage;

    String ID=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_images_list);

        super.OnCreateDrawer();

        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Candidates List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        btnAddImage=(Button) findViewById(R.id.btnAddImage);

        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {

                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else {

                Intent intent = getIntent();
                if (intent != null) {
                    ID = intent.getExtras().getString("ID");
                } else {

                }

                String query = "SELECT * from tblPropertyImages where PropertyID='" + ID + "'";

                PreparedStatement stmt = conn.prepareStatement(query);
                rss = stmt.executeQuery();
                ex = new ArrayList<>();
                while (rss.next()) {
                    //Log.d("ResultSet", rs.getString("ID"));
                    ImageDataType dt = new ImageDataType();
                    dt.setID(rss.getString("ID").toString());
                    dt.setPropertyID(rss.getString("PropertyID").toString());
                    dt.setImage(rss.getString("Image").toString());
                    dt.setSubject(rss.getString("Subject").toString());
                    dt.setDescription(rss.getString("Description").toString());
                    ex.add(dt);
                }
                ImageAdapter eadapters = new ImageAdapter(getApplicationContext(), ex);
                recyclerView.setAdapter(eadapters);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));

            Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
        }

        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PropertiesImagesAddActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("ID", ID);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // do something on back.
        finish();
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }
}