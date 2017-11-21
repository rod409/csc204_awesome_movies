package app;

public class Movie {
    public String title;
    public String imdbPictureURL;
    public int year;
    public int rtAudienceScore;
    public String rtPictureURL;
    public String director;
    public String country;
    
    public Movie(String title, String imdbPictureURL, int year, int rtAudienceScore, String rtPictureURL, String director, String country){
        this.title = title;
        this.imdbPictureURL = imdbPictureURL;
        this.year = year;
        this.rtAudienceScore = rtAudienceScore;
        this.rtPictureURL = rtPictureURL;
        this.director = director;
        this.country = country;
    }
}
