package com.example.allesin1app.album;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.album_list_item, parent, false);
        }

        Album currentAlbum = albumList.get(position);
        int songCount = currentAlbum.getSongCount();

        AlbumListItemDraw x = listItem.findViewById(R.id.albumSongCounter);
        x.rectangleCount(songCount);

        TextView name = listItem.findViewById(R.id.listItem);

        name.setText(currentAlbum.getName());

        return listItem;
    }
}
