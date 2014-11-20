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
	
	private MainView view;
	
	private BoardModel model;
	
	public OptionMenus(MainView view
			, BoardModel model){
		this.view = view;
		this.model = model;
	}
	
	public void showOptions() {
		// Put up an options panel to change the number of moves per second
        final JFrame f_options = new JFrame();
        f_options.setTitle("Options");
        f_options.setSize(300,60);
        f_options.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - f_options.getWidth())/2, 
            (Toolkit.getDefaultToolkit().getScreenSize().height - f_options.getHeight())/2);
        f_options.setResizable(false);
        JPanel p_options = new JPanel();
        p_options.setOpaque(false);
        f_options.add(p_options);
        p_options.add(new JLabel("Number of moves per second:"));
        Integer[] secondOptions = {1,2,3,4,5,10,15,20};
        final JComboBox cb_seconds = new JComboBox(secondOptions);
        p_options.add(cb_seconds);
        // TODO:
        /* This should probably be set in the main method initially and then retrieved by the controller.*/
        //cb_seconds.setSelectedItem(view.gb_gameBoard.i_movesPerSecond);
        cb_seconds.setSelectedItem(this.model.getMovesPerSecond());
        //cb_seconds.addActionListener(mController);
        // TODO:
        /* You probably don't want to initialize a controller within another controller.*/
        MovesController mController = 
        		new MovesController(f_options, 
        				cb_seconds, 
        				this.view,
        				this.model);
        registerMoves(cb_seconds, mController);
        f_options.setVisible(true);
	}
	
	public void showAutoFill(){
		// Put up an options panel to auto fill the board
		final JFrame f_autoFill = new JFrame();
        f_autoFill.setTitle("Autofill");
        f_autoFill.setSize(360, 60);
        f_autoFill.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - f_autoFill.getWidth())/2, 
            (Toolkit.getDefaultToolkit().getScreenSize().height - f_autoFill.getHeight())/2);
        f_autoFill.setResizable(false);
        JPanel p_autoFill = new JPanel();
        p_autoFill.setOpaque(false);
        f_autoFill.add(p_autoFill);
        p_autoFill.add(new JLabel("What percentage should be filled? "));
        Object[] percentageOptions = {"Select",5,10,15,20,25,30,40,50,60,70,80,90,95};
        final JComboBox cb_percent = new JComboBox(percentageOptions);
        p_autoFill.add(cb_percent);
        //cb_percent.addActionListener(pController);
        PercentController pController = 
        		new PercentController(
        				f_autoFill, 
        				cb_percent, 
        				this.view,
        				this.model);
        registerPercent(cb_percent, pController);
        f_autoFill.setVisible(true);
	}
	
	public void registerPercent(JComboBox cb_percent, PercentController controller) {
		cb_percent.addActionListener(controller);
	}
	
	public void registerMoves(JComboBox cb_seconds, MovesController controller) {
		cb_seconds.addActionListener(controller);
	}
}
