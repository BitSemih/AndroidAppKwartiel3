package com.example.allesin1app.album;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allesin1app.AlbumApplication;
import com.example.allesin1app.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlbumAddActivity extends AppCompatActivity {

    private EditText albumName;
    private Album album;
    private Calendar calendar;
    private TextView albumDateView;
    private int year, month, day;
    private Date releaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.albumName = findViewById(R.id.addAlbumName);
        this.albumDateView = findViewById(R.id.albumDateView);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year, month, day);
        //DatePickerDialog.OnDateSetListener(View->onSetDate);
    }

    public void addAlbum(View view) {
        album = new Album(this.albumName.getText().toString(), this.releaseDate);
        ((AlbumApplication) getApplicationContext()).albumDataProvider.addToAlbums(album);
        finish();
    }

    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, R.style.DialogTheme, myDateListener, year, month, day);
        }
        return null;
    }

    private void showDate(int year, int month, int day) {
        this.releaseDate = new Date(year, month, day);
        this.albumDateView.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).append(" - Klik om het te veranderen"));
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

