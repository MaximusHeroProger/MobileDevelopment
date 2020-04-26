package com.example.contacts;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListAdapter adapter;
    List<ListItem> list;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
        adapter = new ListAdapter(this, list);
        recyclerView = findViewById(R.id.listView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {
            if (requestCode == EditActivity.ADD_REQUEST) {
                Drawable avatar = getResources().getDrawable(android.R.drawable.ic_delete);
                String name = data.getExtras().getString(EditActivity.NAME);
                String number = data.getExtras().getString(EditActivity.NUMBER);
                String email = data.getExtras().getString(EditActivity.EMAIL);
                ListItem item = new ListItem(avatar, name, number, email);
                list.add(item);
                adapter.notifyDataSetChanged();
            }
            if (requestCode == EditActivity.EDIT_REQUEST) {
                int position = data.getExtras().getInt(EditActivity.POSITION);
                ListItem item = list.get(position);
                item.name = data.getExtras().getString(EditActivity.NAME);
                item.number = data.getExtras().getString(EditActivity.NUMBER);
                item.email = data.getExtras().getString(EditActivity.EMAIL);
                list.set(position, item);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setData() {
        list = new ArrayList<>();
        list.add(new ListItem(
                getDrawable(android.R.drawable.ic_delete),
                "Максим Хоцевич",
                "+7 (921) 085-53-50",
                "xozewitc@yandex.ru")
        );
    }

    public void onClick(View view) {
        Intent addIntent = new Intent(this, EditActivity.class);
        startActivityForResult(addIntent, EditActivity.ADD_REQUEST);
    }
}
