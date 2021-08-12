package com.maomao.androidtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maomao.androidtest.ui.main.MainFragment;
import com.maomao.androidtest.util.LogWithUI;
import com.maomao.androidtest.TestCaseActivity;
import com.maomao.androidtest.testcase.CaseManager;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    class CaseButton extends AppCompatButton {

        public CaseButton(Context context) {
            super(context);
        }

        @Override
        public void setOnClickListener(@Nullable OnClickListener l) {
            super.setOnClickListener(l);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

        caseManager = new CaseManager((String name) -> {
            Intent intent = new Intent(MainActivity.this, TestCaseActivity.class);
            intent.putExtra("name", name);
            LogWithUI.I("Switch to TestCase: " + name);
            startActivity(intent);
        });

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                caseManager.runCurrentTestCase();
            }
        }, new IntentFilter("testcase.activity.ready"));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!initCaseList) {
            LinearLayout caseListLayout = findViewById(R.id.caseList);
            Set<String> caseList = caseManager.getCaseList();
            for(String name: caseList) {
                Button button = new Button(this);
                button.setText(name);
                button.setOnClickListener((View v) -> {
                    LogWithUI.I(name + " is clicked");
                    caseManager.switchTestCase(name);
                });
                caseListLayout.addView(button, SUB_ACTIVITY_READY);
            }
            initCaseList = true;
        }
    }

    public void onClickExit(View v) {
        LogWithUI.I("Exit clicked.");
        finish();
    }

    CaseManager caseManager;
    boolean initCaseList = false;
    static final int SUB_ACTIVITY_READY = 1;
}