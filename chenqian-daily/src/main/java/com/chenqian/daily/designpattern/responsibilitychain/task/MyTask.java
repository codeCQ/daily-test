package com.chenqian.daily.designpattern.responsibilitychain.task;

import lombok.Data;

import java.util.function.Function;

/**
 * @author chenQian
 * @date 2021年12月24日 15:39
 */
@Data
public class MyTask {

    //任务类型
    private Integer type;

    //任务类型1
    private Function<String,String> function;

    //任务类型2
    private Runnable runnable;
}
