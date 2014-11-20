package gol.main.controller;

/**
 * This controller is for the moves per second window
 */

import gol.main.model.BoardModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class MovesController implements ActionListener {
	private BoardModel boardModel;
	private JComboBox movesPerSecondCombo;
	private JFrame optionsFrame;
	
	public MovesController(
			JFrame optionsFrame, 
			JComboBox movesPerSecondCombo,
			BoardModel model){
		this.movesPerSecondCombo = movesPerSecondCombo;
		this.optionsFrame = optionsFrame;
		this.boardModel = model;
	}
	
	@Override
    public void actionPerformed(ActionEvent ae) {
		this.boardModel.setMovesPerSecond(
				(int)movesPerSecondCombo.getSelectedItem());
        optionsFrame.dispose();
    }
}
