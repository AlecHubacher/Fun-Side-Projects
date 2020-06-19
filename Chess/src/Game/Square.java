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

public class Square extends JComponent{
	
	public Piece currentPiece;
	private Color origSquareColor;
	private Color newColor;
	private int row;
	private int col;
	private int color;
	private int xSize;
	private int ySize;
	private boolean needsRemoving=false;
	private boolean needsRecoloring=false;
	public Image image = ImageIO.read(new File("C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\wqueen.png"));
	
	public Square(int xCor, int yCor, int color,int xSize, int ySize) throws IOException
	{
		this.row=xCor;
		this.col=yCor;
		this.color=color;
		this.xSize=xSize;
		this.ySize=ySize;
		this.setBackground(Color.BLACK);
		//this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		this.setVisible(true);
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
		this.color=3;
	}
	public void newColor(int c)
	{
		if(c==1)
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
	}
	
	public Color getSquareColor()
	{
		return origSquareColor;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if(needsRemoving)
		{
			g.setColor(origSquareColor);
			g.fillRect(this.row, this.col, xSize, ySize);
			needsRemoving=false;
			return;
		}
		
		if(needsRecoloring)
		{
			System.out.println("needs recoloring complete");
			g.setColor(origSquareColor);
			g.fillRect(this.row, this.col, xSize, ySize);
			needsRecoloring=false;
			return;
		}
		if(color==1)
		{
			g.setColor(new Color(153, 102, 51));
			origSquareColor=new Color(153, 102, 51);
			g.fillRect(this.row, this.col, xSize, ySize);
		}
		else if(color==0)
		{
			g.setColor(new Color(223, 189, 159));
			g.fillRect(this.row, this.col, xSize, ySize);
			origSquareColor = new Color(223, 189, 159);
		}
		else if(color==3)
		{
			g.setColor(Color.BLUE);
			g.fillRect(this.row, this.col, xSize, ySize);
		}
		else if(color==4)
		{
			g.setColor(origSquareColor);
			g.fillRect(this.row, this.col, xSize, ySize);
		}
		
		if(currentPiece!=null)
		{
			g.drawImage(currentPiece.getImage(),row+21,col+26,null);//21 and 26 are used to center the image on the square
		}
		
		//this is used when you move a piece so it needs to be removed from the old square
		/*if(needsRemoving)
		{
			g.setColor(origSquareColor);
			g.fillRect(this.row, this.col, xSize, ySize);
			needsRemoving=false;
		}
		
		if(needsRecoloring)
		{
			System.out.println("needs recoloring complete");
			g.setColor(origSquareColor);
			g.fillRect(this.row, this.col, xSize, ySize);
			needsRecoloring=false;
		}*/
		
		
	}
	
	public int getRow() {
		return row;
	}


	public int getCol() {
		return col;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}


}
