package com.example.mydata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mydata.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<Integer> idList=new ArrayList();
    ArrayList<String> nameList=new ArrayList<>();
    ArrayList<String> emailList=new ArrayList<>();
    int id;
    String name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        MydataBase mydataBase=new MydataBase(MainActivity.this);
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=binding.editName.getText().toString();
                String email=binding.editemail.getText().toString();
                mydataBase.addData(name,email);
                binding.editName.setText("");
                binding.editemail.setText("");
            }
        });
        binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor=mydataBase.showData();
                while (cursor.moveToNext())
                {
//                    id=cursor.getInt(0);
//                    name=cursor.getString(1);
//                    email=cursor.getString(2);
                    idList.add(cursor.getInt(0));
                    nameList.add(cursor.getString(1));
                    emailList.add(cursor.getString(2));


                }
                Log.d("TTT", "onClick: id="+idList+"\tName="+nameList+"\tEmail="+emailList);
                Intent intent=new Intent(MainActivity.this,RecycleviewActivity.class);
                intent.putExtra("idlist",idList);
                intent.putExtra("namelist",nameList);
                intent.putExtra("emaillist",emailList);
                startActivity(intent);
            }
        });
    }


}