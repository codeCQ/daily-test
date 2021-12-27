package com.chenqian.daily.designpattern.responsibilitychain.handler;

import com.chenqian.daily.designpattern.responsibilitychain.task.MyTask;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author chenQian
 * @date 2021年12月24日 15:40
 * 责任链处理器抽象类
 */
@Data
@NoArgsConstructor
public abstract class MyAbstractHandler  implements  MyHandlerStandard{

    //优先级
    //设置为private 会被子类继承 无法显示调用
    private Integer priority;
    private   MyAbstractHandler nextHandler;

    @Override
    public void setNextHandler(MyAbstractHandler nextHandler){
        this.nextHandler=nextHandler;
    }

    //设置优先级方法 设置为public 开放给子类使用
    public  MyAbstractHandler(Integer priority) {
        this.priority = priority;
    }

    public void processOriented(MyTask task){
        if (Objects.equals(task.getType(),priority)){
            process(task);
        }else {
            if ( nextHandler!= null){
                nextHandler.processOriented(task);
            }else {
                System.out.println("流程结束 责任链中无对应处理器");
            }
        }
    }

}
