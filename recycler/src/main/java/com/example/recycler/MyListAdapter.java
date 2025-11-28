package com.example.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Random;

public class MyListAdapter extends RecyclerView.Adapter <MyListAdapter.ViewHolder> {
    private MyListData[] listData;
    public MyListAdapter(MyListData[] listData){
        this.listData=listData;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItem=layoutInflater.inflate(R.layout.list_item,parent,false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyListData myListData=listData[position];
        holder.textView.setText(listData[position].getTitle());
        holder.imageView.setImageResource(listData[position].getImageid());
        Random random =new Random();
        int randomHeight=random.nextInt(500)+200;
//        int randomwidth=random.nextInt(400)+300;
//        ViewGroup.LayoutParams params=holder.imageView.getLayoutParams();
//        params.height=randomHeight;
//        holder.imageView.setLayoutParams(params);
//        holder.linearLayout.getLayoutParams().width=randomwidth;
        holder.linearLayout.getLayoutParams().height=randomHeight;
        holder.linearLayout.requestLayout();


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Clicked on item: "+myListData.getTitle(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView=itemView.findViewById(R.id.imageView);
            this.textView=itemView.findViewById(R.id.textView);
            this.linearLayout=itemView.findViewById(R.id.linearLayout);
        }
    }
}
