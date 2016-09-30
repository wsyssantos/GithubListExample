package com.example.wesley.githublist.user;

import com.example.wesley.githublist.model.User;
import com.example.wesley.githublist.service.GitHubService;

import rx.Subscriber;

/**
 * Created by wesley on 9/15/16.
 */
public class UserPresenter implements UserContract.Presenter {
    protected User user;
    protected UserContract.View view;

    public UserPresenter(UserContract.View view) {
        this.view = view;
    }

    @Override
    public void gerUserByLogin(String login) {
        GitHubService gitHubService = GitHubService.getInstance();
        gitHubService.getUserByLogin(login, new Subscriber<User>() {
            @Override
            public void onCompleted() {
                view.userReceived(user);
            }

            @Override
            public void onError(Throwable e) {
                view.errorReceived(e);
            }

            @Override
            public void onNext(User userReceived) {
                user = userReceived;
            }
        });
    }
}
