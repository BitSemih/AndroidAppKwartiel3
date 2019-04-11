package com.example.allesin1app.song;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.allesin1app.R;

import java.util.ArrayList;
import java.util.List;

//Custom adapter for song a list in an album
public class SongAdapter extends ArrayAdapter<Song> {
    private Context context;
    private List<Song> songList;

    public SongAdapter(@NonNull Context context, ArrayList<Song> list) {
        super(context, 0, list);
        this.context = context;
        this.songList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        //Check if listitem does not excist and if not create a new one
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.generic_list_item, parent, false);
        }

        //Getting position of current song
        Song currentSong = songList.get(position);

        //finding textview and adding name of song to it
        TextView name = listItem.findViewById(R.id.listItem);
        name.setText(currentSong.getName());

        return listItem;
    }
}

