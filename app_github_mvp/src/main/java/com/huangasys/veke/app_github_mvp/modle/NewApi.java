package com.huangasys.veke.app_github_mvp.modle;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**i
 * Created by huangasys on 2017/12/1.14:08
 */

public interface NewApi {

    @GET("users/{username}/repos")
    Observable<List<UserRepo>> publicRepositories(@Path("username") String userName);

    class Factory{
        public static NewApi create(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(NewApi.class);
        }
    }
}
