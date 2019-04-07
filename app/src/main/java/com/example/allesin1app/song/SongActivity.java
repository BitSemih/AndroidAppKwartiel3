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

public class SongActivity extends AppCompatActivity {

    private static final String SHARED_PREFERENCE = "";
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

        this.gv = (GlobalVars) getApplicationContext();

        Intent intent = getIntent();
        songId = intent.getIntExtra("song id", 0);
        albumId = intent.getIntExtra("album id", 0);

        if (songId == 0 && albumId == 0) {
            SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCE, 0);
            songId = settings.getInt("song id", 0);
            albumId = settings.getInt("album id", 0);
        }

        songName = findViewById(R.id.songName);
        songGenres = findViewById(R.id.songGenres);
        songArtist = findViewById(R.id.songArtist);
        songReleaseDate = findViewById(R.id.songReleaseDate);
        songLength = findViewById(R.id.songLength);
        songExplicit = findViewById(R.id.songExplicit);

        getData();
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
        getData();
    }

    private void getData() {
        this.album = gv.adp.findAlbumById(albumId);
        this.song = album.findSongById(songId);
        setTitle(song.getName());
        if (song != null) {
            songName.setText(song.getName());
            songGenres.setText(song.getGenres());
            songArtist.setText(song.getArtist());
            songReleaseDate.setText(song.getReleaseDate());
            songLength.setText(String.valueOf(song.getLength()));
            if (song.isExplicit()) {
                songExplicit.setText("Explicit");
            } else {
                songExplicit.setText("Niet Explicit");
            }
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
