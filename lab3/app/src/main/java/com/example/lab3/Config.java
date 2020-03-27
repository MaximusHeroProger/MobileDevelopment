package com.example.lab3;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class Config extends Activity {
    public final static String WIDGET_PREF = "widget_pref";
    public final static String WIDGET_TEXT = "widget_text_";
    public final static String WIDGET_COLOR = "widget_color_";

    int widgetID = AppWidgetManager.INVALID_APPWIDGET_ID;
    Intent resultValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) widgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        if (widgetID == AppWidgetManager.INVALID_APPWIDGET_ID) finish();
        resultValue = new Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        setResult(RESULT_CANCELED, resultValue);
        setContentView(R.layout.config);
    }
    public void onClick(View view) {
        RadioGroup colorsRadioGroup = findViewById(R.id.colorsRadioGroup);
        int color;
        switch (colorsRadioGroup.getCheckedRadioButtonId()) {
            case R.id.redRadio:
                color = R.color.red;
                break;
            case R.id.orangeRadio:
                color = R.color.orange;
                break;
            case R.id.yellowRadio:
                color = R.color.yellow;
                break;
            case R.id.greenRadio:
                color = R.color.greed;
                break;
            case R.id.blueRadio:
                color = R.color.blue;
                break;
            case R.id.darkBlueRadio:
                color = R.color.darkBlue;
                break;
            case R.id.purpleRadio:
                color = R.color.purple;
                break;
            default:
                color = R.color.white;
        }

        EditText widgetText = findViewById(R.id.widgetText);
        SharedPreferences preferences = getSharedPreferences(WIDGET_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(WIDGET_TEXT + widgetID, widgetText.getText().toString());
        editor.putInt(WIDGET_COLOR + widgetID, getResources().getColor(color));
        editor.apply();

        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        Widget.updateWidget(this, manager, preferences, widgetID);

        setResult(RESULT_OK, resultValue);
        finish();
    }
}
