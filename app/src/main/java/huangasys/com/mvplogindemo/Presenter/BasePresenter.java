package huangasys.com.mvplogindemo.Presenter;

import huangasys.com.mvplogindemo.Model.User;
import huangasys.com.mvplogindemo.View.BaseView;

/**
 * Created by "huangsays"  on 2017/8/23.14:57"huangays@gmail.com"
 */

public interface BasePresenter {

    void attachView(BaseView v);//绑定View

    void detachView();//分离View

    void login(User user);//登录逻辑

}
