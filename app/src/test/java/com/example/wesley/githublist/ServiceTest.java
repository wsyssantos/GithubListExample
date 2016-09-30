package com.example.wesley.githublist;

import com.example.wesley.githublist.model.PullRequest;
import com.example.wesley.githublist.model.Repository;
import com.example.wesley.githublist.model.RepositoryListResponse;
import com.example.wesley.githublist.model.User;
import com.example.wesley.githublist.service.GitHubService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by wesley on 9/15/16.
 */
public class ServiceTest {
    private GitHubService service;

    @Before
    public void setUp() {
        service = GitHubService.getInstance();

        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.newThread();
            }
        });
    }

    @After
    public void tearDown() {
        RxAndroidPlugins.getInstance().reset();
    }

    @Test
    public void loadFirstPageFromGitHub() {
        TestSubscriber<RepositoryListResponse> testSubscriber = new TestSubscriber<>();
        service.getReposiryListByPage(1, testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        RepositoryListResponse response = testSubscriber.getOnNextEvents().get(0);
        assertNotNull(response);
        assertNotNull(response.getItems());
        assertThat(response.getItems().size(), greaterThan(0));
    }

    @Test
    public void loadSecondPageFromGitHub() {
        TestSubscriber<RepositoryListResponse> testSubscriber = new TestSubscriber<>();
        service.getReposiryListByPage(2, testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        RepositoryListResponse response = testSubscriber.getOnNextEvents().get(0);
        assertNotNull(response);
        assertNotNull(response.getItems());
        assertThat(response.getItems().size(), greaterThan(0));
    }

    @Test
    public void loadThirdPageFromGitHub() {
        TestSubscriber<RepositoryListResponse> testSubscriber = new TestSubscriber<>();
        service.getReposiryListByPage(3, testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        RepositoryListResponse response = testSubscriber.getOnNextEvents().get(0);
        assertNotNull(response);
        assertNotNull(response.getItems());
        assertThat(response.getItems().size(), greaterThan(0));
    }


    @Test
    public void loadPullRequestsFromFacebookReactNativeRepository() {
        TestSubscriber<List<PullRequest>> testSubscriber = new TestSubscriber<>();
        service.getRepositoryPullRequestList("facebook", "react-native", testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        List<PullRequest> response = testSubscriber.getOnNextEvents().get(0);
        assertNotNull(response);
        assertThat(response.size(), greaterThan(0));
    }

    @Test
    public void loadFourthPageFromGithub_LoadThridElementPullRequests() {
        TestSubscriber<RepositoryListResponse> pageSubscriber = new TestSubscriber<>();
        service.getReposiryListByPage(4, pageSubscriber);
        pageSubscriber.awaitTerminalEvent();
        pageSubscriber.assertNoErrors();
        pageSubscriber.assertCompleted();
        RepositoryListResponse pageResponse = pageSubscriber.getOnNextEvents().get(0);
        assertNotNull(pageResponse);
        assertNotNull(pageResponse.getItems());
        assertNotNull(pageResponse.getItems().get(2));
        Repository selectedRepository = pageResponse.getItems().get(2);
        assertNotNull(selectedRepository.getName());
        assertNotNull(selectedRepository.getOwner());
        assertNotNull(selectedRepository.getOwner().getLogin());
        TestSubscriber<List<PullRequest>> pullSubscriber = new TestSubscriber<>();
        service.getRepositoryPullRequestList(selectedRepository.getOwner().getLogin(), selectedRepository.getName(), pullSubscriber);
        pullSubscriber.awaitTerminalEvent();
        pullSubscriber.assertNoErrors();
        pullSubscriber.assertCompleted();
        List<PullRequest> response = pullSubscriber.getOnNextEvents().get(0);
        assertNotNull(response);
        assertThat(response.size(), greaterThan(0));
    }

    @Test
    public void getFacebookUserDetail() {
        TestSubscriber<User> testSubscriber = new TestSubscriber<>();
        service.getUserByLogin("facebook", testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        User user = testSubscriber.getOnNextEvents().get(0);
        assertNotNull(user);
        assertNotNull(user.getName());
    }
}
