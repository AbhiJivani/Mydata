package com.example.mydata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.mydata.databinding.ActivityRecycleviewBinding;

import java.util.ArrayList;

public class RecycleviewActivity extends AppCompatActivity {
    ActivityRecycleviewBinding binding;
    ArrayList<Integer> idList=new ArrayList();
    ArrayList<String> nameList=new ArrayList<>();
    ArrayList<String> emailList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        binding=ActivityRecycleviewBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        showData();

    }

    private void showData()
    {
        MydataBase mydataBase=new MydataBase(RecycleviewActivity.this);
        Cursor cursor=mydataBase.showData();
        while (cursor.moveToNext())
        {
            idList.add(cursor.getInt(0));
            nameList.add(cursor.getString(1));
            emailList.add(cursor.getString(2));
        }
        RecyclerAdapter adapter=new RecyclerAdapter(RecycleviewActivity.this, idList,nameList,emailList);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerview.setLayoutManager(manager);
        binding.recyclerview.setAdapter(adapter);
    }
}