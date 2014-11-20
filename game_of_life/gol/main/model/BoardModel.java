package gol.main.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class BoardModel {
	
	private ArrayList<Point> points;
	
	private BoardConfigModel boardConfigModel;
	
	public BoardModel(int height, int width){
		this.points = new ArrayList<Point>(0);
		this.boardConfigModel = new BoardConfigModel(width, height);
	}
	
	public void setMovesPerSecond(int movesPerSec){
		this.boardConfigModel.movesPerSecond = movesPerSec;
	}
	
	public int getMovesPerSecond(){
		return this.boardConfigModel.movesPerSecond;
	}
	
	public void setGameBoardHeight(int height){
		this.boardConfigModel.gameBoard_Height = height;
	}
	
	public void setGameBoardWidth(int width){
		this.boardConfigModel.gameBoard_Width = width;
	}
	
	public int getGameBoardHeight(){
		return this.boardConfigModel.gameBoard_Height;
	}
	
	public int getGameBoardWidth(){
		return this.boardConfigModel.gameBoard_Width;
	}
	
	public void updateArraySize() { 
        ArrayList<Point> removeList = new ArrayList<Point>(0);
        for (Point current : points) {
            if ((current.x > this.getGameBoardWidth() - 1) 
            		|| (current.y > this.getGameBoardHeight() - 1)) {
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
        for (int i=0; i < this.getGameBoardWidth(); i++) {
            for (int j=0; j < this.getGameBoardHeight(); j++) {
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
    			new boolean[ this.getGameBoardWidth() + 2 ]
    					   [ this.getGameBoardHeight() + 2 ];
    	for (Point current : points) {
    		gameBoard[(current.x+1)][(current.y+1)] = true;
        }
        ArrayList<Point> survivingCells = new ArrayList<Point>(0);
        // Iterate through the array, follow game of life rules
        for (int i=1; i < gameBoard.length - 1; i++) {
            for (int j=1; j < gameBoard[0].length - 1; j++) {
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
		return BoardConfigModel.BLOCK_SIZE;
	}
}
