package gol.main.view;

import gol.main.controller.PrimaryController;
import gol.main.view.GameBoardView;

import javax.swing.*;

public class MainView extends JFrame {
	
    private JMenuBar menuBar;
    
    public JMenu menuFile;
    public JMenu menuGame;
    public JMenu menuHelp;
    
    public JMenuItem menuFile_Options;
    public JMenuItem menuFile_Exit;
    
    public JMenuItem menuGame_Autofill;
    public JMenuItem menuGame_Play;
    public JMenuItem menuGame_Stop;
    public JMenuItem menuGame_Reset;
    
    public JMenuItem menuHelp_About;
    public JMenuItem menuHelp_Source;
    
    private GameBoardView gameBoardView;
	
	public MainView() {
        // Setup menus
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        // Create and add menus to menuBar.
        menuFile = new JMenu("File");
        menuGame = new JMenu("Game");
        menuHelp = new JMenu("Help");
        menuBar.add(menuFile);
        menuBar.add(menuGame);
        menuBar.add(menuHelp);
        
        // Create and add menu items to menuFile.
        menuFile_Options = new JMenuItem("Options");
        menuFile_Exit = new JMenuItem("Exit");
        menuFile.add(menuFile_Options);
        menuFile.add(new JSeparator());
        menuFile.add(menuFile_Exit);
        
        // Create and add menu items to menuGame.
        menuGame_Autofill = new JMenuItem("Autofill");
        menuGame_Play = new JMenuItem("Play");
        menuGame_Stop = new JMenuItem("Stop");
        menuGame_Stop.setEnabled(false);
        menuGame_Reset = new JMenuItem("Reset");
        menuGame.add(menuGame_Autofill);
        menuGame.add(new JSeparator());
        menuGame.add(menuGame_Play);
        menuGame.add(menuGame_Stop);
        menuGame.add(menuGame_Reset);
        
        // Create and add menu items to menuHelp.
        menuHelp_About = new JMenuItem("About");
        menuHelp_Source = new JMenuItem("Source");
        menuHelp.add(menuHelp_About);
        menuHelp.add(menuHelp_Source);
    }
	
	public void addGameBoard(GameBoardView board){
		this.gameBoardView = board;
		add(board);
	}
	
	public void updateGameBoard(){
		this.gameBoardView.repaint();
	}
	
	public void register(PrimaryController controller) {		
		menuFile_Options.addActionListener(controller);
		menuFile_Exit.addActionListener(controller);
		menuGame_Autofill.addActionListener(controller);
		menuGame_Play.addActionListener(controller);
		menuGame_Stop.addActionListener(controller);
		menuGame_Reset.addActionListener(controller);
		menuHelp_About.addActionListener(controller);
		menuHelp_Source.addActionListener(controller);
	}
}
