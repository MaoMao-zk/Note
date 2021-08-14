package com.maomao.androidtest.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.util.Log;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogWithUI {
    public static void I(String s) {
        s = "[" + Thread.currentThread().getName() + "] " + s;
        Log.i("LogWithUI", s);
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            printOnUI(s);
        } else {
            String asyncString = s;
            textView.post(() -> {
                printOnUI(asyncString);
            });
        }

    }

    public static void RegisterTextView(TextView v) {
        textViewLock.lock();
        textView = v;
        textViewLock.unlock();
    }

    static void printOnUI(String s) {
        textViewLock.lock();
        if (textView != null) {
            textView.append(s + "\n");
        }
        textViewLock.unlock();
    }

    static TextView textView;
    static Lock textViewLock = new ReentrantLock();
}
