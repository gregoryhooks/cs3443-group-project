package gol.main.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class BoardModel {
	
	private static final int BLOCK_SIZE = 10;
	
	private int movesPerSecond = 3;
	
	private int gameBoard_Width = 0;
	
	private int gameBoard_Height = 0;
	
	private ArrayList<Point> points;
	
	public BoardModel(int height, int width){
		this.points = new ArrayList<Point>(0);
		this.gameBoard_Height = height;
		this.gameBoard_Width = width;
	}
	
	public void setMovesPerSecond(int movesPerSec){
		this.movesPerSecond = movesPerSec;
	}
	
	public int getMovesPerSecond(){
		return this.movesPerSecond;
	}
	
	public void setGameBoardHeight(int height){
		this.gameBoard_Height = height;
	}
	
	public void setGameBoardWidth(int width){
		this.gameBoard_Width = width;
	}
	
	public int getGameBoardHeight(){
		return this.gameBoard_Height;
	}
	
	public int getGameBoardWidth(){
		return this.gameBoard_Width;
	}
	
	public void updateArraySize() { 
        ArrayList<Point> removeList = new ArrayList<Point>(0);
        for (Point current : points) {
            if ((current.x > this.gameBoard_Width-1) 
            		|| (current.y > this.gameBoard_Height-1)) {
                removeList.add(current);
            }
        }
        points.removeAll(removeList);
    }
	
	public void addPoint(int x, int y) {
		Point temp = new Point(x, y);
        if (!points.contains(temp)) {
            points.add(temp);
        }         
    }
	
    public void removePoint(int x, int y) { 
        points.remove(new Point(x,y));
    }
    
    public ArrayList<Point> getPoints(){
    	return this.points;
    }

    public void resetBoard() { 
        points.clear();
    }

    public void randomlyFillBoard(int percent) { 
    	Random test = new java.util.Random();
        for (int i=0; i < this.gameBoard_Width; i++) {
            for (int j=0; j < this.gameBoard_Height; j++) {
                if (test.nextInt(101) < percent) {
                	// this method is used when 'points' has been cleared,
                	// so assume adding to empty list to save performance.
                    points.add(new Point(i, j));
                }
            }
        }
    }
    
    public void updateModel(){
    	boolean[][] gameBoard = 
    			new boolean[ this.gameBoard_Width + 2 ]
    					   [ this.gameBoard_Height + 2 ];
    	for (Point current : points) {
    		gameBoard[(current.x+1)][(current.y+1)] = true;
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
        points.addAll(survivingCells);
    }
    
    public static int getBlockSize() { // model function
		return BLOCK_SIZE;
	}
}
