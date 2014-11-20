package gol.main.controller;

import gol.main.model.BoardModel;
import gol.main.view.MainView;

/**
 * This controller is used for the auto fill window
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class PercentController implements ActionListener {
	
	private MainView view;
	private JComboBox cb_percent;
	private JFrame f_autoFill;
	private BoardModel model;
	
	public PercentController(
			JFrame f_autoFill, 
			JComboBox cb_percent, 
			MainView view,
			BoardModel model){
		this.view = view;
		this.cb_percent = cb_percent;
		this.f_autoFill = f_autoFill;
		this.model = model;
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        if (cb_percent.getSelectedIndex() > 0) {
        	this.model.resetBoard();
        	this.model.randomlyFillBoard((Integer)cb_percent.getSelectedItem());
        	this.view.updateGameBoard();
        	f_autoFill.dispose();
        }
    }
}