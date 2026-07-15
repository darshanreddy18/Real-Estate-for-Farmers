package com.example.farmerrealestate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmerrealestate.locations.GpsTracker;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PropertiesDetailsActivity extends NavigationDrawerBaseActivity {

    ConnectionClass connectionClass = new ConnectionClass();

    TextView tvID,tvCategory,tvAddressLine1,tvAddressLine2,tvCity,tvState,tvArea,tvDescription,tvFeatures,tvRemarks,tvType,tvPrice;
    Button btnViewLocation;

    TextView tvOEmailID,tvOMobile,tvOState,tvOCity,tvOAddressLine2,tvOAddressLine1,tvOwnerName;

    double Ulatitude=0;
    double Ulongitude=0;

    double Slatitude=0;
    double Slongitude=0;

    GpsTracker gpsTracker;

    String Status=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_details);

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
        tvOwnerName=(TextView)findViewById(R.id.tvOwnerName);

        btnViewLocation=(Button)findViewById(R.id.btnViewLocation);

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
                    tvOMobile.setText(rs.getString("OwnerID").toString());
                    Slatitude=Double.parseDouble(rs.getString("LatAd").toString());
                    Slongitude=Double.parseDouble(rs.getString("LongAd").toString());
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
                String query1 = "Select * from tblOwners where Mobile='" + tvOMobile.getText().toString() + "'";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                ResultSet rs = preparedStatement2.executeQuery();
                if (rs.next()) {
                    tvOwnerName.setText(rs.getString("OwnerName").toString());
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

        btnViewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetchLocation();

                if(Ulatitude==0.0  && Ulongitude==0.0)
                {
                    btnViewLocation.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Click Again, Because failed to fetch your location.", Toast.LENGTH_LONG).show();
                }
                else {

                    String sdestination = Double.toString(Slatitude) + ", " + Double.toString(Slongitude);
                    String  ssource = Double.toString(Ulatitude) + ", "+ Double.toString(Ulongitude);


                    if (ssource.equals("") && sdestination.equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Enter both location", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        DisplayTrack(ssource, sdestination);
                    }

                }

            }
        });

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void DisplayTrack(String ssource, String sdestination)
    {
        try
        {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + ssource + "/" + sdestination);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            //   String URI= String.format(Locale.ENGLISH, "http://google.com/maps?q=loc:%f ,%f", 75.909032,14.432432);
            //  Intent intent1 = new Intent(Intent.ACTION_VIEW,Uri.parse(URI));
            //   startActivity(intent);
            Uri URI= Uri.parse("http://maps.googleapis.com/maps/api/geocode/json?latlng=lat,long&sesor=true");
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e)
        {
            Uri uri = Uri.parse("https://play.google.com/store/apps/dtails?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
    }

    public void fetchLocation()
    {
        try {
            if
            (ContextCompat.checkSelfPermission(getApplicationContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    !=
                    PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new

                        String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        gpsTracker = new GpsTracker(PropertiesDetailsActivity.this);
        if(gpsTracker.canGetLocation()){
            Ulatitude = gpsTracker.getLatitude();
            Ulongitude = gpsTracker.getLongitude();
        }else {
            gpsTracker.showSettingsAlert();
        }

    }

    @Override
    public void onBackPressed() {
        // do something on back.
        finish();
        if(Status.equals("Booked"))
        {
            startActivity(new Intent(getApplicationContext(),PropertiesBookedListActivity.class));
        }
        else
            startActivity(new Intent(getApplicationContext(),PropertiesListActivity.class));
    }
}