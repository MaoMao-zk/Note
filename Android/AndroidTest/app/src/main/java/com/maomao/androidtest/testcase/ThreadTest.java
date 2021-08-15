package com.maomao.androidtest.testcase;


import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;

import com.maomao.androidtest.util.LogWithUI;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


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

        Intent intent = new Intent();
        intent.putExtra("action", "task");
        MyIntentService.enqueueWork(context, intent);

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
            Intent intent = new Intent();
            intent.putExtra("action", "abnormal");
            intent.putExtra("abnormal", "TwoSecondTask is interrupted.");
            MyIntentService.enqueueWork(context, intent);
            context.startService(intent);
        }
        LogWithUI.I("End running TwoSecondTask.");
    }

    ExecutorService threadPool;
}
