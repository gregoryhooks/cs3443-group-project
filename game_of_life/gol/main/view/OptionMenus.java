package gol.main.view;

import gol.main.controller.MovesController;
import gol.main.controller.PercentController;
import gol.main.model.BoardModel;

import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionMenus extends JPanel {
	
	private MainView mainView;
	
	private BoardModel boardModel;
	
	public OptionMenus(MainView view
			, BoardModel model){
		this.mainView = view;
		this.boardModel = model;
	}
	
	public void showOptions() {
		// Put up an options panel to change the number of moves per second
        final JFrame optionsFrame = new JFrame();
        optionsFrame.setTitle("Options");
        optionsFrame.setSize(300,60);
        optionsFrame.setLocation(
        	(Toolkit.getDefaultToolkit().getScreenSize().width 
        			- optionsFrame.getWidth())/2, 
            (Toolkit.getDefaultToolkit().getScreenSize().height 
            		- optionsFrame.getHeight())/2);
        optionsFrame.setResizable(false);
        JPanel p_options = new JPanel();
        p_options.setOpaque(false);
        optionsFrame.add(p_options);
        p_options.add(new JLabel("Number of moves per second:"));
        Integer[] secondOptions = {1,2,3,4,5,10,15,20};
        final JComboBox<?> movesPerSecondCombo = new JComboBox(secondOptions);
        p_options.add(movesPerSecondCombo);
        movesPerSecondCombo.setSelectedItem(
        		this.boardModel.getMovesPerSecond());
        MovesController mController = 
        		new MovesController(optionsFrame, 
        				movesPerSecondCombo,
        				this.boardModel);
        registerMoves(movesPerSecondCombo, mController);
        optionsFrame.setVisible(true);
	}
	
	public void showAutoFill(){
		// Put up an options panel to auto fill the board
		final JFrame autoFillFrame = new JFrame();
        autoFillFrame.setTitle("Autofill");
        autoFillFrame.setSize(360, 60);
        autoFillFrame.setLocation(
        	(Toolkit.getDefaultToolkit().getScreenSize().width 
        			- autoFillFrame.getWidth())/2, 
            (Toolkit.getDefaultToolkit().getScreenSize().height 
            		- autoFillFrame.getHeight())/2);
        autoFillFrame.setResizable(false);
        JPanel autoFillPanel = new JPanel();
        autoFillPanel.setOpaque(false);
        autoFillFrame.add(autoFillPanel);
        autoFillPanel.add(new JLabel("What percentage should be filled? "));
        Object[] percentageOptions = 
        	{"Select",5,10,15,20,25,30,40,50,60,70,80,90,95};
        final JComboBox percentCombo = new JComboBox(percentageOptions);
        autoFillPanel.add(percentCombo);
        PercentController pController = 
        		new PercentController(
        				autoFillFrame, 
        				percentCombo, 
        				this.mainView,
        				this.boardModel);
        registerPercent(percentCombo, pController);
        autoFillFrame.setVisible(true);
	}
	
	public void registerPercent(JComboBox percentCombo, 
			PercentController percentController) {
		percentCombo.addActionListener(percentController);
	}
	
	public void registerMoves(JComboBox percentCombo, 
			MovesController movesController) {
		percentCombo.addActionListener(movesController);
	}
}
