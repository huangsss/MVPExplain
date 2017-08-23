package huangasys.com.app_mvp2.Presenter;

import huangasys.com.app_mvp2.Model.User;
import huangasys.com.app_mvp2.View.MainBaseView;

/**
 * 只为MainActivity提供业务逻辑;
 * Created by "huangsays"  on 2017/8/23.20:07"huangays@gmail.com"
 */

public interface MainBasePresenter  extends BasePresenter<MainBaseView>{

    void login(User user);//登录逻辑
}
