package com.example.paskutineuzd;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Loggedin_shoe_config {
    private Context context;
    private Loggedin_shoe_config.ShoeAdapter shoeAdapter;

    public void setConfig(RecyclerView recyclerView, Context mcontext, List<Shoe> shoes, List<String>keys){
        context=mcontext;
        shoeAdapter=new Loggedin_shoe_config.ShoeAdapter(shoes,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
        recyclerView.setAdapter(shoeAdapter);
    }
    class ShoeItemView extends RecyclerView.ViewHolder {
        private TextView mbrand;
        private TextView mcost;
        private TextView mdesc;
        private TextView mid;
        private Button mdelete,medit;

        private String key;

        public ShoeItemView(ViewGroup parent){
            super(LayoutInflater.from(context).inflate(R.layout.logedinentry, parent, false));

            mbrand=(TextView) itemView.findViewById(R.id.listshoebrand);
            mcost=(TextView) itemView.findViewById(R.id.listshoecost);
            mdesc=(TextView) itemView.findViewById(R.id.listshoedescription);
            mdelete=(Button) itemView.findViewById(R.id.mygdel);
            medit=(Button) itemView.findViewById(R.id.mygedit);
        }
        public void bind (Shoe shoe,String key){
            mbrand.setText(shoe.getShoeBrand());
            mcost.setText(shoe.getShoeCost() + " $");
            mdesc.setText(shoe.getShoeDescription());
            this.key=key;

            medit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);

                    alert.setTitle("Edit");
                    alert.setMessage("\n Edit cost of shoe");
                    final EditText input = new EditText(context);
                    input.setInputType(2);
                    alert.setView(input);

                    alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Editable value = input.getText();
                            DatabaseReference shoeRef = FirebaseDatabase
                                    .getInstance("https://paslaugos-a64b7-default-rtdb.europe-west1.firebasedatabase.app")
                                    .getReference().child("shoe").child(shoe.getId()).child("shoeCost");
                            shoeRef.setValue(value.toString());
                        }
                    });

                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Canceled.
                        }
                    });

                    alert.show();
                }
            });
            mdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Delete");
                    builder.setMessage("Are you sure want to delete: " + shoe.getShoeBrand());
                    builder.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Task<Void> tshirtRef = FirebaseDatabase
                                            .getInstance("https://paslaugos-a64b7-default-rtdb.europe-west1.firebasedatabase.app")
                                            .getReference().child("shoe").child(shoe.getId()).removeValue();
                                }
                            });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            });
        }

    }

    class  ShoeAdapter extends RecyclerView.Adapter<Loggedin_shoe_config.ShoeItemView>{
        private List<Shoe> mShoe;
        private List<String>mKeys;

        public ShoeAdapter(List<Shoe> mShoe, List<String> mKeys) {
            this.mShoe = mShoe;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public Loggedin_shoe_config.ShoeItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Loggedin_shoe_config.ShoeItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull Loggedin_shoe_config.ShoeItemView holder, int position) {
            holder.bind(mShoe.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mShoe.size();
        }
    }
}
