package com.example.minimart;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class Mainmenu extends AppCompatActivity {
    FloatingActionButton ofmenu, onmenu;
    FirebaseAuth Fauth;
    Button logoutbt,no,yes;
    TextView semail;
    Dialog logout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_mainmenu );

        FloatingActionButton ofmenu = findViewById ( R.id.ofmenu );
        ofmenu.setOnClickListener ( v -> {
            Intent intent = new Intent ( this , ofmenu.class );
            startActivity ( intent );
        } );

        FloatingActionButton onmenu = findViewById ( R.id.onmenu );
        onmenu.setOnClickListener ( v -> {
            Intent intent = new Intent ( this , Onlinewel.class );
            startActivity ( intent );
            semail = findViewById ( R.id.semail );
            Fauth = getInstance ( );
        } );



        logoutbt = findViewById ( R.id.logoutbt );

        logout = new Dialog ( Mainmenu.this );
        logout.setContentView ( R.layout.logout );
        logout.getWindow ( ).setBackgroundDrawable ( getDrawable ( R.drawable.bg_logout ) );
        logout.getWindow ( ).setLayout ( ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT );


        yes = logout.findViewById ( R.id.yes );
        no = logout.findViewById ( R.id.no );

        no.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                FirebaseAuth.getInstance ( ).signOut ( );
                Toast.makeText ( com.example.minimart.Mainmenu.this , "Logout Canceled" , Toast.LENGTH_SHORT ).show ( );
                logout.dismiss ( );
            }
        } );

        yes.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                Toast.makeText ( com.example.minimart.Mainmenu.this , "Logouting...." , Toast.LENGTH_SHORT ).show ( );
                logout.dismiss ( );
                startActivity ( new Intent ( getApplicationContext ( ) , logp.class ) );
            }
        } );


    }

    @Override
    protected void onStart ( ) {

        super.onStart ( );

        FirebaseUser user = Fauth.getInstance ( ).getCurrentUser ( );
        if (user != null) {
            String email = user.getEmail ( );
        } else {
            startActivity ( new Intent ( getApplicationContext ( ) , logp.class ) );
            finish ( );
        }
    }



    public void onClick ( View view ) {
        logout.show ();
    }

}
