package gol.main.controller;
import gol.main.model.BoardModel;
import gol.main.view.GameBoardView;
import gol.main.view.OptionMenus;
import gol.main.view.MainView;

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

public class PrimaryController implements ActionListener, ComponentListener, MouseListener, MouseMotionListener, Runnable {
	private BoardModel boardModel;
	private GameBoardView gameBoardView;
	private MainView mainView;
	private OptionMenus optionMenus;
	
	private Thread gameThread;

	public PrimaryController(GameBoardView gameBoard, 
			MainView view, 
			OptionMenus menus,
			BoardModel model){
		gameBoard.addComponentListener(this);
        gameBoard.addMouseListener(this);
        gameBoard.addMouseMotionListener(this);
        this.gameBoardView = gameBoard;
		this.mainView = view;
		this.optionMenus = menus;
		this.boardModel = model;
	}
	
    @Override
    //Controller
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(mainView.menuFile_Exit)) {
            // Exit the game
            System.exit(0);
        } else if (ae.getSource().equals(mainView.menuFile_Options)) {
        	optionMenus.showOptions();
        } else if (ae.getSource().equals(mainView.menuGame_Autofill)) {
        	optionMenus.showAutoFill();
        } else if (ae.getSource().equals(mainView.menuGame_Reset)) {
            this.boardModel.resetBoard();
            this.gameBoardView.repaint();
        } else if (ae.getSource().equals(mainView.menuGame_Play)) {
            setGameBeingPlayed(true);
        } else if (ae.getSource().equals(mainView.menuGame_Stop)) {
            setGameBeingPlayed(false);
        } else if (ae.getSource().equals(mainView.menuHelp_Source)) {
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            try {
                desktop.browse(new URI("https://github.com/Burke9077/Conway-s-Game-of-Life"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Source is available on GitHub at:\nhttps://github.com/Burke9077/Conway-s-Game-of-Life", "Source", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (ae.getSource().equals(mainView.menuHelp_About)) {
            JOptionPane.showMessageDialog(null, "Conway's game of life was a cellular animation devised by the mathematician John Conway.\nThis Java, swing based implementation was created by Matthew Burke.\n\nhttp://burke9077.com\nBurke9077@gmail.com\n@burke9077\n\nCreative Commons Attribution 4.0 International");
        }
    }
    
    public void setGameBeingPlayed(boolean isBeingPlayed) {
        if (isBeingPlayed) {
            mainView.menuGame_Play.setEnabled(false);
            mainView.menuGame_Stop.setEnabled(true);
            this.gameThread = new Thread(this);
            this.gameThread.start();
        } else {
            mainView.menuGame_Play.setEnabled(true);
            mainView.menuGame_Stop.setEnabled(false);
            this.gameThread.interrupt();
        }
    }
    
    @Override
    public void componentResized(ComponentEvent e) {
        // Setup the game board size with proper boundaries
        gameBoardView.gameBoardSize = new Dimension(
        		gameBoardView.getWidth() / BoardModel.getBlockSize() - 2, 
        		gameBoardView.getHeight() / BoardModel.getBlockSize() - 2);
        this.boardModel.setGameBoardHeight(gameBoardView.getHeight());
        this.boardModel.setGameBoardWidth(gameBoardView.getWidth());
        this.boardModel.updateArraySize();
    }
    
    @Override
    public void componentMoved(ComponentEvent e) {}
    
    @Override
    public void componentShown(ComponentEvent e) {}
    
    @Override
    public void componentHidden(ComponentEvent e) {}
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // Mouse was clicked
        this.addPointClick(e);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {}
    
    @Override
    public void mouseReleased(MouseEvent e) {}
    
    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        // Mouse is being dragged, user wants multiple selections
        this.addPointDrag(e);
    }
    @Override
    public void mouseMoved(MouseEvent e) {}

	@Override
	public void run() {
		while(true){
			this.boardModel.updateModel();
			this.gameBoardView.repaint();
			try {
				Thread.sleep(1000/this.boardModel.getMovesPerSecond());
				
			} catch( InterruptedException iex){
				break;
			}
		}
	}
	
    public void addPointClick(MouseEvent me) { 
        int x = me.getPoint().x/BoardModel.getBlockSize()-1;
        int y = me.getPoint().y/BoardModel.getBlockSize()-1;
        if ((x >= 0) && (x < boardModel.getGameBoardWidth()) 
        		&& (y >= 0) && (y < boardModel.getGameBoardHeight())) {
            boardModel.addPointClick(x,y);
            mainView.updateGameBoard();
        }
    }
    
    public void addPointDrag(MouseEvent me) { 
        int x = me.getPoint().x/BoardModel.getBlockSize()-1;
        int y = me.getPoint().y/BoardModel.getBlockSize()-1;
        if ((x >= 0) && (x < boardModel.getGameBoardWidth()) 
        		&& (y >= 0) && (y < boardModel.getGameBoardHeight())) {
            boardModel.addPointDrag(x,y);
            mainView.updateGameBoard();
        }
    }
}
