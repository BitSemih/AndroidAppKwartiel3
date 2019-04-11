package com.example.allesin1app.album;

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

//Custom adapter for album list
public class AlbumAdapter extends ArrayAdapter<Album> {
    private Context context;
    private List<Album> albumList;

    public AlbumAdapter(@NonNull Context context, ArrayList<Album> list) {
        super(context, 0, list);
        this.context = context;
        this.albumList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        //Check if listitem does not excist and if not create a new one
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.album_list_item, parent, false);
        }

        //Getting position of current album
        Album currentAlbum = albumList.get(position);
        //Get how many songs are in the current album
        int songCount = currentAlbum.getSongCount();

        //Call ondraw class
        AlbumListItemDraw x = listItem.findViewById(R.id.albumSongCounter);
        x.setRectangleCount(songCount);

        //finding textview and adding name of song to it
        TextView name = listItem.findViewById(R.id.listItem);
        name.setText(currentAlbum.getName());

        return listItem;
    }
}
