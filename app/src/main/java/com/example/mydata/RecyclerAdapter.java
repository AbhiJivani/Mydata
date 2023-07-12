package com.example.mydata;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    private final ArrayList<Integer> idList;
    private final ArrayList<String> nameList;
    private final ArrayList<String> emailList;
    RecycleviewActivity recycleviewActivity;


    public RecyclerAdapter(RecycleviewActivity recycleviewActivity, ArrayList<Integer> idList, ArrayList<String> nameList, ArrayList<String> emailList) {
        this.recycleviewActivity = recycleviewActivity;
        this.idList = idList;
        this.nameList = nameList;
        this.emailList = emailList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view=LayoutInflater.from(recycleviewActivity).inflate(R.layout.recycleview_item,parent,false);
    RecyclerHolder holder=new RecyclerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.idtxt.setText(""+idList.get(position));
        holder.nametxt.setText(""+nameList.get(position));
        holder.emailtxt.setText(""+emailList.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MydataBase mydataBase=new MydataBase(recycleviewActivity);
//                mydataBase.deleteData(idList.get(holder.getAdapterPosition()));
//                idList.remove(position);
//                nameList.remove(holder.getAdapterPosition());
//                emailList.remove(position);
//                notifyDataSetChanged();
                PopupMenu popupMenu=new PopupMenu(recycleviewActivity,holder.button);
                recycleviewActivity.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId()==R.id.update)
                        {
                            Toast.makeText(recycleviewActivity, "Update", Toast.LENGTH_LONG).show();
                            Dialog dialog=new Dialog(recycleviewActivity);
                            dialog.setContentView(R.layout.dialog_layout);
                            EditText txt1,txt2;
                            Button update;
                            txt1=dialog.findViewById(R.id.dialog_Name);
                            txt2=dialog.findViewById(R.id.dialog_email);
                            update=dialog.findViewById(R.id.dialog_Update);
                            txt1.setText(""+nameList.get(position));
                            txt2.setText(""+emailList.get(position));
                            update.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    MydataBase mydataBase=new MydataBase(recycleviewActivity.getApplicationContext());
                                    mydataBase.updateData(idList.get(position),txt1.getText().toString(),txt2.getText().toString());
                                    Intent intent=new Intent(recycleviewActivity,RecycleviewActivity.class);
                                    recycleviewActivity.startActivity(intent);
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        }
                        if(menuItem.getItemId()==R.id.delete)
                        {
                            Toast.makeText(recycleviewActivity, "Delete", Toast.LENGTH_LONG).show();
                            MydataBase mydataBase = new MydataBase(recycleviewActivity);
                            mydataBase.deleteData(idList.get(holder.getAdapterPosition()));
                            idList.remove(position);
                            nameList.remove(holder.getAdapterPosition());
                            emailList.remove(position);
                            notifyDataSetChanged();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return idList.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView idtxt;
        TextView nametxt;
        TextView emailtxt;
        ImageButton button;
        public RecyclerHolder(View view) {
            super(view);
            idtxt=view.findViewById(R.id.txtId);
            nametxt=view.findViewById(R.id.name);
            emailtxt=view.findViewById(R.id.email);
            button=view.findViewById(R.id.more);
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    MydataBase mydataBase=new MydataBase(recycleviewActivity);
                    mydataBase.updateData(idList.get(getAdapterPosition()),nameList.get(getAdapterPosition()),emailList.get(getAdapterPosition()));
                    return true;
                }
            });

        }
    }
}
