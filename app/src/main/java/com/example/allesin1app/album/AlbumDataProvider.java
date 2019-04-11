package com.example.allesin1app.album;

import java.util.ArrayList;

//Data provider that contains all the albums
public class AlbumDataProvider {
    private ArrayList<Album> albums;

    //Initialize array
    public AlbumDataProvider() {
        this.albums = new ArrayList<>();
    }

    //Finding specific album with given id
    public Album findAlbumById(int id) {
        for (Album album : albums) {
            if (album.getId() == id) {
                return album;
            }
        }
        return null;
    }

    //Finding specific album with given name
    public Album findAlbumByName(String name) {
        for (Album album : albums) {
            if (album.getName().equals(name)) {
                return album;
            }
        }
        return null;
    }

    public void addToAlbums(Album album) {
        albums.add(album);
    }

    public void deleteAlbum(Album album) {
        albums.remove(album);
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }
}
