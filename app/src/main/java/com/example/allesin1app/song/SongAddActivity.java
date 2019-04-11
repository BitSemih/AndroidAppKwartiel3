package com.example.allesin1app.song;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allesin1app.GlobalVars;
import com.example.allesin1app.R;
import com.example.allesin1app.album.Album;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SongAddActivity extends AppCompatActivity {

    private EditText songName, songGenres, songArtist, songReleaseDate, songLength;
    private Album album;
    private Song song;
    private CheckBox songExplicit;
    private TextView songAddDateView;
    private int albumId, year, month, day;
    private boolean checked = false;
    private Date releaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GlobalVars gv = (GlobalVars) getApplicationContext();
        Intent intent = getIntent();

        albumId = intent.getIntExtra("album id", 0);
        album = gv.adp.findAlbumById(albumId);

        songName = findViewById(R.id.songName);
        songGenres = findViewById(R.id.songGenres);
        songArtist = findViewById(R.id.songArtist);
        songReleaseDate = findViewById(R.id.songReleaseDate);
        songLength = findViewById(R.id.songLength);
        songExplicit = findViewById(R.id.songExplicit);
        songAddDateView = findViewById(R.id.songAddDateView);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year, month, day);
    }

    public void addSong(View view){
        if (songExplicit.isChecked()){
            checked = true;
        }
        song = new Song(songName.getText().toString(), songGenres.getText().toString(), songArtist.getText().toString(), this.releaseDate, Integer.parseInt(songLength.getText().toString()), checked);
        album.AddSongToAlbum(song);
        super.finish();
    }

    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, R.style.DialogTheme, myDateListener, year, month, day);
        }
        return null;
    }

    private void showDate(int year, int month, int day) {
        this.releaseDate = new Date(year, month, day);
        this.songAddDateView.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).append(" - Klik om het te veranderen"));
        System.out.println(this.releaseDate);
    }

    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Kies uw uitbreng datum", Toast.LENGTH_SHORT).show();
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2, arg3);
        }
    };
}
