package com.example.raksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public EditText Enter_Username;
    public EditText Enter_Password;
    public Button SAVE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Enter_Username =(EditText)findViewById(R.id.tv2);
        Enter_Password =(EditText)findViewById(R.id.tv3);
        SAVE =(Button)findViewById(R.id.tv4);

        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String struname, strpassword;
                struname= Enter_Username.getText().toString();
                strpassword= Enter_Password.getText().toString();
                Intent intent =new Intent(MainActivity.this,HomeActivity.class);
                intent.putExtra("Username",struname);

                intent.putExtra("Password",strpassword);
                startActivity(intent);

                HashMap<String,Object> map= new HashMap<>();
                map.put("Enter_Username",Enter_Username.getText().toString());
                map.put("Enter_Password",Enter_Password.getText().toString());

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
