package com.example.paskutineuzd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoggedInActivity extends AppCompatActivity {
    Button addshoes,addtshirt,filtertshirt,filtershoe;
    RecyclerView recyclerView;
    TextView filteris;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        filteris=findViewById(R.id.viewforloggedin);
        recyclerView=findViewById(R.id.listforloggedin);
        addshoes=findViewById(R.id.addshoe);
        addtshirt=findViewById(R.id.addtshirt);
        filtertshirt=findViewById(R.id.filterTshirt2);
        filtershoe=findViewById(R.id.filterShoe2);

        filtershoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatabaseManipulator().readShoes(new DatabaseManipulator.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Shoe> shoes, List<String> keys) {
                        filteris.setText("Shoes");
                        new Loggedin_shoe_config().setConfig(recyclerView,LoggedInActivity.this,shoes,keys);
                    }
                });
            }
        });

        filtertshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatabaseManipulatorTshirt().readTshirt(new DatabaseManipulatorTshirt.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Tshirt> tshirts, List<String> keys) {
                        filteris.setText("Tshirts");
                        new LoggedIn_tshirt_config().setConfig(recyclerView,LoggedInActivity.this,tshirts,keys);
                    }
                });
            }
        });
        addtshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoggedInActivity.this,AddTShirtActivity.class);
                startActivity(intent);
            }
        });
        addshoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoggedInActivity.this,AddShoeActivity.class);
                startActivity(intent);
            }
        });
    }
}