package com.example.mydata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        idList=getIntent().getIntegerArrayListExtra("idlist");
        nameList=getIntent().getStringArrayListExtra("namelist");
        emailList=getIntent().getStringArrayListExtra("emaillist");

        RecyclerAdapter adapter=new RecyclerAdapter(RecycleviewActivity.this, idList,nameList,emailList);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerview.setLayoutManager(manager);
        binding.recyclerview.setAdapter(adapter);
    }
}