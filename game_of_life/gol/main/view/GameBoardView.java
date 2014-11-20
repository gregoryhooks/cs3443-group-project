package gol.main.view;

import gol.main.model.BoardModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ConcurrentModificationException;

import javax.swing.JPanel;

public class GameBoardView extends JPanel {
	
	private BoardModel model;
	
	private MainView view;
	
    public Dimension gameBoardSize = null;

    public GameBoardView(MainView view, BoardModel model) {
    	this.view = view;
    	this.model = model;
    }

    @Override
    public void paintComponent(Graphics g) { //view function
        super.paintComponent(g); 
        try {
            for (Point newPoint : this.model.getPoints()) {
                // Draw new point
                g.setColor(Color.blue);
                g.fillRect(
                		BoardModel.getBlockSize() 
                			+ (BoardModel.getBlockSize() * newPoint.x), 
                		BoardModel.getBlockSize() 
                			+ (BoardModel.getBlockSize() * newPoint.y), 
                		BoardModel.getBlockSize(), 
                		BoardModel.getBlockSize());
            }
        } catch (ConcurrentModificationException cme) {}
        // Setup grid
        g.setColor(Color.BLACK);
        for (int i = 0; i <= this.model.getGameBoardWidth(); i++) {
    		g.drawLine(
    				((i * BoardModel.getBlockSize()) 
    					+ BoardModel.getBlockSize()), 
    				BoardModel.getBlockSize(), 
    				(i * BoardModel.getBlockSize()) 
    					+ BoardModel.getBlockSize(), 
    				BoardModel.getBlockSize() 
    					+ (BoardModel.getBlockSize() 
    					* this.model.getGameBoardHeight()));
    	}
    	for (int i = 0; i <= this.model.getGameBoardHeight(); i++) {
    		g.drawLine(
    				BoardModel.getBlockSize(), 
    				((i * BoardModel.getBlockSize()) 
    					+ BoardModel.getBlockSize()), 
    				BoardModel.getBlockSize() 
    					* (this.model.getGameBoardWidth() + 1), 
    				((i * BoardModel.getBlockSize()) 
    					+ BoardModel.getBlockSize()));
    	}
    }
    
    public void updateGameBoard(){
    	repaint();
    }
}
