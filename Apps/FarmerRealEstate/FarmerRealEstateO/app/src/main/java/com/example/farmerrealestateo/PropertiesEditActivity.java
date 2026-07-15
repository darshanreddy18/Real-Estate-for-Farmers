package com.example.farmerrealestateo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;

public class PropertiesEditActivity extends NavigationDrawerBaseActivity {

    ConnectionClass connectionClass = new ConnectionClass();

    TextView tvID;
    EditText etAddressLine1,etAddressLine2,etCity,etState,etArea,etDescription,etFeatures,etRemarks,etPrice;
    Spinner spinnerCategory,spinnerType;

    Button btnUpdate,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_edit);

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

        tvID = (TextView) findViewById(R.id.tvID);

        btnUpdate=(Button) findViewById(R.id.btnUpdate);
        btnDelete=(Button)findViewById(R.id.btnDelete);

        String category=null;
        String type=null;

        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {

                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else {

                Intent intent = getIntent();
                if(intent != null) {
                    tvID.setText(intent.getExtras().getString("ID"));
                } else {
                    tvID.setText("Data not available");
                }

                String query1 = "Select * from tblProperties where ID='" + tvID.getText().toString() + "'";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                ResultSet rs = preparedStatement2.executeQuery();
                if (rs.next()) {
                    tvID.setText(rs.getString("ID").toString());
                    category = rs.getString("Category").toString();
                    etAddressLine1.setText(rs.getString("AddressLine1").toString());
                    etAddressLine2.setText(rs.getString("AddressLine2").toString());
                    etCity.setText(rs.getString("City").toString());
                    etState.setText(rs.getString("State").toString());
                    etArea.setText(rs.getString("Area").toString());
                    etDescription.setText(rs.getString("Description").toString());
                    etFeatures.setText(rs.getString("Features").toString());
                    etRemarks.setText(rs.getString("Remarks").toString());
                    type = rs.getString("Type").toString();
                    etPrice.setText(rs.getString("Price").toString());
                }
            }
        }
        catch (Exception e)
        {
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


                for (int i = 0; i < spinnerCategory.getCount(); i++) {
                    if (spinnerCategory.getItemAtPosition(i).equals(category)) {
                        spinnerCategory.setSelection(i);
                        break;
                    }
                }

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

                for (int i = 0; i < spinnerCategory.getCount(); i++) {
                    if (spinnerType.getItemAtPosition(i).equals(type)) {
                        spinnerType.setSelection(i);
                        break;
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));

            Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
        }

        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {
                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else {
                        String query2 = "Update tblProperties set Category='" + spinnerCategory.getSelectedItem().toString() + "'," +
                                "AddressLine1='" + etAddressLine1.getText().toString() + "'," +
                                "AddressLine2='" + etAddressLine2.getText().toString() + "'," +
                                "City='" + etCity.getText().toString() + "'," +
                                "State='" + etState.getText().toString() + "'," +
                                "Area='" + etArea.getText().toString() + "'," +
                                "Description='" + etDescription.getText().toString() + "'," +
                                "Features='" + etFeatures.getText().toString() + "'," +
                                "Remarks='" + etRemarks.getText().toString() + "'," +
                                "Type='" + spinnerType.getSelectedItem().toString() + "'," +
                                "Price='" + etPrice.getText().toString() + "' " +
                                " where ID='" + tvID.getText().toString() + "'";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query2);
                        preparedStatement2.executeUpdate();

                        Toast.makeText(getApplicationContext(), "Updated Successfully. ", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(PropertiesEditActivity.this, PropertiesListActivity.class);
                        finish();
                        startActivity(intent);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Writer writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));

                    Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {
                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else {
                        String query2 = "Delete from tblProperties " +
                                " where ID='" + tvID.getText().toString() + "'";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query2);
                        preparedStatement2.executeUpdate();

                        Toast.makeText(getApplicationContext(), "Deleted Successfully. ", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(PropertiesEditActivity.this, PropertiesListActivity.class);
                        finish();
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Writer writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));

                    Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        // do something on back.
        finish();
        startActivity(new Intent(getApplicationContext(),PropertiesListActivity.class));
    }
}