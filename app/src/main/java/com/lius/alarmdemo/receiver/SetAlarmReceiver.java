package com.lius.alarmdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lius.alarmdemo.control.MyAlarmManager;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class SetAlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        int alarmPosition=intent.getIntExtra("alarmPosition",-1);
        switch (intent.getAction()){
            case "com.lius.alarmdemo.openalarm":
                MyAlarmManager.getInstance().openAlarm(alarmPosition);
                break;
            case "com.lius.alarmdemo.closealarm":
                MyAlarmManager.getInstance().cancelAlarm(alarmPosition);
                break;
            default:
                break;
        }
    }
}
