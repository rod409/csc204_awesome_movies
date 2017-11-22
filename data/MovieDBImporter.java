import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
//import java.util.Enumeration;

public final class MovieDBImporter
{
	private static final String DATASETDIRECTORY = "D:\\0\\Documents\\studies\\C\\Rotten Tomatos Dataset";
	private static final String URL = "mysql://localhost:3306/movies";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	public static void main(String[] args)
	{

		long start = System.currentTimeMillis();
		Connection con = null;
		try
		{
			System.out.println("running");
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}

			con = (Connection) DriverManager.getConnection("jdbc:" + URL + "?useSSL=false", USERNAME, PASSWORD);
			OutputTable table;

			table = new OutputTable("Person");
			table.AddColumn("movie_directors.dat", "directorID", "id", DataType.String);
			table.AddColumn("movie_directors.dat", "directorName", "name", DataType.String);
			table.ImportData(con, true);

			table = new OutputTable("Movie");
			table.AddColumn("movies.dat", "id", "id", DataType.Number);
			table.AddColumn("movies.dat", "title", "title", DataType.String);
			table.AddColumn("movies.dat", "imdbPictureURL", "imdbPictureURL", DataType.String);
			table.AddColumn("movies.dat", "year", "year", DataType.Number);
			table.AddColumn("movies.dat", "rtAudienceRating", "rtAudienceRating", DataType.Number);
			table.AddColumn("movies.dat", "rtAudienceNumRatings", "rtAudienceNumRatings", DataType.Number);
			table.AddColumn("movies.dat", "rtAudienceScore", "rtAudienceScore", DataType.Number);
			table.AddColumn("movies.dat", "rtPictureURL", "rtPictureURL", DataType.String);
			table.AddNullColumn("country");
			table.AddNullColumn("directorID");
			table.ImportData(con, true);

			table = new OutputTable("Movie.directorID");
			table.AddColumn("movie_directors.dat", "directorID", "directorID", DataType.String);
			table.AddColumn("movie_directors.dat", "movieID", "movieID", DataType.Number);
			table.runQuery(con, "UPDATE Movie SET directorID = ? WHERE id = ?");

			table = new OutputTable("Movie.country");
			table.AddColumn("movie_countries.dat", "country", "country", DataType.String);
			table.AddColumn("movie_countries.dat", "movieID", "movieID", DataType.Number);
			table.runQuery(con, "UPDATE Movie SET country = ? WHERE id = ?");

			table = new OutputTable("Movie_Genre");
			table.AddColumn("movie_genres.dat", "movieID", "movieID", DataType.Number);
			table.AddColumn("movie_genres.dat", "genre", "genre", DataType.String);
			table.ImportData(con, true);

			table = new OutputTable("User_Rated_Movie");
			table.AddColumn("user_ratedmovies-timestamps.dat", "userID", "userID", DataType.Number);
			table.AddColumn("user_ratedmovies-timestamps.dat", "movieID", "movieID", DataType.Number);
			table.AddColumn("user_ratedmovies-timestamps.dat", "rating", "rating", DataType.Number);
			table.AddColumn("user_ratedmovies-timestamps.dat", "timestamp", "timestamp", DataType.Timestamp);
			table.ImportData(con, true);

			table = new OutputTable("Tag");
			table.AddColumn("tags.dat", "id", "id", DataType.Number);
			table.AddColumn("tags.dat", "value", "value", DataType.String);
			table.ImportData(con, true);

			table = new OutputTable("User_Tagged_Movie");
			table.AddColumn("user_taggedmovies-timestamps.dat", "userID", "userID", DataType.Number);
			table.AddColumn("user_taggedmovies-timestamps.dat", "movieID", "movieID", DataType.Number);
			table.AddColumn("user_taggedmovies-timestamps.dat", "tagID", "tagID", DataType.Number);
			table.AddColumn("user_taggedmovies-timestamps.dat", "timestamp", "timestamp", DataType.Timestamp);
			table.ImportData(con, true);

			table = new OutputTable("Movie_Tag");
			table.AddColumn("movie_tags.dat", "movieID", "movieID", DataType.Number);
			table.AddColumn("movie_tags.dat", "tagID", "tagID", DataType.Number);
			table.AddColumn("movie_tags.dat", "tagWeight", "tagWeight", DataType.Number);
			table.ImportData(con, true);

			table = new OutputTable("Person");
			table.AddColumn("movie_actors.dat", "actorID", "id", DataType.String);
			table.AddColumn("movie_actors.dat", "actorName", "name", DataType.String);
			table.ImportData(con, false);

			table = new OutputTable("Movie_Actor");
			table.AddColumn("movie_actors.dat", "movieID", "movieID", DataType.Number);
			table.AddColumn("movie_actors.dat", "actorID", "actorID", DataType.String);
			table.AddColumn("movie_actors.dat", "ranking", "ranking", DataType.Number);
			table.ImportData(con, true);

		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				con.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		long finish = System.currentTimeMillis();
		System.out.println("done");
		System.out.println("duration: " + ((finish - start) / 60000) + " minutes");
	}

