package com.example.minimart;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class oflocation extends AppCompatActivity {
    private ListView listView;
    private DatabaseReference ref;
    private android.app.ProgressDialog ProgressDialog;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_oflocation );

        listView =  findViewById(R.id.ListView);


        ProgressDialog = new ProgressDialog (oflocation.this);
        ProgressDialog.show();
        ProgressDialog.setContentView ( R.layout.loadpg );
        ProgressDialog.getWindow ().setBackgroundDrawableResource (
                android.R.color.transparent
        );

        final ArrayList<String> myArrayList = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, myArrayList);
        listView.setAdapter(adapter);
        ref= FirebaseDatabase.getInstance().getReference().child("Location");
        ref.addValueEventListener(new ValueEventListener ()

        {

            @Override

            public void onDataChange( DataSnapshot dataSnapshot) {
                myArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    myArrayList.add(snapshot.getValue().toString());

                adapter.notifyDataSetChanged();
                ProgressDialog.dismiss ();
            }


            @Override
            public void onCancelled( DatabaseError databaseerror) {

            }
        });


    }
}