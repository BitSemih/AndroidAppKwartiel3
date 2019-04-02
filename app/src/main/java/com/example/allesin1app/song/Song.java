package com.example.allesin1app.song;

public class Song {

    private String songName, genres, artist, releaseDate;
    private int length;
    private boolean explicit;
    private static int lastId;
    private int id;

    public Song(String songName, String genres, String artist, String releaseDate, int length, boolean explicit) {
        lastId++;
        this.id = lastId;
        this.songName = songName;
        this.genres = genres;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.length = length;
        this.explicit = explicit;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return songName;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songName='" + songName + '\'' +
                ", genres='" + genres + '\'' +
                ", artist='" + artist + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", length=" + length +
                '}';
    }
}
