package model;

import interfaces.Displayable;

public class Song extends MediaItem implements Displayable {
    private Artist artist;
    private Album album;
    private String genre;
    private int durationInSeconds;

    private static int songCount = 0;

    public Song(int songId, String title, Artist artist, Album album, String genre, int durationInSeconds) {
        super(validateId(songId), cleanText(title, "Unknown Song"));
        this.artist = artist;
        this.album = album;
        this.genre = cleanText(genre, "Unknown Genre");
        setDurationInSeconds(durationInSeconds);
        songCount++;
    }

    private static int validateId(int id) {
        if (id > 0) {
            return id;
        }
        return 0;
    }

    private static String cleanText(String value, String defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        return value.trim();
    }

    // Returns the artist of this song.

    public Artist getArtist() {
        return artist;
    }

    // Returns the album this song belongs to.

    public Album getAlbum() {
        return album;
    }

    // Returns the genre of this song.

    public String getGenre() {
        return genre;
    }

    // Returns the duration of this song in seconds.

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    // Sets the artist for this song.

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    // Sets the album for this song.

    public void setAlbum(Album album) {
        this.album = album;
    }

    // Sets the genre of this song. Silently ignores null or blank values.

    public void setGenre(String genre) {
        if (genre != null && !genre.trim().isEmpty()) {
            this.genre = genre.trim();
        }
    }

    // Sets the duration of this song in seconds. If the value is not positive,
    // defaults to 0 and prints an error message.

    public void setDurationInSeconds(int durationInSeconds) {
        if (durationInSeconds > 0) {
            this.durationInSeconds = durationInSeconds;
        } else {
            System.err.println( "Invalid duration provided for song '" + getTitle() + "'. Setting duration to 0.");
            this.durationInSeconds = 0;
        }
    }

    // Returns the duration formatted as mm:ss (e.g. "3:45").

    public String getFormattedDuration() {
        int minutes = durationInSeconds / 60;
        int seconds = durationInSeconds % 60;

        if (seconds < 10) {
            return minutes + ":0" + seconds;
        }

        return minutes + ":" + seconds;
    }



    @Override
    public void displayInfo() {
        String artistName = "Unknown Artist";
        String albumTitle = "Unknown Album";

        if (artist != null) {
            artistName = artist.getName();
        }

        if (album != null) {
            albumTitle = album.getTitle();
        }

        super.displayInfo(); // prints: ID and Title from MediaItem
        System.out.println("  Artist: " + artistName);
        System.out.println("  Album: " + albumTitle);
        System.out.println("  Genre: " + genre);
        System.out.println("  Duration: " + getFormattedDuration());
    }

    // Returns the total number of Song instances created.

    public static int getSongCount() {
        return songCount;
    }
}
