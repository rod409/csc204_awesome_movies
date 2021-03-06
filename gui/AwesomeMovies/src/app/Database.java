package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private static Connection con;
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String databaseUrl = "jdbc:mysql://localhost:3306/awesome_movies?useSSL=false";
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
    
    public static List<Movie> getTopMoviesByGenre(int k, String genre){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT M.title, M.imdbPictureURL, M.year, M.rtAudienceScore, M.rtPictureURL"
                + " FROM Movie AS M, Movie_Genre AS MG"
                + " WHERE MG.Genre LIKE ? AND MG.movieID = M.id"
                + " ORDER BY rtAudienceScore DESC LIMIT ?;";
        List<Movie> movies = new ArrayList<Movie>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + genre + "%");
            stmt.setInt(2, k);
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
        String sql = "SELECT M.title, M.imdbPictureURL, M.year, M.rtAudienceScore, M.rtPictureURL"
                + " FROM Movie AS M, Person AS P"
                + " WHERE M.directorId = P.id AND P.name LIKE ?"
                + " ORDER BY M.rtAudienceScore DESC;";
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
    
    public static List<Movie> getTopNMoviesByDirector(String director, int n){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT M.title, M.imdbPictureURL, M.year, M.rtAudienceScore, M.rtPictureURL"
                + " FROM Movie AS M, Person AS P"
                + " WHERE M.directorId = P.id AND P.name LIKE ?"
                + " ORDER BY M.rtAudienceScore DESC LIMIT ?;";
        List<Movie> movies = new ArrayList<Movie>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + director + "%");
            stmt.setInt(2, n);
            rs = stmt.executeQuery();
            movies = getMoviesFromResultSet(rs);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    
    public static List<Movie> getMoviesByActor(String actor){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT M.title, M.imdbPictureURL, M.year, M.rtAudienceScore, M.rtPictureURL"
                + " FROM Movie AS M, Person AS P, Movie_Actor AS MA"
                + " WHERE P.name LIKE ? AND P.id = MA.actorID AND M.id = MA.movieID"
                + " ORDER BY M.rtAudienceScore DESC;";
        List<Movie> movies = new ArrayList<Movie>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + actor + "%");
            rs = stmt.executeQuery();
            movies = getMoviesFromResultSet(rs);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    
    public static List<Movie> getMoviesByTag(String tag){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT M.title, M.imdbPictureURL, M.year, M.rtAudienceScore, M.rtPictureURL"
                + " FROM Movie as M, Tag as T, Movie_Tag as MT"
                + " WHERE T.value LIKE ? AND MT.movieID = M.id AND MT.tagID = T.id"
                + " ORDER BY M.rtAudienceScore DESC;";
        List<Movie> movies = new ArrayList<Movie>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + tag + "%");
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
        if(!movies.isEmpty()){
            m.setTags(tags);
            movies.add(m);
        }
        return movies;
    }
    
    public static List<PersonRanking> getTopDirectors(int movieLimit, int minimumMovies){
        String sql = "SELECT P.name, AVG(M.rtAudienceScore) as Rating"
                + " FROM Person as P, Movie as M"
                + " WHERE M.directorID = P.id"
                + " GROUP BY P.name"
                + " Having COUNT(*) >= ?"
                + " ORDER BY Rating DESC LIMIT ?";
        List<PersonRanking> rankings = getTopPeople(sql, minimumMovies, movieLimit);
        return rankings;
    }
    
    public static List<PersonRanking> getTopActors(int movieLimit, int minimumMovies){
        String sql = "SELECT p.name, actsco.Rating"
                + " FROM actor_score as actsco, Person as P"
                + " WHERE actsco.Movie_Count >= ? AND actsco.id = P.id"
                + " LIMIT ?;";
        List<PersonRanking> rankings = getTopPeople(sql, minimumMovies, movieLimit);
        return rankings;
    }
    
    private static List<PersonRanking> getTopPeople(String sql, int minimumMovies, int movieLimit){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<PersonRanking> rankings = new ArrayList<PersonRanking>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, minimumMovies);
            stmt.setInt(2, movieLimit);
            rs = stmt.executeQuery();
            rankings = getRankingFromResultSet(rs);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rankings;
    }
    
    private static List<PersonRanking> getRankingFromResultSet(ResultSet rs) throws SQLException{
        List<PersonRanking> rankings = new ArrayList<PersonRanking>();
        while(rs.next()){
            PersonRanking p = new PersonRanking(rs.getString("name"), rs.getDouble("Rating"));
            rankings.add(p);
        }
        return rankings;
    }
    
    public static List<UserRatedMovie> getUserRatedMovies(int userID){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT M.id, M.title, MG.Genre, URM.timestamp, URM.rating"
                + " FROM Movie as M, User_Rated_Movie as URM, Movie_Genre as MG"
                + " WHERE URM.userID = ? AND URM.movieID = M.id AND M.id = MG.movieID"
                + " ORDER BY URM.timestamp DESC, M.id";
        List<UserRatedMovie> ratings = new ArrayList<UserRatedMovie>();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userID);
            rs = stmt.executeQuery();
            ratings = getUserRatedMovieFromResultSet(rs);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratings;
    }
    
    private static List<UserRatedMovie> getUserRatedMovieFromResultSet (ResultSet rs) throws SQLException{
        List<UserRatedMovie> ratings = new ArrayList<UserRatedMovie>();
        UserRatedMovie urm = null;
        int previousID = 0;
        boolean first = true;
        List<String> genres = Collections.<String>emptyList();
        while(rs.next()){
            int currentID = rs.getInt("id");
            if(first){
                first = false;
                previousID = currentID;
                urm = new UserRatedMovie(rs.getString("title"), rs.getTimestamp("timestamp"), rs.getDouble("rating"));
                genres = new ArrayList<String>();
            }
            if(currentID != previousID){
                previousID = currentID;
                urm.setGenres(genres);
                ratings.add(urm);
                genres = new ArrayList<String>();
                urm = new UserRatedMovie(rs.getString("title"), rs.getTimestamp("timestamp"), rs.getDouble("rating"));
            }
            genres.add(rs.getString("Genre"));
        }
        //add last urm to list
        if(urm != null){
            urm.setGenres(genres);
            ratings.add(urm);
        }
        return ratings;
    }
    
    public static Map<String, List<Movie>> getRecommendationByGenre(List<String> movieTitles){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, List<Movie>> topMoviesByGenre = new HashMap<String, List<Movie>>();
        String sql = "SELECT DISTINCT MG.Genre"
                + " FROM Movie_Genre as MG, Movie as M"
                + " WHERE MG.movieID = M.id AND (" + likeOrChain("M.title", movieTitles.size()) + ");";
         
        try {
            stmt = con.prepareStatement(sql);
            int i = 1;
            for(String title : movieTitles){
                stmt.setString(i, "%" + title + "%");
                ++i;
            }
            rs = stmt.executeQuery();
            List<String> genres = new ArrayList<String>();
            while(rs.next()){
                topMoviesByGenre.put(rs.getString("Genre"), new ArrayList<Movie>());
                genres.add(rs.getString("Genre"));
            }
            sql = "";
            String queryByGenre = "(SELECT M.title, M.imdbPictureURL, M.year, M.rtAudienceScore, M.rtPictureURL, MG.Genre"
                    + " FROM Movie AS M, Movie_Genre AS MG"
                    + " WHERE MG.Genre LIKE ? AND MG.movieID = M.id"
                    + " ORDER BY rtAudienceScore DESC LIMIT 5)";
            for(int k = 0; k < genres.size()-1; ++k){
                sql += queryByGenre + " UNION ";
            }
            if(!genres.isEmpty()){
                sql += queryByGenre;
            }
            stmt = con.prepareStatement(sql);
            i = 1;
            for(String genre : genres){
                stmt.setString(i, "%" + genre + "%");
                ++i;
            }
            rs = stmt.executeQuery();
            while(rs.next()){
                Movie m = new Movie(rs.getString("title"), rs.getString("imdbPictureURL"), rs.getInt("year"), rs.getInt("rtAudienceScore"), rs.getString("rtPictureURL"), "", "");
                topMoviesByGenre.get(rs.getString("Genre")).add(m);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topMoviesByGenre;
    }
    
    public static List<Movie> getRecommendationByDirector(List<String> movieTitles){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT M.title, M.imdbPictureURL, M.year, M.rtAudienceScore, M.rtPictureURL"
                + " FROM Movie AS M"
                + " WHERE M.directorID IN ("
                + " SELECT mov.directorID"
                + " FROM Movie as Mov"
                + " WHERE " + likeOrChain("Mov.title", movieTitles.size()) + ")"
                + " GROUP BY M.title"
                + " ORDER BY rtAudienceScore DESC"
                + " LIMIT 5;";
            
        List<Movie> movies = new ArrayList<Movie>();
        try {
            stmt = con.prepareStatement(sql);
            int i = 1;
            for(String title : movieTitles){
                stmt.setString(i, "%" + title + "%");
                ++i;
            }
            rs = stmt.executeQuery();
            movies = getMoviesFromResultSet(rs);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    
    public static List<Movie> getRecommendationByActor(List<String> movieTitles){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT M.title, M.imdbPictureURL, M.year, M.rtAudienceScore, M.rtPictureURL"
                + " FROM Movie AS M, Movie_Actor as MA"
                + " WHERE M.id = MA.movieID AND MA.actorID IN ("
                + " SELECT DISTINCT MAct.actorID"
                + " FROM Movie as Mov, Movie_Actor as MAct"
                + " WHERE (MAct.ranking = 1 OR MAct.ranking = 2) AND MAct.movieID = Mov.id AND (" + likeOrChain("Mov.title", movieTitles.size()) + "))"
                + " GROUP BY M.title"
                + " ORDER BY rtAudienceScore DESC"
                + " LIMIT 5;";
            
        List<Movie> movies = new ArrayList<Movie>();
        try {
            stmt = con.prepareStatement(sql);
            int i = 1;
            for(String title : movieTitles){
                stmt.setString(i, "%" + title + "%");
                ++i;
            }
            rs = stmt.executeQuery();
            movies = getMoviesFromResultSet(rs);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
    
    private static String likeOrChain(String columnName, int length){
        String result = "";
        for(int i = 0; i < length - 1; ++i){
            result += columnName + " LIKE ? OR ";
        }
        result += columnName + " LIKE ?";
        return result;
    }
}
