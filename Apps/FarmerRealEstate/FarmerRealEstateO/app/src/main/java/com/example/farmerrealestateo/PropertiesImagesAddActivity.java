package com.example.farmerrealestateo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.text.TextRunShaper;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.AndroidRuntimeException;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertiesImagesAddActivity extends NavigationDrawerBaseActivity {

    ConnectionClass connectionClass = new ConnectionClass();

    EditText etSubject,etDescription;
    TextView tvPropertyID;

    private static final int RESULT_LOAD_IMAGE = 1;
    Button uploadpic;
    ImageView imagebox;
    ProgressBar progressBar;

    byte[] byteArray;
    String encodedImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_images_add);

        super.OnCreateDrawer();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Post Offense");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        uploadpic = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imagebox = (ImageView) findViewById(R.id.imageView);

        progressBar.setVisibility(View.GONE);

        etSubject=(EditText) findViewById(R.id.etSubject);
        etDescription=(EditText) findViewById(R.id.etDescription);

        tvPropertyID=(TextView)findViewById(R.id.tvPropertyID);

        String ID;
        Intent intent = getIntent();
        if (intent != null) {
            tvPropertyID.setText(intent.getExtras().getString("ID"));
        } else {

        }


        uploadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && !Environment.getExternalStorageState().equals(Environment.MEDIA_CHECKING)) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

                } else {
                    Toast.makeText(PropertiesImagesAddActivity.this, "No activity found to perform this task", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            progressBar.setVisibility(View.VISIBLE);
            Bitmap originBitmap = null;
            Uri selectedImage = data.getData();
            Toast.makeText(PropertiesImagesAddActivity.this, selectedImage.toString(), Toast.LENGTH_LONG).show();
            InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(selectedImage);
                originBitmap = BitmapFactory.decodeStream(imageStream);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage().toString());
            }
            if (originBitmap != null) {
                this.imagebox.setImageBitmap(originBitmap);
                Log.w("Image Setted in", "Done Loading Image");
                try {
                    Bitmap image = ((BitmapDrawable) imagebox.getDrawable()).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                    byteArray = byteArrayOutputStream.toByteArray();
                    encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    UploadImage uploadImage = new UploadImage();
                    uploadImage.execute("");

                } catch (Exception e) {
                    Log.w("OOooooooooo", "exception");
                }
                Toast.makeText(PropertiesImagesAddActivity.this, "Conversion Done", Toast.LENGTH_SHORT).show();
            }

        } else {
            System.out.println("Error Occured");
        }
    }

    public class UploadImage extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPostExecute(String r)
        {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(PropertiesImagesAddActivity.this , "Image Inserted Succesfully" , Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), PropertiesImagesListActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("ID", tvPropertyID.getText().toString());
            startActivity(i);
        }
        @Override
        protected String doInBackground(String... params) {

            String msg = "unknown";
            try {
                Connection conn = connectionClass.CONN(); //Connection Object


                if (conn == null) {
                    Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                    return "";
                }

                int intID;
                String query1 = "Select ID from tblPropertyImages order by 1 Desc";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query1);
                ResultSet rs = preparedStatement2.executeQuery();
                if (rs.next()){
                    String inID=rs.getString("ID");
                    intID=Integer.parseInt(inID);
                    intID=intID+1;

                }else{
                    intID=1;
                }

                String query = "insert into tblPropertyImages (ID,PropertyID,Image,Subject,Description) " +
                        "values (" + intID + ",'" + tvPropertyID.getText().toString() + "'," +
                        "'" + encodedImage + "'," +
                        "'" + etSubject.getText().toString() + "'," +
                        "'" + etDescription.getText().toString() + "')";

                PreparedStatement preStmt = conn.prepareStatement(query);
                preStmt.executeUpdate();
                msg = "Inserted Successfully";
            } catch (SQLException ex) {
                msg = ex.getMessage().toString();
                Log.d( "Error no 1:", msg );
            } catch (IOError ex) {
                msg = ex.getMessage().toString();
                Log.d( "Error no 2:", msg );
            } catch (AndroidRuntimeException ex) {
                msg = ex.getMessage().toString();
                Log.d( "Error no 3:", msg );
            } catch (NullPointerException ex) {
                msg = ex.getMessage().toString();
                Log.d( "Error no 4:", msg );
            } catch (Exception ex) {
                msg = ex.getMessage().toString();
                Log.d( "Error no 5:", msg );
            }
            System.out.println( msg );
            return "";

        }
    }
    @Override
    public void onBackPressed() {
        // do something on back.
        finish();
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }
}