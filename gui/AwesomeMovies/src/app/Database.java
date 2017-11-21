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
    private static String user = "root";
    private static String password = "root";
    
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
            while(rs.next()){
                Movie m = new Movie(rs.getString("title"), rs.getString("imdbPictureURL"), rs.getInt("year"), rs.getInt("rtAudienceScore"), rs.getString("rtPictureURL"), "", "");
                movies.add(m);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
