package com.example.allesin1app.song;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.example.allesin1app.GlobalVars;
import com.example.allesin1app.R;
import com.example.allesin1app.album.Album;
import com.example.allesin1app.album.AlbumActivity;
import com.example.allesin1app.album.AlbumDataProvider;
import com.example.allesin1app.album.AlbumListActivity;

import java.util.ArrayList;

public class SongAddActivity extends AppCompatActivity {

    private EditText songName, songGenres, songArtist, songReleaseDate, songLength, songExplicit;
    private Button songAddButton;
    private Song song;
    private ArrayList<Album> albums;
    private Album album;
    private int albumId;

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
        songAddButton = findViewById(R.id.songAddButton);

        songAddButton.setOnClickListener(View -> addSong());
    }

    public void addSong(){
        song = new Song(songName.getText().toString(), songGenres.getText().toString(), songArtist.getText().toString(), songReleaseDate.getText().toString(), Integer.parseInt(songLength.getText().toString()), true);
        album.AddSongToAlbum(song);
        super.finish();
    }
}
