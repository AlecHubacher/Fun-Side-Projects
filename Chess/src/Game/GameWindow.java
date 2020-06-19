package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameWindow extends JFrame{ 
	JPanel gamePanel = new JPanel(new BorderLayout());
	public GameWindow() throws IOException 
	{
		this.setSize(1920,1080);
		this.setLayout(new GridBagLayout());
		//this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel.add(new Board(this));
		this.add(gamePanel);
		this.setVisible(true);
	}
	
	public JFrame getGameFrame()
	{
		return this;
	}
	
	public JPanel getGamePanel()
	{
		return gamePanel;
	}
	
	public static void main(String[] args) throws IOException
	{
		GameWindow gw = new GameWindow();
		
		
		
	}
}
