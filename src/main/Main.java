package main;

import java.util.ArrayList;

import db.DatabaseManager;
import model.Album;
import model.Artist;
import model.Playlist;
import model.Song;
import service.MusicPlayer;

// Entry point of the music application.
// Loads sample data, creates playlists, and demonstrates
// the functionality of the MusicPlayer system.

public class Main {
    // Application entry point. Initializes the database,
    // creates a music player, demonstrates playback controls,
    // and displays search/filter features.

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        ArrayList<Song> songLibrary = databaseManager.loadSongs();

        MusicPlayer player = new MusicPlayer("CAM Music Player");
        player.loadLibrary(songLibrary);

        System.out.println("Database: " + DatabaseManager.getDatabaseName());
        System.out.println("Songs loaded: " + songLibrary.size());

        System.out.println("\nAll Songs in Library:");
        for (Song song : songLibrary) {
            song.displayInfo();
        }

        Playlist studyPlaylist = new Playlist(1, "Study Playlist");

        if (songLibrary.size() >= 3) {
            studyPlaylist.addSong(songLibrary.get(0));
            studyPlaylist.addSong(songLibrary.get(1));
            studyPlaylist.addSong(songLibrary.get(2));
        }

        studyPlaylist.displayInfo();

        player.addPlaylistToQueue(studyPlaylist);
        player.displayQueue();

        player.play();
        player.skipToNext();
        player.skipToNext();
        player.skipToPrevious();

        player.pause();
        player.setVolume(80);

        System.out.println("\nSearch by title:");
        Song foundSong = player.searchSongByTitle("Coffee Time");

        if (foundSong != null) {
            foundSong.displayInfo();
        } else {
            System.out.println("Song not found.");
        }

        System.out.println("\nFilter by genre: Pop");
        ArrayList<Song> popSongs = player.filterSongsByGenre("Pop");

        if (popSongs.isEmpty()) {
            System.out.println("No songs found for this genre.");
        } else {
            for (Song song : popSongs) {
                song.displayInfo();
            }
        }

        System.out.println("\nSearch by artist: Dara Music");
        ArrayList<Song> artistSongs = player.searchSongsByArtist("Dara Music");

        for (Song song : artistSongs) {
            song.displayInfo();
        }

        player.displayHistory();
        player.displayInfo();

        System.out.println("\nStatic counters vs collection size:");
        System.out.println("Song.getSongCount(): " + Song.getSongCount());
        System.out.println("songLibrary.size(): " + songLibrary.size());

        System.out.println("Artist.getArtistCount(): " + Artist.getArtistCount());
        System.out.println("Album.getAlbumCount(): " + Album.getAlbumCount());
        System.out.println("Playlist.getPlaylistCount(): " + Playlist.getPlaylistCount());

        System.out.println("player.getLibrarySize(): " + player.getLibrarySize());
        System.out.println("player.getQueueSize(): " + player.getQueueSize());
        System.out.println("player.getHistorySize(): " + player.getHistorySize());
    }
}
