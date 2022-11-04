package com.example.minimart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ofmoredatilsmanual extends AppCompatActivity {
Button moremanual;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_ofmoredatilsmanual );

        Button moremanual = findViewById ( R.id.moremanual);
        moremanual.setOnClickListener ( v -> {
            Intent intent = new Intent ( this , ofmoredatails1.class );
            startActivity ( intent );
        } );
    }
}