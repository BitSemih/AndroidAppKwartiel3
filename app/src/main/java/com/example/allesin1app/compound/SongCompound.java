package com.example.allesin1app.compound;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.allesin1app.AlbumApplication;
import com.example.allesin1app.R;
import com.example.allesin1app.album.Album;
import com.example.allesin1app.song.Song;

import java.util.Date;

public class SongCompound extends LinearLayout {

    private Context context;
    private TextView songName, songGenres, songArtist, songReleaseDate, songLength, songExplicit;
    private AlbumApplication gv;
    private Date date;


    public SongCompound(Context context) {
        super(context);
        this.context = context;
        initializeView();
    }

    public SongCompound(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeView();
    }

    public SongCompound(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initializeView();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        songName = this.findViewById(R.id.songName);
        songGenres = this.findViewById(R.id.songGenres);
        songArtist = this.findViewById(R.id.songArtist);
        songReleaseDate = this.findViewById(R.id.songReleaseDate);
        songLength = this.findViewById(R.id.songLength);
        songExplicit = this.findViewById(R.id.songExplicit);
    }

    public void populateView(Song song) {
        date = song.getReleaseDate();
        songName.setText(song.getName());
        songGenres.setText(song.getGenres());
        songArtist.setText(song.getArtist());
        songReleaseDate.setText(new StringBuilder().append(date.getDay()).append("/").append(date.getMonth() + 1).append("/").append(date.getYear()));
        songLength.setText(String.valueOf(song.getLength()));
        if (song.isExplicit()) {
            songExplicit.setText("Explicit");
        } else {
            songExplicit.setText("Niet Explicit");
        }
    }

    private void initializeView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.compound_song_view, this);
    }
}
