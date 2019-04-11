package com.example.allesin1app.album;

import com.example.allesin1app.song.Song;

import java.util.ArrayList;
import java.util.Date;

public class Album {
    private static int lastid;
    private int id;
    private ArrayList<Song> songs;
    private String albumName;
    private Date albumReleaseDate;

    //On creation of album
    public Album(String albumName, Date albumReleaseDate){
        songs = new ArrayList<>();
        lastid++;
        this.id = lastid;
        this.albumName = albumName;
        this.albumReleaseDate = albumReleaseDate;
    }

    //Find specific song by given id
    public Song findSongById(int id){
        for (Song song : songs){
            if(song.getId() == (id)){
                return song;
            }
        }
        return null;
    }

    //Finding specific song by given name
    public Song findSongByName(String name){
        for (Song song : songs){
            if(song.getName().equals(name)){
                return song;
            }
        }
        return null;
    }

    public void deleteSong(Song song){
        songs.remove(song);
    }

    //Getters
    public int getSongCount(){
        return songs.size();
    }

    public ArrayList<Song> getAlbumSongs(){
        return songs;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return albumName;
    }

    public void AddSongToAlbum(Song song) {
        songs.add(song);
    }
}
