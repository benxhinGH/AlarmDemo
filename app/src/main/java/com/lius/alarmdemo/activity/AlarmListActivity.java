package com.lius.alarmdemo.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lius.alarmdemo.model.Alarm;
import com.lius.alarmdemo.adapter.AlarmListAdapter;
import com.lius.alarmdemo.control.MyAlarmManager;
import com.lius.alarmdemo.util.MyApplication;
import com.lius.alarmdemo.util.MyDatabaseHelper;
import com.lius.alarmdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class AlarmListActivity extends AppCompatActivity {

    private ListView alarmLv;
    private Toolbar toolbar;
    private MyDatabaseHelper myDatabaseHelper;
    private MyAlarmManager myAlarmManager;
    private List<Alarm> alarmListDatas;
    public static AlarmListAdapter alarmListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmlist);

        findViews();

        setSupportActionBar(toolbar);

        initDatas();

        setListeners();

        String[] strings={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this,strings,1);


    }

    private void findViews(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        alarmLv=(ListView)findViewById(R.id.alarm_list);
    }


    private void initDatas(){
        myAlarmManager=MyAlarmManager.getInstance();
        myAlarmManager.setContext(this);
        myDatabaseHelper=new MyDatabaseHelper(this,"Alarm.db",null,1);
        SQLiteDatabase database=myDatabaseHelper.getReadableDatabase();
        Cursor cursor=database.query("Alarm",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            int id;
            int hour;
            int minute;
            int type;
            int status;
            do{
                id=cursor.getInt(0);
                hour=cursor.getInt(1);
                minute=cursor.getInt(2);
                type=cursor.getInt(3);
                status=cursor.getInt(4);
                myAlarmManager.addAlarm(new Alarm(id,hour,minute,type,status));
            }while (cursor.moveToNext());
        }
        cursor.close();


        alarmListDatas=myAlarmManager.getAlarmList();

        alarmListAdapter=new AlarmListAdapter(this,alarmListDatas);
        alarmLv.setAdapter(alarmListAdapter);
        if(myAlarmManager.getCurrentRunningAlarm()==null)myAlarmManager.setNextAlarm();
    }

    private void setListeners(){
        alarmLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(AlarmListActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确定删除此闹钟？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Alarm alarm=alarmListDatas.get(position);
                        myAlarmManager.removeAlarm(alarm);
                        alarmListAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
                return true;
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
                        int status=cursor.getInt(4);
                        myAlarmManager.addAlarm(new Alarm(id,hour,minute,type,status));
                    }
                    cursor.close();
                    alarmListAdapter.notifyDataSetChanged();

                }
                break;
            default:
                break;
        }
    }
}
