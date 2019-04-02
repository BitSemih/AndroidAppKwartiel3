package com.example.allesin1app.song;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.allesin1app.GlobalVars;
import com.example.allesin1app.R;
import com.example.allesin1app.album.Album;

public class SongActivity extends AppCompatActivity {

    private int songId, albumId;
    private Song song;
    private Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        songId = intent.getIntExtra("song id", 0);
        albumId = intent.getIntExtra("album id", 0);

        GlobalVars gv = (GlobalVars) getApplicationContext();
        album = gv.adp.findAlbumById(albumId);
        song = album.findSongById(songId);
        System.out.println(song);
    }
}
