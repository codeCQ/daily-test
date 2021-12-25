package com.chenqian.daily.service.impl;

import com.chenqian.daily.service.MyAbstractFather;

public class MySon extends MyAbstractFather {
    public MySon() {

    }

    @Override
    public void doSome(){
        System.out.println("MySon doSome 执行了");
    }

    //private方法 子类无法继承无法重写
    private void doSome3(){
        System.out.println("MyAbstractFather doSome3 执行了");;
    }

}
