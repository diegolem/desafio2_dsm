package com.example.desafio2dsm_lt171997_au171965;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.desafio2dsm_lt171997_au171965.model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Movies extends AppCompatActivity {

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refMovies = database.getReference("movies");

    // Ordenamiento
    Query consultaOrdenada = refMovies.orderByChild("title");

    List<Movie> moviesList;
    ListView listP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies);

        FloatingActionButton addBtn= findViewById(R.id.addBtn);
        listP = findViewById(R.id.ListP);

        listP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), AddMovie.class);

                intent.putExtra("action","e");
                intent.putExtra("id", moviesList.get(i).getId());
                intent.putExtra("title",moviesList.get(i).getTitle());
                intent.putExtra("description",moviesList.get(i).getDescription());
                intent.putExtra("releaseyear",moviesList.get(i).getReleaseyear());
                intent.putExtra("rating",moviesList.get(i).getRating());

                startActivity(intent);
            }
        });

        listP.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder ad = new AlertDialog.Builder(Movies.this);
                ad.setMessage("¿Deseas eliminar la pelicula?").setTitle("Confirmar");

                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(Movies.this,"La pelicula no fue eliminada",Toast.LENGTH_SHORT).show();
                    }
                });

                ad.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Movies.refMovies.child(moviesList.get(position).getId()).removeValue();

                        Toast.makeText(Movies.this, "Pelicula eliminada exitosamente",Toast.LENGTH_SHORT).show();
                    }
                });

                ad.show();
                return true;
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), AddMovie.class);
                i.putExtra("action","a");
                i.putExtra("key","");
                i.putExtra("title","");
                i.putExtra("description","");
                i.putExtra("releaseyear","");
                i.putExtra("rating","");

                startActivity(i);
            }
        });

        moviesList = new ArrayList<>();

        consultaOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                moviesList.removeAll(moviesList);

                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Movie mov_obj = dato.getValue(Movie.class);
                    mov_obj.setId(dato.getKey());
                    moviesList.add(mov_obj);
                }

                ListMovies adapter = new ListMovies(Movies.this, moviesList);
                listP.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
