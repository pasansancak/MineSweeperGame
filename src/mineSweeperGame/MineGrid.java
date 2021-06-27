package mineSweeperGame;

import java.util.Random;

public class MineGrid {
	private int remainingMines;
	private static final int MINE = -1;
	private int[][] gridInfo;
	private static int stop;
	
	
	public MineGrid(int numRows, int numCols, int numMines) {
		
		remainingMines=numMines;
		gridInfo=new int[numRows][numCols];
		initializeCells();
		placeMines(numMines);
		setgridInformation();
		stop=0;
		

	}


	private void setgridInformation() {
		for(int x=0;x<gridInfo.length;x++) {
			for(int y=0;y<gridInfo[0].length;y++) {
				if(gridInfo[x][y]==MINE) {
					
					incrementMineCountAt(x-1,y-1);
					incrementMineCountAt(x-1,y);
					incrementMineCountAt(x-1,y+1);
					
					incrementMineCountAt(x,y-1);
					incrementMineCountAt(x,y+1);
					
					incrementMineCountAt(x+1,y-1);
					incrementMineCountAt(x+1,y);
					incrementMineCountAt(x+1,y+1);
				}
			}
		}
	}

	private void placeMines(int numMines) {
		Random random=new Random();
		for(int x=0;x<numMines;x++) {
			int r=random.nextInt(gridInfo.length);
			int c=random.nextInt(gridInfo[0].length);
			if(gridInfo[r][c]!=MINE)
				gridInfo[r][c]=MINE;
			else
				x--;
		}
	}

	private void initializeCells() {
		
		for(int x=0;x<gridInfo.length;x++) {
			for(int y=0;y<gridInfo[0].length;y++) {
				gridInfo[x][y]=0;
			}
		}
	}
	
	private void incrementMineCountAt(int x,int y) {
		if (isInsideGrid(x,y) && !isMine(x,y))
			gridInfo[x][y]++;
	}
	
	protected boolean isInsideGrid(int x, int y) {
		return (x >= 0 && x <gridInfo.length) && (y >= 0 && y < gridInfo[0].length); 
	}
	
	protected boolean isMine (int x, int y) {
		return gridInfo[x][y]==MINE;
	}
	
	protected int getCellContent (int x, int y) {
		return gridInfo[x][y];
	}
	
	protected void setCellContent (int x, int y,int z) {
		gridInfo[x][y]=z;
	}
	
	protected int[][] getGridInfo(){
		return this.gridInfo;
	}
	protected static int getStop() {
		return stop;
	}
	protected void setStop() {
		stop=-1;
	}
	protected int getRemainingMines() {
		return remainingMines;
	}



}
