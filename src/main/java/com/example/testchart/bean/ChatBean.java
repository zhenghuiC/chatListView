package com.example.testchart.bean;

/**
 * 创建人 16925
 * 时间  2017/11/14.
 * 类描述 ：
 */

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
