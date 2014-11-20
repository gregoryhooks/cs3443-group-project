package gol.main;

import gol.main.controller.PrimaryController;
import gol.main.model.BoardModel;
import gol.main.view.GameBoardView;
import gol.main.view.OptionMenus;
import gol.main.view.MainView;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class ProgramStart {
	
    private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(800, 600);
    private static final Dimension MINIMUM_WINDOW_SIZE = new Dimension(400, 400);
	
	public static void main(String[] args) {
        // Setup the swing specifics
        //JFrame game = new ConwaysGameOfLife();
		
		BoardModel model = new BoardModel(DEFAULT_WINDOW_SIZE.height
				, DEFAULT_WINDOW_SIZE.width);
		
		MainView view = new MainView();
        
		OptionMenus menus = 
				new OptionMenus(view, model);
		
		GameBoardView board = 
				new GameBoardView(view, model);
		
		view.addGameBoard(board);
        
        PrimaryController controller = 
        		new PrimaryController(
	        		board, 
	        		view, 
	        		menus,
	        		model);
        
        view.register(controller);
		
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Conway's Game of Life");
        //game.setIconImage(new ImageIcon(ConwaysGameOfLife.class.getResource("/images/logo.png")).getImage());
        view.setSize(DEFAULT_WINDOW_SIZE);
        view.setMinimumSize(MINIMUM_WINDOW_SIZE);
        view.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - board.getWidth())/2, 
                (Toolkit.getDefaultToolkit().getScreenSize().height - board.getHeight())/2);
        view.setVisible(true);
    }
}
