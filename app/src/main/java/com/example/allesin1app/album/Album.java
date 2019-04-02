package com.example.allesin1app.album;

import com.example.allesin1app.song.Song;

import java.util.ArrayList;

public class Album {
    private static int lastid;
    private int id;
    private ArrayList<Song> songs;
    private String albumName, albumReleaseDate;

    public Album(String albumName, String albumReleaseDate){
        songs = new ArrayList<>();
        lastid++;
        this.id = lastid;
        this.albumName = albumName;
        this.albumReleaseDate = albumReleaseDate;
    }

    public void AddSongToAlbum(Song song){
        songs.add(song);
    }

    public ArrayList<Song> getAlbumSongs(){
        return songs;
    }

    public Song findSongById(int id){
        for (Song song : songs){
            if(song.getId() == (id)){
                return song;
            }
        }
        return null;
    }

    public Song findSongByName(String name){
        for (Song song : songs){
            if(song.getName().equals(name)){
                return song;
            }
        }
        return null;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return albumName;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", songs=" + songs +
                ", albumName='" + albumName + '\'' +
                ", albumReleaseDate='" + albumReleaseDate + '\'' +
                '}';
    }
}
