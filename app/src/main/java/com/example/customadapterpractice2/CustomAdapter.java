package com.example.customadapterpractice2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter
{
    Activity activity;
    int[] imageArray;

    ArrayList<Integer> imageArrayList;

public CustomAdapter(Activity activity,ArrayList<Integer> imageArrayList) {
    this.activity=activity;
    this.imageArrayList = imageArrayList;
}

    @Override
    public int getCount() {
        return imageArrayList.size();
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
        convertView=LayoutInflater.from(activity).inflate(R.layout.gallery_grid_layout,null);
        ImageView image=convertView.findViewById(R.id.imageView);

        image.setImageResource(imageArrayList.get(position));
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ImageActivity.class);
                intent.putExtra("imageId",imageArrayList.get(position));
                activity.startActivity(intent);
            }
        });

        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Delete Item");
                builder.setMessage("Are you sure you want to delete this item ?");
                builder.setIcon(R.drawable.image4);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       imageArrayList.remove(imageArrayList.get(position));
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
