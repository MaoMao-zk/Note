package com.maomao.androidtest.testcase;


import android.content.Context;

abstract public class TestCase {
    TestCase(Context c) {
        context = c;
    }
    abstract public String getName();
    abstract public void Run();

    Context context;
}
