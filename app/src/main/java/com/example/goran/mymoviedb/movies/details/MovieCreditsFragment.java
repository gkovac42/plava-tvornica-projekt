package com.example.goran.mymoviedb.movies.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.goran.mymoviedb.BaseApplication;
import com.example.goran.mymoviedb.R;
import com.example.goran.mymoviedb.data.model.details.Cast;
import com.example.goran.mymoviedb.di.MovieCreditsFragmentModule;
import com.example.goran.mymoviedb.movies.adapters.CastAdapter;
import com.example.goran.mymoviedb.person.PersonActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goran on 16.1.2018..
 */

public class MovieCreditsFragment extends Fragment implements MovieCreditsContract.View {

    @Inject
    MovieCreditsContract.Presenter presenter;

    private CastAdapter adapter;

    @BindView(R.id.txt_credits_director) TextView txtDirector;
    @BindView(R.id.img_credits_director) SimpleDraweeView imgDirector;
    @BindView(R.id.txt_credits_writer) TextView txtWriter;
    @BindView(R.id.img_credits_writer) SimpleDraweeView imgWriter;
    @BindView(R.id.view_director) LinearLayout directorView;
    @BindView(R.id.view_writer) LinearLayout writerView;
    @BindView(R.id.recycler_credits_cast) RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getActivity());

        (((BaseApplication) getActivity().getApplication()).getAppComponent())
                .movieCreditsFragmentSubcomponent(new MovieCreditsFragmentModule(this))
                .inject(this);

        return inflater.inflate(R.layout.fragment_movie_credits, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        adapter = new CastAdapter();
        adapter.setListener(personId -> presenter.onClickPerson(personId));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

        presenter.initPresenter(getActivity().getIntent().getIntExtra("movie_id", 0));
        presenter.loadCredits();
    }

    @Override
    public void displayDirector(String name, Uri profile, int id) {
        txtDirector.setText(name);
        imgDirector.setImageURI(profile);
        directorView.setOnClickListener(view -> presenter.onClickPerson(id));

    }

    @Override
    public void displayWriter(String name, Uri profile, int id) {
        txtWriter.setText(name);
        imgWriter.setImageURI(profile);
        writerView.setOnClickListener(view -> presenter.onClickPerson(id));
    }

    @Override
    public void displayCast(List<Cast> cast) {
        adapter.setDataSource(cast);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToPerson(int personId) {
        Intent intent = new Intent(getActivity(), PersonActivity.class);
        intent.putExtra("person_id", personId);
        startActivity(intent);
    }
}
