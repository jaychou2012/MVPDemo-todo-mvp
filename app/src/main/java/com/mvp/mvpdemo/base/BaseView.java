package com.mvp.mvpdemo.base;

/**
 * Created by Tandong on 2018/4/2.
 * MVP中的V的基类，采用接口的形式，然后定义接口方法，把V和P关联起来
 */

public interface BaseView<T> {

    /**
     * 关联设置Presenter的接口方法
     *
     * @param presenter
     */
    void setPresenter(T presenter);
}
