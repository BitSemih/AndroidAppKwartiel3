package com.example.allesin1app.album;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.example.allesin1app.GlobalVars;
import com.example.allesin1app.R;

public class AlbumAddActivity extends AppCompatActivity {

    private EditText albumName, albumDate;
    private Button backToHomeButton;
    private Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        albumName = findViewById(R.id.addAlbumName);
        albumDate = findViewById(R.id.addAlbumRelease);
        backToHomeButton = findViewById(R.id.albumAddButton);

        backToHomeButton.setOnClickListener(View -> addAlbum());
    }

    private void addAlbum() {
        album = new Album(albumName.getText().toString(), albumDate.getText().toString());
        System.out.println(album);
        ((GlobalVars) getApplicationContext()).adp.addToAlbums(album);
        finish();
    }
}

