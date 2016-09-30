package com.example.wesley.githublist.repositorylist;

import com.example.wesley.githublist.model.Repository;
import com.example.wesley.githublist.model.RepositoryListResponse;
import com.example.wesley.githublist.service.GitHubService;

import java.util.List;

import rx.Subscriber;

/**
 * Created by wesley on 9/15/16.
 */
public class RepositoryListPresenter implements RepositoryListContract.Presenter {
    private RepositoryListContract.View view;
    private List<Repository> repositoryList;

    public RepositoryListPresenter(RepositoryListContract.View view) {
        this.view = view;
    }

    @Override
    public void loadRepositoriesList(int pageNumber) {
        view.showLoading();
        GitHubService gitHubService = GitHubService.getInstance();
        gitHubService.getReposiryListByPage(pageNumber, new Subscriber<RepositoryListResponse>() {
            @Override
            public void onCompleted() {
                view.hideLoading();
                view.repositoriesReceived(repositoryList);
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                view.errorReceived(e);
            }

            @Override
            public void onNext(RepositoryListResponse repositoryListResponse) {
                if(repositoryListResponse != null && repositoryListResponse.getItems() != null) {
                    repositoryList = repositoryListResponse.getItems();
                }
            }
        });
    }
}
