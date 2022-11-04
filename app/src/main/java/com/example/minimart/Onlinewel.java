package com.example.minimart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Onlinewel extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.onlinewel );

        FloatingActionButton onmenu = findViewById (R.id.onmenu );
        onmenu.setOnClickListener ( v -> {
            Intent intent = new Intent (this,onmenu.class);
            startActivity ( intent );
        });
    }
}