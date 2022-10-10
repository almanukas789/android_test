package com.example.paskutineuzd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button loginbtn,registerbtn,shoefilterbtn,tshirtfilterbtn,delete;
    RecyclerView recyclerView;
    TextView filteris;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginbtn=(Button) findViewById(R.id.loginMain);
        registerbtn=(Button) findViewById(R.id.register);
        recyclerView=findViewById(R.id.recview);
       shoefilterbtn=findViewById(R.id.filterShoe);
       tshirtfilterbtn=findViewById(R.id.filterTshirt);
        filteris=(TextView) findViewById(R.id.filterview);

       shoefilterbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               new DatabaseManipulator().readShoes(new DatabaseManipulator.DataStatus() {
                   @Override
                   public void DataIsLoaded(List<Shoe> shoes, List<String> keys) {
                       filteris.setText("Shoes");
                       new Shoe_view_config().setConfig(recyclerView,MainActivity.this,shoes,keys);
                   }
               });
           }
       });
       tshirtfilterbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               new DatabaseManipulatorTshirt().readTshirt(new DatabaseManipulatorTshirt.DataStatus() {
                   @Override
                   public void DataIsLoaded(List<Tshirt> tshirts, List<String> keys) {
                       filteris.setText("Tshirts");
                       new Tshirt_view_config().setConfig(recyclerView,MainActivity.this,tshirts,keys);
                   }
               });
           }
       });












        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}