package com.example.allesin1app.album;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.allesin1app.AlbumApplication;
import com.example.allesin1app.R;

import java.util.ArrayList;

//Activity for display a list with albums
public class AlbumListActivity extends AppCompatActivity {
    private ArrayList<Album> albums = new ArrayList<>();
    private ArrayAdapter<Album> adapter;
    private TextView listItem;
    private String albumName;
    private Album album;
    private AlbumApplication albumApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        albumApplication = (AlbumApplication) getApplicationContext();

        //Getting all albums
        albums = albumApplication.albumDataProvider.getAlbums();
        //Creating custom adapter
        this.adapter = new AlbumAdapter(this, albums);

        //Placing adapter in listview
        ListView listView = findViewById(R.id.albumList);
        listView.setAdapter(adapter);
        //On click listener for each item
        listView.setOnItemClickListener(this::onItemClick);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.adapter.notifyDataSetChanged();
    }

    //On click method for each item
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Find textview
        listItem = view.findViewById(R.id.listItem);
        albumName = listItem.getText().toString();
        //Finding album
        album = albumApplication.albumDataProvider.findAlbumByName(albumName);
        goToAlbum(album.getId());
    }

    //Method for starting album activity with a specific album
    public void goToAlbum(int albumId) {
        //Starting activity and sending album id along
        Intent intent = new Intent(this, AlbumActivity.class);
        intent.putExtra("album id", albumId);
        startActivity(intent);
    }

    //Method for starting album add activity
    public void goAddAlbum(View view) {
        //Starting activity
        Intent intent = new Intent(this, AlbumAddActivity.class);
        startActivity(intent);
    }
}
