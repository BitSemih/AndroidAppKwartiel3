package com.example.allesin1app.song;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.allesin1app.AlbumApplication;
import com.example.allesin1app.R;
import com.example.allesin1app.album.Album;
import com.example.allesin1app.compound.SongCompound;

//Activity for the song overview
public class SongActivity extends AppCompatActivity {
    private static final String SHARED_PREFERENCE = "";
    private int songId, albumId;
    private SongCompound comp;
    private Song song;
    private Album album;
    private AlbumApplication albumApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.albumApplication = (AlbumApplication) getApplicationContext();

        //Getting album id and song id that were given on creation of this activity
        Intent intent = getIntent();
        this.songId = intent.getIntExtra("song id", 0);
        this.albumId = intent.getIntExtra("album id", 0);

        //Check if song id and album id are the default value of above; 0
        if (songId == 0 && albumId == 0) {
            //Accessing sharedpreferences and getting song id and album id
            SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCE, 0);
            this.songId = settings.getInt("song id", 0);
            this.albumId = settings.getInt("album id", 0);
        }

        //Finding album and song associated with that album
        this.album = albumApplication.albumDataProvider.findAlbumById(this.albumId);
        this.song = album.findSongById(this.songId);

        setTitle(song.getName());

        //Finding and populating custom song compound view
        this.comp = this.findViewById(R.id.compound_view);
        comp.populateView(this.song);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Accessing sharedpreferences and initializing edit mode
        SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCE, 0);
        SharedPreferences.Editor editor = settings.edit();

        //Clearing and setting vars for later use
        editor.clear();
        editor.putInt("song id", songId);
        editor.putInt("album id", albumId);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Finding album and associated song
        this.album = albumApplication.albumDataProvider.findAlbumById(this.albumId);
        this.song = album.findSongById(this.songId);
        //Populating custom song compound view
        this.comp.populateView(this.song);
    }

    //Method for going to edit mode for this song
    public void goEditSong(View view) {
        //Starting activity and sending song id and album id along
        Intent intent = new Intent(this, SongEditActivity.class);
        intent.putExtra("song id", songId);
        intent.putExtra("album id", albumId);
        startActivity(intent);
    }

    //Method for deleting current song
    public void deleteSong(View view) {
        album.deleteSong(song);
        finish();
    }
}
