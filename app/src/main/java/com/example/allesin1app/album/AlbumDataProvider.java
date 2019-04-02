package com.example.allesin1app.album;

import com.example.allesin1app.GlobalVars;

import java.util.ArrayList;

public class AlbumDataProvider {
    private ArrayList<Album> albums;

    public AlbumDataProvider(){
        this.albums = new ArrayList<>();
    }

    public Album findAlbumById(int id){
        for (Album album : albums){
            if(album.getId() == id){
                return album;
            }
        }
        return null;
    }

    public Album findAlbumByName(String name){
        for (Album album : albums){
            if(album.getName().equals(name)){
                return album;
            }
        }
        return null;
    }


    public void addToAlbums(Album album){
        albums.add(album);
    }

    public ArrayList<Album> getAlbums(){
        return albums;
    }
}
