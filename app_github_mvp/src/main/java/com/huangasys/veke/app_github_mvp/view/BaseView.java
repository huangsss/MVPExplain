package com.huangasys.veke.app_github_mvp.view;

/**
 * Created by huangasys on 2017/12/1.11:45
 * UI操作放在View下.此项目所用到的显示progress+隐藏 显示RecyclerView;
 */

public interface BaseView<T> {

    //显示progress;
    void showProgress();
    //显示错误信息;
    void showErrorMessage();
    //显示RecycleView数据;
    void showRecyclerView(T t);

}
