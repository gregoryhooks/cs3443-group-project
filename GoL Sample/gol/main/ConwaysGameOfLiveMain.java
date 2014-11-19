package gol.main;

import gol.main.controller.ConwaysGameOfLifeController;
import gol.main.model.BoardModel;
import gol.main.view.ConwaysGameOfLifeOptionMenus;
import gol.main.view.ConwaysGameOfLifeView;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class ConwaysGameOfLiveMain {
	
    private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(800, 600);
    private static final Dimension MINIMUM_WINDOW_SIZE = new Dimension(400, 400);
	
	public static void main(String[] args) {
        // Setup the swing specifics
        //JFrame game = new ConwaysGameOfLife();
		
		BoardModel model = new BoardModel(DEFAULT_WINDOW_SIZE.height
				, DEFAULT_WINDOW_SIZE.width);
		
		ConwaysGameOfLifeView view = new ConwaysGameOfLifeView();
        
		ConwaysGameOfLifeOptionMenus menus = new ConwaysGameOfLifeOptionMenus(view);
        
        ConwaysGameOfLifeController controller = new ConwaysGameOfLifeController(
        		view.gb_gameBoard, 
        		view, 
        		menus,
        		model);
        
        view.register(controller);
		
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Conway's Game of Life");
        //game.setIconImage(new ImageIcon(ConwaysGameOfLife.class.getResource("/images/logo.png")).getImage());
        view.setSize(DEFAULT_WINDOW_SIZE);
        view.setMinimumSize(MINIMUM_WINDOW_SIZE);
        view.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - view.gb_gameBoard.getWidth())/2, 
                (Toolkit.getDefaultToolkit().getScreenSize().height - view.gb_gameBoard.getHeight())/2);
        view.setVisible(true);
    }
}
