package com.lius.alarmdemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class AlarmListActivity extends AppCompatActivity {

    public static ListView alarmLv;
    private Toolbar toolbar;
    private MyDatabaseHelper myDatabaseHelper;
    private MyAlarmManager myAlarmManager;
    private List<Alarm> alarmListDatas;
    private AlarmListAdapter alarmListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmlist);

        findViews();

        setSupportActionBar(toolbar);

        initDatas();

        setListeners();


    }

    private void findViews(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        alarmLv=(ListView)findViewById(R.id.alarm_list);
    }

    private List<Alarm> getDatas(){
        List<Alarm> list=new ArrayList<>();
        list.add(new Alarm(0,1,1,Alarm.ONCE));
        list.add(new Alarm(1,2,2,Alarm.EVERYDAY));
        list.add(new Alarm(2,10,12,Alarm.ONCE));
        list.add(new Alarm(3,23,59,Alarm.EVERYDAY));
        list.add(new Alarm(4,3,3,Alarm.EVERYDAY));
        list.add(new Alarm(5,4,4,Alarm.ONCE));
        list.add(new Alarm(6,5,5,Alarm.EVERYDAY));
        list.add(new Alarm(7,10,10,Alarm.ONCE));
        return list;
    }

    private void initDatas(){
        myAlarmManager=MyAlarmManager.getInstance();
        myDatabaseHelper=new MyDatabaseHelper(this,"Alarm.db",null,1);
        SQLiteDatabase database=myDatabaseHelper.getReadableDatabase();
        Cursor cursor=database.query("Alarm",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            int id;
            int hour;
            int minute;
            int type;
            do{
                id=cursor.getInt(0);
                hour=cursor.getInt(1);
                minute=cursor.getInt(2);
                type=cursor.getInt(3);
                myAlarmManager.addAlarm(new Alarm(id,hour,minute,type));
            }while (cursor.moveToNext());
        }
        cursor.close();


        alarmListDatas=myAlarmManager.getAlarmList();

        alarmListAdapter=new AlarmListAdapter(this,alarmListDatas);
        alarmLv.setAdapter(alarmListAdapter);
    }

    private void setListeners(){
        alarmLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AlarmListActivity.this, "点击item:"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_alarmlist,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_alarm:
                startActivityForResult(new Intent(AlarmListActivity.this,AddAlarmActivity.class),1);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    SQLiteDatabase database=myDatabaseHelper.getReadableDatabase();
                    Cursor cursor=database.rawQuery("select * from Alarm where id=(select max(id) from Alarm)",null);
                    if(cursor.moveToFirst()){
                        int id=cursor.getInt(0);
                        int hour=cursor.getInt(1);
                        int minute=cursor.getInt(2);
                        int type=cursor.getInt(3);
                        myAlarmManager.addAlarm(new Alarm(id,hour,minute,type));
                    }
                    cursor.close();
                    alarmListDatas=myAlarmManager.getAlarmList();
                    alarmListAdapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }
}
