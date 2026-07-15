package com.example.farmerrealestateo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.farmerrealestateo.locations.GpsTracker;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PropertiesAddActivity extends NavigationDrawerBaseActivity {

    ConnectionClass connectionClass = new ConnectionClass();

    EditText etAddressLine1,etAddressLine2,etCity,etState,etArea,etDescription,etFeatures,etRemarks,etPrice;
    Spinner spinnerCategory,spinnerType;

    Button btnSave;

    Double LatAd=0.0,LongAd=0.0;

    GpsTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_add);

        super.OnCreateDrawer();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Complaint Information");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etAddressLine1=(EditText)findViewById(R.id.etAddressLine1);
        etAddressLine2=(EditText)findViewById(R.id.etAddressLine2);
        etCity=(EditText)findViewById(R.id.etCity);
        etState=(EditText)findViewById(R.id.etState);
        etArea=(EditText)findViewById(R.id.etArea);
        etDescription=(EditText)findViewById(R.id.etDescription);
        etFeatures=(EditText)findViewById(R.id.etFeatures);
        etRemarks=(EditText)findViewById(R.id.etRemarks);
        etPrice=(EditText)findViewById(R.id.etPrice);

        spinnerCategory=(Spinner)findViewById(R.id.spinnerCategory);
        spinnerType=(Spinner)findViewById(R.id.spinnerType);

        btnSave=(Button) findViewById(R.id.btnSave);

        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {
                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else {
                String query = "SELECT Category From tblCategory";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query);
                ResultSet rs = preparedStatement2.executeQuery();
                ArrayList<String> data1 = new ArrayList<String>();

                String Category;

                while (rs.next()){
                    Category = rs.getString("Category");
                    data1.add(Category);
                }
                String[] array = data1.toArray(new String[0]);
                ArrayAdapter NoCoreAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, data1);
                spinnerCategory.setAdapter(NoCoreAdapter);
            }
        }catch (Exception e) {
            e.printStackTrace();
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));

            Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
        }

        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {
                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else {
                String query = "SELECT Type From tblType";
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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetchLocation();

                if(LatAd==0.0  && LongAd==0.0)
                {
                    Toast.makeText(getApplicationContext(), "Click on take image again", Toast.LENGTH_LONG).show();

                }
                else {

                    try {
                        Connection conn = connectionClass.CONN(); //Connection Object

                        String PrpertyID = "";
                        String query1 = "Select ID from tblProperties order by 1 Desc";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                        ResultSet rs = preparedStatement2.executeQuery();
                        if (rs.next()) {
                            String inID = rs.getString("ID");
                            int intID;
                            intID = Integer.parseInt(inID.substring(inID.indexOf("-") + 1).toString());
                            intID = intID + 1;

                            String unpadded = Integer.toString(intID);
                            String padded = "0000".substring(unpadded.length()) + unpadded;

                            PrpertyID = "PRP-" + padded;

                        } else {
                            PrpertyID = "PRP-0001";
                        }

                        //fetching value from shared preference
                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LK", 0);
                        String user_id = sharedPreferences.getString("user_id", "");

                        String query2 = "Insert into tblProperties (ID,Category,AddressLine1,AddressLine2,City," +
                                "State,Area,Description,Features,Remarks,Type,Price,LatAd,LongAd,Status,OwnerID) " +
                                "values ('" + PrpertyID + "','" + spinnerCategory.getSelectedItem().toString().trim() + "'," +
                                "'" + etAddressLine1.getText().toString().trim() + "','" + etAddressLine2.getText().toString().trim() + "'," +
                                "'" + etCity.getText().toString() + "','" + etState.getText().toString() + "', " +
                                "'" + etArea.getText().toString() + "','" + etDescription.getText().toString() + "', " +
                                "'" + etFeatures.getText().toString() + "','" + etRemarks.getText().toString() + "', " +
                                "'" + spinnerType.getSelectedItem().toString() + "','" + etPrice.getText().toString() + "', " +
                                "'" + LatAd + "','" + LongAd + "','New', " +
                                "'" + user_id + "')";
                        preparedStatement2 = conn.prepareStatement(query2);
                        preparedStatement2.executeUpdate();

                        Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(PropertiesAddActivity.this, PropertiesListActivity.class);
                        finish();
                        startActivity(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Writer writer = new StringWriter();
                        e.printStackTrace(new PrintWriter(writer));

                        Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    public void fetchLocation()
    {
        try {
            if
            (ContextCompat.checkSelfPermission(getApplicationContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    !=
                    PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(PropertiesAddActivity.this, new
                        String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        gpsTracker = new GpsTracker(PropertiesAddActivity.this);
        if(gpsTracker.canGetLocation()){
            LatAd = gpsTracker.getLatitude();
            LongAd = gpsTracker.getLongitude();
        }else {
            gpsTracker.showSettingsAlert();
        }


    }
}