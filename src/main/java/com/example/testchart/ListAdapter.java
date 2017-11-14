package com.example.testchart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.testchart.bean.ChatBean;

import java.util.List;

/**
 * 创建人 16925
 * 时间  2017/11/14.
 * 类描述 ：
 */

public class ListAdapter extends BaseAdapter {
    Context context;
    List<ChatBean> chatBeanList;
    LayoutInflater layoutInflater;
    public ListAdapter(Context context,List<ChatBean> list){
        this.context =context;
        this.chatBeanList =list;
        layoutInflater =LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return chatBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return chatBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    //获取两种类型，到时候使用哪种布局
    @Override
    public int getItemViewType(int position) {
        ChatBean bean =chatBeanList.get(position);
        return bean.getType();
    }
    //返回两种类型
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            if(getItemViewType(i)==0){   //如果类型是0，说明是接收
                holder =new ViewHolder();
                view =layoutInflater.inflate(R.layout.item_list2,null);
                holder.textView =(TextView)view.findViewById(R.id.inTv);
            }else{   //输出类型
                holder =new ViewHolder();
                view =layoutInflater.inflate(R.layout.item_list,null);
                holder.textView =(TextView)view.findViewById(R.id.outTv);
            }
            view.setTag(holder);

        }else{
            holder =(ViewHolder)view.getTag();
        }
        //显示文字
        holder.textView.setText(chatBeanList.get(i).getText());
        return view;
    }
    class ViewHolder {
        TextView textView;
    }

}
