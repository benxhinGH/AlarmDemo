package com.lius.alarmdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class AlarmListAdapter extends BaseAdapter {


    private List<Alarm> listDatas;
    private Context context;



    public AlarmListAdapter(Context context, List<Alarm> listDatas) {
        this.listDatas=listDatas;
        this.context=context;
    }

    @Override
    public int getCount() {
        Log.d("AlarmListAdapter","getCount:"+listDatas.size());
        return listDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return listDatas.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d("AlarmListAdapter","getView,position:"+position);
        ViewHolder viewHolder=null;

        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.alarm_list_item,null);
            Log.d("AlarmListAdapter","convertView is null,position:"+position);
            viewHolder=new ViewHolder();
            viewHolder.alarmTimeTv=(TextView)convertView.findViewById(R.id.alarm_time);
            viewHolder.alarmTypeTv=(TextView)convertView.findViewById(R.id.alarm_type);
            viewHolder.alarmSwitch=(Switch)convertView.findViewById(R.id.alarm_switch);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }

        viewHolder.alarmTimeTv.setText(listDatas.get(position).getStringTime());
        switch (listDatas.get(position).getType()){
            case Alarm.ONCE:
                viewHolder.alarmTypeTv.setText("一次");
                break;
            case Alarm.EVERYDAY:
                viewHolder.alarmTypeTv.setText("每天");
                break;
            default:
                break;
        }
        viewHolder.alarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Intent intent=new Intent("com.lius.alarmdemo.openalarm");
                    intent.putExtra("alarmId",position);
                    context.sendBroadcast(intent);

                }else{
                    Intent intent=new Intent("com.lius.alarmdemo.closealarm");
                    intent.putExtra("alarmId",position);
                    context.sendBroadcast(intent);
                }
            }
        });

        return convertView;
    }
    class ViewHolder{
        public TextView alarmTimeTv;
        public TextView alarmTypeTv;
        public Switch alarmSwitch;
    }
}
