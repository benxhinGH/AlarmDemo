package com.lius.alarmdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class AlarmListAdapter extends BaseAdapter {

    private List<Alarm> listDatas;
    private View convertView;
    private Context context;



    public AlarmListAdapter(Context context, int resource, List<Alarm> listDatas) {
        convertView= LayoutInflater.from(context).inflate(resource,null);
        this.listDatas=listDatas;
        this.context=context;
    }

    @Override
    public int getCount() {
        return listDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=null;

        if(convertView==null){
            convertView=this.convertView;

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
                    Toast.makeText(context, "打开闹钟"+listDatas.get(position).getId(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "关闭闹钟"+listDatas.get(position).getId(), Toast.LENGTH_SHORT).show();
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
