package voxspell;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import voxspell.cards.StageSelect;
import voxspell.cards.Menu;
import voxspell.cards.Quiz;
import voxspell.cards.Settings;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JInternalFrame;


@SuppressWarnings({ "serial", "unused" })
public class Voxspell extends JFrame implements ActionListener {

	/*
	public enum QuizResult {
		MASTERED(1), 
		FAULTED(2), 
		FAILED(3);
		
		public final int integerValue;
		
		private QuizResult(int integer) {
			integerValue = integer;
		}
	}
	*/
	
	final public static File WORDLIST = new File("NZCER-spelling-lists.txt");

	private CardLayout layout = new CardLayout();
	private JPanel cards = new JPanel();

	// Cards
	private Menu menu = new Menu(this);
	private Quiz quiz = new Quiz(this);
	private StageSelect stageSelect = new StageSelect(this);
	private Settings settings = new Settings(this);

	private Voxspell(String[] args) throws FileNotFoundException {
		setResizable(false);
		setTitle("VOXSPELL");
		setSize(500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Set up important files
		createVoiceSettingFiles();
		createStatsFiles();
		
		cards.setLayout(layout);
		
		// Set up cards
		cards.add(menu, "Menu");
		cards.add(stageSelect, "Level Select");
		cards.add(quiz, "Quiz");
		cards.add(settings, "Settings");

		getContentPane().add(cards);
		returnToMenu();

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menu.settings) {
			layout.show(cards, "Settings");
		}
	}


	public static void createStatsFiles() {
		// Initialise .history
		File f = new File(".history");
		if (!f.exists() || !f.isDirectory()) {
			f.mkdir();
		}
		// create a folder for each level. Each folder contains a file for numerical statistics for each word 
		// and a file which contains only the words to be reviewed.
		for (int i = 1; i < 12; i++) {
			f = new File(".history/level"+i);
			if (!f.exists() || !f.isDirectory()) {
				f.mkdir();
			}
			try {
				new File(".history/level"+i+"/stats").createNewFile();
				new File(".history/level"+i+"/toReview").createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new Voxspell(args);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void startQuiz(int level) {
		layout.show(cards, "Quiz");
		quiz.startQuiz(level);
	}
	
	public void returnToMenu() {
		layout.show(cards, "Menu");
	}
	
	private boolean reviewFilesEmpty() {
		for (int i = 1; i < 12; i++) {
			File f = new File(".history/level"+i+"/toReview");
			if (f.length() > 0) {
				return false;
			}
		}
		return true;
	}
	
	private static void createVoiceSettingFiles() {
		File f = new File(".festival");
		if (!f.exists() || !f.isDirectory()) {
			f.mkdir();
		}
		
		f = new File(".festival/.message.scm");
		try {
			f.createNewFile();
		} catch (Exception e) { }
		
		try {   
			List<String> linesToWrite = new ArrayList<>();
			linesToWrite.add(Settings.DEFAULT_VOICE);
		    Files.write(Festival.SCHEME_FILE.toPath(), linesToWrite); 
		} catch (Exception e) { } 
		
	}
	
	private boolean wordListExists() {
		if (!WORDLIST.exists()) {
			JOptionPane.showMessageDialog(this, "File 'NZCER-spelling-lists.txt' does not exist within "+ClassLoader.getSystemClassLoader().getResource(".").getPath() +"\n"
					+ "If you ran VOXSPELL without using the runVoxspell.sh script, then 'NZCER-spelling-lists.txt' doesn't exist within your home directory\n"
					+ "Please make sure this file exists within the correct directory before attempting to start a quiz");
			return false;
		} else {
			return true;
		}
	}

}
