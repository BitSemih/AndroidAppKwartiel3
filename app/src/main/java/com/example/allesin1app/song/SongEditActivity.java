package com.example.allesin1app.song;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.allesin1app.GlobalVars;
import com.example.allesin1app.R;
import com.example.allesin1app.album.Album;

public class SongEditActivity extends AppCompatActivity {

    private Song song;
    private Album album;
    private GlobalVars gv;
    private int songId, albumId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        this.songId = intent.getIntExtra("song id", 0);
        this.albumId = intent.getIntExtra("album id", 0);

        this.gv = (GlobalVars) getApplicationContext();
        album = gv.adp.findAlbumById(albumId);
        song = album.findSongById(songId);
        setTitle(song.getName());
    }
}
