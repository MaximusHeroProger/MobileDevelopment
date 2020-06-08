package com.example.contactssql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class EditActivity extends AppCompatActivity {

    public static final int ADD = 0;
    public static final int EDIT = 1;
    public static final int SELECT = 2;
    public static final String SURNAME = "SURNAME";
    public static final String NAME = "NAME";
    public static final String PATRONYMIC = "PATRONYMIC";
    public static final String PHONE = "PHONE";
    public static final String EMAIL = "EMAIL";
    public static final String POSITION = "POSITION";
    public static final String PHOTO = "PHOTO";
    Intent result;
    EditText editSurname, editName, editPatronymic, editPhone, editEmail;
    ImageView imageEdit;
    int position;
    Uri photoUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editSurname = findViewById(R.id.editSurname);
        editName = findViewById(R.id.editName);
        editPatronymic = findViewById(R.id.editPatronymic);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
        imageEdit = findViewById(R.id.imageEdit);

        Intent intent = getIntent();
        if (intent.getExtras() !=  null) {
            position = intent.getExtras().getInt(POSITION);
            editSurname.setText(intent.getExtras().getString(SURNAME));
            editName.setText(intent.getExtras().getString(NAME));
            editPatronymic.setText(intent.getExtras().getString(PATRONYMIC));
            editPhone.setText(intent.getExtras().getString(PHONE));
            editEmail.setText(intent.getExtras().getString(EMAIL));
            photoUri = Uri.parse(intent.getExtras().getString(PHOTO));

        } else {
            photoUri = MainActivity.getUriFromDrawable(this, android.R.drawable.ic_menu_camera);
        }
        imageEdit.setImageURI(photoUri);
        result = new Intent();
        setResult(RESULT_CANCELED, result);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == SELECT) {
                photoUri = data.getData();
                imageEdit.setImageURI(photoUri);
            }
        }
    }
    public void onSaveClick(View view) {
        result.putExtra(POSITION, position);
        result.putExtra(SURNAME, editSurname.getText().toString());
        result.putExtra(NAME, editName.getText().toString());
        result.putExtra(PATRONYMIC, editPatronymic.getText().toString());
        result.putExtra(PHONE, editPhone.getText().toString());
        result.putExtra(EMAIL, editEmail.getText().toString());
        result.putExtra(PHOTO, photoUri.toString());
        setResult(RESULT_OK, result);
        finish();
    }
    public void onSelectPhoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT);
    }
}
