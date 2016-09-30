package com.example.wesley.githublist.repositorylist;

import com.example.wesley.githublist.base.BasePresenter;
import com.example.wesley.githublist.base.BaseView;
import com.example.wesley.githublist.model.Repository;

import java.util.List;

/**
 * Created by wesley on 9/15/16.
 */
public interface RepositoryListContract {

    interface View extends BaseView<Presenter> {
        void repositoriesReceived(List<Repository> repositoryList);
    }

    interface Presenter extends BasePresenter {
        void loadRepositoriesList(int pageNumber);
    }
}
