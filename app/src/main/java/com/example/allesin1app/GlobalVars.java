package com.example.allesin1app;

import android.app.Application;

import com.example.allesin1app.album.AlbumDataProvider;

public class GlobalVars extends Application {
    public AlbumDataProvider adp = new AlbumDataProvider();
}
