package huangasys.com.mvplogindemo.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import huangasys.com.mvplogindemo.Model.User;
import huangasys.com.mvplogindemo.Presenter.MainPresenterImpl;
import huangasys.com.mvplogindemo.R;

public class MainActivity extends AppCompatActivity implements BaseView{

    private EditText name;
    private EditText pwd;
    private Button mButton;

    private MainPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.edit_name);
        pwd = (EditText) findViewById(R.id.edit_pwd);
        mButton = (Button) findViewById(R.id.button);

        mPresenter = new MainPresenterImpl();
        mPresenter.attachView(this);//初始化;  mPresenter.login(user);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(name.getText().toString().trim(),pwd.getText().toString().trim());
                mPresenter.login(user);
            }
        });
    }

    @Override
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
