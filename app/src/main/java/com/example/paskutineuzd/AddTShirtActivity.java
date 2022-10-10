package com.example.paskutineuzd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddTShirtActivity extends AppCompatActivity {
    EditText inputBrand,inputCost,inputDescription;
    Button btnadd;
    DatabaseReference tshirtRef = FirebaseDatabase
            .getInstance("https://paslaugos-a64b7-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference().child("tshirt");
    long id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tshirt);

        inputBrand=findViewById(R.id.tshirtbrand);
        inputCost=findViewById(R.id.tshirtcost);
        inputDescription=findViewById(R.id.tshirtdescription);
        btnadd=findViewById(R.id.addnewtshirt);
        tshirtRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    id=(snapshot.getChildrenCount()+1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tshirtbrand= inputBrand.getText().toString();
                String tshirtdesc= inputDescription.getText().toString();
                String tshirtcost= inputCost.getText().toString();


                Tshirt shoe = new Tshirt(String.valueOf(id),tshirtbrand,tshirtcost,tshirtdesc);
                tshirtRef.child(String.valueOf(id)).setValue(shoe).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddTShirtActivity.this,"T-Shirt Added!",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(AddTShirtActivity.this,LoggedInActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });
    }
}