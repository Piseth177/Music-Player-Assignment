-- Spotify Clone MySQL Schema
-- Run this entire script in MySQL Workbench to set up your tables!

CREATE DATABASE IF NOT EXISTS spotifycopy;
USE spotifycopy;

-- 1. Artists Table
CREATE TABLE IF NOT EXISTS artists (
    artist_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    bio TEXT
);

-- 2. Albums Table
CREATE TABLE IF NOT EXISTS albums (
    album_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    record_label VARCHAR(255),
    release_year INT,
    artist_id INT,
    FOREIGN KEY (artist_id) REFERENCES artists(artist_id)
);

-- 3. Songs Table
-- A song belongs to one artist and one album.
CREATE TABLE IF NOT EXISTS songs (
    song_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    duration_seconds INT,
    release_year INT,
    is_explicit BOOLEAN,
    artist_id INT,
    album_id INT,
    FOREIGN KEY (artist_id) REFERENCES artists(artist_id),
    FOREIGN KEY (album_id) REFERENCES albums(album_id)
);

-- 4. Playlists Table
CREATE TABLE IF NOT EXISTS playlists (
    playlist_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    follower_count INT DEFAULT 0,
    is_public BOOLEAN DEFAULT TRUE
);

-- 5. Playlist_Songs Join Table
-- Because one playlist can have many songs, AND one song can be in many playlists,
-- we need a "Join Table" to connect them together!
CREATE TABLE IF NOT EXISTS playlist_songs (
    playlist_id INT,
    song_id INT,
    PRIMARY KEY (playlist_id, song_id),
    FOREIGN KEY (playlist_id) REFERENCES playlists(playlist_id),
    FOREIGN KEY (song_id) REFERENCES songs(song_id)
);

-- Let's insert some dummy data so your database isn't empty!
INSERT INTO artists (name, genre, bio) VALUES ('The Weeknd', 'R&B/Pop', 'Canadian singer');
INSERT INTO artists (name, genre, bio) VALUES ('Dua Lipa', 'Pop', 'English/Albanian singer');

-- Assuming The Weeknd is ID 1 and Dua Lipa is ID 2
INSERT INTO albums (title, record_label, release_year, artist_id) VALUES ('After Hours', 'Republic', 2020, 1);
INSERT INTO albums (title, record_label, release_year, artist_id) VALUES ('Future Nostalgia', 'Warner', 2020, 2);

-- Assuming Albums are 1 and 2
INSERT INTO songs (title, genre, duration_seconds, release_year, is_explicit, artist_id, album_id) 
VALUES ('Blinding Lights', 'Synth-pop', 200, 2019, FALSE, 1, 1);
INSERT INTO songs (title, genre, duration_seconds, release_year, is_explicit, artist_id, album_id) 
VALUES ('Levitating', 'Dance-pop', 203, 2020, FALSE, 2, 2);
