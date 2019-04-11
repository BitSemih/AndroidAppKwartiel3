package com.example.allesin1app.album;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allesin1app.AlbumApplication;
import com.example.allesin1app.R;

import java.util.Calendar;
import java.util.Date;

//Activity for adding album
public class AlbumAddActivity extends AppCompatActivity {
    private EditText albumName;
    private Album album;
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

        //Finding views
        this.albumName = findViewById(R.id.addAlbumName);
        this.albumDateView = findViewById(R.id.albumDateView);

        //Initializing the calender utility and setting vars that represent today
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year, month, day);
    }

    //Creating album and adding to data provider
    public void addAlbum(View view) {
        album = new Album(this.albumName.getText().toString(), this.releaseDate);
        ((AlbumApplication) getApplicationContext()).albumDataProvider.addToAlbums(album);
        finish();
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
        this.albumDateView.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).append(R.string.datepicker_button_text));
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

