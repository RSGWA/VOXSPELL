package voxspell.cards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import voxspell.Voxspell;

@SuppressWarnings("serial")
public class StageSelect extends Card implements ActionListener{

	public StageSelect(Voxspell sp) {
		super(sp, "");
		
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// Checks if return to menu button has been pressed
		super.actionPerformed(e);
		
	}
	
}
