package com.example.contacts;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private List<ListItem> list;

    ListAdapter(Context context, List<ListItem> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ListItem item = list.get(position);
        holder.avatar.setImageURI(item.avatar);
        holder.name.setText(item.name);
        holder.number.setText(item.number);
        holder.email.setText(item.email);
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(context, EditActivity.class);
                editIntent.putExtra(EditActivity.AVATAR, item.avatar.toString());
                editIntent.putExtra(EditActivity.NAME, item.name);
                editIntent.putExtra(EditActivity.NUMBER, item.number);
                editIntent.putExtra(EditActivity.EMAIL, item.email);
                editIntent.putExtra(EditActivity.POSITION, position);
                ((Activity)context).startActivityForResult(editIntent, EditActivity.EDIT_REQUEST);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                SharedPreferences preferences = context.getSharedPreferences(EditActivity.PREF, context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove(EditActivity.AVATAR + list.size());
                editor.remove(EditActivity.NAME + list.size());
                editor.remove(EditActivity.NUMBER + list.size());
                editor.remove(EditActivity.EMAIL + list.size());
                editor.putInt(EditActivity.POSITION, list.size() - 1);
                for (int i = position; i < list.size(); i++) {
                    editor.putString(EditActivity.AVATAR + i, list.get(i).avatar.toString());
                    editor.putString(EditActivity.NAME + i, list.get(i).name);
                    editor.putString(EditActivity.NUMBER + i, list.get(i).number);
                    editor.putString(EditActivity.EMAIL + i, list.get(i).email);
                }
                editor.apply();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() { return list.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name, number, email;
        Button editButton, deleteButton;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatarView);
            name = itemView.findViewById(R.id.nameView);
            number = itemView.findViewById(R.id.numberView);
            email = itemView.findViewById(R.id.emailView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
