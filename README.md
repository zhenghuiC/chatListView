
自己看别人的项目学习的笔记记录，原创地址不记得了，自己学习用哦，不是盗版
### 一、先看效果图
![image](https://raw.githubusercontent.com/zhenghuiC/chatListView/master/src/main/res/mipmap-xxhdpi/listviewscroll.gif)


### 二、git地址：
    
    https://github.com/zhenghuiC/chatListView
    
### 三、解析：
    
- listview中有两个方法```getItemViewType（int position） ```和```getViewTypeCount() ```，在getView（）中进行判断获取什么样子的布局就可以了。

### 四、自定义Adapter
```
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
```

xml的布局只要显示在不同的位置即可
    
###### 1、R.layout.item_list.xml
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:tools="http://schemas.android.com/tools">

    <TextView
        android:id="@+id/outTv"
        tools:text="测试内容"
        android:textSize="18sp"
        android:textColor="#333333"
        android:background="@drawable/text_out"
        android:layout_marginLeft="50dp"
        android:layout_marginTop="10dp"
        android:layout_marginBottom="10dp"
        android:layout_marginRight="10dp"
        android:layout_alignParentRight="true"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
</RelativeLayout>

```
###### 2、R.layout.item_list2.xml
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:tools="http://schemas.android.com/tools">
    <TextView
        android:id="@+id/inTv"
        tools:text="测试内容"
        android:textSize="18sp"
        android:textColor="#333333"
        android:background="@drawable/text_in"
        android:layout_marginLeft="10dp"
        android:layout_marginTop="10dp"
        android:layout_marginBottom="10dp"
        android:layout_marginRight="50dp"
        android:layout_alignParentRight="true"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
</LinearLayout>

```

###### 3、ChatBean.class
```
public class ChatBean  {
    private int type;  //类型，用于判断使用哪种布局
    private String text;//文字描述

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}

```
### 五、给listview增加弹性，拉到底或者拉到顶可以弹弹弹

##### 1、原理

重写ListView的方法 ```overScrollBy() ```，修改其中的```maxOverScrollY ```

```
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
```
