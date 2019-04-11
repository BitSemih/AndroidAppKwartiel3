package com.example.allesin1app;

import android.app.Application;

import com.example.allesin1app.album.Album;
import com.example.allesin1app.album.AlbumDataProvider;
import com.example.allesin1app.song.Song;

import java.util.Date;

// Rename to ~album application
// Geen afkortingen van "adp" maken gwn voluit
public class GlobalVars extends Application {
    public AlbumDataProvider albumDataProvider = new AlbumDataProvider();

    public GlobalVars() {
        //Data added to the app on startup

        //Create album
        Album album1 = new Album("Coole album", new Date(1999, 4, 20));
        Album album2 = new Album("Mooie album", new Date(1999, 4, 20));
        Album album3 = new Album("Zieke album", new Date(1999, 4, 20));
        Album album4 = new Album("Gaafe album", new Date(1999, 4, 20));
        Album album5 = new Album("Super coole album", new Date(1999, 4, 20));

        //Create songs
        Song song1 = new Song("Cool liedje", "Hip-hop", "Henk", new Date(1999, 4, 20), 300, true);
        Song song2 = new Song("Coolere liedje", "Hardcore", "Gekkie", new Date(1999, 4, 20), 300, false);
        Song song3 = new Song("Coolste liedje", "Hip-hop", "Henk", new Date(1999, 4, 20), 300, true);
        Song song4 = new Song("Cool liedje", "Pop", "Henk1", new Date(1999, 4, 20), 300, true);
        Song song5 = new Song("Super Cool liedje", "Frenchcore", "Semih", new Date(1999, 4, 20), 300, false);
        Song song6 = new Song("Cool liedje", "Hip-hop", "Henk3", new Date(1999, 4, 20), 300, true);
        Song song7 = new Song("Geen cool liedje", "Hip-hop", "Henk434", new Date(1999, 4, 20), 300, false);
        Song song8 = new Song("Cool liedje", "Pop", "Henk234", new Date(1999, 4, 20), 300, false);
        Song song9 = new Song("Nog coolere liedje", "Hardcore", "Henk234", new Date(1999, 4, 20), 300, true);
        Song song10 = new Song("Vlieg liedje", "Hip-hop", "Henk234", new Date(1999, 4, 20), 300, false);
        Song song11 = new Song("Cool liedje", "Klassiek", "Henk553", new Date(1999, 4, 20), 300, true);
        Song song12 = new Song("Cool liedje", "Hip-hop", "Henk5334", new Date(1999, 4, 20), 300, true);

        //Add to albums
        album1.addSongToAlbum(song1);
        album1.addSongToAlbum(song6);
        album1.addSongToAlbum(song3);
        album1.addSongToAlbum(song4);
        album1.addSongToAlbum(song9);
        album1.addSongToAlbum(song12);

        album2.addSongToAlbum(song3);
        album2.addSongToAlbum(song5);
        album2.addSongToAlbum(song11);

        album3.addSongToAlbum(song8);
        album3.addSongToAlbum(song7);
        album3.addSongToAlbum(song5);
        album3.addSongToAlbum(song4);

        album4.addSongToAlbum(song2);
        album4.addSongToAlbum(song10);

        album5.addSongToAlbum(song9);
        album5.addSongToAlbum(song3);
        album5.addSongToAlbum(song2);
        album5.addSongToAlbum(song8);
    }
}
