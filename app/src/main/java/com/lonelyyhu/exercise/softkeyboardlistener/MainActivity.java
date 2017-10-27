package com.lonelyyhu.exercise.softkeyboardlistener;

import android.content.Context;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KeyboardUtil.addKeyboardToggleListener(this, new KeyboardUtil.SoftKeyboardToggleListener() {
            @Override
            public void onToggleSoftKeyboard(boolean isVisible) {
                Log.wtf("MainActivity", "onToggleSoftKeyboard => isVisible: " + isVisible);
            }
        });

        contentView = findViewById(android.R.id.content);

        contentView.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        Log.wtf("MainActivity", "onTouch");

        InputMethodManager manager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS, new ResultReceiver(null) {

            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                super.onReceiveResult(resultCode, resultData);

                if (resultCode == InputMethodManager.RESULT_HIDDEN) {
                    Log.wtf("MainActivity", "hidden success");
                }

            }
        });

        return false;
    }
}
