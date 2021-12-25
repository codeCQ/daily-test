package com.chenqian.daily.observer;

/**
 * 观察者1
 */
public class MyObserverOne implements MyObserver {


    public void update( Object ...objects) {
        String s1 = (String)objects[0];
        String s2 = (String)objects[0];
        System.out.println("MyObserverOne"+s1);
        System.out.println("MyObserverOne"+s2);

    }
}
