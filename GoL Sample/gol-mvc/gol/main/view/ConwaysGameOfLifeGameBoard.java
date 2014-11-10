package gol.main.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.JPanel;

public class ConwaysGameOfLifeGameBoard extends JPanel implements Runnable {
	
	private static final int BLOCK_SIZE = 10; //View
	
    public int i_movesPerSecond = 3; //model

    public Thread game; //model
	
    public Dimension d_gameBoardSize = null; //model - but may need to duplicate for View(or add getter)
    private ArrayList<Point> point = new ArrayList<Point>(0); //model

    //public ConwaysGameOfLifeGameBoard(ConwaysGameOfLifeView view) {}

    public void updateArraySize() { // funtion would be in model - add connected function to view for repaint()
        ArrayList<Point> removeList = new ArrayList<Point>(0);
        for (Point current : point) {
            if ((current.x > d_gameBoardSize.width-1) || (current.y > d_gameBoardSize.height-1)) {
                removeList.add(current);
            }
        }
        point.removeAll(removeList);
        repaint();
    }

    public void addPoint(int x, int y) { //function in model - repaint handled by view
        if (!point.contains(new Point(x,y))) {
            point.add(new Point(x,y));
        } 
        repaint();
    }

    public void addPoint(MouseEvent me) { //function in model - repaint handled by view
        int x = me.getPoint().x/10-1;
        int y = me.getPoint().y/10-1;
        if ((x >= 0) && (x < d_gameBoardSize.width) && (y >= 0) && (y < d_gameBoardSize.height)) {
            addPoint(x,y);
        }
    }

    public void removePoint(int x, int y) { //function in mode - repaint handled by view
        point.remove(new Point(x,y));
    }

    public void resetBoard() { //function in model
        point.clear();
    }

    public void randomlyFillBoard(int percent) { //random board fill - we will need to modify this -- all model
        for (int i=0; i<d_gameBoardSize.width; i++) {
            for (int j=0; j<d_gameBoardSize.height; j++) {
                if (Math.random()*100 < percent) {
                    addPoint(i,j);
                }
            }
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
        boolean[][] gameBoard = new boolean[d_gameBoardSize.width+2][d_gameBoardSize.height+2];
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
        point.addAll(survivingCells);
        repaint();
        try {
            Thread.sleep(1000/i_movesPerSecond);
            run();
        } catch (InterruptedException ex) {}
    }

	public static int getBlockSize() { // model function
		return BLOCK_SIZE;
	}
}
