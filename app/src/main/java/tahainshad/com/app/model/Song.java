package tahainshad.com.app.model;

/**
 * Created by Saeed on 21/03/2017.
 */

public class Song {
    private int    id;
    private int    singer_id;
    private String name;
    private String url;
    private String lyrics;
    private int    music;
    private int    visits;


    public Song(int id, int singer_id, String name, String url, String lyrics, int music, int visits) {
        this.id = id;
        this.singer_id = singer_id;
        this.name = name;
        this.url = url;
        this.lyrics = lyrics;
        this.music = music;
        this.visits = visits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(int singer_id) {
        this.singer_id = singer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", singer_id=" + singer_id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", lyrics='" + lyrics + '\'' +
                ", music=" + music +
                ", visits=" + visits +
                '}';
    }
}

