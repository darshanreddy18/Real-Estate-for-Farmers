package com.example.farmerrealestateo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PropertiesBookedDetailsActivity extends NavigationDrawerBaseActivity {

    ConnectionClass connectionClass = new ConnectionClass();

    TextView tvID,tvCategory,tvAddressLine1,tvAddressLine2,tvCity,tvState,tvArea,tvDescription,tvFeatures,tvRemarks,tvType,tvPrice;

    TextView tvOEmailID,tvOMobile,tvOState,tvOCity,tvOAddressLine2,tvOAddressLine1,tvUserName;

    String Status=null;

    Button btnAccept,btnRejected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_booked_details);

        super.OnCreateDrawer();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Registered Information");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvID=(TextView)findViewById(R.id.tvID);
        tvCategory=(TextView)findViewById(R.id.tvCategory);
        tvAddressLine1=(TextView)findViewById(R.id.tvAddressLine1);
        tvAddressLine2=(TextView)findViewById(R.id.tvAddressLine2);
        tvCity=(TextView)findViewById(R.id.tvCity);
        tvState=(TextView)findViewById(R.id.tvState);
        tvArea=(TextView)findViewById(R.id.tvArea);
        tvDescription=(TextView)findViewById(R.id.tvDescription);
        tvFeatures=(TextView)findViewById(R.id.tvFeatures);
        tvRemarks=(TextView)findViewById(R.id.tvRemarks);
        tvType=(TextView)findViewById(R.id.tvType);
        tvPrice=(TextView)findViewById(R.id.tvPrice);

        tvOEmailID=(TextView)findViewById(R.id.tvOEmailID);
        tvOMobile=(TextView)findViewById(R.id.tvOMobile);
        tvOState=(TextView)findViewById(R.id.tvOState);
        tvOCity=(TextView)findViewById(R.id.tvOCity);
        tvOAddressLine2=(TextView)findViewById(R.id.tvOAddressLine2);
        tvOAddressLine1=(TextView)findViewById(R.id.tvOAddressLine1);
        tvUserName=(TextView)findViewById(R.id.tvUserName);


        btnRejected=(Button)findViewById(R.id.btnRejected);
        btnAccept = (Button)findViewById(R.id.btnAccept);

        Intent intent = getIntent();
        if (intent != null) {
            tvID.setText(intent.getExtras().getString("ID"));
        } else {

        }

        try {
            Connection conn = connectionClass.CONN(); //Connection Object

            if (conn == null) {

                Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            } else {
                String query1 = "Select * from tblProperties where ID='" + tvID.getText().toString() + "'";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                ResultSet rs = preparedStatement2.executeQuery();
                if (rs.next()) {
                    tvID.setText(rs.getString("ID").toString());
                    tvCategory.setText(rs.getString("Category").toString());
                    tvAddressLine1.setText(rs.getString("AddressLine1").toString());
                    tvAddressLine2.setText(rs.getString("AddressLine2").toString());
                    tvCity.setText(rs.getString("City").toString());
                    tvState.setText(rs.getString("State").toString());
                    tvArea.setText(rs.getString("Area").toString());
                    tvDescription.setText(rs.getString("Description").toString());
                    tvFeatures.setText(rs.getString("Features").toString());
                    tvRemarks.setText(rs.getString("Remarks").toString());
                    tvType.setText(rs.getString("Type").toString());
                    tvPrice.setText(rs.getString("Price").toString());
                    tvOMobile.setText(rs.getString("UserID").toString());
                    Status=rs.getString("Status").toString();
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
                String query1 = "Select * from tblUsers where Mobile='" + tvOMobile.getText().toString() + "'";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                ResultSet rs = preparedStatement2.executeQuery();
                if (rs.next()) {
                    tvUserName.setText(rs.getString("UserName").toString());
                    tvOAddressLine1.setText(rs.getString("AddressLine1").toString());
                    tvOAddressLine2.setText(rs.getString("AddressLine2").toString());
                    tvOCity.setText(rs.getString("City").toString());
                    tvOState.setText(rs.getString("State").toString());
                    tvOMobile.setText(rs.getString("Mobile").toString());
                    tvOEmailID.setText(rs.getString("EmailID").toString());
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

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {

                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else {
                        String query1 = "Update tblProperties set Status='Accepted' where ID='" + tvID.getText().toString() + "'";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                        preparedStatement2.executeUpdate();

                        Toast.makeText(getApplicationContext(),"Accepted Successfully", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                    Writer writer = new StringWriter();
                    e.printStackTrace(new PrintWriter(writer));

                    Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {

                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else {
                        String query1 = "Update tblProperties set Status='Rejected' where ID='" + tvID.getText().toString() + "'";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                        preparedStatement2.executeUpdate();

                        Toast.makeText(getApplicationContext(),"Rejected Successfully", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e)
                {
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
        startActivity(new Intent(getApplicationContext(),PropertiesBookedListActivity.class));
    }
}