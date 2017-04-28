package com.lius.alarmdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.lius.alarmdemo.control.MyAlarmManager;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到闹钟广播", Toast.LENGTH_SHORT).show();
        MyAlarmManager.getInstance().triggerAlarm();

    }
}
