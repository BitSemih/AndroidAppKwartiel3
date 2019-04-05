package com.example.allesin1app.album;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.allesin1app.GlobalVars;
import com.example.allesin1app.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AlbumListActivity extends AppCompatActivity {

    private ArrayList<Album> albums = new ArrayList<>();
    private ArrayList<String> albumNames;
    private ArrayAdapter<String> adapter;
    private TextView listItem;
    private String albumName;
    private Album album;
    private GlobalVars gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gv = (GlobalVars) getApplicationContext();

        updateAlbumList();

        this.adapter = new ArrayAdapter<>(this, R.layout.generic_list_item, albumNames);

        ListView listView = findViewById(R.id.albumList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this::onItemClick);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAlbumList();
        this.adapter.clear();
        this.adapter.addAll(albumNames);
        this.adapter.notifyDataSetChanged();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        listItem = view.findViewById(R.id.label);
        albumName = listItem.getText().toString();
        album = gv.adp.findAlbumByName(albumName);
        goToAlbum(album.getId());
    }

    public void updateAlbumList(){
        albums = gv.adp.getAlbums();
        albumNames = new ArrayList<>();

        for (Album album : albums) {
            this.albumNames.add(album.getName());
        }
    }

    public void goToAlbum(int albumId) {
        Intent intent = new Intent(this, AlbumActivity.class);
        intent.putExtra("album id", albumId);
        startActivity(intent);
    }

    public void goAddAlbum(View view) {
        Intent intent = new Intent(this, AlbumAddActivity.class);
        startActivity(intent);
    }
}
