package com.example.allesin1app.song;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.util.Date;

//Activity for editing a song
public class SongEditActivity extends AppCompatActivity {
    private int songId, albumId;
    private AlbumApplication albumApplication;
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

        this.albumApplication = (AlbumApplication) getApplicationContext();

        //Getting album id and song id that were given on creation of this activity
        Intent intent = getIntent();
        this.songId = intent.getIntExtra("song id", 0);
        this.albumId = intent.getIntExtra("album id", 0);

        //Searching for album and song in that album
        album = albumApplication.albumDataProvider.findAlbumById(albumId);
        this.song = album.findSongById(songId);
        //Setting the title of the activity
        setTitle(song.getName() + " " + getResources().getString(R.string.song_edit_subtitle));

        //Finding fields and assigning to variables
        songName = findViewById(R.id.songName);
        songGenres = findViewById(R.id.songGenres);
        songArtist = findViewById(R.id.songArtist);
        songEditDateView = findViewById(R.id.songEditDateView);
        songLength = findViewById(R.id.songLength);
        songExplicit = findViewById(R.id.songExplicit);

        //Check if song is found; so if you came properly from an song overview activity
        if (song != null) {
            date = song.getReleaseDate();
            songName.setText(song.getName());
            songGenres.setText(song.getGenres());
            songArtist.setText(song.getArtist());
            showDate(song.getReleaseDate().getYear(), song.getReleaseDate().getMonth(), song.getReleaseDate().getDay());
            songLength.setText(String.valueOf(song.getLength()));
            songExplicit.setChecked(song.isExplicit());
        }
    }

    //On creation of a datepicker popup
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            //Initializing datepicker popup
            return new DatePickerDialog(this, R.style.DialogTheme, myDateListener, song.getReleaseDate().getYear(), song.getReleaseDate().getMonth(), song.getReleaseDate().getDay());
        }
        return null;
    }

    //Method for placing the chosen date in a textfield and saving it in a var
    private void showDate(int year, int month, int day) {
        this.newReleaseDate = new Date(year, month, day);
        this.songEditDateView.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).append(" ").append(getResources().getString(R.string.datepicker_button_text)));
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

    //Method for saving changes made by the user
    public void saveSongChanges(View view) {
        song.setSongName(songName.getText().toString());
        song.setGenres(songGenres.getText().toString());
        song.setArtist(songArtist.getText().toString());
        song.setReleaseDate(newReleaseDate);
        song.setLength(Integer.parseInt(songLength.getText().toString()));
        song.setExplicit(songExplicit.isChecked());
        finish();
    }
}
