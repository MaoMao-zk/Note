package com.maomao.androidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.maomao.androidtest.util.LogWithUI;

import org.w3c.dom.Text;

public class TestCaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_case);

        Intent intent =getIntent();
        name = intent.getStringExtra("name");
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView testTitleView = findViewById(R.id.testTitle);
        if (name == null)
            testTitleView.setText("Unknow");
        else
            testTitleView.setText(name);

        LogWithUI.I("Register TextView for print log.");
        TextView logView = findViewById(R.id.logTextView);
        LogWithUI.RegisterTextView(logView);

        Intent intent = new Intent("testcase.activity.ready");
        sendBroadcast(intent);
    }

    @Override
    protected void onPause() {
        LogWithUI.RegisterTextView(null);
        LogWithUI.I("Unregister TextView.");
        super.onPause();
    }

    String name;
}