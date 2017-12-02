package app;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class AwesomeMovies {

	private JFrame frame;
	private JTextArea textArea;
	private JTextField textField;
	private JTextField txtTop;
	private JTextField txtTitle;

	private JLabel lblTitle;
	private JPanel contentPanel;
	private JTextField txtAppearances;
	private JTextField txtTimeLine;
	
	private ButtonGroup bgOptions;
	private int icounter = 0;
	private JComboBox cboQueries;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Database.connect();
	    EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AwesomeMovies window = new AwesomeMovies();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AwesomeMovies() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		//Top Panel
		BorderLayout topLayout = new BorderLayout();
		//FlowLayout topLayout = new FlowLayout();
		//topLayout.setHgap(10);
		//topLayout.setVgap(10);

		JLabel lblAwesomeMovies = new JLabel("");
		lblAwesomeMovies.setHorizontalAlignment(SwingConstants.CENTER);
		lblAwesomeMovies.setIcon(new ImageIcon("AwesomeMovies/src/AwesomeMovies.png"));
		//frame.getContentPane().add(lblHelloWorld, BorderLayout.NORTH);

		JLabel lblLeftCurtain = new JLabel("");
		lblLeftCurtain.setIcon(new ImageIcon("AwesomeMovies/src/LeftCurtain.png"));

		JLabel lblRightCurtain = new JLabel("");
		lblRightCurtain.setIcon(new ImageIcon("AwesomeMovies/src/RightCurtain.png"));

		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.BLACK);
		topPanel.setLayout(topLayout);
		topPanel.setPreferredSize(new Dimension(900, 80));

		topPanel.add(lblLeftCurtain, BorderLayout.LINE_START);
		topPanel.add(lblAwesomeMovies, BorderLayout.CENTER);
		topPanel.add(lblRightCurtain, BorderLayout.LINE_END);

		mainPanel.add(topPanel, BorderLayout.PAGE_START);

		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.BLACK);
		leftPanel.setPreferredSize(new Dimension(20, 80));
		mainPanel.add(leftPanel, BorderLayout.LINE_START);

		
		//Middle Panel
		BorderLayout midLayout = new BorderLayout();
		//midLayout.setHgap(10);
		//midLayout.setVgap(10);
		
		JPanel midPanel = new JPanel();
		midPanel.setLayout(midLayout);
		midPanel.setPreferredSize(new Dimension(700, 500));
		//midPanel.add(btnClose);

		//************************************************
		//**  Work Area  *********************************
		//************************************************

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setPreferredSize(new Dimension(700, 500));
				
		
		//**********************
		// Middle Text Panel
		contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
		scrollPane = new JScrollPane(contentPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, scrollPane.getPreferredSize().height));
		textArea = new JTextArea();
		//scrollPane.setViewportView(textArea);
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		//centerPanel.add(contentPanel);
		
		//GridLayout gRLayout = new GridLayout(2, 5, 0, 0);
		FlowLayout fl_searchPanelTop = new FlowLayout(FlowLayout.LEFT);
		FlowLayout fl_searchPanelBottom = new FlowLayout();
				
		JPanel centerBottomPanel = new JPanel();
		centerBottomPanel.setBackground(new Color(154, 205, 50));
		centerBottomPanel.setPreferredSize(new Dimension(100, 60));
		centerBottomPanel.setLayout(new BorderLayout());		
		//centerBottomPanel.setLayout(fl_centerBottomPanel);		
		
		JPanel searchPanelTop = new JPanel();
		searchPanelTop.setBackground(new Color(154, 205, 50));
		searchPanelTop.setPreferredSize(new Dimension(100, 30));
		searchPanelTop.setLayout(fl_searchPanelTop);		
		centerBottomPanel.add(searchPanelTop, BorderLayout.NORTH);
		
		JLabel lblSearch = new JLabel("  Search:    ");
		searchPanelTop.add(lblSearch);

		/*
		bgOptions = new ButtonGroup();
		*/
		
		cboQueries = new JComboBox();
		cboQueries.setMaximumRowCount(12);
		cboQueries.setPrototypeDisplayValue("--------          Select Search Type          --------");
		cboQueries.addItem("1) Top Popular Movies "); 
		cboQueries.addItem("2) Movies by Title "); 
		cboQueries.addItem("3) Top Movies by Genre "); 
		cboQueries.addItem("4) All Movies by Director "); 
		cboQueries.addItem("5) All Movies by Actor "); 
		cboQueries.addItem("6) All Movies by Tag "); 
		cboQueries.addItem("7) Top Directors "); 
		cboQueries.addItem("8) Top Actors "); 
		cboQueries.addItem("9) All Movies by User "); 
		cboQueries.addItem("10) All Tags Associated With Movie "); 
		cboQueries.addItem("11) Recommended Top 5 Movies by Genre "); 
		cboQueries.addItem("12) Recommended Top 5 Movies by Director "); 
		searchPanelTop.add(cboQueries);

		ActionListener ComboBoxListener = new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				DropDownChanged(e);
			}
		};

		cboQueries.addActionListener(ComboBoxListener);
		
		centerPanel.add(centerBottomPanel, BorderLayout.SOUTH);

		JPanel searchPanelBottom = new JPanel();
		searchPanelBottom.setBackground(new Color(154, 205, 50));
		searchPanelBottom.setPreferredSize(new Dimension(100, 30));
		searchPanelBottom.setLayout(fl_searchPanelBottom);		
		centerBottomPanel.add(searchPanelBottom, BorderLayout.SOUTH);
		
		JLabel lblTop = new JLabel(" Top");
		centerBottomPanel.add(lblTop);
		
		txtTop = new JTextField();
		txtTop.setText("10");
		searchPanelBottom.add(txtTop);
		txtTop.setColumns(2);

		JLabel lblMovies = new JLabel("Movies       ");
		searchPanelBottom.add(lblMovies);

		lblTitle = new JLabel("By Title:");
		searchPanelBottom.add(lblTitle);
		
		txtTitle = new JTextField();
		txtTitle.setEnabled(false);
		searchPanelBottom.add(txtTitle);
		txtTitle.setColumns(20);

		JLabel lblAppearances2 = new JLabel(" who Appeared in at Least");
		searchPanelBottom.add(lblAppearances2);
		
		txtAppearances = new JTextField();
		txtAppearances.setEnabled(false);
		txtAppearances.setText("1");
		searchPanelBottom.add(txtAppearances);
		txtAppearances.setColumns(2);

		JLabel lblAppearances = new JLabel("Movies.");
		searchPanelBottom.add(lblAppearances);

		JLabel lblSpacer5b = new JLabel("__________________________________________");
		lblSpacer5b.setForeground(new Color(154, 205, 50));
		searchPanelBottom.add(lblSpacer5b);
				
		//************************************************
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setForeground(Color.BLACK);
		rightPanel.setBackground(Color.BLACK);
		rightPanel.setPreferredSize(new Dimension(20, 80));
		mainPanel.add(rightPanel, BorderLayout.LINE_END);
		
		
		//Bottom Panel
		FlowLayout layout = new FlowLayout();
		layout.setHgap(10);
		layout.setVgap(10);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setForeground(Color.BLACK);
		bottomPanel.setBackground(Color.BLACK);
		bottomPanel.setLayout(layout);
		bottomPanel.setPreferredSize(new Dimension(900, 60));
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setPreferredSize(new Dimension(100,20));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Search(arg0);
			}
		});

		bottomPanel.add(btnSearch);
		
		JButton btnClose = new JButton("Close");
		btnClose.setPreferredSize(new Dimension(100,20));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    Database.close();
			    System.exit(0);
			}
		});

		bottomPanel.add(btnClose);
		
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		//mainPanel.add(btnClose, BorderLayout.SOUTH);
		frame.addWindowListener(new WindowListener(){
			@Override public void windowActivated(WindowEvent arg0){}
			@Override public void windowClosed(WindowEvent arg0){}
			@Override public void windowClosing(WindowEvent arg0){ Database.close(); }
			@Override public void windowDeactivated(WindowEvent arg0){}
			@Override public void windowDeiconified(WindowEvent arg0){}
			@Override public void windowIconified(WindowEvent arg0){}
			@Override public void windowOpened(WindowEvent arg0){}});
		frame.setContentPane(mainPanel);
		frame.pack();
		//frame.getContentPane().add(panel, BorderLayout.CENTER);
	}
	
	public void DropDownChanged(ActionEvent e)
	{
		if(this.cboQueries.getSelectedIndex() == 0)  //"1) Top Popular Movies "
		{
			this.txtTop.setEnabled(true);
			this.txtTitle.setEnabled(false);
			txtAppearances.setEnabled(false);
		}
		else if(this.cboQueries.getSelectedIndex() == 1)  //"2) Movies by Title "
		{
			this.txtTop.setEnabled(false);
			lblTitle.setText("Title:");
			this.txtTitle.setEnabled(true);
			txtAppearances.setEnabled(false);
		}
		else if(this.cboQueries.getSelectedIndex() == 2)  //"3) Top Movies by Genre "
		{
			this.txtTop.setEnabled(true);
			lblTitle.setText("Genre:");
			this.txtTitle.setEnabled(true);
			txtAppearances.setEnabled(false);
		}
		else if(this.cboQueries.getSelectedIndex() == 3) //"4) All Movies by Director "
		{
			this.txtTop.setEnabled(false);
			lblTitle.setText("Director:");
			this.txtTitle.setEnabled(true);
			txtAppearances.setEnabled(false);
		}
		else if(this.cboQueries.getSelectedIndex() == 4)  //"5) All Movies by Actor "
		{
			this.txtTop.setEnabled(false);
			lblTitle.setText("Actor:");
			this.txtTitle.setEnabled(true);
			txtAppearances.setEnabled(false);
		}
		else if(this.cboQueries.getSelectedIndex() == 5)  //"6) All Movies by Tag "
		{
			this.txtTop.setEnabled(false);
			lblTitle.setText("Tag:");
			this.txtTitle.setEnabled(true);
			txtAppearances.setEnabled(false);
		}
		else if(this.cboQueries.getSelectedIndex() == 6)  //"7) Top Directors "
		{
			this.txtTop.setEnabled(true);
			lblTitle.setText("Director:");
			this.txtTitle.setEnabled(false);
			txtAppearances.setEnabled(true);
		}
		else if(this.cboQueries.getSelectedIndex() == 7)  //"8) Top Actors "
		{
			this.txtTop.setEnabled(true);
			lblTitle.setText("Actor:");
			this.txtTitle.setEnabled(false);
			txtAppearances.setEnabled(true);
		}
		else if(this.cboQueries.getSelectedIndex() == 8)  //"9) All Movies by User "
		{
			this.txtTop.setEnabled(false);
			lblTitle.setText("User:");
			this.txtTitle.setEnabled(true);
			txtAppearances.setEnabled(false);
		}
		else if(this.cboQueries.getSelectedIndex() == 9)  //"10) All Tags Associated With Movie "
		{
			this.txtTop.setEnabled(false);
			lblTitle.setText("Title:");
			this.txtTitle.setEnabled(true);
			txtAppearances.setEnabled(false);
		}
		else if(this.cboQueries.getSelectedIndex() == 10)  //"11) Recommended Top 5 Movies by Genre "
		{
			this.txtTop.setEnabled(true);
			lblTitle.setText("Genre:");
			this.txtTitle.setEnabled(true);
			txtAppearances.setEnabled(true);
		}
		else if(this.cboQueries.getSelectedIndex() == 11)  //"12) Recommended Top 5 Movies by Director "
		{
			this.txtTop.setEnabled(true);
			lblTitle.setText("Director:");
			this.txtTitle.setEnabled(true);
			txtAppearances.setEnabled(true);
		}		
	}
	
	public void Search(ActionEvent arg0)
	{
		String sMessage = "Search by "; 
		
		if(this.cboQueries.getSelectedIndex() == 0)  //"1) Top Popular Movies "
		{
			sMessage += "Top " + txtTop.getText() + " Movie(s) Selected (Popularity, Rotten Tomato Score)";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindTopNMovies(Integer.parseInt(this.txtTop.getText()));
		}
		else if(this.cboQueries.getSelectedIndex() == 1)  //"2) Movies by Title "
		{
			sMessage += "Title Selected";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindMovieByTitle(this.txtTitle.getText());
		}
		else if(this.cboQueries.getSelectedIndex() == 2)  //"3) Top Movies by Genre "
		{
			sMessage += "Genre Selected";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindTopNMoviesByGenre(Integer.parseInt(this.txtTop.getText()), this.txtTitle.getText());
		}
		else if(this.cboQueries.getSelectedIndex() == 3) //"4) All Movies by Director "
		{
			sMessage += "All Movies by Director Selected";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindAllMoviesByDirector(this.txtTitle.getText());
		}
		else if(this.cboQueries.getSelectedIndex() == 4)  //"5) All Movies by Actor "
		{
			sMessage += "All Movies by Actor Selected";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindAllMoviesByActor(this.txtTitle.getText());
		}
		else if(this.cboQueries.getSelectedIndex() == 5)  //"6) All Movies by Tag "
		{
			sMessage += "Tag Selected";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindTopAllMoviesByTag(this.txtTitle.getText());
		}
		else if(this.cboQueries.getSelectedIndex() == 6)  //"7) Top Directors "
		{
			sMessage += "Top " + txtTop.getText() + " Directors Selected";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindTopNDirectors(Integer.parseInt(this.txtTop.getText()), Integer.parseInt(this.txtAppearances.getText()));
		}
		else if(this.cboQueries.getSelectedIndex() == 7)  //"8) Top Actors "
		{
			sMessage += "Top " + txtTop.getText() + " Actors Selected";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindTopNActors(Integer.parseInt(this.txtTop.getText()), Integer.parseInt(this.txtAppearances.getText()));
		}
		else if(this.cboQueries.getSelectedIndex() == 8)  //"9) All Movies by User "
		{
			sMessage += "Show All Timelines by User";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindAllTimelinesByUser(this.txtTitle.getText());
		}
		else if(this.cboQueries.getSelectedIndex() == 9)  //"10) All Tags Associated With Movie "
		{
			sMessage += "Show All Tags by User";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindMovieByTitle(this.txtTitle.getText());
		}
		else if(this.cboQueries.getSelectedIndex() == 10)  //"11) Recommended Top 5 Movies by Genre "
		{
			sMessage += "Show Recommended Top 5 by Genre";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindTop5MoviesByGenre(this.txtTitle.getText());
		}
		else if(this.cboQueries.getSelectedIndex() == 11)  //"12) Recommended Top 5 Movies by Director "
		{
			sMessage += "Show Recommended Top 5 by Director";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindTop5MoviesByDirector(this.txtTitle.getText());
		}
		
		this.textArea.setText(sMessage);
					
		icounter++;
	}
	
	private String FindTopNMovies(int n)
	{
		String results = "";
		List<Movie> movies = Database.getTopMovies(n);
		showMovieList(movies);
		
		return results;
	}
	
	private void showMovieList(List<Movie> movies){
	    contentPanel.removeAll();
        for(Movie m : movies){
            JPanel row = new JPanel();
            row.setLayout(new BoxLayout(row, BoxLayout.PAGE_AXIS));
            
            JPanel imageRow = new JPanel(new FlowLayout());
            imageRow.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            String movieInfo = m.title + " Year: " + m.year + " Audience Score: " + m.rtAudienceScore;
            String tagText = "<html>";
            if(m.getTags().size() > 0){
                tagText += "Tags: ";
                int counter = 0;
                for(String s : m.getTags()){
                    tagText += "{" + s + "} ";
                    ++counter;
                    if(counter == 10){
                        counter = 0;
                        tagText += "<br/>";
                    }
                }
            }
            tagText += "</html>";
            JLabel text = new JLabel(movieInfo);
            JLabel tags = new JLabel(tagText, SwingConstants.CENTER);
            text.setAlignmentX(Component.CENTER_ALIGNMENT);
            tags.setAlignmentX(Component.CENTER_ALIGNMENT);
            ImageIcon imdb = imageFromURL(m.imdbPictureURL);
            ImageIcon rt = imageFromURL(m.rtPictureURL);
            imageRow.add(new JLabel(imdb));
            imageRow.add(new JLabel(rt));
            row.add(text);
            row.add(tags);
            row.add(imageRow);
            contentPanel.add(row);
        }
        contentPanel.getParent().getParent().validate();
        contentPanel.getParent().getParent().repaint();
	}
	
	private ImageIcon imageFromURL(String url){
	    URL img = null;
	    ImageIcon image = null;
        try {
            img = new URL(url);
        } catch (MalformedURLException e) {
            //e.printStackTrace();
        }
        if(img != null){
            image = new ImageIcon(img);
        }
        return image;
	}
	
	private String FindMovieByTitle(String title) 
	{
		String results = "";
		List<Movie> movies = Database.getMoviesByTitle(title);
        showMovieList(movies);
		return results;		
	}
	
	private String FindTop5MoviesByGenre(String genre)
	{		
		return FindTopNMoviesByGenre(5, genre);
	}

	private String FindTopNMoviesByGenre(int n, String genre)
	{
		String results = "";
		List<Movie> movies = Database.getTopMoviesByGenre(n, genre);
        showMovieList(movies);
		return results;
	}

	private String FindAllMoviesByDirector(String director)
	{
		String results = "";
		List<Movie> movies = Database.getMoviesByDirector(director);
        showMovieList(movies);
		return results;
	}
	
	private String FindAllMoviesByActor(String actor)
	{
		String results = "";
		List<Movie> movies = Database.getMoviesByActor(actor);
        showMovieList(movies);
		return results;
	}
	
	private String FindTopAllMoviesByTag(String tag)
	{
		String results = "";
		List<Movie> movies = Database.getMoviesByTag(tag);
        showMovieList(movies);
		return results;
	}

	private String FindTopNDirectors(int n, int atLeastK) 
	{
		String results = "";
		List<PersonRanking> directors = Database.getTopDirectors(n, atLeastK);
		showPersonList(directors);
		return results;
	}

	private String FindTopNActors(int n, int atLeastK) 
	{
		String results = "";
		List<PersonRanking> actors = Database.getTopActors(n, atLeastK);
        showPersonList(actors);
		return results;
	}
	
	private void showPersonList(List<PersonRanking> people){
        contentPanel.removeAll();
        for(PersonRanking p : people){
            String directorInfo = "Name: " + p.name + " Rating: " + p.rating;
            JLabel text = new JLabel(directorInfo);
            text.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(text);
        }
        contentPanel.validate();
        contentPanel.repaint();
    }

	private String FindTop5MoviesByDirector(String director) 
	{
		String results = "";
		List<Movie> movies = Database.getTopNMoviesByDirector(director, 5);
        showMovieList(movies);
		return results;
	}

	private String FindTopNMoviesByDirector(int n, String director, int atLeastK) 
	{
		String results = "";
		
		return results;
	}
	
	private String FindTopNMoviesByActor(int n, String actor, int atLeastK)
	{
		String results = "";
		
		return results;
	}
	
	private String FindAllTimelinesByUser(String user)
	{	
		String results = "";
		List<UserRatedMovie> ratings = Database.getUserRatedMovies(Integer.parseInt(user));
        showUserRatingsList(ratings);
		return results;		
	}
	
	private void showUserRatingsList(List<UserRatedMovie> ratings){
        contentPanel.removeAll();
        Map<String, Integer> genreBreakdown = new HashMap<String, Integer>();
        int totalRatings = 0;
        JPanel genres = new JPanel();
        genres.setLayout(new BoxLayout(genres, BoxLayout.PAGE_AXIS));
        contentPanel.add(genres);
        for(UserRatedMovie urm : ratings){
            for(String s: urm.getGenres()){
                Integer count = genreBreakdown.get(s);
                if(count != null){
                    genreBreakdown.put(s, count+1);
                } else {
                    genreBreakdown.put(s, 1);
                }
                ++totalRatings;
            }
            String ratingInfo = "Movie Title: " + urm.title + " Rating: " + urm.rating + " DateTime: " + urm.timestamp.toString();
            JLabel text = new JLabel(ratingInfo);
            text.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(text);
        }
        for(Entry<String, Integer> pair : genreBreakdown.entrySet()){
            String percent = new DecimalFormat("###.##").format((double)pair.getValue()*100/totalRatings);
            String genreInfo = "Genre: " + pair.getKey() + " Percent: " + percent;
            JLabel text = new JLabel(genreInfo);
            text.setAlignmentX(Component.CENTER_ALIGNMENT);
            genres.add(text);
        }
        contentPanel.validate();
        contentPanel.repaint();
    }
	
	private String FindAllTagsByUser(String user)
	{	
		String results = "";
		
		return results;		
	}

}
