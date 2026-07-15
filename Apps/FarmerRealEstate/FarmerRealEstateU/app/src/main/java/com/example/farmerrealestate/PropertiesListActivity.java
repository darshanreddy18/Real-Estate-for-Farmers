package com.example.farmerrealestate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.farmerrealestate.adapters.PropertyAdapter;
import com.example.farmerrealestate.data_models.PropertyDataType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PropertiesListActivity extends NavigationDrawerBaseActivity {

    ConnectionClass connectionClass = new ConnectionClass();

    RecyclerView recyclerView;
    ResultSet rss;
    ArrayList<PropertyDataType> ex;

    Spinner spinnerCategory,spinnerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_list);

        super.OnCreateDrawer();

        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Property List Page");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        spinnerType=(Spinner) findViewById(R.id.spinnerType);

        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {
                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else {
                String query = ("SELECT distinct(Category) FROM tblProperties");
                PreparedStatement preparedStatement2 = conn.prepareStatement(query);
                ResultSet rs = preparedStatement2.executeQuery();
                ArrayList<String> data = new ArrayList<String>();

                String Category;

                while (rs.next()){
                    Category = rs.getString("Category");
                    data.add(Category);
                }
                String[] array = data.toArray(new String[0]);
                ArrayAdapter NoCoreAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
                spinnerCategory.setAdapter(NoCoreAdapter);
            }
        }catch (Exception e) {
            e.printStackTrace();
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));

            Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
        }


        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String category = spinnerCategory.getSelectedItem().toString();

                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {
                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else {
                        String query = "SELECT distinct(Type) From tblProperties where Category='" + category + "'";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query);
                        ResultSet rs = preparedStatement2.executeQuery();
                        ArrayList<String> data1 = new ArrayList<String>();

                        String Type;

                        while (rs.next()){
                            Type = rs.getString("Type");
                            data1.add(Type);
                        }
                        String[] array = data1.toArray(new String[0]);
                        ArrayAdapter NoCoreAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, data1);
                        spinnerType.setAdapter(NoCoreAdapter);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    Writer writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));

                    Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String type = spinnerType.getSelectedItem().toString();
                String category = spinnerCategory.getSelectedItem().toString();

                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {

                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else
                    {
                        String query="SELECT * from tblProperties where Category='" + category + "' and Type='" + type + "' and (Status='New' or Status='Rejected')";

                        PreparedStatement stmt = conn.prepareStatement(query);
                        rss = stmt.executeQuery();
                        ex = new ArrayList<>();
                        while(rss.next()) {
                            //Log.d("ResultSet", rs.getString("ID"));
                            PropertyDataType dt = new PropertyDataType();
                            dt.setID(rss.getString("ID").toString());
                            dt.setCategory(rss.getString("Category").toString());
                            dt.setStatus(rss.getString("Status").toString());
                            ex.add(dt);
                        }
                        PropertyAdapter eadapters = new PropertyAdapter(getApplicationContext(),ex);
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
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    @Override
    public void onBackPressed() {
        // do something on back.
        Intent intent=new Intent(PropertiesListActivity.this,HomeActivity.class);
        finish();
        startActivity(intent);
    }
}