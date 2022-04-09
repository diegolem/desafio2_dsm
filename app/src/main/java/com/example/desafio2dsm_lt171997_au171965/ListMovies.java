package com.example.desafio2dsm_lt171997_au171965;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.desafio2dsm_lt171997_au171965.model.Movie;

import java.util.List;

public class ListMovies extends ArrayAdapter<Movie> {
    List<Movie> movies;
    private Activity context;

    public ListMovies(@NonNull Activity context, @NonNull List<Movie> movies) {
        super(context, R.layout.movie, movies);
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowView;

        if(view != null){
            rowView = view;
        }else{
            rowView = layoutInflater.inflate(R.layout.movie,null);
        }

        TextView txtTitle = (TextView) rowView.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) rowView.findViewById(R.id.txtDescription);
        TextView txtReleaseYear = (TextView) rowView.findViewById(R.id.txtReleaseYear);
        TextView txtRating = (TextView) rowView.findViewById(R.id.txtRating);

        String title = movies.get(position).getTitle();
        String releaseYear = movies.get(position).getReleaseyear();
        String rating = movies.get(position).getRating();
        String description = movies.get(position).getDescription();

        txtTitle.setText("Título: " + title);
        txtReleaseYear.setText("Año de estreno: " + releaseYear);
        txtRating.setText("Calificación: " + rating);
        txtDescription.setText("Descripción: " + description);

        return rowView;
    }
}
