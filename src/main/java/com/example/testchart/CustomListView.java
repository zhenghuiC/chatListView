package com.example.testchart;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/**
 * 创建人 16925
 * 时间  2017/11/14.
 * 类描述 ：
 */

public class CustomListView extends ListView{
    int mMaxOverDistance=50;
    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //使listview具有弹性的功能
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxOverDistance, isTouchEvent);
    }
    //获取屏幕的分辨率，使其适用所有分辨率的手机
    private void initView(){
        DisplayMetrics metrics =getResources().getDisplayMetrics();
        float density =metrics.density;
        mMaxOverDistance =(int)(density*mMaxOverDistance);
    }
}
