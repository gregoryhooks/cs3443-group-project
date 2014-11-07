package gol.main.controller;
import gol.main.view.ConwaysGameOfLifeGameBoard;
import gol.main.view.ConwaysGameOfLifeOptionMenus;
import gol.main.view.ConwaysGameOfLifeView;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URI;

import javax.swing.JOptionPane;

public class ConwaysGameOfLifeController implements ActionListener, ComponentListener, MouseListener, MouseMotionListener {
	
	private ConwaysGameOfLifeGameBoard game;
	private ConwaysGameOfLifeView view;
	private ConwaysGameOfLifeOptionMenus menus;

	public ConwaysGameOfLifeController(ConwaysGameOfLifeGameBoard game, ConwaysGameOfLifeView view, ConwaysGameOfLifeOptionMenus menus){
		game.addComponentListener(this);
        game.addMouseListener(this);
        game.addMouseMotionListener(this);
		this.game = game;
		this.view = view;
		this.menus = menus;
	}
	
    @Override
    //Controller
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(view.mi_file_exit)) {
            // Exit the game
            System.exit(0);
        } else if (ae.getSource().equals(view.mi_file_options)) {
        	menus.showOptions();
        } else if (ae.getSource().equals(view.mi_game_autofill)) {
        	menus.showAutoFill();
        	
        } else if (ae.getSource().equals(view.mi_game_reset)) {
            view.gb_gameBoard.resetBoard();
            view.gb_gameBoard.repaint();
        } else if (ae.getSource().equals(view.mi_game_play)) {
            setGameBeingPlayed(true);
        } else if (ae.getSource().equals(view.mi_game_stop)) {
            setGameBeingPlayed(false);
        } else if (ae.getSource().equals(view.mi_help_source)) {
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            try {
                desktop.browse(new URI("https://github.com/Burke9077/Conway-s-Game-of-Life"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Source is available on GitHub at:\nhttps://github.com/Burke9077/Conway-s-Game-of-Life", "Source", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (ae.getSource().equals(view.mi_help_about)) {
            JOptionPane.showMessageDialog(null, "Conway's game of life was a cellular animation devised by the mathematician John Conway.\nThis Java, swing based implementation was created by Matthew Burke.\n\nhttp://burke9077.com\nBurke9077@gmail.com\n@burke9077\n\nCreative Commons Attribution 4.0 International");
        }
    }
    
    public void setGameBeingPlayed(boolean isBeingPlayed) {
        if (isBeingPlayed) {
            view.mi_game_play.setEnabled(false);
            view.mi_game_stop.setEnabled(true);
            game.game = new Thread(view.gb_gameBoard);
            game.game.start();
        } else {
            view.mi_game_play.setEnabled(true);
            view.mi_game_stop.setEnabled(false);
            game.game.interrupt();
        }
    }
    
    @Override
    public void componentResized(ComponentEvent e) {
        // Setup the game board size with proper boundaries
        game.d_gameBoardSize = new Dimension(game.getWidth()/ConwaysGameOfLifeGameBoard.getBlockSize()-2, game.getHeight()/ConwaysGameOfLifeGameBoard.getBlockSize()-2);
        game.updateArraySize();
    }
    @Override
    public void componentMoved(ComponentEvent e) {}
    @Override
    public void componentShown(ComponentEvent e) {}
    @Override
    public void componentHidden(ComponentEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {
        // Mouse was released (user clicked)
        game.addPoint(e);
    }
    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        // Mouse is being dragged, user wants multiple selections
        game.addPoint(e);
    }
    @Override
    public void mouseMoved(MouseEvent e) {}
}
