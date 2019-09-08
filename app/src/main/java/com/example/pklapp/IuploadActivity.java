package com.example.pklapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.DatePicker;
import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;
import java.util.UUID;
import java.util.Calendar;

public class IuploadActivity extends AppCompatActivity{

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    Button SelectButton, UploadButton;

    TextView PdfNameEditText, mDisplayDate ;

    EditText PdfIdEditText;

    Uri uri;

    public static final String PDF_UPLOAD_HTTP_URL = "http://192.168.1.2/legalisir/ijazah_upload.php";

    public int PDF_REQ_CODE = 1;

    String PdfNameHolder, PdfIdHolder, PdfPathHolder, PdfID, NIMHolder, DateHolder, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iupload);

        AllowRunTimePermission();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Upload Berkas Ijazah");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SelectButton = (Button) findViewById(R.id.button);
        UploadButton = (Button) findViewById(R.id.button2);
        PdfNameEditText = (TextView) findViewById(R.id.editText);
        PdfIdEditText = (EditText) findViewById(R.id.nomorijazah);
        mDisplayDate = (TextView) findViewById(R.id.dateText);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        IuploadActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d("IuploadActivity", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        SelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // PDF selection code start from here .

                Intent intent = new Intent();

                intent.setType("application/pdf");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);

            }
        });

        UploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PdfUploadFunction();


            }


        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PDF_REQ_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData();

            SelectButton.setText("PDF is Selected");

            PdfNameEditText.setText("NIM_Ijazah");
        }
    }


    public void PdfUploadFunction() {

        PdfNameHolder = PdfNameEditText.getText().toString().trim();

        PdfIdHolder = PdfIdEditText.getText().toString().trim();

        PdfPathHolder = FilePath.getPath(this, uri);

        NIMHolder = "165150200111163";

        DateHolder = date;

        if (PdfPathHolder == null) {

            Toast.makeText(this, "Please move your PDF file to internal storage & try again.", Toast.LENGTH_LONG).show();

        } else {

            try {

                PdfID = UUID.randomUUID().toString();

                new MultipartUploadRequest(this, PdfID, PDF_UPLOAD_HTTP_URL)
                        .addFileToUpload(PdfPathHolder, "pdf")
                        .addParameter("name", PdfNameHolder)
                        .addParameter("id", PdfIdHolder)
                        .addParameter("nim", NIMHolder)
                        .addParameter("date", DateHolder)
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(5)
                        .startUpload();

            } catch (Exception exception) {

                Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }


    }


    public void AllowRunTimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(IuploadActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE))
        {

            Toast.makeText(IuploadActivity.this,"READ_EXTERNAL_STORAGE permission Access Dialog", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(IuploadActivity.this,new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] Result) {

        switch (RC) {

            case 1:

                if (Result.length > 0 && Result[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(IuploadActivity.this,"Permission Granted", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(IuploadActivity.this,"Permission Canceled", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }


}