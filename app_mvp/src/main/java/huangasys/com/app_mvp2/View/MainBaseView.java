package huangasys.com.app_mvp2.View;

/**
 * Created by "huangsays"  on 2017/8/23.20:05"huangays@gmail.com"
 */

public interface MainBaseView extends BaseView {

    void loginSuccess(String msg);//登陆成功

    void loginFail(String msg);//登录失败
}
