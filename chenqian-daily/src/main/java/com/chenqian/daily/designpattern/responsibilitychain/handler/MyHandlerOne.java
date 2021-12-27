package com.chenqian.daily.designpattern.responsibilitychain.handler;

import com.chenqian.daily.designpattern.responsibilitychain.task.MyTask;

/**
 * @author chenQian
 * @date 2021年12月24日 16:00
 */
public class MyHandlerOne extends MyAbstractHandler {


    //设置处理策略 处理哪一种
    public MyHandlerOne(Integer priority ) {
        super(priority);
    }

    //具体处理逻辑
    @Override
    public void process(MyTask task) {
        System.out.println("one 处理了 task-type-"+task.getType());
    }


}
