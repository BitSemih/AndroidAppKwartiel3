package com.example.allesin1app.song;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.allesin1app.GlobalVars;
import com.example.allesin1app.R;
import com.example.allesin1app.album.Album;

public class SongActivity extends AppCompatActivity {

    private int songId, albumId;
    private Song song;
    private Album album;
    private GlobalVars gv;
    private TextView songName, songGenres, songArtist, songReleaseDate, songLength, songExplicit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        songId = intent.getIntExtra("song id", 0);
        albumId = intent.getIntExtra("album id", 0);

        songName = findViewById(R.id.songName);
        songGenres = findViewById(R.id.songGenres);
        songArtist = findViewById(R.id.songArtist);
        songReleaseDate = findViewById(R.id.songReleaseDate);
        songLength = findViewById(R.id.songLength);
        songExplicit = findViewById(R.id.songExplicit);

        this.gv = (GlobalVars) getApplicationContext();
        album = gv.adp.findAlbumById(albumId);
        song = album.findSongById(songId);
        setTitle(song.getName());
        if (song != null){
            songName.setText(song.getName());
            songGenres.setText(song.getGenres());
            songArtist.setText(song.getArtist());
            songReleaseDate.setText(song.getReleaseDate());
            songLength.setText(String.valueOf(song.getLength()));
            if (song.isExplicit()){
                songExplicit.setText("Explicit");
            } else {
                songExplicit.setText("Niet Explicit");
            }
        }
    }
}
