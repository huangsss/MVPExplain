package com.huangasys.veke.app_github_mvp.presenter.impl;

import android.support.v7.view.menu.MenuView;
import android.util.Log;

import com.huangasys.veke.app_github_mvp.modle.NewApi;
import com.huangasys.veke.app_github_mvp.modle.UserRepo;
import com.huangasys.veke.app_github_mvp.presenter.MainActivityPresenter;
import com.huangasys.veke.app_github_mvp.view.MainActivityView;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huangasys on 2017/12/1.13:56
 */

public class MainPresenterImpl implements MainActivityPresenter {

    private MainActivityView mView;
    private List<UserRepo> mUserRepoList;
    private Subscription mSubscribe;

    @Override
    public void attachView(MainActivityView mainActivityView) {
        this.mView = mainActivityView;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
        if (mSubscribe != null){
            mSubscribe.unsubscribe();
        }
    }

    //下载数据;
    @Override
    public void loadUserData(String userName) {
        mView.showProgress();
        mSubscribe = NewApi.Factory.create().publicRepositories(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserRepo>>() {
                    @Override
                    public void onCompleted() {
                        mView.showRecyclerView(mUserRepoList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("print", "onError: "+e);
                        mView.showErrorMessage();
                    }

                    @Override
                    public void onNext(List<UserRepo> userRepos) {
                        mUserRepoList = userRepos;
                    }
                });
    }
}
