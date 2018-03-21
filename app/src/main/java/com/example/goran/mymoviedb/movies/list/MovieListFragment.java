package com.example.goran.mymoviedb.movies.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.goran.mymoviedb.BaseApplication;
import com.example.goran.mymoviedb.BaseFragment;
import com.example.goran.mymoviedb.R;
import com.example.goran.mymoviedb.data.model.list.Movie;
import com.example.goran.mymoviedb.di.MovieListFragmentModule;
import com.example.goran.mymoviedb.movies.adapters.LargeMovieAdapter;
import com.example.goran.mymoviedb.movies.adapters.MovieAdapterListener;
import com.example.goran.mymoviedb.movies.details.MovieDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Goran on 22.12.2017..
 */

public class MovieListFragment extends BaseFragment implements MovieListContract.View {

    @BindView(R.id.recycler_list) RecyclerView recyclerView;
    @BindView(R.id.progress_list) ProgressBar progressBar;

    @Inject
    MovieListContract.Presenter presenter;

    private LargeMovieAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        (((BaseApplication) getActivity().getApplication()).getAppComponent())
                .movieListFragmentSubcomponent(new MovieListFragmentModule(this))
                .inject(this);

        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, getActivity());

        initAdapter();

        recyclerView.setAdapter(adapter);

        int category = getArguments().getInt("category");

        presenter.initPresenter(category);
        presenter.loadMovies();
    }


    public static MovieListFragment newInstance(int category) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putInt("category", category);
        fragment.setArguments(args);

        return fragment;
    }

    private void initAdapter() {
        adapter = new LargeMovieAdapter();
        adapter.setListener(new MovieAdapterListener() {
            @Override
            public void onClick(int movieId) {
                presenter.onClickMovie(movieId);
            }

            @Override
            public void onBottomReached() {
                presenter.onBottomReached();
            }
        });
    }

    @Override
    public void updateAdapter(List<Movie> movies) {
        adapter.addMovies(movies);
    }

    @Override
    public void navigateToMovie(int movieId) {
        startActivity(MovieDetailsActivity.newIntent(getActivity(), movieId));
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        hideProgressDialog();
    }
}
