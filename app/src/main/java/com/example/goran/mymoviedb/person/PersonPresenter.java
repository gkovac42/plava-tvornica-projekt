package com.example.goran.mymoviedb.person;

import com.example.goran.mymoviedb.data.interactors.PersonInteractor;
import com.example.goran.mymoviedb.data.interactors.PersonInteractorImpl;
import com.example.goran.mymoviedb.data.model.person.PersonData;
import com.example.goran.mymoviedb.di.scope.PerActivity;

import javax.inject.Inject;

/**
 * Created by Goran on 26.1.2018..
 */

@PerActivity
public class PersonPresenter implements PersonContract.Presenter, PersonInteractorImpl.PersonListener {

    private PersonContract.View personView;
    private PersonInteractor personInteractor;

    private int personId;

    @Inject
    public PersonPresenter(PersonContract.View personView, PersonInteractor personInteractor) {
        this.personView = personView;
        this.personInteractor = personInteractor;

        personInteractor.setListener(this);
    }

    @Override
    public void initPresenter(int personId) {
        this.personId = personId;
    }

    @Override
    public void loadPersonData() {
        personView.showProgressDialog();
        personInteractor.getPersonData(personId);

    }

    @Override
    public void onDataReady(PersonData personData) {
        personView.displayPersonDetails(personData.getPerson());
        personView.displayRelatedMovies(personData.getRelatedMovies());
        personView.hideProgressDialog();
    }

    @Override
    public void onError() {
        personView.hideProgressDialog();
    }

    @Override
    public void onClickRelatedMovie(int movieId) {
        personView.navigateToRelatedMovie(movieId);
    }

}
