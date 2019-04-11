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

import com.example.allesin1app.AlbumApplication;
import com.example.allesin1app.R;
import com.example.allesin1app.album.Album;

import java.util.Calendar;
import java.util.Date;

public class SongEditActivity extends AppCompatActivity {

    private int songId, albumId, year, month, day;
    private AlbumApplication gv;
    private Album album;
    private Song song;
    private Date date, newReleaseDate;
    private EditText songName, songGenres, songArtist, songLength;
    private TextView songEditDateView;
    private CheckBox songExplicit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.gv = (AlbumApplication) getApplicationContext();

        Intent intent = getIntent();
        this.songId = intent.getIntExtra("song id", 0);
        this.albumId = intent.getIntExtra("album id", 0);

        album = gv.albumDataProvider.findAlbumById(albumId);
        song = album.findSongById(songId);
        setTitle(song.getName() + " (bewerking modus)");

        songName = findViewById(R.id.songName);
        songGenres = findViewById(R.id.songGenres);
        songArtist = findViewById(R.id.songArtist);
        songEditDateView = findViewById(R.id.songEditDateView);
        songLength = findViewById(R.id.songLength);
        songExplicit = findViewById(R.id.songExplicit);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        if (song != null) {
            date = song.getReleaseDate();
            songName.setText(song.getName());
            songGenres.setText(song.getGenres());
            songArtist.setText(song.getArtist());
            showDate(year, month, day);
            songLength.setText(String.valueOf(song.getLength()));
            songExplicit.setChecked(song.isExplicit());
        }
    }

    public void saveSongChanges(View view) {
        song.setSongName(songName.getText().toString());
        song.setGenres(songGenres.getText().toString());
        song.setArtist(songArtist.getText().toString());
        song.setReleaseDate(newReleaseDate);
        song.setLength(Integer.parseInt(songLength.getText().toString()));
        song.setExplicit(songExplicit.isChecked());
        finish();
    }

    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, R.style.DialogTheme, myDateListener, year, month, day);
        }
        return null;
    }

    private void showDate(int year, int month, int day) {
        this.newReleaseDate = new Date(year, month, day);
        this.songEditDateView.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year));
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
