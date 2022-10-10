package com.example.paskutineuzd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Tshirt_view_config {
    private Context context;
    private Tshirt_view_config.tshirtAdapter tshirtAdapter;

    public void setConfig(RecyclerView recyclerView, Context mcontext, List<Tshirt> tshirts, List<String>keys){
        context=mcontext;
        tshirtAdapter=new Tshirt_view_config.tshirtAdapter(tshirts,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
        recyclerView.setAdapter(tshirtAdapter);
    }
    class TshirtItemView extends RecyclerView.ViewHolder {
        private TextView mbrand;
        private TextView mcost;
        private TextView mdesc;
        private TextView mid;

        private String key;

        public TshirtItemView(ViewGroup parent){
            super(LayoutInflater.from(context).inflate(R.layout.shoeentry, parent, false));

            mbrand=(TextView) itemView.findViewById(R.id.listshoebrand);
            mcost=(TextView) itemView.findViewById(R.id.listshoecost);
            mdesc=(TextView) itemView.findViewById(R.id.listshoedescription);


        }
        public void bind (Tshirt tshirt, String key){
            mbrand.setText(tshirt.getTshirtBrand());
            mcost.setText(tshirt.getTshirtCost() + " $");
            mdesc.setText(tshirt.getTshirtDesc());
            this.key=key;
        }

    }
    class tshirtAdapter extends RecyclerView.Adapter<Tshirt_view_config.TshirtItemView>{
        private List<Tshirt> mShoe;
        private List<String>mKeys;

        public tshirtAdapter(List<Tshirt> mShoe, List<String> mKeys) {
            this.mShoe = mShoe;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public Tshirt_view_config.TshirtItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Tshirt_view_config.TshirtItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull Tshirt_view_config.TshirtItemView holder, int position) {
            holder.bind(mShoe.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mShoe.size();
        }
    }
}
