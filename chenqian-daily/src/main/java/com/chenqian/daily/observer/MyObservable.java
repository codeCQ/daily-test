package com.chenqian.daily.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 */
public class MyObservable  {
    //观察者存放list
    private List<MyObserver> MyObservers
            = new ArrayList<MyObserver>();
    private int state;

    public int getState() {
        return state;
    }

    /**
     * 进行set操作时 通知所有观察者
     * @param state
     */
    public void setState(int state) {
        this.state = state;
        notifyAllMyObservers();
    }

    public void attach(MyObserver MyObserver){
        MyObservers.add(MyObserver);
    }

    public void notifyAllMyObservers(){
        for (MyObserver MyObserver : MyObservers) {
            MyObserver.update(state+"1",state+"2");
        }
    }

}
