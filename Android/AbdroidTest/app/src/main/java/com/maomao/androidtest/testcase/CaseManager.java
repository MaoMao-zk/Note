package com.maomao.androidtest.testcase;

import android.content.Intent;
import android.app.Activity;

import java.util.HashSet;
import java.util.Set;
import java.util.Map;

import com.maomao.androidtest.TestCaseActivity;
import com.maomao.androidtest.testcase.TestCase;
import com.maomao.androidtest.util.LogWithUI;

class ErrorCase implements TestCase {
    @Override
    public String getName() {
        return "ErrorCase";
    }

    @Override
    public void Run() {
        LogWithUI.I("Error TestCase!!!! Please Try others.");
    }
}


public class CaseManager {

    @FunctionalInterface
    public interface SwitchTestCaseRunnable {
        public void run(String s);
    }

    public CaseManager(SwitchTestCaseRunnable runnable) {
        switchTestCaseActivity = runnable;
        caseSet.add("Error");
    }

    public Set<String> getCaseList() {
        return caseSet;
    }

    public void switchTestCase(String name) {
        currentCase = createTestCase(name);
        switchTestCaseActivity.run(name);
    }

    public void runCurrentTestCase() {
        currentCase.Run();
    }

    TestCase createTestCase(String name) {
        switch(name) {
            case "Error":
            default:
                return new ErrorCase();
        }
    }

    Set<String> caseSet = new HashSet<String>();
    TestCase currentCase;
    SwitchTestCaseRunnable switchTestCaseActivity;
}
