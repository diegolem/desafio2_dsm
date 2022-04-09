package com.example.desafio2dsm_lt171997_au171965;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnFailureListener;

import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class ResetPassword extends AppCompatActivity {
    private Button resetPasswordBtn;
    private EditText txtEmailRecovery;
    private FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        fbAuth = FirebaseAuth.getInstance();

        resetPasswordBtn = findViewById(R.id.btnResetPasword);
        txtEmailRecovery = findViewById(R.id.txtEmailRecovery);

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recoveryEmail;
                recoveryEmail = txtEmailRecovery.getText().toString();
                //ciegolem7@gmail.com

                fbAuth.sendPasswordResetEmail(recoveryEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ResetPassword.this,"El correo para recuperar tu contraseña fue enviado correctamente",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getBaseContext(), Login.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(ResetPassword.this,"Ocurrio un error al enviar el correo de recuperación de contraseña",Toast.LENGTH_LONG).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ResetPassword.this,"Ocurrio un erro al resetear la contraseña",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
