package com.example.allesin1app.song;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.allesin1app.GlobalVars;
import com.example.allesin1app.R;
import com.example.allesin1app.album.Album;
import com.example.allesin1app.album.AlbumActivity;
import com.example.allesin1app.compound.SongCompound;

public class SongActivity extends AppCompatActivity {

    private static final String SHARED_PREFERENCE = "";
    private int songId, albumId;
    private SongCompound comp;
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

        this.gv = (GlobalVars) getApplicationContext();

        Intent intent = getIntent();
        this.songId = intent.getIntExtra("song id", 0);
        this.albumId = intent.getIntExtra("album id", 0);

        if (songId == 0 && albumId == 0) {
            SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCE, 0);
            this.songId = settings.getInt("song id", 0);
            this.albumId = settings.getInt("album id", 0);

        }

        System.out.println(songId);
        System.out.println(albumId);

        this.album = gv.adp.findAlbumById(this.albumId);
        this.song = album.findSongById(this.songId);

        this.comp = this.findViewById(R.id.compound_view);
        comp.populateView(this.song);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCE, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.clear();
        editor.putInt("song id", songId);
        editor.putInt("album id", albumId);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.album = gv.adp.findAlbumById(this.albumId);
        this.song = album.findSongById(this.songId);
        System.out.println(song);
        if (song == null){
            System.out.println("PANIEK");
        } else {
            this.comp.populateView(this.song);
        }
    }


    public void goEditSong(View view) {
        Intent intent = new Intent(this, SongEditActivity.class);
        intent.putExtra("song id", songId);
        intent.putExtra("album id", albumId);
        startActivity(intent);
    }

    public void deleteSong(View view){
        album.deleteSong(song);
        finish();
    }
}
