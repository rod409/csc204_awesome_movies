package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public static List<Movie> getMoviesByDirector(String director){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT title, imdbPictureURL, `year`, rtAudienceScore, rtPictureURL"
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
}
