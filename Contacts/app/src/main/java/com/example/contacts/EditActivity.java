package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class EditActivity extends AppCompatActivity {

    private Intent resultIntent;
    private EditText editName, editNumber, editEmail;
    private ImageView avatar;

    public static final int ADD_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;
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

        try {
            Intent intent = getIntent();
            editName.setText(intent.getExtras().getString(NAME));
            editNumber.setText(intent.getExtras().getString(NUMBER));
            editEmail.setText(intent.getExtras().getString(EMAIL));
        } catch (Exception e) { }
        resultIntent = new Intent();
        setResult(RESULT_CANCELED, resultIntent);
    }

    public void onClick(View view) {
        resultIntent.putExtra(NAME, editName.getText().toString());
        resultIntent.putExtra(NUMBER, editNumber.getText().toString());
        resultIntent.putExtra(EMAIL, editEmail.getText().toString());
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
