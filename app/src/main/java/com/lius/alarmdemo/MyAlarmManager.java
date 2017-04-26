package com.lius.alarmdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class MyAlarmManager {

    private List<Alarm> alarmList;

    private static MyAlarmManager myAlarmManager;



    public static MyAlarmManager getInstance(){
        if(myAlarmManager==null){
            myAlarmManager=new MyAlarmManager();
        }
        return myAlarmManager;
    }


    private MyAlarmManager(){
        alarmList=new ArrayList<>();
    }

    public void addAlarm(Alarm alarm){
        alarmList.add(alarm);
    }

    public void removeAlarm(Alarm alarm){
        alarmList.remove(alarm);
    }

    public List<Alarm> getAlarmList(){
        return alarmList;
    }
}
