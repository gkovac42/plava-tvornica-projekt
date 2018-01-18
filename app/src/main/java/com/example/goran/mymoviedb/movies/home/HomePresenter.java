package com.example.goran.mymoviedb.movies.home;

import com.example.goran.mymoviedb.di.scope.ActivityScope;
import com.example.goran.mymoviedb.login.LoginContract;

import javax.inject.Inject;

/**
 * Created by Goran on 12.1.2018..
 */

@ActivityScope
public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View homeView;
    private LoginContract.Model loginInteractor;

    @Inject
    public HomePresenter(HomeContract.View homeView, LoginContract.Model loginInteractor) {
        this.homeView = homeView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void getUserData() {
        homeView.initUser();
    }

    @Override
    public void onClickLoginOut() {

        if (!homeView.isGuest()) {
            loginInteractor.deleteCurrentUser();
        }

        homeView.navigateToLogin();
    }

    @Override
    public void onClickPlayingNow() {
        homeView.showPlayingNowList();
    }

    @Override
    public void onClickUpcoming() {
        homeView.showUpcomingList();
    }

    @Override
    public void onClickPopular() {
        homeView.showPopularList();
    }

    @Override
    public void onClickTopRated() {
        homeView.showTopRatedList();
    }

    @Override
    public void onClickSearch() {
        homeView.showSearchFragment();
    }
}
