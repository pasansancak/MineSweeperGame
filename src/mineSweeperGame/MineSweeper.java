package mineSweeperGame;

import java.awt.Dimension;  
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class MineSweeper {
	private static final int NUM_MINES = 100;
	private static final int SIZE = 20;
	private static int flagCount=0;
	
	
	public static void main (String[] args) throws InterruptedException {
		
		JFrame frame = new JFrame ("Minesweeper");
		frame.add(new MineSweeperGUI(SIZE,SIZE,NUM_MINES));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(510,600);
		frame.setMinimumSize(new Dimension(510,600));
		frame.setMaximumSize(new Dimension(610,720));
		
		frame.setVisible(true);
		
		int a=0;
		
		while(MineGrid.getStop()!=-1 && a!=1000) {
			MineSweeperGUI.setTimeLabel(a);
			a++;
			TimeUnit.SECONDS.sleep(1);
				
		}
	}
	
	protected static void increaseFlagCount() {
		flagCount++;
	}
	protected static int getFlagCount() {
		return flagCount;
	}
	protected static int getNumMines() {
		return NUM_MINES;
	}

	protected static void decreaseFlagCount() {
		flagCount--;
	}
}
