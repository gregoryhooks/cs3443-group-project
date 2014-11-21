package gol.main.model;

public class BoardConfigModel {
	
	static final int BLOCK_SIZE = 10;
	
	int movesPerSecond = 3;
	
	int gameBoard_Width = 0;
	
	int gameBoard_Height = 0;

	public BoardConfigModel(int gameBoard_Width, int gameBoard_Height) {
		this.gameBoard_Height = gameBoard_Height;
		this.gameBoard_Width = gameBoard_Width;
	}

}
