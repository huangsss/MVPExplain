### MVP模式的登录小案例;
**MVP模式的核心思想**
> 把Activity中的UI逻辑抽象成View接口，把业务逻辑抽象成Presenter接口，Model类还是原来的Moderl类;

**作用**
- 1.分离视图逻辑和业务逻辑，降低耦合
- 2.Activity只处理生命周期任务，代码简洁
- 3.视图逻辑和业务逻辑抽象到了View和Presenter之中，提高可读性
- 4.Presenter被抽象成接口，可以有多种具体的实现
- 5.业务逻辑在Presenter中，避免后台线程引用Activity导致内存泄露

#### 一、Model—User
> 既然是模拟登录，那么就需要我们的User类;

```
public class User {

    private String name;
    private String pwd;

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
```

#### 二、View—BaseView
> 上面的核心思想也说了，将Activity的UI逻辑抽象成View接口，那么接下来我们实现它；
将UI逻辑，比如这次案例只需要用到的Toast，登录成功或者失败弹出，写成一个接口。

```
public interface BaseView {

    void showToast(String msg);//弹一个吐司

    void loginSuccess(String msg);//登陆成功

    void loginFail(String msg);//登录失败
}
```

#### 三、Presenter—BasePresenter
> 把业务逻辑抽象成Presenter接口，模拟登录，那么业务逻辑肯定是点击登录按钮以后，发起的登录了。
这里我们需要三个方法，分别是绑定刚刚写好的BaseView，解绑，已经登录的逻辑;

```
public interface BasePresenter {

    void attachView(BaseView v);//绑定View

    void detachView();//分离View

    void login(User user);//登录逻辑

}
```

#### 具体实现
首先我们的布局只是最简单的两个EditText加上一个Button；这里没什么好说的。
那么在我们的MainActivity之中通过findViewById找到他们，也是很基础的东西，这里也不多做解释。

##### 第一步
MainActivity实现BaseView的接口。重写他的方法；
```
1.public class MainActivity extends AppCompatActivity implements BaseView

2.  @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginSuccess(String msg) {
        showToast(msg);
    }

    @Override
    public void loginFail(String msg) {
        showToast(msg);
    }

```

##### 第二步
新建MainPresenterImpl实现BasePresenter接口。
实现它的方法,那么在这里，我们直接实现需要的业务逻辑。
```
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
```

#### 第三步
最后也是最重要的一步，首先，需要点击登录按钮，才要去实现登录的逻辑，因此在按钮的点击事件里面我们需要实现业务逻辑。

```
 private MainPresenterImpl mPresenter;//定义业务逻辑;

mPresenter = new MainPresenterImpl();
        mPresenter.attachView(this);//初始化;

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(name.getText().toString().trim(),pwd.getText().toString().trim());
                mPresenter.login(user);//实现需要的业务逻辑;
            }
        });
```

### 总结
大致上就是这样，有点基础的看看代码就能理解了。主要思想也就是将UI逻辑抽象成View接口，把业务逻辑抽象成Presenter接口。
Activity再去实现View接口，定义一个Presenter类实现Presenter接口。最后再去Activity初始化，在需要执行某一逻辑的时候执行。
这样就能实现一个最简单的MVP模式的小例子。
不过这样还有一个问题，那就是如果又有一个OtherActivity也需要实现Toast，以及绑定+解绑View。那就需要转换一下小小的思路了。