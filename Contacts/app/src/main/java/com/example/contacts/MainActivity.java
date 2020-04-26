package com.example.contacts;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
        if (resultCode == RESULT_OK) {
            ListItem item = null;
            int position = -1;
            if (requestCode == EditActivity.ADD_REQUEST) {
                Uri avatar = Uri.parse(data.getExtras().getString(EditActivity.AVATAR));
                String name = data.getExtras().getString(EditActivity.NAME);
                String number = data.getExtras().getString(EditActivity.NUMBER);
                String email = data.getExtras().getString(EditActivity.EMAIL);
                item = new ListItem(avatar, name, number, email);
                position = list.size();
                list.add(item);
                adapter.notifyDataSetChanged();
            }
            if (requestCode == EditActivity.EDIT_REQUEST) {
                position = data.getExtras().getInt(EditActivity.POSITION);
                item = list.get(position);
                item.avatar = Uri.parse(data.getExtras().getString(EditActivity.AVATAR));
                item.name = data.getExtras().getString(EditActivity.NAME);
                item.number = data.getExtras().getString(EditActivity.NUMBER);
                item.email = data.getExtras().getString(EditActivity.EMAIL);
                list.set(position, item);
                adapter.notifyDataSetChanged();
            }
            SharedPreferences preferences = getSharedPreferences(EditActivity.PREF, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(EditActivity.AVATAR + position, item.avatar.toString());
            editor.putString(EditActivity.NAME + position, item.name);
            editor.putString(EditActivity.NUMBER + position, item.number);
            editor.putString(EditActivity.EMAIL + position, item.email);
            editor.putInt(EditActivity.POSITION, list.size() - 1);
            editor.apply();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setData() {
        list = new ArrayList<>();
        SharedPreferences preferences = getSharedPreferences(EditActivity.PREF, MODE_PRIVATE);
        int position = preferences.getInt(EditActivity.POSITION, -1);
        for (int i = 0; i <= position; i++) {
            Uri avatar = Uri.parse(preferences.getString(EditActivity.AVATAR + i, ""));
            String name = preferences.getString(EditActivity.NAME + i, "");
            String number = preferences.getString(EditActivity.NUMBER + i, "");
            String email = preferences.getString(EditActivity.EMAIL + i, "");
            list.add(new ListItem(avatar, name, number, email));
        }
    }
    public static Uri getUriToDrawable(@NonNull Context context,
                                       @AnyRes int drawableId) {
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId) );
        return imageUri;
    }
    public void onClick(View view) {
        Intent addIntent = new Intent(this, EditActivity.class);
        addIntent.putExtra(EditActivity.AVATAR, getUriToDrawable(this, android.R.drawable.star_on).toString());
        addIntent.putExtra(EditActivity.NAME, "");
        addIntent.putExtra(EditActivity.NUMBER, "");
        addIntent.putExtra(EditActivity.EMAIL, "");
        addIntent.putExtra(EditActivity.POSITION, -1);
        startActivityForResult(addIntent, EditActivity.ADD_REQUEST);
    }
}
