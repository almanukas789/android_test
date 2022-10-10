package com.example.paskutineuzd;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Shoe_view_config {

    private Context context;
    private ShoeAdapter shoeAdapter;

    public void setConfig(RecyclerView recyclerView,Context mcontext,List<Shoe> shoes,List<String>keys){
        context=mcontext;
        shoeAdapter=new ShoeAdapter(shoes,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
        recyclerView.setAdapter(shoeAdapter);
    }
    class ShoeItemView extends RecyclerView.ViewHolder {
        private TextView mbrand;
        private TextView mcost;
        private TextView mdesc;
        private TextView mid;

        private String key;

        public ShoeItemView(ViewGroup parent){
            super(LayoutInflater.from(context).inflate(R.layout.shoeentry, parent, false));

            mbrand=(TextView) itemView.findViewById(R.id.listshoebrand);
            mcost=(TextView) itemView.findViewById(R.id.listshoecost);
            mdesc=(TextView) itemView.findViewById(R.id.listshoedescription);

        }
        public void bind (Shoe shoe,String key){
            mbrand.setText(shoe.getShoeBrand());
            mcost.setText(shoe.getShoeCost() + " $");
            mdesc.setText(shoe.getShoeDescription());
            this.key=key;


        }

    }
    class  ShoeAdapter extends RecyclerView.Adapter<ShoeItemView>{
        private List<Shoe> mShoe;
        private List<String>mKeys;

        public ShoeAdapter(List<Shoe> mShoe, List<String> mKeys) {
            this.mShoe = mShoe;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public ShoeItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ShoeItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ShoeItemView holder, int position) {
            holder.bind(mShoe.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mShoe.size();
        }
    }
}
