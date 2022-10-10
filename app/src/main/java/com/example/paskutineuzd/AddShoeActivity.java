package com.example.paskutineuzd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddShoeActivity extends AppCompatActivity {

    EditText inputBrand,inputCost,inputDescription;
    Button btnadd;
    DatabaseReference shoeRef = FirebaseDatabase
            .getInstance("https://paslaugos-a64b7-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference().child("shoe");
    long id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shoe);

        inputBrand=findViewById(R.id.shoebrand);
        inputCost=findViewById(R.id.shoecost);
        inputDescription=findViewById(R.id.shoedescription);
        btnadd=findViewById(R.id.addnewshoe);
        shoeRef.addValueEventListener(new ValueEventListener() {
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
                String shoebrand= inputBrand.getText().toString();
                String shoedescription= inputDescription.getText().toString();
                String shoecost= inputCost.getText().toString();

                Shoe shoe = new Shoe(String.valueOf(id),shoebrand,shoecost,shoedescription);
                shoeRef.child(String.valueOf(id)).setValue(shoe).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                      Toast.makeText(AddShoeActivity.this,"Shoe Added!",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(AddShoeActivity.this,LoggedInActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });
    }
}