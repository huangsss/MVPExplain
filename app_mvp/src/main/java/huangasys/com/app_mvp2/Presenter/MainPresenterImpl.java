package huangasys.com.app_mvp2.Presenter;

import android.text.TextUtils;

import huangasys.com.app_mvp2.Model.User;
import huangasys.com.app_mvp2.View.MainBaseView;

/**
 * MainActivity的具体实现
 * Created by "huangsays"  on 2017/8/23.20:00"huangays@gmail.com"
 */

public class MainPresenterImpl implements MainBasePresenter{

    private MainBaseView mMainBaseView;

    @Override
    public void attachView(MainBaseView v) {
        this.mMainBaseView = v;
    }

    @Override
    public void detachView() {
        mMainBaseView = null ;
    }

    /**
     *
     * 登录的逻辑
     * @param user
     */
    @Override
    public void login(User user) {
        if (!TextUtils.isEmpty(user.getName())&&!TextUtils.isEmpty(user.getPwd())){
            if (user.getName().equals("123")&&user.getPwd().equals("123")){
                mMainBaseView.loginSuccess("登陆成功");
            }else {
                mMainBaseView.loginFail("登录失败");
            }
        }else{
            mMainBaseView.showToast("用户名或者密码不能为空");
        }
    }


}
