package com.example.allesin1app;

import android.app.Application;

import com.example.allesin1app.album.AlbumDataProvider;

// Rename to ~album application
// Geen afkortingen van "adp" maken gwn voluit
public class GlobalVars extends Application {
    public AlbumDataProvider adp = new AlbumDataProvider();

    public GlobalVars() {
        //new album bla bla song

    }
}
