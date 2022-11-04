package com.example.minimart;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class logp extends AppCompatActivity {

    Button loging;
    EditText uname, pword;
    ProgressBar pbar;
    TextView forpword;
    FirebaseAuth fAuth;
    ProgressDialog ProgressDialog;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_logp );

        Button signbt = findViewById ( R.id.signbt );
        signbt.setOnClickListener ( v -> {
            Intent intent = new Intent ( this , signpage.class );
            startActivity ( intent );

        } );

        uname = findViewById ( R.id.uname );
        pword = findViewById ( R.id.pword );
        forpword = findViewById ( R.id.forpword );
        pbar = findViewById ( R.id.pbar );
        loging = findViewById ( R.id.logbt );
        fAuth = FirebaseAuth.getInstance ( );
        forpword = findViewById ( R.id.forpword );


        if (fAuth.getCurrentUser ( ) != null) {
            startActivity ( new Intent ( getApplicationContext ( ) , Mainmenu.class ) );
            finish ( );
        }

        loging.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                String username = uname.getText ( ).toString ( ).trim ( );
                String password = pword.getText ( ).toString ( ).trim ( );
                if (TextUtils.isEmpty ( username )) {
                    uname.setError ( "Email Is Required." );
                    return;
                }
                if (TextUtils.isEmpty ( password )) {
                    pword.setError ( "User Name Is Required." );
                    return;
                }



                ProgressDialog = new ProgressDialog (logp.this);
                ProgressDialog.show();
                ProgressDialog.setContentView ( R.layout.loadpg );
                ProgressDialog.getWindow ().setBackgroundDrawableResource (
                        android.R.color.transparent
                );

                fAuth.signInWithEmailAndPassword ( username , password ).addOnCompleteListener ( new OnCompleteListener<AuthResult> ( ) {
                    @Override
                    public void onComplete ( @NonNull Task<AuthResult> task ) {

                        if (task.isSuccessful ( )) {
                            Toast.makeText ( logp.this , "Logged in Successfully " , Toast.LENGTH_SHORT ).show ( );
                            startActivity ( new Intent ( getApplicationContext ( ) , Mainmenu.class ) );
                        } else {
                            Toast.makeText ( logp.this , "Error ! " + task.getException ( ).getMessage ( ) , Toast.LENGTH_SHORT ).show ( );
                            ProgressDialog.dismiss ();
                        }
                    }
                } );


            }
        } );

        forpword.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                EditText resetMail = new EditText ( v.getContext ( ) );
                AlertDialog.Builder passwordresetDialog = new AlertDialog.Builder ( v.getContext ( ) );
                passwordresetDialog.setTitle ( "RESET PASSWORD ?" );
                passwordresetDialog.setMessage ( "Enter Your Email to Received Reset Link." );
                passwordresetDialog.setView ( resetMail );
                passwordresetDialog.setPositiveButton ( "No" , new DialogInterface.OnClickListener ( ) {

                    @Override
                    public void onClick ( DialogInterface dialog , int which ) {


                    }
                } );
                passwordresetDialog.setPositiveButton ( "Yes" , new DialogInterface.OnClickListener ( ) {
                    @Override
                    public void onClick ( DialogInterface dialog , int which ) {
                        String mail = resetMail.getText ( ).toString ( );
                        fAuth.sendPasswordResetEmail ( mail ).addOnSuccessListener ( new OnSuccessListener<Void> ( ) {
                            @Override
                            public void onSuccess ( Void aVoid ) {
                                Toast.makeText ( logp.this , "Reset Link Sent To Your Email." , Toast.LENGTH_SHORT ).show ( );

                            }
                        } ).addOnFailureListener ( new OnFailureListener ( ) {
                            @Override
                            public void onFailure ( @NonNull Exception e ) {
                                Toast.makeText ( logp.this , "Error! Reset Link is Not Sent." + e.getMessage ( ) , Toast.LENGTH_SHORT ).show ( );
                            }
                        } );
                    }
                } );

                passwordresetDialog.create ( ).show ( );
            }
        } );
    }
}