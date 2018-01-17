package com.example.goran.mymoviedb.movies.util;

import android.text.TextUtils;

import com.example.goran.mymoviedb.data.model.Genre;
import com.example.goran.mymoviedb.data.model.singlemovie.MovieDetails;

import java.util.ArrayList;

/**
 * Created by Goran on 23.12.2017..
 */

public class MovieUtils {

    public static String formatTitle(String title, String date) {
        return title + " (" + date.substring(0, 4) + ")";
    }

    public static String getGenres(MovieDetails movieDetails) {
        ArrayList<String> genres = new ArrayList<>();

        for (Genre genre : movieDetails.getGenres()) {
            genres.add(genre.getName());
        }

        return TextUtils.join(",\n", genres);
    }

    public static String getGenreName(int id) {

        String genreName;

        switch (id) {
            case 28:
                genreName = "Action";
                break;
            case 12:
                genreName = "Adventure";
                break;
            case 16:
                genreName = "Animation";
                break;
            case 35:
                genreName = "Comedy";
                break;
            case 80:
                genreName = "Crime";
                break;
            case 99:
                genreName = "Documentary";
                break;
            case 18:
                genreName = "Drama";
                break;
            case 10751:
                genreName = "Family";
                break;
            case 14:
                genreName = "Fantasy";
                break;
            case 36:
                genreName = "History";
                break;
            case 27:
                genreName = "Horror";
                break;
            case 10402:
                genreName = "Music";
                break;
            case 9648:
                genreName = "Mystery";
                break;
            case 10749:
                genreName = "Romance";
                break;
            case 878:
                genreName = "SciFi";
                break;
            case 10770:
                genreName = "TV";
                break;
            case 53:
                genreName = "Thriller";
                break;
            case 10752:
                genreName = "War";
                break;
            case 37:
                genreName = "Western";
                break;
            default:
                genreName = "N/A";
        }
        return genreName;
    }
}