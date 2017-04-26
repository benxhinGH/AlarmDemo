package com.lius.alarmdemo;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class AlarmListManager {

    public static void changeItemColor(int itemId,int color){
        View view=AlarmListActivity.alarmLv.getChildAt(itemId);
        TextView alarmTimeTv=(TextView)view.findViewById(R.id.alarm_time);
        TextView alarmTypeTv=(TextView)view.findViewById(R.id.alarm_type);
        alarmTimeTv.setTextColor(color);
        alarmTypeTv.setTextColor(color);
    }
}
