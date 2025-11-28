package com.example.recycler;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyListData[] myListData=new MyListData[]{
                new MyListData("image 1",R.drawable.image_1),
                new MyListData("image 2",R.drawable.image_2),
                new MyListData("image 3",R.drawable.image_3),
                new MyListData("image 4",R.drawable.image_4),
                new MyListData("image 5",R.drawable.image_5),
                new MyListData("image 6",R.drawable.image_6),
                new MyListData("image 7",R.drawable.image_7),
                new MyListData("image 8",R.drawable.image_8),
                new MyListData("image 9",R.drawable.image_9),
                new MyListData("image 10",R.drawable.image_10)




        };

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        MyListAdapter adapter =new MyListAdapter(myListData);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

//        int numberofcolumns=2;
//        recyclerView.setLayoutManager(new GridLayoutManager(this,numberofcolumns));
//        recyclerView.setAdapter(adapter);

        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



    }
}