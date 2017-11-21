import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class AwesomeMovies {

	private JFrame frame;
	private JTextArea textArea;
	private JTextField textField;
	private JTextField txtTop;
	private JTextField txtTitle;
	private JTextField txtGenre;
	private JTextField txtDirector;
	private JTextField txtActor;

	private JTextField txtTag;
	private JTextField txtTopDirectors;
	private JTextField txtTopActors;
	private JTextField txtAppearances;
	private JTextField txtUser;
	private JTextField txtTimeLine;
	
	private ButtonGroup bgOptions;
	private JRadioButton rdbtnTopMovies;
	private JRadioButton rdbtnBytitle;
	private JRadioButton rdbtnBygenre;
	private JRadioButton rdbtnBydirector;
	private JRadioButton rdbtnByactor;
	private JRadioButton rdbtnBytag;
	private JRadioButton rdbtnTopdirectors;
	private JRadioButton rdbtnTopactors;
	private JRadioButton rdbtnTimelineByUser;
	private JRadioButton rdbtnShowAllTagsByUser;
	private JRadioButton rdbtnShowTopNByDirector;
	private JRadioButton rdbtnShowTopNByGenre;
	private int icounter = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		lblAwesomeMovies.setIcon(new ImageIcon("C:\\Data\\eclipse-workspace\\AwesomeMovies\\src\\AwesomeMovies.png"));
		//frame.getContentPane().add(lblHelloWorld, BorderLayout.NORTH);

		JLabel lblLeftCurtain = new JLabel("");
		lblLeftCurtain.setIcon(new ImageIcon("C:\\Data\\eclipse-workspace\\AwesomeMovies\\src\\LeftCurtain.png"));

		JLabel lblRightCurtain = new JLabel("");
		lblRightCurtain.setIcon(new ImageIcon("C:\\Data\\eclipse-workspace\\AwesomeMovies\\src\\RightCurtain.png"));

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
		JScrollPane scrollPane = new JScrollPane();
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		
		//GridLayout gRLayout = new GridLayout(2, 5, 0, 0);
		FlowLayout fl_centerBottomPanel = new FlowLayout();

		JPanel centerBottomPanel = new JPanel();
		centerBottomPanel.setBackground(new Color(154, 205, 50));
		centerBottomPanel.setPreferredSize(new Dimension(100, 180));
		centerBottomPanel.setLayout(fl_centerBottomPanel);
		centerPanel.add(centerBottomPanel, BorderLayout.SOUTH);
		

		JLabel lblSearch = new JLabel("  Search:    ");
		centerBottomPanel.add(lblSearch);
		
		bgOptions = new ButtonGroup();
		ActionListener RadioButtonListener = new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				RadioButtonChanged(e);
			}
		};
		
		rdbtnTopMovies = new JRadioButton("");
		rdbtnTopMovies.setSelected(true);
		rdbtnTopMovies.setBackground(new Color(154, 205, 50));
		centerBottomPanel.add(rdbtnTopMovies);
		rdbtnTopMovies.addActionListener(RadioButtonListener);
		bgOptions.add(rdbtnTopMovies);
		
		JLabel lblTop = new JLabel(" Top");
		centerBottomPanel.add(lblTop);
		
		txtTop = new JTextField();
		txtTop.setText("10");
		centerBottomPanel.add(txtTop);
		txtTop.setColumns(2);

		JLabel lblMovies = new JLabel("Movies       ");
		centerBottomPanel.add(lblMovies);
		
		rdbtnBytitle = new JRadioButton("");
		rdbtnBytitle.setBackground(new Color(154, 205, 50));
		centerBottomPanel.add(rdbtnBytitle);
		rdbtnBytitle.addActionListener(RadioButtonListener);
		bgOptions.add(rdbtnBytitle);

		JLabel lblTitle = new JLabel("By Title:");
		centerBottomPanel.add(lblTitle);
		
		txtTitle = new JTextField();
		txtTitle.setEnabled(false);
		centerBottomPanel.add(txtTitle);
		txtTitle.setColumns(20);

		JLabel lblSpacer1 = new JLabel("__");
		lblSpacer1.setForeground(new Color(154, 205, 50));
		centerBottomPanel.add(lblSpacer1);

		rdbtnBygenre = new JRadioButton("");
		rdbtnBygenre.setBackground(new Color(154, 205, 50));
		centerBottomPanel.add(rdbtnBygenre);
		rdbtnBygenre.addActionListener(RadioButtonListener);
		bgOptions.add(rdbtnBygenre);
		
		JLabel lblGenre = new JLabel("By Genre:");
		centerBottomPanel.add(lblGenre);

		txtGenre = new JTextField();
		txtGenre.setEnabled(false);
		centerBottomPanel.add(txtGenre);
		txtGenre.setColumns(20);

		JLabel lblSpacer1c = new JLabel("__");
		lblSpacer1c.setForeground(new Color(154, 205, 50));
		centerBottomPanel.add(lblSpacer1c);

		JLabel lblSpacer2 = new JLabel("________________________");
		lblSpacer2.setForeground(new Color(154, 205, 50));
		centerBottomPanel.add(lblSpacer2);
		
		rdbtnBydirector = new JRadioButton("");
		rdbtnBydirector.setBackground(new Color(154, 205, 50));
		centerBottomPanel.add(rdbtnBydirector);
		rdbtnBydirector.addActionListener(RadioButtonListener);
		bgOptions.add(rdbtnBydirector);

		JLabel lblDirector = new JLabel("By Director:");
		centerBottomPanel.add(lblDirector);
		
		txtDirector = new JTextField();
		txtDirector.setEnabled(false);
		centerBottomPanel.add(txtDirector);
		txtDirector.setColumns(20);

		JLabel lblSpacer2b = new JLabel("__");
		lblSpacer2b.setForeground(new Color(154, 205, 50));
		centerBottomPanel.add(lblSpacer2b);
		
		rdbtnByactor = new JRadioButton("");
		rdbtnByactor.setBackground(new Color(154, 205, 50));
		centerBottomPanel.add(rdbtnByactor);
		rdbtnByactor.addActionListener(RadioButtonListener);
		bgOptions.add(rdbtnByactor);

		//rdbtnByactor.action(evt, what)
		bgOptions.add(rdbtnByactor);

		JLabel lblActor = new JLabel("By Actor:");
		centerBottomPanel.add(lblActor);
		
		txtActor = new JTextField();
		txtActor.setEnabled(false);
		centerBottomPanel.add(txtActor);
		txtActor.setColumns(20);

		JLabel lblSpacer3 = new JLabel("_______________________________");
		lblSpacer3.setForeground(new Color(154, 205, 50));
		centerBottomPanel.add(lblSpacer3);
		
		rdbtnBytag = new JRadioButton("");
		rdbtnBytag.setBackground(new Color(154, 205, 50));
		centerBottomPanel.add(rdbtnBytag);
		rdbtnBytag.addActionListener(RadioButtonListener);
		bgOptions.add(rdbtnBytag);
		
		JLabel lblTag = new JLabel("By Tag: ");
		centerBottomPanel.add(lblTag);
		
		txtTag = new JTextField();
		txtTag.setEnabled(false);
		centerBottomPanel.add(txtTag);
		txtTag.setColumns(20);

		JLabel lblSpacer3b = new JLabel("___________________________________________");
		lblSpacer3b.setForeground(new Color(154, 205, 50));
		centerBottomPanel.add(lblSpacer3b);

		JLabel lblSpacer4 = new JLabel("________________________________");
		lblSpacer4.setForeground(new Color(154, 205, 50));
		centerBottomPanel.add(lblSpacer4);
		
		rdbtnTopdirectors = new JRadioButton("");
		rdbtnTopdirectors.setBackground(new Color(154, 205, 50));
		centerBottomPanel.add(rdbtnTopdirectors);
		rdbtnTopdirectors.addActionListener(RadioButtonListener);
		bgOptions.add(rdbtnTopdirectors);

		JLabel lblTopDirectors = new JLabel("Top");
		centerBottomPanel.add(lblTopDirectors);
		
		txtTopDirectors = new JTextField();
		txtTopDirectors.setEnabled(false);
		txtTopDirectors.setText("10");
		centerBottomPanel.add(txtTopDirectors);
		txtTopDirectors.setColumns(2);

		JLabel lblTopDirectorsb = new JLabel("Directors                     ");
		centerBottomPanel.add(lblTopDirectorsb);
		
		rdbtnTopactors = new JRadioButton("");
		rdbtnTopactors.setBackground(new Color(154, 205, 50));
		centerBottomPanel.add(rdbtnTopactors);
		rdbtnTopactors.addActionListener(RadioButtonListener);
		bgOptions.add(rdbtnTopactors);

		JLabel lblTopActors = new JLabel("Top");
		centerBottomPanel.add(lblTopActors);
		
		txtTopActors = new JTextField();
		txtTopActors.setEnabled(false);
		txtTopActors.setText("10");
		centerBottomPanel.add(txtTopActors);
		txtTopActors.setColumns(2);

		JLabel lblTopActorsb = new JLabel("Actors ");
		centerBottomPanel.add(lblTopActorsb);

		JLabel lblAppearances2 = new JLabel(" who Appeared in at Least");
		centerBottomPanel.add(lblAppearances2);
		
		txtAppearances = new JTextField();
		txtAppearances.setEnabled(false);
		txtAppearances.setText("1");
		centerBottomPanel.add(txtAppearances);
		txtAppearances.setColumns(2);

		JLabel lblAppearances = new JLabel("Movies.");
		centerBottomPanel.add(lblAppearances);

		JLabel lblSpacer5 = new JLabel("_____________________________________");
		lblSpacer5.setForeground(new Color(154, 205, 50));
		centerBottomPanel.add(lblSpacer5);
		
		JLabel lblUser = new JLabel("User: ");
		centerBottomPanel.add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setEnabled(false);
		centerBottomPanel.add(txtUser);
		txtUser.setColumns(20);

		JLabel lblSpacer5b = new JLabel("__________________________________________");
		lblSpacer5b.setForeground(new Color(154, 205, 50));
		centerBottomPanel.add(lblSpacer5b);

		rdbtnTimelineByUser = new JRadioButton("Show all timelines by user.");
		rdbtnTimelineByUser.setBackground(new Color(154, 205, 50));
		centerBottomPanel.add(rdbtnTimelineByUser);
		rdbtnTimelineByUser.addActionListener(RadioButtonListener);
		bgOptions.add(rdbtnTimelineByUser);		
		
		rdbtnShowAllTagsByUser = new JRadioButton("Show all tags by user.");
		rdbtnShowAllTagsByUser.setBackground(new Color(154, 205, 50));
		centerBottomPanel.add(rdbtnShowAllTagsByUser);
		rdbtnShowAllTagsByUser.addActionListener(RadioButtonListener);
		bgOptions.add(rdbtnShowAllTagsByUser);		

		rdbtnShowTopNByGenre = new JRadioButton("Show Recommended Top 5 by Genre.");
		rdbtnShowTopNByGenre.setBackground(new Color(154, 205, 50));
		centerBottomPanel.add(rdbtnShowTopNByGenre);
		rdbtnShowTopNByGenre.addActionListener(RadioButtonListener);
		bgOptions.add(rdbtnShowTopNByGenre);

		rdbtnShowTopNByDirector = new JRadioButton("Show Recommended Top 5 by Director.");
		rdbtnShowTopNByDirector.setBackground(new Color(154, 205, 50));
		centerBottomPanel.add(rdbtnShowTopNByDirector);
		rdbtnShowTopNByDirector.addActionListener(RadioButtonListener);
		bgOptions.add(rdbtnShowTopNByDirector);
				
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
				System.exit(0);
			}
		});

		bottomPanel.add(btnClose);
		
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		//mainPanel.add(btnClose, BorderLayout.SOUTH);
		frame.setContentPane(mainPanel);
		frame.pack();
		//frame.getContentPane().add(panel, BorderLayout.CENTER);
	}

	public void RadioButtonChanged(ActionEvent e)
	{
		if(rdbtnTopMovies.isSelected())
		{
			txtTop.setEnabled(true);
			txtTitle.setEnabled(false);
			txtTopDirectors.setEnabled(false);
			txtTopActors.setEnabled(false);
			txtAppearances.setEnabled(false);
			txtGenre.setEnabled(false);
			txtDirector.setEnabled(false);
			txtActor.setEnabled(false);
			txtTag.setEnabled(false);
			txtUser.setEnabled(false);
		}
		else if(rdbtnBytitle.isSelected())
		{
			txtTop.setEnabled(false);
			txtTitle.setEnabled(true);
			txtTopDirectors.setEnabled(false);
			txtTopActors.setEnabled(false);
			txtAppearances.setEnabled(false);
			txtGenre.setEnabled(false);
			txtDirector.setEnabled(false);
			txtActor.setEnabled(false);
			txtTag.setEnabled(false);
			txtUser.setEnabled(false);
		}
		else if(rdbtnBygenre.isSelected())
		{
			txtTop.setEnabled(true);
			txtTitle.setEnabled(false);
			txtTopDirectors.setEnabled(false);
			txtTopActors.setEnabled(false);
			txtAppearances.setEnabled(false);
			txtGenre.setEnabled(true);
			txtDirector.setEnabled(false);
			txtActor.setEnabled(false);
			txtTag.setEnabled(false);
			txtUser.setEnabled(false);
		}
		else if(rdbtnBydirector.isSelected())
		{
			txtTop.setEnabled(false);
			txtTitle.setEnabled(false);
			txtTopDirectors.setEnabled(false);
			txtTopActors.setEnabled(false);
			txtAppearances.setEnabled(false);
			txtGenre.setEnabled(false);
			txtDirector.setEnabled(true);
			txtActor.setEnabled(false);
			txtTag.setEnabled(false);
			txtUser.setEnabled(false);
		}
		else if(rdbtnByactor.isSelected())
		{
			txtTop.setEnabled(false);
			txtTitle.setEnabled(false);
			txtTopDirectors.setEnabled(false);
			txtTopActors.setEnabled(false);
			txtAppearances.setEnabled(false);
			txtGenre.setEnabled(false);
			txtDirector.setEnabled(false);
			txtActor.setEnabled(true);
			txtTag.setEnabled(false);
			txtUser.setEnabled(false);
		}
		else if(rdbtnBytag.isSelected())
		{
			txtTop.setEnabled(false);
			txtTitle.setEnabled(false);
			txtTopDirectors.setEnabled(false);
			txtTopActors.setEnabled(false);
			txtAppearances.setEnabled(false);
			txtGenre.setEnabled(false);
			txtDirector.setEnabled(false);
			txtActor.setEnabled(false);
			txtTag.setEnabled(true);
			txtUser.setEnabled(false);
		}
		else if(rdbtnTopdirectors.isSelected())
		{
			txtTop.setEnabled(false);
			txtTitle.setEnabled(false);
			txtTopDirectors.setEnabled(true);
			txtTopActors.setEnabled(false);
			txtAppearances.setEnabled(true);
			txtGenre.setEnabled(false);
			txtDirector.setEnabled(false);
			txtActor.setEnabled(false);
			txtTag.setEnabled(false);
			txtUser.setEnabled(false);
		}
		else if(rdbtnTopactors.isSelected())
		{
			txtTop.setEnabled(false);
			txtTitle.setEnabled(false);
			txtTopDirectors.setEnabled(false);
			txtTopActors.setEnabled(true);
			txtAppearances.setEnabled(true);
			txtGenre.setEnabled(false);
			txtDirector.setEnabled(false);
			txtActor.setEnabled(false);
			txtTag.setEnabled(false);
			txtUser.setEnabled(false);
		}
				
		else if(rdbtnTimelineByUser.isSelected())
		{
			txtTop.setEnabled(false);
			txtTitle.setEnabled(false);
			txtTopDirectors.setEnabled(false);
			txtTopActors.setEnabled(false);
			txtAppearances.setEnabled(false);
			txtGenre.setEnabled(false);
			txtDirector.setEnabled(false);
			txtActor.setEnabled(false);
			txtTag.setEnabled(false);
			txtUser.setEnabled(true);
		}
		else if(rdbtnShowAllTagsByUser.isSelected())
		{
			txtTop.setEnabled(false);
			txtTitle.setEnabled(false);
			txtTopDirectors.setEnabled(false);
			txtTopActors.setEnabled(false);
			txtAppearances.setEnabled(false);
			txtGenre.setEnabled(false);
			txtDirector.setEnabled(false);
			txtActor.setEnabled(false);
			txtTag.setEnabled(false);
			txtUser.setEnabled(true);			
		}
		else if(rdbtnShowTopNByGenre.isSelected())
		{
			txtTop.setEnabled(true);
			txtTop.setText("5");
			txtTitle.setEnabled(false);
			txtTopDirectors.setEnabled(false);
			txtTopActors.setEnabled(false);
			txtAppearances.setEnabled(false);
			txtGenre.setEnabled(true);
			txtDirector.setEnabled(false);
			txtActor.setEnabled(false);
			txtTag.setEnabled(false);
			txtUser.setEnabled(false);
		}
		else if(rdbtnShowTopNByDirector.isSelected())
		{
			txtTop.setEnabled(true);
			txtTop.setText("5");
			txtTitle.setEnabled(false);
			txtTopDirectors.setEnabled(false);
			txtTopActors.setEnabled(false);
			txtAppearances.setEnabled(false);
			txtGenre.setEnabled(false);
			txtDirector.setEnabled(true);
			txtActor.setEnabled(false);
			txtTag.setEnabled(false);
			txtUser.setEnabled(false);
		}			
	}
	
	public void Search(ActionEvent arg0)
	{
		String sMessage = "Search by "; 
		
		if(rdbtnTopMovies.isSelected())
		{
			sMessage += "Top " + txtTop.getText() + " Movie(s) Selected (Popularity, Rotten Tomato Score)";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindTopNMovies(Integer.parseInt(this.txtTop.getText()));
		}
		else if(rdbtnBytitle.isSelected())
		{
			sMessage += "Title Selected";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindMovieByTitle(this.txtTitle.getText());
		}
		else if(rdbtnBygenre.isSelected())
		{
			sMessage += "Genre Selected";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindTopNMoviesByGenre(Integer.parseInt(this.txtTop.getText()), this.txtGenre.getText());
		}
		else if(rdbtnBydirector.isSelected())
		{
			sMessage += "All Movies by Director Selected";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindAllMoviesByDirector(this.txtDirector.getText());
		}
		else if(rdbtnByactor.isSelected())
		{
			sMessage += "All Movies by Actor Selected";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindAllMoviesByActor(this.txtActor.getText());
		}
		else if(rdbtnBytag.isSelected())
		{
			sMessage += "Tag Selected";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindTopAllMoviesByTag(this.txtTag.getText());
		}
		else if(rdbtnTopdirectors.isSelected())
		{
			sMessage += "Top " + txtTopDirectors.getText() + " Directors Selected";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindTopNDirectors(Integer.parseInt(this.txtTopDirectors.getText()), Integer.parseInt(this.txtAppearances.getText()));
		}
		else if(rdbtnTopactors.isSelected())
		{
			sMessage += "Top " + txtTopActors.getText() + " Actors Selected";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindTopNActors(Integer.parseInt(this.txtTopActors.getText()), Integer.parseInt(this.txtAppearances.getText()));
		}
		else if(rdbtnTimelineByUser.isSelected())
		{
			sMessage += "Show All Timelines by User";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindAllTimelinesByUser(this.txtUser.getText());
		}
		else if(rdbtnShowAllTagsByUser.isSelected())
		{
			sMessage += "Show All Tags by User";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindAllTagsByUser(this.txtUser.getText());
		}
		else if(rdbtnShowTopNByGenre.isSelected())
		{
			sMessage += "Show Recommended Top 5 by Genre";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindTop5MoviesByGenre(this.txtGenre.getText());
		}
		else if(rdbtnShowTopNByDirector.isSelected())
		{
			sMessage += "Show Recommended Top 5 by Director";
			sMessage += "\n";
			sMessage += "\n";
			sMessage += FindTop5MoviesByDirector(this.txtGenre.getText());
		}
		
		this.textArea.setText(sMessage);
		
		icounter++;
	}
	
	private String FindTopNMovies(int n)
	{
		String results = "";
		
		return results;
	}
	
	private String FindMovieByTitle(String title) 
	{
		String results = "";
		
		return results;		
	}
	
	private String FindTop5MoviesByGenre(String genre)
	{		
		return FindTopNMoviesByGenre(5, genre);
	}

	private String FindTopNMoviesByGenre(int n, String genre)
	{
		String results = "";
		
		return results;
	}

	private String FindAllMoviesByDirector(String director)
	{
		String results = "";
		
		return results;
	}
	
	private String FindAllMoviesByActor(String actor)
	{
		String results = "";
		
		return results;
	}
	
	private String FindTopAllMoviesByTag(String tag)
	{
		String results = "";
		
		return results;
	}

	private String FindTopNDirectors(int n, int atLeastK) 
	{
		String results = "";
		
		return results;
	}

	private String FindTopNActors(int n, int atLeastK) 
	{
		String results = "";
		
		return results;
	}

	private String FindTop5MoviesByDirector(String director) 
	{
		String results = "";
		
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
		
		return results;		
	}
	
	private String FindAllTagsByUser(String user)
	{	
		String results = "";
		
		return results;		
	}

}
