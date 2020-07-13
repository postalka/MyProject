package com.example.raksha;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class
HomeActivity extends AppCompatActivity {
    Button HELP;
    Button CALL;
    final Context context = this;
    Button LOCATION;
    Button CAMERA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HELP = (Button) findViewById(R.id.hm1);
        CALL = (Button) findViewById(R.id.hm2);
        LOCATION = (Button) findViewById(R.id.hm3);
        CAMERA = (Button) findViewById(R.id.hm4);





        HELP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:9939590757"));
                smsIntent.putExtra("sms_body", "I am in emergency situation");
                startActivity(smsIntent);
            }
        });


        CALL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel: 9939590757"));

                if (ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });

        LOCATION.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });

        CAMERA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageintent = new Intent(HomeActivity.this,CameraActivity.class);
                startActivity(imageintent);

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



        getMenuInflater().inflate(R.menu.activity_home_menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1) {
            Intent intent = new Intent(HomeActivity.this,HomeActivity.class);
            startActivity(intent);
            return true;
        }
        else
            if (id == R.id.item2) {
            Intent intent = new Intent(HomeActivity.this,Profile_Activity.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item3) {
            Intent intent = new Intent(HomeActivity.this,My_Location_Activity.class);
            startActivity(intent);
            return true;
        }
        else
            if (id == R.id.item4) {
            Intent intent = new Intent(HomeActivity.this,aboutActivity.class);
            startActivity(intent);
            return true;
        }
        else
            if (id == R.id.item5) {
                Intent intent = new Intent(HomeActivity.this,Safety_Tips_Activity.class);
                startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


