package gol.main.model;

import java.awt.Point;
import java.util.ArrayList;

public class BoardModel {
	
	private static final int BLOCK_SIZE = 10;
	
	private int i_movesPerSecond = 3;
	
	private int gameBoard_Width = 0;
	
	private int gameBoard_Height = 0;
	
	private ArrayList<Point> point = new ArrayList<Point>(0);
	
	public BoardModel(int height, int width){
		this.gameBoard_Height = height;
		this.gameBoard_Width = width;
	}
	
	public void setMovesPerSecond(int movesPerSec){
		this.i_movesPerSecond = movesPerSec;
	}
	
	public int getMovesPerSecond(){
		return this.i_movesPerSecond;
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
	
	public void updateArraySize() { // funtion would be in model - add connected function to view for repaint()
        ArrayList<Point> removeList = new ArrayList<Point>(0);
        for (Point current : point) {
            if ((current.x > this.gameBoard_Height-1) || (current.y > this.gameBoard_Width-1)) {
                removeList.add(current);
            }
        }
        point.removeAll(removeList);
    }
	
	public void addPoint(int x, int y) { //function in model - repaint handled by view
        if (!point.contains(new Point(x,y))) {
            point.add(new Point(x,y));
        }         
    }
	
    public void removePoint(int x, int y) { //function in mode - repaint handled by view
        point.remove(new Point(x,y));
    }

    public void resetBoard() { //function in model
        point.clear();
    }

    public void randomlyFillBoard(int percent) { //random board fill - we will need to modify this -- all model
        for (int i=0; i < this.gameBoard_Width; i++) {
            for (int j=0; j < this.gameBoard_Width; j++) {
                if (Math.random()*100 < percent) {
                    addPoint(i,j);
                }
            }
        }
    }
    
    public void updateModel(){
    	boolean[][] gameBoard = 
    			new boolean[ this.gameBoard_Width + 2 ]
    					   [ this.gameBoard_Height + 2 ];
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
    }
}
