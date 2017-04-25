package com.lius.alarmdemo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;

    private TimePicker timePicker;
    private Button setAlarmBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        timePicker=(TimePicker)findViewById(R.id.timePicker);
        setAlarmBtn=(Button)findViewById(R.id.set_alarm_btn);

        setAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AlarmReceiver.class);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,0,intent,0);

                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);

                Log.d("MainActivity","获取时间："+calendar.getTime().toString());


                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

                Toast.makeText(MainActivity.this, "创建闹钟", Toast.LENGTH_SHORT).show();


            }
        });
    }
}
