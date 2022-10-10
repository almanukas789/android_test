package com.example.paskutineuzd;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManipulator {
    DatabaseReference shoeRef = FirebaseDatabase
            .getInstance("https://paslaugos-a64b7-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference().child("shoe");

    public interface DataStatus{
        void DataIsLoaded(List<Shoe> shoes,List<String> keys);
    }
    private List<Shoe> shoes= new ArrayList<>();
    public void readShoes(final DataStatus dataStatus){
        shoeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                shoes.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: snapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    Shoe shoe =keyNode.getValue(Shoe.class);
                    shoes.add(shoe);
                }
                dataStatus.DataIsLoaded(shoes,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
