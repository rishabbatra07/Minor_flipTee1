package com.example.welcome.fliptee;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class designerActivity extends AppCompatActivity {
    ImageView iv;
    Button but_gall, cam;
    private static int request = 1;
    String ImageDecode;
    Button but_val;
    String[] FILE;
    Uri file;
    //String[] file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_designer);
        iv = (ImageView) findViewById(R.id.image_gallery);
        but_gall = (Button) findViewById(R.id.button_gallery);
        but_gall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, request);
            }

        });
        cam = (Button) findViewById(R.id.camera);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            cam.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file = Uri.fromFile(getOutputMediaFile());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request && resultCode == RESULT_OK && data != null) {
            Uri URI = data.getData();
            String[] FILE = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(URI, FILE, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(FILE[0]);
            ImageDecode = cursor.getString(columnIndex);
            cursor.close();
            iv.setImageBitmap(BitmapFactory.decodeFile(ImageDecode));
            but_val = (Button) findViewById(R.id.button_validate);
            TextView tv1 = (TextView) findViewById(R.id.text_title);
            tv1.setVisibility(View.VISIBLE);
            but_val.setVisibility(View.VISIBLE);
            but_val.setOnClickListener(new View.OnClickListener() {       //here add to admin database for validation
                @Override
                public void onClick(View v) {
                    Toast.makeText(designerActivity.this, "Submitted", Toast.LENGTH_LONG).show();
                }
            });
        }
        else if (requestCode == 100)
        {
           // if (resultCode == RESULT_OK) {
            /*if(data!=null)
            {
               Uri URI = data.getData();
                String[] FILE = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(URI, FILE, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(FILE[0]);
                String ImageDecodeCam = cursor.getString(columnIndex);
                cursor.close();
                //iv.setImageURI(file);
                iv.setImageBitmap(BitmapFactory.decodeFile(ImageDecodeCam));
            }
            else
                Toast.makeText(this,"SAVED IN GALLERY",Toast.LENGTH_LONG);*/
        } else {
            Toast.makeText(this, "Could Not Fetch Image", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                cam.setEnabled(true);
            }
        }
    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");
    }
}

