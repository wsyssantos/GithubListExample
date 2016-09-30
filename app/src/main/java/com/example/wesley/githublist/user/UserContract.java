package com.example.wesley.githublist.user;

import com.example.wesley.githublist.base.BasePresenter;
import com.example.wesley.githublist.base.BaseView;
import com.example.wesley.githublist.model.User;

/**
 * Created by wesley on 9/16/16.
 */
public interface UserContract {

    interface View extends BaseView<Presenter> {
        void userReceived(User user);
    }

    interface Presenter extends BasePresenter {
        void gerUserByLogin(String login);
    }
}
