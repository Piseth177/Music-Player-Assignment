package interfaces;

import java.util.ArrayList;
import model.Song;

// Defines data loading capabilities for the music system.

public interface Loadable {
    // Loads all songs from the data source.

    ArrayList<Song> loadSongs();
}
