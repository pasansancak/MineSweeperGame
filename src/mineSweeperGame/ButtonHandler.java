package mineSweeperGame;

import java.awt.event.MouseEvent;  
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class ButtonHandler extends MouseAdapter {
	private int row, col;
	private int goal=MineSweeper.getNumMines();
	private MineGrid grid;
	private MineSweeperGUI gui;
	
	
	public ButtonHandler (int r, int c, MineGrid g,MineSweeperGUI g2) {
		row=r;
		col=c;
		grid=g;
		gui=g2;
	}
	
	public ButtonHandler (MineGrid g,MineSweeperGUI g2) {
		grid=g;
		gui=g2;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(grid.getStop()==0) {
				
				if(!grid.isMine(row, col)) {
					revealMineFreeGrids(row,col);
				}
				else if(grid.isMine(row, col) && gui.getButtons(row, col).isEnabled()) {
					
					JButton button = (JButton)e.getSource();
					if(button.isEnabled())
						button.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("images/mine_click.png")));
					button.setEnabled(false);
					
					revealMines();
					gui.setEmoji("images/facelose.png");
					grid.setStop();
				}
			}
			
			if(e.getComponent()==gui.getEmoji()) {
				System.exit(0);
			}
			
			
		}
		if(e.getButton() == MouseEvent.BUTTON3) {
			if(grid.getStop()==0) {
				if(gui.getButtons(row,col).isEnabled()){
					gui.setButton(row,col,"images/flag.png");
					MineSweeper.increaseFlagCount();
					gui.setBombLabel(MineSweeper.getNumMines()-MineSweeper.getFlagCount());
					gui.getButtons(row, col).setEnabled(false);
					if(grid.isMine(row, col)) {
						goal--;
					}
					if(goal==0) {
						JOptionPane.showMessageDialog(null, "You’re a genius!");
						System.exit(0);
					}
				}
				else if(!gui.getButtons(row,col).isEnabled()){
					gui.setButton(row,col,"images/blank.png");
					if(MineSweeper.getFlagCount()>0)
						MineSweeper.decreaseFlagCount();
					gui.setBombLabel(MineSweeper.getNumMines()-MineSweeper.getFlagCount());
					gui.getButtons(row, col).setEnabled(true);
				}
			}
		}
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		if(grid.getStop()==0)
			gui.setEmoji("images/facesurprised.png");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(grid.getStop()==0)
			gui.setEmoji("images/facesmile.png");
	}

	private void revealMineFreeGrids(int x, int y) {
		
		if( grid.isInsideGrid(x,y) && gui.getButtons(x,y).isEnabled()) {
			
			if (grid.getCellContent(x, y)==0) {
				
				gui.setButton(x,y,"images/m0.png");
				
				revealMineFreeGrids(x-1,y-1);
				revealMineFreeGrids(x-1,y);
				revealMineFreeGrids(x-1,y+1);
				
				revealMineFreeGrids(x,y-1);
				revealMineFreeGrids(x,y+1);
				
				revealMineFreeGrids(x+1,y-1);
				revealMineFreeGrids(x+1,y);
				revealMineFreeGrids(x+1,y+1);
				}
			
			else if(grid.getCellContent(x, y)!=-1 && grid.getCellContent(x, y)!=0){
			gui.setButton(x,y,"images/m"+grid.getCellContent(x, y)+".png");
			}
			
		}
	}


	private void revealMines() {
		for(int x=0;x<grid.getGridInfo().length;x++) {
			for(int y=0;y<grid.getGridInfo().length;y++) {
				if(grid.isMine(x, y) && (x!=row || y!=col )) {
					gui.setButton(x,y,"images/mine.png");
						
				}
			}
		}
		
	}
	
}


class MouseAdapter implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
