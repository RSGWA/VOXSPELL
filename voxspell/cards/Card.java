package voxspell.cards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import voxspell.Voxspell;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public abstract class Card extends JPanel implements ActionListener {
	protected Voxspell vox;
	protected JButton menuButton;
	
	public Card(Voxspell vox, String str) {
		this.vox = vox;
	
		setLayout(null);
		menuButton = new JButton("");
		menuButton.setIcon(new ImageIcon("/home/rsgwa/206/VOXSPELL/backButtonTwo.png"));
		menuButton.setContentAreaFilled(false);
		menuButton.setBounds(12, 12, 48, 48);
		menuButton.addActionListener(this);
		
		add(menuButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuButton) {
			vox.returnToMenu();
		}
	}

}
