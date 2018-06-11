package com.liuh.threadtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MyThread myThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myThread = new MyThread();
//        Thread thread = new Thread(myThread);
        myThread.start();
    }


    @Override
    protected void onResume() {
        super.onResume();
        myThread.resumeThread();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myThread.pauseThread();
    }
}
