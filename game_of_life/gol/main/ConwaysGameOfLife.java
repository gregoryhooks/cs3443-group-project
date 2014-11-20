package gol.main;

import gol.main.controller.PrimaryController;
import gol.main.model.BoardModel;
import gol.main.view.GameBoardView;
import gol.main.view.OptionMenus;
import gol.main.view.MainView;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class ConwaysGameOfLife {
	
    private static final Dimension DEFAULT_WINDOW_SIZE = 
    		new Dimension(800, 600);
    private static final Dimension MINIMUM_WINDOW_SIZE = 
    		new Dimension(400, 400);
	
	public static void main(String[] args) {
		BoardModel boardModel = 
				new BoardModel
				(DEFAULT_WINDOW_SIZE.height,
				DEFAULT_WINDOW_SIZE.width);
		
		MainView mainView = new MainView();
        
		OptionMenus optionMenus = 
				new OptionMenus(mainView, boardModel);
		
		GameBoardView gameBoardView = 
				new GameBoardView(mainView, boardModel);
		
		mainView.addGameBoard(gameBoardView);
        
        PrimaryController controller = 
        		new PrimaryController(
	        		gameBoardView, 
	        		mainView, 
	        		optionMenus,
	        		boardModel);
        
        mainView.register(controller);
		
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainView.setTitle("Conway's Game of Life");
        mainView.setSize(DEFAULT_WINDOW_SIZE);
        mainView.setMinimumSize(MINIMUM_WINDOW_SIZE);
        mainView.setLocation(
        	(Toolkit.getDefaultToolkit().getScreenSize().width 
        			- gameBoardView.getWidth())/2, 
            (Toolkit.getDefaultToolkit().getScreenSize().height 
            		- gameBoardView.getHeight())/2);
        mainView.setVisible(true);
    }
}
