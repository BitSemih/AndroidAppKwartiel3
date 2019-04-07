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

public class SongEditActivity extends AppCompatActivity {

    private int songId, albumId;
    private GlobalVars gv;
    private Album album;
    private Song song;
    private EditText songName, songGenres, songArtist, songReleaseDate, songLength;
    private CheckBox songExplicit;

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

        album = gv.adp.findAlbumById(albumId);
        song = album.findSongById(songId);
        setTitle(song.getName() + " (bewerking modus)");

        songName = findViewById(R.id.songName);
        songGenres = findViewById(R.id.songGenres);
        songArtist = findViewById(R.id.songArtist);
        songReleaseDate = findViewById(R.id.songReleaseDate);
        songLength = findViewById(R.id.songLength);
        songExplicit = findViewById(R.id.songExplicit);

        if (song != null) {
            songName.setText(song.getName());
            songGenres.setText(song.getGenres());
            songArtist.setText(song.getArtist());
            songReleaseDate.setText(song.getReleaseDate());
            songLength.setText(String.valueOf(song.getLength()));
            if (song.isExplicit()) {
                songExplicit.setChecked(true);
            }
        }
    }

    public void saveSongChanges(View view) {
        song.setSongName(songName.getText().toString());
        song.setGenres(songGenres.getText().toString());
        song.setArtist(songArtist.getText().toString());
        song.setReleaseDate(songReleaseDate.getText().toString());
        song.setLength(Integer.parseInt(songLength.getText().toString()));
        song.setExplicit(songExplicit.isChecked());
        finish();
    }
}
