package com.lius.alarmdemo.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.lius.alarmdemo.model.Alarm;
import com.lius.alarmdemo.util.MyDatabaseHelper;
import com.lius.alarmdemo.R;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class AddAlarmActivity extends AppCompatActivity {


    private Toolbar toolbar;

    private TimePicker timePicker;

    private Spinner typeSpinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addalarm);
        findViews();
        setSupportActionBar(toolbar);


    }

    private void findViews(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        timePicker=(TimePicker)findViewById(R.id.timePicker);
        typeSpinner=(Spinner)findViewById(R.id.type_spinner);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addalarm,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_confirm:
                MyDatabaseHelper myDatabaseHelper=new MyDatabaseHelper(this,"Alarm.db",null,1);
                SQLiteDatabase database=myDatabaseHelper.getWritableDatabase();
                ContentValues contentValues=new ContentValues();
                contentValues.put("hour",timePicker.getCurrentHour());
                contentValues.put("minute",timePicker.getCurrentMinute());
                String typeStr=getResources().getStringArray(R.array.alarm_type)[(int)typeSpinner.getSelectedItemId()];
                if(typeStr.equals("一次"))contentValues.put("type", Alarm.ONCE);
                else if(typeStr.equals("每天"))contentValues.put("type",Alarm.EVERYDAY);
                contentValues.put("status",Alarm.RUN);
                database.insert("Alarm",null,contentValues);
                Log.d("AddAlarmActivity","点击confirmAction");
                setResult(RESULT_OK);
                finish();
                break;
            default:
                break;
        }

        return true;
    }
}
