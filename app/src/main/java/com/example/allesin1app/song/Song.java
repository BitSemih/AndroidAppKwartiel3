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

    public String getGenres() {
        return genres;
    }

    public String getArtist() {
        return artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getLength() {
        return length;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
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
