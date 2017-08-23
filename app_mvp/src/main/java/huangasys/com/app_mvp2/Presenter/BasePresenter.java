package huangasys.com.app_mvp2.Presenter;


/**
 * 业务逻辑的基类;
 * Created by "huangsays"  on 2017/8/23.14:57"huangays@gmail.com"
 */

public interface BasePresenter<T> {

    void attachView(T v);//绑定View

    void detachView();//分离View

}
