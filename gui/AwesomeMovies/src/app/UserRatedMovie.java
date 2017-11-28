package app;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

public class UserRatedMovie {
    public final String title;
    public final Timestamp timestamp;
    public final double rating;
    public List<String> genres;
    
    public UserRatedMovie(String title, Timestamp timestamp, double rating){
        this.title = title;
        this.timestamp = timestamp;
        this.rating = rating;
        genres = Collections.<String>emptyList();
    }
    
    public void setGenres(List<String> genres){
        this.genres = genres;
    }
    
    public List<String> getGenres(){
        return genres;
    }
}
