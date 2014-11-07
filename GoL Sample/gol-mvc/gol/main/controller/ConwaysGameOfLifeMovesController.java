package gol.main.controller;

/**
 * This controller is for the moves per second window
 */

import gol.main.view.ConwaysGameOfLifeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class ConwaysGameOfLifeMovesController implements ActionListener {
	
	private ConwaysGameOfLifeView view;
	private JComboBox cb_seconds;
	private JFrame f_options;
	
	public ConwaysGameOfLifeMovesController(JFrame f_options, JComboBox cb_seconds, ConwaysGameOfLifeView view){
		this.view = view;
		this.cb_seconds = cb_seconds;
		this.f_options = f_options;
	}
	
	@Override
    public void actionPerformed(ActionEvent ae) {
        view.gb_gameBoard.i_movesPerSecond = (Integer)cb_seconds.getSelectedItem();
        f_options.dispose();
    }
}
