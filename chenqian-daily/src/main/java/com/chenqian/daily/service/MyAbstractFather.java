package com.chenqian.daily.service;

/**
 * 测试子类创建时，父类是否会创建 结论：会
 *
 */
public class MyAbstractFather {
    public MyAbstractFather(){
        System.out.println("MyAbstractFather 初始化了");
        doSome();
        doSome2();
    }

    //子类重写
    public void doSome(){
        System.out.println("MyAbstractFather doSome 执行了");;
    }
    //子类不重写
    public void doSome2(){
        System.out.println("MyAbstractFather doSome2 执行了");;
    }
    //静态方法无法被继承
    public static void doSome4(){
        System.out.println("MyAbstractFather doSome4 执行了");;
    }
}
