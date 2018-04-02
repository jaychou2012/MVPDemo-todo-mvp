package com.mvp.mvpdemo.base;

/**
 * Created by Tandong on 2018/4/2.
 * MVP中的P的基类，采用接口形式，定义接口方法
 */

public interface BasePresenter {
    /**
     * 定义一个通用的基类方法，其他的presenter可以调用
     */
    void start();
}
