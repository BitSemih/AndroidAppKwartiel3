package com.example.allesin1app.compound;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.allesin1app.R;

public class SongCompound extends LinearLayout {

    private Context context;

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

    private void initializeView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.compound_song_view, this);
    }


}
