package gol.main.view;

import gol.main.model.BoardModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.JPanel;

public class ConwaysGameOfLifeGameBoard extends JPanel implements Runnable {
	
	private BoardModel model;
	
	private static final int BLOCK_SIZE = 10; //View

    public Thread game; //model <- This is a thread and shouldn't exist in the model
	
    public Dimension d_gameBoardSize = null; //model - but may need to duplicate for View(or add getter)
    private ArrayList<Point> point = new ArrayList<Point>(0); //model

    //public ConwaysGameOfLifeGameBoard(ConwaysGameOfLifeView view) {}

    /* This is an event driven function and calls the model function addPoint(int, int). This is
     * a controller method that will feed information to the model.*/
    public void addPoint(MouseEvent me) { //function in model - repaint handled by view
        int x = me.getPoint().x/10-1;
        int y = me.getPoint().y/10-1;
        if ((x >= 0) && (x < d_gameBoardSize.width) && (y >= 0) && (y < d_gameBoardSize.height)) {
            model.addPoint(x,y);
        }
    }


    @Override
    public void paintComponent(Graphics g) { //view function
        super.paintComponent(g); 
        try {
            for (Point newPoint : point) {
                // Draw new point
                g.setColor(Color.blue);
                g.fillRect(getBlockSize() + (getBlockSize()*newPoint.x), getBlockSize() + (getBlockSize()*newPoint.y), getBlockSize(), getBlockSize());
            }
        } catch (ConcurrentModificationException cme) {}
        // Setup grid
        g.setColor(Color.BLACK);
        if(d_gameBoardSize != null){ //Prevents null exception error at game start
        	for (int i=0; i<=d_gameBoardSize.width; i++) {
        		g.drawLine(((i*getBlockSize())+getBlockSize()), getBlockSize(), (i*getBlockSize())+getBlockSize(), getBlockSize() + (getBlockSize()*d_gameBoardSize.height));
        	}
        	for (int i=0; i<=d_gameBoardSize.height; i++) {
        		g.drawLine(getBlockSize(), ((i*getBlockSize())+getBlockSize()), getBlockSize()*(d_gameBoardSize.width+1), ((i*getBlockSize())+getBlockSize()));
        	}
        }
    }
    
    @Override
    public void run() { //model function - would need to move repainting out of it
    	/* This is an inherited method from Runnable. The model should not control the
    	 * time between 'ticks' in the game, just recreate and edit what should be displayed
    	 * and then return that to the view.*/
    	// moved below component to model
        /*boolean[][] gameBoard = new boolean[d_gameBoardSize.width+2][d_gameBoardSize.height+2];
        for (Point current : point) {
            gameBoard[current.x+1][current.y+1] = true;
        }
        ArrayList<Point> survivingCells = new ArrayList<Point>(0);
        // Iterate through the array, follow game of life rules
        for (int i=1; i<gameBoard.length-1; i++) {
            for (int j=1; j<gameBoard[0].length-1; j++) {
                int surrounding = 0;
                if (gameBoard[i-1][j-1]) { surrounding++; }
                if (gameBoard[i-1][j])   { surrounding++; }
                if (gameBoard[i-1][j+1]) { surrounding++; }
                if (gameBoard[i][j-1])   { surrounding++; }
                if (gameBoard[i][j+1])   { surrounding++; }
                if (gameBoard[i+1][j-1]) { surrounding++; }
                if (gameBoard[i+1][j])   { surrounding++; }
                if (gameBoard[i+1][j+1]) { surrounding++; }
                if (gameBoard[i][j]) {
                    // Cell is alive, Can the cell live? (2-3)
                    if ((surrounding == 2) || (surrounding == 3)) {
                        survivingCells.add(new Point(i-1,j-1));
                    } 
                } else {
                    // Cell is dead, will the cell be given birth? (3)
                    if (surrounding == 3) {
                        survivingCells.add(new Point(i-1,j-1));
                    }
                }
            }
        }
        resetBoard();
        point.addAll(survivingCells);*/
        repaint();
        try {
            Thread.sleep(1000/model.getMovesPerSecond());
            run();
        } catch (InterruptedException ex) {}
    }

	public static int getBlockSize() { // model function
		return BLOCK_SIZE;
	}	
}
