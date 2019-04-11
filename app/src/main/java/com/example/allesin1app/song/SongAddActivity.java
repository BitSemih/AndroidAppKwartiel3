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

//Activity for adding a song
public class SongAddActivity extends AppCompatActivity {
    private EditText songName, songGenres, songArtist, songLength;
    private Album album;
    private Song song;
    private CheckBox songExplicit;
    private TextView songAddDateView;
    private int albumId, year, month, day;
    private boolean checked = false;
    private Date releaseDate;
    private AlbumApplication albumApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.albumApplication = (AlbumApplication) getApplicationContext();

        //Getting album id that was given on creation of this activity
        Intent intent = getIntent();
        albumId = intent.getIntExtra("album id", 0);
        album = albumApplication.albumDataProvider.findAlbumById(albumId);

        //Finding fields and assigning to variables
        songName = findViewById(R.id.songName);
        songGenres = findViewById(R.id.songGenres);
        songArtist = findViewById(R.id.songArtist);
        songLength = findViewById(R.id.songLength);
        songExplicit = findViewById(R.id.songExplicit);
        songAddDateView = findViewById(R.id.songAddDateView);

        setTitle(R.string.song_add_title);

        //Initializing the calender utility and setting vars that represent today
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year, month, day);
    }

    //Method for adding song to album
    public void addSong(View view) {
        //Check if the "explicit" checkbox has been checked
        if (songExplicit.isChecked()) {
            checked = true;
        }
        //Creating song and adding to album
        song = new Song(songName.getText().toString(), songGenres.getText().toString(), songArtist.getText().toString(), this.releaseDate, Integer.parseInt(songLength.getText().toString()), checked);
        album.AddSongToAlbum(song);
        super.finish();
    }

    //On creation of a datepicker popup
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, R.style.DialogTheme, myDateListener, year, month, day);
        }
        return null;
    }

    //Method for placing the chosen date in a textfield and saving it in a var
    private void showDate(int year, int month, int day) {
        this.releaseDate = new Date(year, month, day);
        this.songAddDateView.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).append(" ").append(getResources().getString(R.string.datepicker_button_text)));
    }

    //Initializing the datepicker popup
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), R.string.datepicker_toast, Toast.LENGTH_SHORT).show();
    }

    //Datepicker confirm listener
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2, arg3);
        }
    };
}
