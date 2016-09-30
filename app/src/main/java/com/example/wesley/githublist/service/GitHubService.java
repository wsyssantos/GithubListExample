package com.example.wesley.githublist.service;

import com.example.wesley.githublist.api.PullRequestApi;
import com.example.wesley.githublist.api.RepositoryApi;
import com.example.wesley.githublist.api.UserApi;
import com.example.wesley.githublist.model.PullRequest;
import com.example.wesley.githublist.model.RepositoryListResponse;
import com.example.wesley.githublist.model.User;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wesley on 9/15/16.
 */
public class GitHubService {
    public static final String ENDPOINT = "https://api.github.com/";
    private static GitHubService gitHubService;

    private Retrofit retrofit;

    public GitHubService() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getReposiryListByPage(int pageNumber, Subscriber<RepositoryListResponse> subscriber) {
        RepositoryApi repositoryApi = retrofit.create(RepositoryApi.class);
        Observable<RepositoryListResponse> responseObservable = repositoryApi.getPageRepository(pageNumber);
        responseObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getRepositoryPullRequestList(String user, String repository, Subscriber<List<PullRequest>> subscriber) {
        PullRequestApi requestApi = retrofit.create(PullRequestApi.class);
        Observable<List<PullRequest>> listObservable = requestApi.getRepositoryPullRequests(user, repository);
        listObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getUserByLogin(String user, Subscriber<User> subscriber) {
        UserApi userApi = retrofit.create(UserApi.class);
        Observable<User> userObservable = userApi.getUserByLogin(user);
        userObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static GitHubService getInstance() {
        if(gitHubService == null)
            gitHubService = new GitHubService();
        return gitHubService;
    }
}
