package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {
    private static Connection con;
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String databaseUrl = "jdbc:mysql://localhost:3306/awesome_movies?useSSL=false";
    //change to appropriate user and password
    private static String user = "user";
    private static String password = "password1234";
    
    public static void connect(){
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(databaseUrl, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Movie> getTopMovies(int k){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT title, imdbPictureURL, `year`, rtAudienceScore, rtPictureURL FROM Movie ORDER BY rtAudienceScore DESC LIMIT ?;";
        List<Movie> movies = new ArrayList<Movie>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, k);
            rs = stmt.executeQuery();
            movies = getMoviesFromResultSet(rs);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    
    public static List<Movie> getMoviesByTitle(String title){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT M.id, M.title, M.imdbPictureURL, M.year, M.rtAudienceScore, M.rtPictureURL, T.value"
                + " FROM Movie AS M, Tag AS T, Movie_Tag as MT"
                + " WHERE M.title LIKE ? AND MT.movieID = M.id AND MT.tagID = T.id"
                + " ORDER BY rtAudienceScore DESC;";
        List<Movie> movies = new ArrayList<Movie>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + title + "%");
            rs = stmt.executeQuery();
            movies = getMovieTagsFromResultSet(rs);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    
    public static List<Movie> getMoviesByDirector(String director){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT M.title, M.imdbPictureURL, M.year, M.rtAudienceScore, M.rtPictureURL"
                + " FROM Movie AS M, Person AS P"
                + " WHERE M.directorId = P.id AND P.name LIKE ?"
                + " ORDER BY rtAudienceScore DESC;";
        List<Movie> movies = new ArrayList<Movie>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + director + "%");
            rs = stmt.executeQuery();
            movies = getMoviesFromResultSet(rs);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    
    private static List<Movie> getMoviesFromResultSet(ResultSet rs) throws SQLException{
        List<Movie> movies = new ArrayList<Movie>();
        while(rs.next()){
            Movie m = new Movie(rs.getString("title"), rs.getString("imdbPictureURL"), rs.getInt("year"), rs.getInt("rtAudienceScore"), rs.getString("rtPictureURL"), "", "");
            movies.add(m);
        }
        return movies;
    }
    
    private static List<Movie> getMovieTagsFromResultSet(ResultSet rs) throws SQLException{
        List<Movie> movies = new ArrayList<Movie>();
        Movie m = null;
        int previousID = 0;
        boolean first = true;
        List<String> tags = Collections.<String>emptyList();
        while(rs.next()){
            int currentID = rs.getInt("id");
            if(first){
                first = false;
                previousID = currentID;
                m = new Movie(rs.getString("title"), rs.getString("imdbPictureURL"), rs.getInt("year"), rs.getInt("rtAudienceScore"), rs.getString("rtPictureURL"), "", "");
                tags = new ArrayList<String>();
            }
            if(currentID != previousID){
                previousID = currentID;
                m.setTags(tags);
                movies.add(m);
                tags = new ArrayList<String>();
                m = new Movie(rs.getString("title"), rs.getString("imdbPictureURL"), rs.getInt("year"), rs.getInt("rtAudienceScore"), rs.getString("rtPictureURL"), "", "");
            }
            tags.add(rs.getString("value"));
        }
        //add last movie to list
        if(movies != null){
            m.setTags(tags);
            movies.add(m);
        }
        return movies;
    }
}
