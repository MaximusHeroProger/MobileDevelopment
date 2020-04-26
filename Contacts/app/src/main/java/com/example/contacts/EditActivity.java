package com.example.contacts;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;

public class EditActivity extends AppCompatActivity {

    private Intent resultIntent;
    private EditText editName, editNumber, editEmail;
    private ImageView avatar;
    private Uri avatarUri;
    private int position;

    public static final int ADD_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;
    public static final int SELECT_REQUEST = 3;
    public static final String PREF = "PREF";
    public static final String NAME = "NAME";
    public static final String NUMBER = "NUMBER";
    public static final String EMAIL = "EMAIL";
    public static final String AVATAR = "AVATAR";
    public static final String POSITION = "POSITION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editName = findViewById(R.id.editName);
        editNumber = findViewById(R.id.editNumber);
        editEmail = findViewById(R.id.editEmail);
        avatar = findViewById(R.id.avatar);

        Intent intent = getIntent();
        avatarUri = Uri.parse(intent.getExtras().getString(AVATAR));
        avatar.setImageURI(avatarUri);
        editName.setText(intent.getExtras().getString(NAME));
        editNumber.setText(intent.getExtras().getString(NUMBER));
        editEmail.setText(intent.getExtras().getString(EMAIL));
        position = intent.getExtras().getInt(POSITION);
        resultIntent = new Intent();
        setResult(RESULT_CANCELED, resultIntent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == SELECT_REQUEST) {
                avatarUri = data.getData();
                avatar.setImageURI(avatarUri);
            }
        }
    }
    public void onChangeImage(View view) {
        Intent selectIntent = new Intent(Intent.ACTION_PICK);
        selectIntent.setType("image/*");
        startActivityForResult(selectIntent, SELECT_REQUEST);
    }
    public void onClick(View view) {
        resultIntent.putExtra(AVATAR, avatarUri.toString());
        resultIntent.putExtra(NAME, editName.getText().toString());
        resultIntent.putExtra(NUMBER, editNumber.getText().toString());
        resultIntent.putExtra(EMAIL, editEmail.getText().toString());
        resultIntent.putExtra(POSITION, position);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
