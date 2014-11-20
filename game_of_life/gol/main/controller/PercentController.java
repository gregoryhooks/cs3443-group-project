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
	
	private MainView mainView;
	private JComboBox percentCombo;
	private JFrame autoFillFrame;
	private BoardModel model;
	
	public PercentController(
			JFrame autoFillFrame, 
			JComboBox percentCombo, 
			MainView view,
			BoardModel model){
		this.mainView = view;
		this.percentCombo = percentCombo;
		this.autoFillFrame = autoFillFrame;
		this.model = model;
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        if (percentCombo.getSelectedIndex() > 0) {
        	this.model.resetBoard();
        	this.model.randomlyFillBoard((int)percentCombo.getSelectedItem());
        	this.mainView.updateGameBoard();
        	autoFillFrame.dispose();
        }
    }
}
