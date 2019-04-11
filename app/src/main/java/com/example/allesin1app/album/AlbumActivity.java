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

//Activity for album overview
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
    private AlbumApplication albumApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.albumApplication = (AlbumApplication) getApplicationContext();

        //Getting album id that was given on creation of this activity
        Intent intent = getIntent();
        this.albumId = intent.getIntExtra("album id", 0);

        //Check if album id is the default value of above; 0
        if (albumId == 0){
            //Accessing sharedpreferences and getting album id
            SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCE,0);
            albumId = settings.getInt("album id", 0);
        }

        //Finding album and song associated with that album
        this.album = albumApplication.albumDataProvider.findAlbumById(albumId);
        setTitle(album.getName());

        //Getting all song of this album and creating adapter
        this.songs = album.getAlbumSongs();
        this.adapter = new SongAdapter(this, songs);

        //Placing adapter in listview
        ListView listView = findViewById(R.id.songList);
        listView.setAdapter(adapter);

        //Onclick listener for listview item
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

        //Accessing sharedpreferences and initializing edit mode
        SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCE,0);
        SharedPreferences.Editor editor = settings.edit();

        //Clearing and setting vars for later use
        editor.clear();
        editor.putInt("album id", albumId);
        editor.apply();
    }

    //On item click method
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Find textview
        listItem = view.findViewById(R.id.listItem);
        songName = listItem.getText().toString();
        //Finding song
        song = album.findSongByName(songName);
        goToSong(song.getId());
    }

    //Method for starting song activity with a specific song
    public void goToSong(int songId) {
        //Starting activity and sending album id and song id along
        Intent intent = new Intent(this, SongActivity.class);
        intent.putExtra("song id", songId);
        intent.putExtra("album id", albumId);
        startActivity(intent);
    }

    //Method for starting song add activity
    public void goAddSong(View view) {
        //Starting activity and sending album id along
        Intent intent = new Intent(this, SongAddActivity.class);
        intent.putExtra("album id", albumId);
        startActivity(intent);
    }

    public void deleteAlbum(View view) {
        albumApplication.albumDataProvider.deleteAlbum(album);
        finish();
    }
}
