package com.lius.alarmdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class AlarmListActivity extends Activity {

    private ListView alarmLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmlist);
    }
}
