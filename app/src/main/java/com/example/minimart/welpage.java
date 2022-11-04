package com.example.minimart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class welpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_welpage );

        Button stbt = findViewById (R.id.stbt );
        stbt.setOnClickListener ( v -> {
            startActivity (  new Intent (this,logp.class) );
        } );

    }
}