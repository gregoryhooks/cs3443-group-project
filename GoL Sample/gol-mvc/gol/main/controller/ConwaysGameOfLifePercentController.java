package gol.main.controller;

import gol.main.view.ConwaysGameOfLifeView;

/**
 * This controller is used for the auto fill window
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class ConwaysGameOfLifePercentController implements ActionListener {
	
	private ConwaysGameOfLifeView view;
	private JComboBox cb_percent;
	private JFrame f_autoFill;
	
	public ConwaysGameOfLifePercentController(JFrame f_autoFill, JComboBox cb_percent, ConwaysGameOfLifeView view){
		this.view = view;
		this.cb_percent = cb_percent;
		this.f_autoFill = f_autoFill;
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        if (cb_percent.getSelectedIndex() > 0) {
            view.gb_gameBoard.resetBoard();
            view.gb_gameBoard.randomlyFillBoard((Integer)cb_percent.getSelectedItem());
            f_autoFill.dispose();
        }
    }
}
