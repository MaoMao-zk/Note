package com.maomao.androidtest.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogWithUI {
    public static void I(String s) {
        s = "[" + Thread.currentThread().getName() + "] " + s;
        Log.i("LogWithUI", s);

        String asyncString = s;
        appendText.post(() -> {
            textViewLock.lock();
            if (textView != null) {
                textView.append(asyncString + "\n");
            }
            textViewLock.unlock();
        });
    }

    public static void RegisterTextView(TextView v) {
        textViewLock.lock();
        textView = v;
        textViewLock.unlock();
    }

    static TextView textView;
    static Lock textViewLock = new ReentrantLock();
    static Handler appendText = Handler.createAsync(Looper.getMainLooper());
}
