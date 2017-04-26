package com.lius.alarmdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class SetAlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        int alarmId=intent.getIntExtra("alarmId",-1);
        switch (intent.getAction()){
            case "com.lius.alarmdemo.openalarm":
                Toast.makeText(context, "收到打开闹钟广播，id为:"+alarmId, Toast.LENGTH_SHORT).show();
                AlarmListManager.changeItemColor(alarmId, Color.BLACK);
                break;
            case "com.lius.alarmdemo.closealarm":
                Toast.makeText(context, "收到关闭闹钟广播，id为:"+alarmId, Toast.LENGTH_SHORT).show();
                AlarmListManager.changeItemColor(alarmId,0x55000000);
                break;
            default:
                break;
        }
    }
}
