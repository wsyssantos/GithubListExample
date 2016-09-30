package com.example.wesley.githublist.pullrequestlist;

import com.example.wesley.githublist.model.PullRequest;
import com.example.wesley.githublist.model.Repository;
import com.example.wesley.githublist.service.GitHubService;

import java.util.List;

import rx.Subscriber;

/**
 * Created by wesley on 9/15/16.
 */
public class PullRequestListPresenter implements PullRequestListContract.Presenter {

    private PullRequestListContract.View view;
    private List<PullRequest> requestList;

    public PullRequestListPresenter(PullRequestListContract.View view) {
        this.view = view;
    }

    @Override
    public void loadPullRequestList(Repository repo) {
        view.showLoading();
        GitHubService gitHubService = GitHubService.getInstance();
        gitHubService.getRepositoryPullRequestList(repo.getOwner().getLogin(), repo.getName(), new Subscriber<List<PullRequest>>() {
            @Override
            public void onCompleted() {
                view.hideLoading();
                view.pullRequestListReceived(requestList);
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                view.errorReceived(e);
            }

            @Override
            public void onNext(List<PullRequest> pullRequests) {
                requestList = pullRequests;
            }
        });
    }
}
