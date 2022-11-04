package com.example.minimart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class  ofloyalty extends AppCompatActivity {
    private FloatingActionButton addcard;
    private EditText cardn,cardb;
    private EditText  phnumber, pemail;
    private FirebaseDatabase database;
    private DatabaseReference userRef,loyal;
    private FirebaseAuth Fauth;
    private FirebaseUser user;
    private FirebaseFirestore fstore;
    private static final String USER = "user";
    private String currentUserNIC;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_ofloyalty );

        FloatingActionButton add = findViewById ( R.id.add );
        add.setOnClickListener ( v -> {
            Intent intent = new Intent ( this ,ofaddloyalcard.class );
            startActivity ( intent );
        } );


            Intent intent = getIntent ( );
            String email = intent.getStringExtra ( "email" );

            cardb = findViewById ( R.id.cardb );
            cardn = findViewById ( R.id.cardn);
            phnumber = findViewById ( R.id.phnumber );
            pemail = findViewById ( R.id.email );
            database = FirebaseDatabase.getInstance ( );
            userRef = database.getReference ( USER );


        FirebaseUser user = FirebaseAuth.getInstance ( ).getCurrentUser ( );
        if (user != null) {
            currentUserNIC = user.getUid ( );
        } else {
            startActivity ( new Intent ( getApplicationContext ( ) , logp.class ) );
            finish ( );
            currentUserNIC = "UserID";
        }

            DatabaseReference databaseReference = FirebaseDatabase.getInstance ( ).getReference ( "loyalaty" );
            Query checkUser = databaseReference.orderByChild ( currentUserNIC );

            checkUser.addListenerForSingleValueEvent ( new ValueEventListener ( ) {
                @Override
                public void onDataChange ( @NonNull DataSnapshot datasnapshot ) {
                    if (datasnapshot.child ( currentUserNIC ).exists ( )) {
                        String number = datasnapshot.child ( currentUserNIC ).child ( "Card Name" ).getValue (String.class);
                        String balance = datasnapshot.child ( currentUserNIC ).child ( "Card Number" ).getValue (String.class);
                        String email = datasnapshot.child ( currentUserNIC ).child ( "Email" ).getValue (String.class);
                        String phone = datasnapshot.child ( currentUserNIC ).child ( "Phone" ).getValue (String.class);

                        try {
                            cardb.setText (balance );
                            cardn.setText (number);
                            pemail.setText ( email);
                            phnumber.setText (phone );

                        } catch (Exception e){
                            Toast.makeText ( ofloyalty.this , e.getMessage () , Toast.LENGTH_SHORT ).show ( );
                        }

                    }
                }



                @Override
                public void onCancelled ( @NonNull DatabaseError error ) {

                }
            } );
        DatabaseReference databaseRef = FirebaseDatabase.getInstance ( ).getReference ( "Users" );
        Query checkde = databaseRef.orderByChild ( currentUserNIC );

        checkde.addListenerForSingleValueEvent ( new ValueEventListener ( ) {
            @Override
            public void onDataChange ( @NonNull DataSnapshot datasnap ) {
                if (datasnap.child ( currentUserNIC ).exists ( )) {
                    String email = datasnap.child ( currentUserNIC ).child ( "Email" ).getValue (String.class);
                    String phone = datasnap.child ( currentUserNIC ).child ( "Phone" ).getValue (String.class);

                    try {
                        pemail.setText ( email);
                        phnumber.setText (phone );

                    } catch (Exception e){
                        Toast.makeText ( ofloyalty.this , e.getMessage () , Toast.LENGTH_SHORT ).show ( );
                    }

                }
            }

            @Override
            public void onCancelled ( @NonNull  DatabaseError error ) {

            }
        } );
    }
}