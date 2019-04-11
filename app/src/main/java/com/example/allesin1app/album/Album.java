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

    public Album(String albumName, Date albumReleaseDate){
        songs = new ArrayList<>();
        lastid++;
        this.id = lastid;
        this.albumName = albumName;
        this.albumReleaseDate = albumReleaseDate;
    }

    public void addSongToAlbum(Song song){
        songs.add(song);
    }

    public ArrayList<Song> getAlbumSongs(){
        return songs;
    }

    public int getSongCount(){
        return songs.size();
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

    public void deleteSong(Song song){
        songs.remove(song);
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
