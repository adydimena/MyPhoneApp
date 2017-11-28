package com.example.ady.myphoneapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        List<MyContacts> myContacts = new ArrayList<>();
        DataBaseHelper database = new DataBaseHelper(this);


        Cursor result = database.getallData();
        if (result.getCount() == 0){
            return;
        }
        while(result.moveToNext()){
            myContacts.add(new MyContacts(result.getString(0)
                    ,result.getString(1)
                    ,result.getString(2)
                    ,result.getString(3)
                    ,result.getString(4)));

        }
      //  Recycleadapter.OnItemClickListener listener =null;

        RecyclerView recyclerView = findViewById(R.id.recycleName);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(Main2Activity.this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        Recycleadapter recycleadapter = new Recycleadapter(myContacts);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setAdapter(recycleadapter);
        /*
        TextView trying = findViewById(R.id.trying);

        for (int i = 0; i <myContacts.size() ; i++) {
            trying.append("Name: " + myContacts.get(i).getName() + "\n"
                    +"Number: " + myContacts.get(i).getNumber() + "\n"
                    +"Email: " + myContacts.get(i).getEmail() + "\n"
                    +"Address: " + myContacts.get(i).getAddress() + "\n"
                    +"Favorite Sport: " + myContacts.get(i).getSport() + "\n");

        }
        */
    }
}
