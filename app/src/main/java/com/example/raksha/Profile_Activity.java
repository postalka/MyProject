package com.example.raksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Profile_Activity extends AppCompatActivity {
    TextView Personal_Details;
    EditText Name, Email, Mobile_No, Address, Pin_Code, Guardians_Phone_No ;
    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);

        Personal_Details = (TextView) findViewById(R.id.Pro1);
        Name = (EditText) findViewById(R.id.Pro2);
        Email = (EditText) findViewById(R.id.Pro3);
        Mobile_No = (EditText) findViewById(R.id.pro4);
        Address = (EditText) findViewById(R.id.Pro5);
        Pin_Code = (EditText) findViewById(R.id.Pro6);
        Submit = (Button) findViewById(R.id.Pro7);
        Guardians_Phone_No = (EditText) findViewById(R.id.pro8);



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_Activity.this,HomeActivity.class);
                startActivity(intent);

                HashMap<String,Object> map= new HashMap<>();
                map.put("Name",Name.getText().toString());
                map.put("Email",Email.getText().toString());
                map.put("Mobile_No",Mobile_No.getText().toString());
                map.put("Address",Address.getText().toString());
                map.put("Pin_Code",Pin_Code.getText().toString());
                map.put("Guardians_Phone_No",Guardians_Phone_No.getText().toString());




                FirebaseDatabase.getInstance().getReference().child("post").push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("jug","onComplete: ");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("jug","onFailure: "+e.toString());

                    }
                });





            }
        });
    }

}



