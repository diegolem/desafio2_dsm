package com.example.desafio2dsm_lt171997_au171965;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.desafio2dsm_lt171997_au171965.model.Movie;

public class AddMovie extends AppCompatActivity {
    EditText txtTitle, txtDescription, txtReleaseYear, txtRating;
    String id = "";
    String title = "";
    String description = "";
    String action = "";
    String releaseYear = "";
    String rating = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_movie);

        txtTitle = findViewById(R.id.txtTitulo);
        txtDescription = findViewById(R.id.txtDesc);
        txtReleaseYear = findViewById(R.id.txtReleaseYear);
        txtRating = findViewById(R.id.txtRating);

        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        id = datos.getString("id");
        title = datos.getString("title");
        description = datos.getString("description");
        releaseYear = datos.getString("releaseyear");
        rating = datos.getString("rating");
        action = datos.getString("action");

        txtTitle.setText(title);
        txtDescription.setText(description);
        txtRating.setText(rating);
        txtReleaseYear.setText(releaseYear);
    }

    public void cancelar(View v){ finish(); }

    public void guardar(View v){
        String title = txtTitle.getText().toString();
        String description = txtDescription.getText().toString();
        String releaseyear = txtReleaseYear.getText().toString();
        String rating = txtRating.getText().toString();

        Movie obj_movie = new Movie(title, description, releaseyear, rating);

        if(action.equals("e")){
            Movies.refMovies.child(id).setValue(obj_movie);
        }else{
            Movies.refMovies.push().setValue(obj_movie);
        }

        finish();
    }
}
