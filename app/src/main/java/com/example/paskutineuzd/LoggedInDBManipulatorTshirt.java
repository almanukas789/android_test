package com.example.paskutineuzd;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoggedInDBManipulatorTshirt {
    DatabaseReference tshirtRef = FirebaseDatabase
            .getInstance("https://paslaugos-a64b7-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference().child("tshirt");

    public interface DataStatus{
        void DataIsLoaded(List<Tshirt> tshirts, List<String> keys);
    }
    private List<Tshirt> tshirts= new ArrayList<>();
    public void readTshirt(final DatabaseManipulatorTshirt.DataStatus dataStatus){
        tshirtRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tshirts.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: snapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    Tshirt tshirt =keyNode.getValue(Tshirt.class);
                    tshirts.add(tshirt);
                }
                dataStatus.DataIsLoaded(tshirts,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
