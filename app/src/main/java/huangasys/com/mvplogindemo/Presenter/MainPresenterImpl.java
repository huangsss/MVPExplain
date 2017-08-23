package huangasys.com.mvplogindemo.Presenter;

import android.text.TextUtils;

import huangasys.com.mvplogindemo.Model.User;
import huangasys.com.mvplogindemo.View.BaseView;

/**
 *
 * Created by "huangsays"  on 2017/8/23.16:07"huangays@gmail.com"
 */

public class MainPresenterImpl implements BasePresenter {

    private BaseView mBaseView;

    @Override
    public void attachView(BaseView v) {
        this.mBaseView = v;
    }

    @Override
    public void detachView() {
        mBaseView = null ;
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
                mBaseView.loginSuccess("登陆成功");
            }else {
                mBaseView.loginFail("登录失败");
            }
        }else{
            mBaseView.showToast("用户名或者密码不能为空");
        }
    }

}
