package com.example.wesley.githublist.base;

/**
 * Created by wesley on 9/15/16.
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
    void showLoading();
    void hideLoading();
    void errorReceived(Throwable e);
}
