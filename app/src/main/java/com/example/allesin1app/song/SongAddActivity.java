package com.example.allesin1app.song;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.allesin1app.GlobalVars;
import com.example.allesin1app.R;
import com.example.allesin1app.album.Album;

import java.util.ArrayList;

public class SongAddActivity extends AppCompatActivity {

    private EditText songName, songGenres, songArtist, songReleaseDate, songLength;
    private ArrayList<Album> albums;
    private Album album;
    private Song song;
    private CheckBox songExplicit;
    private int albumId;
    private boolean checked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GlobalVars gv = (GlobalVars) getApplicationContext();
        Intent intent = getIntent();

        albums = gv.adp.getAlbums();
        albumId = intent.getIntExtra("album id", 0);
        album = gv.adp.findAlbumById(albumId);



        songName = findViewById(R.id.songName);
        songGenres = findViewById(R.id.songGenres);
        songArtist = findViewById(R.id.songArtist);
        songReleaseDate = findViewById(R.id.songReleaseDate);
        songLength = findViewById(R.id.songLength);
        songExplicit = findViewById(R.id.songExplicit);
    }

    public void addSong(View view){
        if (songExplicit.isChecked()){
            checked = true;
        }
        song = new Song(songName.getText().toString(), songGenres.getText().toString(), songArtist.getText().toString(), songReleaseDate.getText().toString(), Integer.parseInt(songLength.getText().toString()), checked);
        album.AddSongToAlbum(song);
        super.finish();
    }
}
