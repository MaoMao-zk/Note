package com.maomao.androidtest.testcase;


import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.maomao.androidtest.util.LogWithUI;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


class MyIntentService extends IntentService {
    public MyIntentService() {
        super("zk-thread");
        LogWithUI.I("MyIntentService is constructed");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
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
}

public class ThreadTest extends TestCase {
    ThreadTest(Context c) {
        super(c);
    }

    @Override
    public String getName() {
        return "Android Thread Test";
    }

    @Override
    public void Run() {
        threadPool = Executors.newCachedThreadPool();
        threadPool.execute(() -> {
            AsyncRun();
        });
    }

    public void AsyncRun() {
        FutureTask task = new FutureTask(() -> {
            ThreadTest.this.TwoSecondTask();
            return true;
        });

        threadPool.execute(task);

        Intent intent = new Intent(context, MyIntentService.class);
        intent.putExtra("action", "task");
        ComponentName result = context.startService(intent);
        if (result == null) {
            LogWithUI.I("context.startService return null");
        } else {
            LogWithUI.I("context.startService return " + result);
        }

        try {
            task.get(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LogWithUI.I("InterruptedException:" + e);
            // e.printStackTrace();
        } catch (ExecutionException e) {
            LogWithUI.I("ExecutionException:" + e);
            e.printStackTrace();
        } catch (TimeoutException e) {
            LogWithUI.I("TimeoutException:" + e);
            // e.printStackTrace();
            task.cancel(true);
        }
    }

    void TwoSecondTask() {
        LogWithUI.I("Start running TwoSecondTask.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LogWithUI.I("TwoSecondTask is interrupted.");
            // e.printStackTrace();
            Intent intent = new Intent(context, MyIntentService.class);
            intent.putExtra("action", "abnormal");
            intent.putExtra("abnormal", "TwoSecondTask is interrupted.");
            context.startService(intent);
        }
        LogWithUI.I("End running TwoSecondTask.");
    }

    ExecutorService threadPool;
}
