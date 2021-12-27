package com.chenqian.daily.designpattern.responsibilitychain.handler;

import com.chenqian.daily.designpattern.responsibilitychain.task.MyTask;

/**
 * @author chenQian
 * @date 2021年12月24日 15:54
 * 责任链处理器规范
 */
public interface MyHandlerStandard {

    void process(MyTask task);

    void setNextHandler(MyAbstractHandler handler);
}
