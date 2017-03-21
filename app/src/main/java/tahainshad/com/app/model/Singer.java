package tahainshad.com.app.model;

/**
 * Created by Saeed on 21/03/2017.
 */

public class Singer {
    private String country;
    private int    id;
    private String singer_name;
    private String image;
    private int    visits;
    private int    cnt;

    public Singer(String country, int id, String singer_name, String image, int visits, int cnt) {
        this.country = country;
        this.id = id;
        this.singer_name = singer_name;
        this.image = image;
        this.visits = visits;
        this.cnt = cnt;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSinger_name() {
        return singer_name;
    }

    public void setSinger_name(String singer_name) {
        this.singer_name = singer_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "country='" + country + '\'' +
                ", id=" + id +
                ", singer_name='" + singer_name + '\'' +
                ", image='" + image + '\'' +
                ", visits=" + visits +
                ", cnt=" + cnt +
                '}';
    }
}

