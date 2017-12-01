package com.huangasys.veke.app_github_mvp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huangasys.veke.app_github_mvp.adapter.RepositoryAdapter;
import com.huangasys.veke.app_github_mvp.modle.UserRepo;
import com.huangasys.veke.app_github_mvp.presenter.MainActivityPresenter;
import com.huangasys.veke.app_github_mvp.presenter.impl.MainPresenterImpl;
import com.huangasys.veke.app_github_mvp.view.MainActivityView;

import java.util.List;

/**
 * 主界面,实现MainActivityView的接口,实现方法;
 * View里面所有重写的方法 都需要在Impl实现类里面用到,不然调用不到;
 * Impl以参数设置进来即可;
 */
public class MainActivity extends AppCompatActivity implements MainActivityView {


    private MainActivityPresenter mPresenter;

    private Toolbar toolbar;
    private EditText edit_text_username;
    private ProgressBar progress;
    private TextView text_info;
    private RecyclerView recyclerView;
    private RepositoryAdapter adapter;
    private ImageButton button_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_repo);
        mPresenter = new MainPresenterImpl();
        mPresenter.attachView(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edit_text_username = (EditText) findViewById(R.id.edit_text_username);
        progress = (ProgressBar) findViewById(R.id.progress);
        text_info = (TextView) findViewById(R.id.text_info);
        recyclerView = (RecyclerView) findViewById(R.id.repos_recycler_view);
        button_search = (ImageButton) findViewById(R.id.button_search);

        setSupportActionBar(toolbar);

        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edit_text_username.getText().toString();
                if (!"".equals(s)) {
                    mPresenter.loadUserData(s);
                }
            }
        });

        mPresenter.loadUserData("huangsss");

        edit_text_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               button_search.setVisibility( s.length() > 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
        text_info.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);

    }

    @Override
    public void showErrorMessage() {
        progress.setVisibility(View.GONE);
        text_info.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showRecyclerView(List<UserRepo> userRepos) {
        progress.setVisibility(View.GONE);
        text_info.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        adapter = new RepositoryAdapter(this, userRepos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        hideSoftKeyboard();
    }

    @Override
    public void isMainUI() {

    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit_text_username.getWindowToken(), 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
