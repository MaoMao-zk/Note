package com.maomao.androidtest.util;

import android.widget.TextView;
import android.util.Log;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogWithUI {
    public static void I(String s) {
        Log.i("LogWithUI", s);
        textViewLock.lock();
        if (textView != null) {
            textView.append(s + "\n");
        }
        textViewLock.unlock();

    }

    public static void RegisterTextView(TextView v) {
        textViewLock.lock();
        textView = v;
        textViewLock.unlock();
    }

    static TextView textView;
    static Lock textViewLock = new ReentrantLock();
}
