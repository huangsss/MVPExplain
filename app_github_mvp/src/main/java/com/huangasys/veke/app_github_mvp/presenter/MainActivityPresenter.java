package com.huangasys.veke.app_github_mvp.presenter;

import com.huangasys.veke.app_github_mvp.view.MainActivityView;

/**
 * Created by huangasys on 2017/12/1.13:52
 * //处理MainActivity内的业务逻辑;
 */

public interface MainActivityPresenter extends BasePresenter<MainActivityView> {

    //下载数据;
    void loadUserData(String userName);
}
