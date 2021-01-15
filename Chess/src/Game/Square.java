package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import pieces.Piece;

public class Square extends JPanel{
	
	public Piece currentPiece;
	private Color origSquareColor;
	private Color newColor;
	private int row;
	private int col;
	private Color color;
	private int xSize;
	private int ySize;
	private boolean needsRemoving=false;
	private boolean needsRecoloring=false;
	public Image image = ImageIO.read(new File("C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\wqueen.png"));
	
	public Square(int xCor, int yCor, int xSize, int ySize, Color color) throws IOException
	{
		this.row=xCor;
		this.col=yCor;
		this.xSize=xSize;
		this.ySize=ySize;
		//this.setBackground(Color.BLACK);
		//this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		this.origSquareColor=color;
		this.setVisible(true);
	}
	
	public void makeOriginalColor()
	{
		this.setBackground(origSquareColor);
	}
	
	public void removePiece()
	{
		needsRemoving=true;
		currentPiece=null;
	}
	public void put(Piece p)
	{
		currentPiece = p;
	}
	
	public Piece getPiece()
	{
		return currentPiece;
	}
	
	public void setRow(int newXCor)
	{
		this.row=newXCor;
	}
	
	public void setCol(int newYCor)
	{
		this.col=newYCor;
	}
	
	
	
	public void resetColor()
	{
		needsRecoloring=true;
	}
	
	public void highlight()
	{
		this.setBackground(new Color(255,223,101,255));
	}
	
	public void highlightMain()
	{
		this.setBackground(new Color(255,255,0));
	}
	
	public void makeCheckColor()
	{
		this.color=new Color(255,0,0);
	}
	/*public void newColor(Color c)
	{
		if(c==Color.BROWN)//this is supposed to be brown
		{
			newColor=new Color(153, 102, 51);
		}
		else if(c==0)
		{
			newColor = new Color(223, 189, 159);
		}
		else
		{
			
		}
		color=c;
	}*/
	
	public Color getSquareColor()
	{
		return origSquareColor;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if(currentPiece!=null)
		{
			g.drawImage(currentPiece.getImage(),21,26,null);//21 and 26 are used to center the image on the square
		}
	}
	
	public int getRow() {
		return row;
	}


	public int getCol() {
		return col;
	}
/*
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	*/


}
