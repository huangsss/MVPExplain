package com.huangasys.veke.app_github_mvp.presenter;

/**
 * Created by huangasys on 2017/12/1.11:53
 */

public interface BasePresenter<T> {
    //业务逻辑层,共有的写在这里即可;

    //绑定View;
    void attachView(T t);
    //解绑View;
    void detachView();
}