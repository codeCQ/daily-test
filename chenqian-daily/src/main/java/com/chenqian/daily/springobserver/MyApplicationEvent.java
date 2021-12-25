package com.chenqian.daily.springobserver;

import org.springframework.context.ApplicationEvent;
/**
 * 事件发布类
 */
public class MyApplicationEvent extends ApplicationEvent {
    //可以自定添加属性
    //在构造方法中完成初始化，然后在监听者中被读取使用
    private String string;
    //构造方法
    public MyApplicationEvent(Object source,String string) {
        super(source);
        this.string=string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
