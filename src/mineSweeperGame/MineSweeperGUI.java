package mineSweeperGame;

import java.awt.*;
import javax.swing.*;

public class MineSweeperGUI extends JPanel  {
	
	private MineGrid grid;
	private JPanel panel1,panel2;
	private static JLabel bombLabel, timeLabel;
	private static JButton emoji;
	private JButton[][] buttons;
	private int seconds,bombCount;
	

	public MineSweeperGUI(int numRows, int numCols, int numMines) {
		
		bombCount=numMines;
		seconds=0;
		buttons=new JButton[numRows][numCols];
		
		grid = new MineGrid(numRows, numCols, numMines);
		setLayout(new BorderLayout());
		
		panel1=new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 105, 5));
		bombLabel=new JLabel(String.format("%03d",bombCount));
		timeLabel=new JLabel(String.format("%03d",seconds));
		
		setLabelSetup(bombLabel);
		setLabelSetup(timeLabel);
		
		emoji=new JButton();
		emoji.setPreferredSize(new Dimension(40, 40));
		emoji.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/facesmile.png")));
		
		panel1.add(bombLabel);
	    panel1.add(emoji);
		panel1.add(timeLabel);
		emoji.addMouseListener(new ButtonHandler(grid,this));
		
	    panel2=new JPanel();
		panel2.setLayout(new GridLayout(numRows,numCols));
		
		for(int x=0;x<numRows;x++) {
			for(int y=0;y<numCols;y++) {
				JButton button=new JButton();
				button.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/blank.png")));
				buttons[x][y]=button;
				panel2.add(buttons[x][y]);
				buttons[x][y].addMouseListener(new ButtonHandler(x,y,grid,this));
			}
		}
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
	}
	
	protected JButton[][] getButton(){
		return buttons;
	}

	protected void setButton(int x,int y,String a) {
		buttons[x][y].setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource(a)));
		buttons[x][y].setEnabled(false);
	}
	
	protected void setEmoji(String a) {
		emoji.setIcon(new ImageIcon(getClass().getClassLoader().getResource(a)));
	}

	protected void setBombLabel(int a) {
		bombLabel.setText(String.format("%03d",a));
	}

	protected static void setTimeLabel(int a) {
		timeLabel.setText(String.format("%03d",a));
	}

	private void setLabelSetup (JLabel label) {
		label.setFont(new Font("Digital-7 Mono",Font.PLAIN,55));
		label.setBackground(new Color(0,0,0));
		label.setForeground(new Color(250,0,0));
		label.setOpaque(true);
	}
	protected JButton getButtons(int x,int y) {
		return buttons[x][y];
	}

	protected JButton getEmoji() {
		return emoji;
	}

	
	
	
	
}

	
