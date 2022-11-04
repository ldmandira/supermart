package com.example.minimart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ofmenu extends AppCompatActivity {
    Button moredbt;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_ofmenu );

        Button calbt = findViewById (R.id.calbt );
        calbt.setOnClickListener ( v -> {
            Intent intent = new Intent (this,ofcal.class);
            startActivity ( intent );
        } );

        Button loyalbt = findViewById (R.id.loyalbt );
        loyalbt.setOnClickListener ( v -> {
            Intent intent = new Intent (this,ofloyalty.class);
            startActivity ( intent );
        } );

        Button editebt = findViewById (R.id.editebt);
        editebt.setOnClickListener ( v -> {
            Intent intent = new Intent (this,ofeditpro.class);
            startActivity ( intent );
        } );

        Button locatbt = findViewById (R.id.locatbt );
        locatbt.setOnClickListener ( v -> {
            Intent intent = new Intent (this,oflocation.class);
            startActivity ( intent );
        } );

        Button contact = findViewById (R.id.contact );
        contact.setOnClickListener ( v -> {
            Intent intent = new Intent (this,ofcontact.class);
            startActivity ( intent );
        } );


        moredbt= findViewById (R.id.moredbt );

        moredbt.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                IntentIntegrator intentIntegrator = new IntentIntegrator (
                        ofmenu.this
                );
                intentIntegrator.setPrompt ( "For flash use volume up key" );

                intentIntegrator.setBeepEnabled ( true );

                intentIntegrator.setOrientationLocked ( true );

                intentIntegrator.setCaptureActivity ( Capture.class );

                intentIntegrator.initiateScan ();


            }
        } );
    }

    @Override
    protected void onActivityResult ( int requestCode , int resultCode , @Nullable Intent data ) {
        super.onActivityResult ( requestCode , resultCode , data );

        IntentResult intentResult = IntentIntegrator.parseActivityResult (
                requestCode,resultCode,data
        );
        if(intentResult.getContents ()!=null) {
            AlertDialog.Builder builder = new AlertDialog.Builder (
                    ofmenu.this
            );
            builder.setTitle ( "Result" );
            builder.setMessage ( intentResult.getContents ( ) );
            builder.setPositiveButton ( "ok" , new DialogInterface.OnClickListener ( ) {
                @Override
                public void onClick ( DialogInterface dialog , int which ) {
                    dialog.dismiss ( );
                }
            } );

            builder.show ( );
        }else {
            Toast.makeText ( getApplicationContext (),"OOPS... You did not scan anything",Toast.LENGTH_LONG ).show ();
        }

    }


}