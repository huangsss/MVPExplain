package com.huangasys.veke.app_github_mvp.view;

import com.huangasys.veke.app_github_mvp.modle.UserRepo;

import java.util.List;

/**
 * Created by huangasys on 2017/12/1.11:50
 * 集成BaseView,自身有需要的UI逻辑再往里面添加即可;
 */

public interface MainActivityView extends BaseView<List<UserRepo>> {
    //主界面才有的UI逻辑;
    void isMainUI();
}
