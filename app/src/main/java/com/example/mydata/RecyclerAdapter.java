package com.example.mydata;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

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
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerHolder holder, int position) {
        holder.idtxt.setText(""+idList.get(position));
        holder.nametxt.setText(""+nameList.get(position));
        holder.emailtxt.setText(""+emailList.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MydataBase mydataBase=new MydataBase(recycleviewActivity);
                mydataBase.deleteData(idList.get(holder.getAdapterPosition()));
                notifyDataSetChanged();
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
