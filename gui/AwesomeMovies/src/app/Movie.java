package app;

import java.util.Collections;
import java.util.List;

public class Movie {
    public final String title;
    public final String imdbPictureURL;
    public final int year;
    public final int rtAudienceScore;
    public final String rtPictureURL;
    public final String director;
    public final String country;
    private List<String> userTags;
    
    public Movie(String title, String imdbPictureURL, int year, int rtAudienceScore, String rtPictureURL, String director, String country){
        this.title = title;
        this.imdbPictureURL = imdbPictureURL;
        this.year = year;
        this.rtAudienceScore = rtAudienceScore;
        this.rtPictureURL = rtPictureURL;
        this.director = director;
        this.country = country;
        userTags = Collections.<String>emptyList();
    }
    
    public void setTags(List<String> tags){
        userTags = tags;
    }
    
    public List<String> getTags(){
        return userTags;
    }
}
