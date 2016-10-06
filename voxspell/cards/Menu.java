package voxspell.cards;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import voxspell.Voxspell;
import java.awt.Color;

@SuppressWarnings("serial")
public class Menu extends Card implements ActionListener {

	public JButton newQuiz;
	public JButton viewStatistics;
	public JButton settings;

	public Menu(Voxspell sp) {
		super(sp, "Welcome to VOXSPELL");
		setBackground(new Color(230, 230, 250));
		
		menuButton.setEnabled(false);
		menuButton.setVisible(false);
		
		newQuiz = new JButton("Take A Quiz!");
		newQuiz.setForeground(new Color(245, 245, 245));
		newQuiz.setBackground(new Color(0, 191, 255));
		newQuiz.setFont(new Font("Dialog", Font.BOLD, 16));
		newQuiz.setBounds(100, 90, 300, 50);
		newQuiz.addActionListener((ActionListener) sp);
		viewStatistics = new JButton("Scores");
		viewStatistics.setBackground(new Color(0, 191, 255));
		viewStatistics.setForeground(new Color(245, 245, 245));
		viewStatistics.setFont(new Font("Dialog", Font.BOLD, 16));
		viewStatistics.setBounds(100, 160, 300, 50);
		viewStatistics.addActionListener(sp);
		settings = new JButton("Settings");
		settings.setBackground(new Color(0, 191, 255));
		settings.setForeground(new Color(245, 245, 245));
		settings.setFont(new Font("Dialog", Font.BOLD, 16));
		settings.setBounds(100, 232, 300, 50);
		settings.addActionListener((ActionListener) sp);

		add(newQuiz);
		add(viewStatistics);
		add(settings);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
