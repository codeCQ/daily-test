package com.chenqian.daily.observer;

import lombok.AllArgsConstructor;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者2
 */
public class MyObserverTwo  implements MyObserver{


    public void update( Object ...objects) {
        String s1 = (String)objects[0];
        String s2 = (String)objects[0];
        System.out.println("MyObserverTwo"+s1);
        System.out.println("MyObserverTwo"+s2);

    }
}
