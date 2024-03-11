package com.example.customadapterpractice2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class CustomAdapter2 extends BaseAdapter {
    Activity activity;
    ArrayList<String> namesArrayList,msgArrayList;
    ArrayList<Integer> imgArrayList;
    public CustomAdapter2(Activity activity, ArrayList<Integer> imgArray, ArrayList<String> namesArray, ArrayList<String> msgArray) {
        this.activity=activity;
        this.imgArrayList=imgArray;
        this.namesArrayList=namesArray;
        this.msgArrayList=msgArray;
    }

    @Override
    public int getCount() {
        return imgArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(position%2!=0){
            convertView= LayoutInflater.from(activity).inflate(R.layout.zigzag_layout,null);
        }
        else{
            convertView= LayoutInflater.from(activity).inflate(R.layout.zigzag_layout2,null);
        }

        ImageView image=convertView.findViewById(R.id.imgZigZag);
        TextView name = convertView.findViewById(R.id.name);
        TextView message = convertView.findViewById(R.id.message);
        LinearLayout linearLayout = convertView.findViewById(R.id.linearLayout);

        image.setImageResource(imgArrayList.get(position));
        name.setText(namesArrayList.get(position));
        message.setText(msgArrayList.get(position));


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ImageActivity.class);
                intent.putExtra("imageId",imgArrayList.get(position));
                activity.startActivity(intent);
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Delete Item");
                builder.setMessage("Are you sure you want to delete this item ?");
                builder.setIcon(R.drawable.image4);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        imgArrayList.remove(imgArrayList.get(position));
                        namesArrayList.remove(namesArrayList.get(position));
                        msgArrayList.remove(msgArrayList.get(position));
                        notifyDataSetChanged();

                        //can do the same for multiple such ArrayLists
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
        return convertView;
    }
}
