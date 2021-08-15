package com.maomao.androidtest.testcase;

import android.content.Context;
import android.content.Intent;

import androidx.core.app.JobIntentService;

import com.maomao.androidtest.util.LogWithUI;

import org.jetbrains.annotations.NotNull;

public class MyIntentService extends JobIntentService {
    // public MyIntentService() {
    //     super("zk-thread");
    //     LogWithUI.I("MyIntentService is constructed");
    // }

    static final int JOB_ID = 10111;

    static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, MyIntentService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NotNull Intent intent) {
        String action = intent.getStringExtra("action");
        switch (action) {
            case "task":
                LogWithUI.I("MyIntentService is running task");
                break;
            case "abnormal":
                String abnormal = intent.getStringExtra("abnormal");
                LogWithUI.I("MyIntentService receive a abnormal:" + abnormal);
                break;
            default:
                LogWithUI.I("MyIntentService receive a unknow action:" + action);
                break;
        }
    }

    // @Override
    // protected void onHandleIntent(@Nullable Intent intent) {
    //     String action = intent.getStringExtra("action");
    //     switch (action) {
    //         case "task":
    //             LogWithUI.I("MyIntentService is running task");
    //             break;
    //         case "abnormal":
    //             String abnormal = intent.getStringExtra("abnormal");
    //             LogWithUI.I("MyIntentService receive a abnormal:" + abnormal);
    //             break;
    //         default:
    //             LogWithUI.I("MyIntentService receive a unknow action:" + action);
    //             break;
    //     }
    // }
}
