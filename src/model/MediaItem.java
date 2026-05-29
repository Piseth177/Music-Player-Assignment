package model;

// Base class for all media items in the music system.
// Provides common fields (id, title) and basic accessors.

public class MediaItem {
    protected int id;
    protected String title;

    // Constructs a MediaItem with the given ID and title.

    public MediaItem(int id, String title) {
        this.id = id;
        setTitle(title);
    }

    // Returns the unique identifier of this media item.

    public int getId() {
        return id;
    }

    // Returns the title of this media item.

    public String getTitle() {
        return title;
    }

    // Sets the title of this media item. Silently ignores null or blank values.

    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title.trim();
        }
    }

    // Displays basic info about this media item.
    // Subclasses should override this to show their own extra details.

    public void displayInfo() {
        System.out.println("ID: " + id + " | Title: " + title);
    }
}
