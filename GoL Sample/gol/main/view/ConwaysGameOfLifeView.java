package gol.main.view;

import gol.main.controller.ConwaysGameOfLifeController;
import gol.main.view.ConwaysGameOfLifeGameBoard;

import javax.swing.*;

public class ConwaysGameOfLifeView extends JFrame {
	
    public JMenuBar mb_menu;
    public JMenu m_file, m_game, m_help;
    public JMenuItem mi_file_options, mi_file_exit;
    public JMenuItem mi_game_autofill, mi_game_play, mi_game_stop, mi_game_reset;
    public JMenuItem mi_help_about, mi_help_source;
    private ConwaysGameOfLifeGameBoard gb_gameBoard;
	
	public ConwaysGameOfLifeView() {
        // Setup menus
        mb_menu = new JMenuBar();
        setJMenuBar(mb_menu);
        m_file = new JMenu("File");
        mb_menu.add(m_file);
        m_game = new JMenu("Game");
        mb_menu.add(m_game);
        m_help = new JMenu("Help");
        mb_menu.add(m_help);
        mi_file_options = new JMenuItem("Options");
        //mi_file_options.addActionListener(this);
        mi_file_exit = new JMenuItem("Exit");
        //mi_file_exit.addActionListener(this);
        m_file.add(mi_file_options);
        m_file.add(new JSeparator());
        m_file.add(mi_file_exit);
        mi_game_autofill = new JMenuItem("Autofill");
        //mi_game_autofill.addActionListener(this);
        mi_game_play = new JMenuItem("Play");
        //mi_game_play.addActionListener(this);
        mi_game_stop = new JMenuItem("Stop");
        mi_game_stop.setEnabled(false);
        //mi_game_stop.addActionListener(this);
        mi_game_reset = new JMenuItem("Reset");
        //mi_game_reset.addActionListener(this);
        m_game.add(mi_game_autofill);
        m_game.add(new JSeparator());
        m_game.add(mi_game_play);
        m_game.add(mi_game_stop);
        m_game.add(mi_game_reset);
        mi_help_about = new JMenuItem("About");
        //mi_help_about.addActionListener(this);
        mi_help_source = new JMenuItem("Source");
        //mi_help_source.addActionListener(this);
        m_help.add(mi_help_about);
        m_help.add(mi_help_source);
        // Setup game board
        //gb_gameBoard = new ConwaysGameOfLifeGameBoard();
        //add(gb_gameBoard);
    }
	
	public void addGameBoard(ConwaysGameOfLifeGameBoard board){
		this.gb_gameBoard = board;
		add(board);
	}
	
	public void updateGameBoard(){
		this.gb_gameBoard.repaint();
	}
	
	public void register(ConwaysGameOfLifeController controller) {		
		mi_file_options.addActionListener(controller);
		mi_file_exit.addActionListener(controller);
		mi_game_autofill.addActionListener(controller);
		mi_game_play.addActionListener(controller);
		mi_game_stop.addActionListener(controller);
		mi_game_reset.addActionListener(controller);
		mi_help_about.addActionListener(controller);
		mi_help_source.addActionListener(controller);
	}
}
