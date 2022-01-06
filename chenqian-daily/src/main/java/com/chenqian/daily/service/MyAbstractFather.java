package com.chenqian.daily.service;

import lombok.Data;

/**
 * 测试子类创建时，父类是否会创建 结论：会
 *
 */
@Data
public class MyAbstractFather {
    private Object i = new Object();
    public Object j = new Object();


    public MyAbstractFather(){
        System.out.println("MyAbstractFather 初始化了 ");
    }

    //子类重写
    public void doSome(){
        System.out.println("MyAbstractFather doSome 执行了");;
    }

    //子类不重写
    public void add(){

    }

    public void show(){
        System.out.println("i = " + i);;
        System.out.println("j = " + j);;
    }
    //静态方法无法被继承
    public static void doSome4(){
        System.out.println("MyAbstractFather doSome4 执行了");;
    }
}