	public static enum DataType
	{
		String, Number, Timestamp, Null
	};

	private static class OutputTable
	{
		private ArrayList<Column> columns;
		private ArrayList<String> columnnames;
		private ArrayList<DataType> columndatatype;
		private String tablename;

		public OutputTable(String tablename)
		{
			this.tablename = tablename;
			columns = new ArrayList<Column>();
			columnnames = new ArrayList<String>();
			columndatatype = new ArrayList<DataType>();
		}

		public void AddNullColumn(String name)
		{
			columns.add(new NullColumn());
			columnnames.add(name);
			columndatatype.add(DataType.Null);
		}

		public void AddColumn(String filename, String columnname, String name, DataType datatype) throws IOException
		{
			columns.add(new InputColumn(filename, columnname));
			columnnames.add(name);
			columndatatype.add(datatype);
		}

		public void runQuery(Connection con, String query) throws SQLException, IOException
		{
			System.out.print(tablename + " ... ");
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);

			Column firstcol = columns.get(0);
			int entrycounter = 0;
			int columncount = columns.size();
			Column[] col = columns.toArray(new Column[columncount]);
			DataType[] coltype = columndatatype.toArray(new DataType[columncount]);

			String s;
			while (firstcol.hasnext())
			{
				for (int i = 0; i < columncount; i++)
				{
					switch (coltype[i])
					{
					case Null:
						ps.setNull(i + 1, java.sql.Types.NULL);
						break;
					case Number:
						s = col[i].next();
						try
						{
							ps.setDouble(i + 1, Double.parseDouble(s));
						} catch (NumberFormatException | ArrayIndexOutOfBoundsException nfe)
						{
							ps.setNull(i + 1, java.sql.Types.NULL);
						}
						break;
					case String:
						s = col[i].next();
						try
						{
							ps.setString(i + 1, s);
						} catch (ArrayIndexOutOfBoundsException nfe)
						{
							ps.setNull(i + 1, java.sql.Types.NULL);
						}
						break;
					case Timestamp:
						s = col[i].next();
						try
						{
							ps.setTimestamp(i + 1, new Timestamp(Long.parseLong(s)));
						} catch (NumberFormatException | ArrayIndexOutOfBoundsException nfe)
						{
							ps.setNull(i + 1, java.sql.Types.NULL);
						}
						break;
					default: //
						throw new RuntimeException("Unsupported Data Type: " + columndatatype.get(i));
					}
				}
				ps.addBatch();
				entrycounter++;
			}
			System.out.print(entrycounter + " entries ... ");
			try
			{
				ps.executeBatch();
			} catch (SQLException sqle)
			{
				//ignore duplicate entry errors
				if (sqle.getErrorCode() != 1062)
				{
					throw sqle;
				}
			}
			for (int i = 0; i < columns.size(); i++)
			{
				columns.get(i).close();
			}

			System.out.println("done");
		}

		public void ImportData(Connection con, boolean deleteold) throws SQLException, IOException
		{
			//System.out.println("importing: " + tablename);

			if (deleteold)
			{
				Statement state = (Statement) con.createStatement();
				state.execute("DELETE FROM " + tablename + ";");
			}
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO " + tablename + " (");
			for (int i = 0; i < columns.size(); i++)
			{
				if (i > 0)
				{
					sb.append(", ");
				}
				sb.append(columnnames.get(i));
			}
			sb.append(") VALUES (");
			for (int i = 0; i < columns.size(); i++)
			{
				if (i > 0)
				{
					sb.append(", ");
				}
				sb.append("?");
			}
			sb.append(");");

			runQuery(con, sb.toString());
		}
	}

	private static abstract interface Column
	{
		public boolean hasnext() throws IOException;

		public String next() throws IOException, ArrayIndexOutOfBoundsException;

		public void close() throws IOException;
	}

	private static class NullColumn implements Column
	{
		public boolean hasnext() throws IOException
		{
			throw new RuntimeException();
		}

		public String next() throws IOException
		{
			throw new RuntimeException();
		}

		public void close() throws IOException
		{
		}
	}

	private static class InputColumn implements Column
	{
		private BufferedReader fr;
		private String[] header;
		private int idx;

		public InputColumn(String filename, String columnname) throws IOException
		{
			fr = new BufferedReader(new FileReader(DATASETDIRECTORY + File.separator + filename));
			String firstline = fr.readLine();
			header = firstline.split("\t");
			idx = -1;
			for (int i = 0; i < header.length; i++)
			{
				if (columnname.equals(header[i]))
				{
					idx = i;
					break;
				}
			}
			if (idx < 0)
			{
				throw new RuntimeException("Unknown Column: \"" + columnname + "\"");
			}
		}

		public boolean hasnext() throws IOException
		{
			return fr.ready();
		}

		public String next() throws IOException
		{
			String s = fr.readLine();
			try
			{
				return s.split("\t")[idx];
			} catch (ArrayIndexOutOfBoundsException ae)
			{
				//System.err.print("Malformed Line \"" + s + "\"");
				return null;
			}
		}

		public void close() throws IOException
		{
			fr.close();
		}
	}
}
