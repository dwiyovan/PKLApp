package com.example.pklapp;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pklapp.API.APIService;
import com.example.pklapp.API.APIUrl;
import com.example.pklapp.Model.legalisir.Transaksi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class UploadBuktiActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String UPLOAD_URL = "http://psik.feb.ub.ac.id/legalisironline/bukti-transfer_upload.php";
    //public static final String UPLOAD_URL = "http://192.168.1.3/legalisir/bukti-transfer_upload.php";

    private TextView vStatusPemesanan;
    private TextView vtotalPembayaran;
    private Button buttonSelect;
    private Button buttonUpload;
    //private ImageView imageView;
    //private EditText editText;

    //Image request code
    private int PICK_IMAGE_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap;

    //Uri to store the image uri
    private Uri filePath;
    private TextView statusBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadbukti);

        String currdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Transfer");

//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        if (getIntent().getExtras()!=null){
            Bundle b = getIntent().getExtras();
            String id = b.getString("id_transaksi");
            id= id.replaceAll("\\s+", "");
            Log.d("", "onCreate: "+id);
            getStatusPemesanan(id);
            getTotalPembayaran(id);
            SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor=prefs.edit();
            editor.putString("id_statuspesan",id);
            editor.putString("tglpesan",currdate);
            editor.apply();

        }else{
            String id =getIntent().getStringExtra("id_transaksi");
            id= id.replaceAll("\\s+", "");
            getStatusPemesanan(id);

            Log.d("asu1", "onCreate: "+id);
        getTotalPembayaran(id);

        }

        vStatusPemesanan = findViewById(R.id.statusPengiriman);
        vtotalPembayaran = findViewById(R.id.vTotalbayar);

        //Requesting storage permission
        requestStoragePermission();

        //Initializing views
        buttonSelect = (Button) findViewById(R.id.buttonSelect);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        //imageView = (ImageView) findViewById(R.id.imageView);
        //editText = (EditText) findViewById(R.id.editTextName);

        //Setting clicklistener
        buttonSelect.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }


    /*
     * This is the method responsible for image upload
     * We need the full image path and the name for the image in this method
     * */
    public void uploadMultipart() {
        //getting name for the image
        String NIMHolder = "165150200111163";

        String name = NIMHolder+"_BuktiTransfer";

        //getting the actual path of the image
        String path = getPath(filePath);

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("name", name) //Adding text parameter to the request
                    .addParameter("nim", NIMHolder)
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //method to get the file path from uri
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }


    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (v == buttonSelect) {
            showFileChooser();
        }
        if (v == buttonUpload) {
            uploadMultipart();
            startActivity(new Intent(UploadBuktiActivity.this, MLegalisir.class));
        }
    }


    public void getStatusPemesanan(String id_status){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(APIUrl.URL_ACCESS2).addConverterFactory(GsonConverterFactory.create()).build();
        APIService service=retrofit.create(APIService.class);
        Call<Transaksi> call = service.getStatusTransaksi(id_status);
        call.enqueue(new Callback<Transaksi>() {
            @Override
            public void onResponse(Call<Transaksi> call, Response<Transaksi> response) {
                Transaksi statusPemesanan= response.body();
                vStatusPemesanan.setText(statusPemesanan.getKeteranganStatus());
                SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor=prefs.edit();
               editor.putString("statuspesan",vStatusPemesanan.getText().toString());
               editor.commit();

                Log.d("", "  "+response.body());
            }

            @Override
            public void onFailure(Call<Transaksi> call, Throwable t) {
                Log.d("asu gk masuk", "  "+t.getMessage());

            }
        });
    }

    public void getTotalPembayaran(String id_total){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(APIUrl.URL_ACCESS2).addConverterFactory(GsonConverterFactory.create()).build();
        APIService service=retrofit.create(APIService.class);
        Call<Transaksi> call = service.getStatusTransaksi(id_total);
        call.enqueue(new Callback<Transaksi>() {
            @Override
            public void onResponse(Call<Transaksi> call, Response<Transaksi> response) {
                Transaksi totalPembayaran= response.body();
                vtotalPembayaran.setText("Rp."+totalPembayaran.getTotalPembayaran());
                Log.d("asu2", "  "+response.body());

            }

            @Override
            public void onFailure(Call<Transaksi> call, Throwable t) {
                //Log.d("asu", "onFailure: "+t.getMessage());
                Log.d("asu", "  "+t.getMessage());

            }
        });
    }
    @Override
    public void onBackPressed(){
        return;
    }

}