package com.example.allesin1app.album;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.allesin1app.AlbumApplication;
import com.example.allesin1app.R;
import com.example.allesin1app.song.Song;
import com.example.allesin1app.song.SongActivity;
import com.example.allesin1app.song.SongAdapter;
import com.example.allesin1app.song.SongAddActivity;

import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity {

    private static final String SHARED_PREFERENCE = "";
    private ArrayList<String> songNames = new ArrayList<>();
    private ArrayList<Song> songs;
    private int albumId;
    private Song song;
    private Album album;
    private String songName;
    private TextView listItem;
    private SongAdapter adapter;
    private AlbumApplication gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.gv = (AlbumApplication) getApplicationContext();

        Intent intent = getIntent();
        albumId = intent.getIntExtra("album id", 0);

        if (albumId == 0){
            SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCE,0);
            albumId = settings.getInt("album id", 0);
        }

        album = gv.albumDataProvider.findAlbumById(albumId);

        setTitle(album.getName());

        album = gv.albumDataProvider.findAlbumById(albumId);
        songs = album.getAlbumSongs();
        this.adapter = new SongAdapter(this, songs);

        ListView listView = findViewById(R.id.songList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this::onItemClick);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCE,0);
        SharedPreferences.Editor editor = settings.edit();

        editor.clear();
        editor.putInt("album id", albumId);
        editor.apply();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        listItem = view.findViewById(R.id.listItem);
        songName = listItem.getText().toString();
        song = album.findSongByName(songName);
        goToSong(song.getId());
    }

    public void goAddSong(View view) {
        Intent intent = new Intent(this, SongAddActivity.class);
        intent.putExtra("album id", albumId);
        startActivity(intent);
    }

    public void goToSong(int songId) {
        Intent intent = new Intent(this, SongActivity.class);
        intent.putExtra("song id", songId);
        intent.putExtra("album id", albumId);
        startActivity(intent);
    }

    public void deleteAlbum(View view) {
        gv.albumDataProvider.deleteAlbum(album);
        finish();
    }
}
