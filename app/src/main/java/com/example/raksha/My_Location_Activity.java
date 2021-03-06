
package com.example.raksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.security.Permission;
import android.Manifest;

public class My_Location_Activity extends FragmentActivity implements OnMapReadyCallback {
    Location mlocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int Request_code=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__location_);

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        GetlastLocation();
    }

    private void GetlastLocation() {
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},Request_code);
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location !=null){
                    mlocation=location;
                    Toast.makeText(getApplicationContext(),mlocation.getLatitude()+""+mlocation.getLongitude(),
                            Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment= (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    supportMapFragment.getMapAsync(My_Location_Activity.this);


                }


            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        LatLng latLng=new LatLng(mlocation.getLatitude(),mlocation.getLongitude());
        MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("I am Here");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,6));
        googleMap.addMarker(markerOptions);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case Request_code:
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                GetlastLocation();
            }
            break;
        }
    }
}





