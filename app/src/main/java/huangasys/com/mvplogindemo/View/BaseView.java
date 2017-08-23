package huangasys.com.mvplogindemo.View;

/**
 * Created by "huangsays"  on 2017/8/23.14:52"huangays@gmail.com"
 */

public interface BaseView {

    void showToast(String msg);//弹一个吐司

    void loginSuccess(String msg);//登陆成功

    void loginFail(String msg);//登录失败
}
