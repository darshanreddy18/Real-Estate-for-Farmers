package com.example.farmerrealestateo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.farmerrealestateo.utils.LoadingDialog;

public class RegisterActivity extends NavigationDrawerBaseActivity {

    ImageView back;

    EditText etOwnerName, etAddressLine1, etAddressLine2, etCity, etMobile,etState,etEmailID;

    ConnectionClass connectionClass = new ConnectionClass();
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etOwnerName = (EditText) findViewById(R.id.etOwnerName);
        etAddressLine1 = (EditText) findViewById(R.id.etAddressLine1);
        etAddressLine2 = (EditText) findViewById(R.id.etAddressLine2);
        etCity = (EditText) findViewById(R.id.etCity);
        etState = (EditText) findViewById(R.id.etState);
        etMobile = (EditText) findViewById(R.id.etMobile);
        etEmailID = (EditText) findViewById(R.id.etEmailID);
        loadingDialog = new LoadingDialog(this);

        findViewById(R.id.imageback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        findViewById(R.id.btnregister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!setValidation())
                    return;

                loadingDialog.show("Creating Account...");

                try {
                    Connection conn = connectionClass.CONN(); //Connection Object

                    if (conn == null) {
                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    } else {

                        String pwd= Integer.toString(generateRandomNumber());

                        String UserID="";
                        String query1 = "Select ID from tblOwners order by 1 Desc";
                        PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                        ResultSet rs = preparedStatement2.executeQuery();
                        if (rs.next()){
                            String inID=rs.getString("ID");
                            int intID;
                            intID=Integer.parseInt(inID.substring(inID.indexOf("-") + 1).toString());
                            intID=intID+1;

                            String unpadded = Integer.toString(intID);
                            String padded = "0000".substring(unpadded.length()) + unpadded;

                            UserID="Buyr-"+padded;

                        }else{
                            UserID="Buyr-0001";
                        }

                        String query2 = "Insert into tblOwners (ID,OwnerName,AddressLine1,AddressLine2,City,State,Mobile,EmailID) " +
                                "values ('" + UserID + "','" + etOwnerName.getText().toString().trim() + "'," +
                                "'" + etAddressLine1.getText().toString().trim() + "'," +
                                "'" + etAddressLine2.getText().toString().trim() + "'," +
                                "'" + etCity.getText().toString().trim() + "','" + etState.getText().toString().trim() + "'," +
                                "'" + etMobile.getText().toString().trim() + "'," +
                                "'" + etEmailID.getText().toString().trim() + "')";
                        preparedStatement2 = conn.prepareStatement(query2);
                        preparedStatement2.executeUpdate();

                        query2 = "Insert into tblLogin (UserID,Password,UserType,UserName) values " +
                                "('" + etMobile.getText().toString().trim() + "','" + pwd + "','Owner'," +
                                "'" + etOwnerName.getText().toString().trim() + "')";
                        preparedStatement2 = conn.prepareStatement(query2);
                        preparedStatement2.executeUpdate();

                        Toast.makeText(getApplicationContext(), "Registered Successfully. Password is "+pwd, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        finish();
                        startActivity(intent);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    java.io.Writer writer = new java.io.StringWriter();
                    e.printStackTrace(new java.io.PrintWriter(writer));
                    Toast.makeText(getApplicationContext(), writer.toString(), Toast.LENGTH_LONG).show();
                } finally {
                    loadingDialog.dismiss();
                }
            }
        });
        findViewById(R.id.textView5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        // do something on back.
        finish();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }

    private boolean setValidation() {
        String username = etOwnerName.getText().toString().trim();
        String addressline1 = etAddressLine1.getText().toString().trim();
        String city = etCity.getText().toString().trim();
        String state = etState.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();

        boolean isUserName, isAddressLine1,isCity,isState,isMobile;

        if (username.isEmpty()) {
            etOwnerName.setError("Farmer Name can't be empty");
            isUserName = false;
        } else {
            etOwnerName.setError(null);
            isUserName = true;
        }

        if (addressline1.isEmpty()) {
            etAddressLine1.setError("AddressLine1 can't be empty");
            isAddressLine1 = false;
        } else {
            etAddressLine1.setError(null);
            isAddressLine1 = true;
        }

        if (city.isEmpty()) {
            etCity.setError("City can't be empty");
            isCity= false;
        } else {
            etCity.setError(null);
            isCity=true;
        }

        if (state.isEmpty()) {
            etState.setError("State can't be empty");
            isState= false;
        } else {
            etState.setError(null);
            isState=true;
        }

        if (mobile.isEmpty()) {
            etMobile.setError("Mobile can't be empty");
            isMobile= false;
        }
        else if (mobile.length()!=10) {
            etMobile.setError("Invalid Mobile number");
            isMobile= false;
        }
        else {
            etMobile.setError(null);
            isMobile= true;
        }

        if(isUserName==true && isAddressLine1==true && isCity==true && isState==true && isMobile==true)
            return true;
        else
            return false;
    }

    public int generateRandomNumber() {
        int range = 9;  // to generate a single number with this range, by default its 0..9
        int length = 4; // by default length is 4

        int randomNumber;

        SecureRandom secureRandom = new SecureRandom();
        String s = "";
        for (int i = 0; i < length; i++) {
            int number = secureRandom.nextInt(range);
            if (number == 0 && i == 0) { // to prevent the Zero to be the first number as then it will reduce the length of generated pin to three or even more if the second or third number came as zeros
                i = -1;
                continue;
            }
            s = s + number;
        }

        randomNumber = Integer.parseInt(s);

        return randomNumber;
    }
}