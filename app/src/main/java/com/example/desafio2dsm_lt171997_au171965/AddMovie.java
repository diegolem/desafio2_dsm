package com.example.desafio2dsm_lt171997_au171965;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

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

    public void cancel(View v){ finish(); }

    public void save(View v){
        String title = txtTitle.getText().toString();
        String description = txtDescription.getText().toString();
        String releaseyear = txtReleaseYear.getText().toString();
        String rating = txtRating.getText().toString();

        if (TextUtils.isEmpty(title)) {
            Toast.makeText(getApplicationContext(), "Ingresa un titulo válido", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(description)) {
            Toast.makeText(getApplicationContext(), "Ingresa una descripción válida", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(releaseyear)) {
            Toast.makeText(getApplicationContext(), "Ingresa un año de lanzamiento válido", Toast.LENGTH_LONG).show();
            return;
        }

        try{
            int rlsYear = Integer.parseInt(releaseyear);
            if(rlsYear < 0){
                Toast.makeText(getApplicationContext(), "El año de lanzamiento no puede ser menor que 0", Toast.LENGTH_LONG).show();
                return;
            }

            if(rlsYear > 2022){
                Toast.makeText(getApplicationContext(), "El año de lanzamiento válido no puede ser mayor al actual", Toast.LENGTH_LONG).show();
                return;
            }
        }
        catch (NumberFormatException ex){
            Toast.makeText(getApplicationContext(), "Ingresa un año de lanzamiento válido", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(rating)) {
            Toast.makeText(getApplicationContext(), "Ingresa una calificación válida", Toast.LENGTH_LONG).show();
            return;
        }

        try{
            int rlsRating = Integer.parseInt(rating);
            if(!(rlsRating >= 1 && rlsRating <= 4)){
                Toast.makeText(getApplicationContext(), "La calificación debe estar entre 1 y 4", Toast.LENGTH_LONG).show();
                return;
            }
        }
        catch (NumberFormatException ex){
            Toast.makeText(getApplicationContext(), "Ingresa un año de lanzamiento válido", Toast.LENGTH_LONG).show();
            return;
        }

        Movie obj_movie = new Movie(title, description, releaseyear, rating);

        if(action.equals("e")){
            Movies.refMovies.child(id).setValue(obj_movie);
        }else{
            Movies.refMovies.push().setValue(obj_movie);
        }

        finish();
    }
}
