package jiang.henry.myapplication;

public class Film extends SWObject {

    private String title;

    private String director;

    private String producer;

    private String releaseDate;

    public Film(String title, String director, String producer, String releaseDate) {
        this.title = title;
        this.director = director;
        this.producer = producer;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
