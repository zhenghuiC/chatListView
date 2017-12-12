package com.example.testchart;

import android.graphics.BitmapRegionDecoder;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.testchart.bean.ChatBean;
import com.idtk.smallchart.chart.CombineChart;
import com.idtk.smallchart.chart.LineChart;
import com.idtk.smallchart.data.LineData;
import com.idtk.smallchart.interfaces.iData.ILineData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    float firstY = 0;//第一次点击的Y坐标
    float currentY ;//当前的Y的坐标
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =(ListView)findViewById(R.id.listView);

        List<ChatBean> list =new ArrayList<>();
        for(int i=0;i<6;i++){
            ChatBean bean =new ChatBean();
            if(i%2==0){
                bean.setType(0);
                bean.setText("这是收到的对话,进行对话的模仿，查看布局效果"+i);
            }else{
                bean.setType(1);
                bean.setText("这是我发出的对话"+i);
            }

            list.add(bean);
        }
        for(int i=0;i<6;i++){
            ChatBean bean =new ChatBean();
            if(i%2==0){
                bean.setType(0);
                bean.setText("进行单行文本测试"+i);
            }else{
                bean.setType(1);
                bean.setText("这是我发出的对话，进行多行文本测试，查看换行效果"+i);
            }

            list.add(bean);
        }
        for(int i=0;i<6;i++){
            ChatBean bean =new ChatBean();
            if(i%2==0){
                bean.setType(0);
                bean.setText("进行多行文本测试，哇咔咔查看效果是怎么样子的"+i);
            }else{
                bean.setType(1);
                bean.setText("这是我发出的对话，进行多行文本测试，查看换行效果"+i);
            }

            list.add(bean);
        }
        listView.setAdapter(new ListAdapter(this,list));
        //获取listview的最小的滑动距离


        final float mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        firstY =motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        currentY =motionEvent.getY();
                        if(currentY -firstY >mTouchSlop){
                            Log.e("hahah","向下滑动");
                        }else if(firstY -currentY>mTouchSlop){
                            Log.e("hahah","向上滑动");
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }

}
