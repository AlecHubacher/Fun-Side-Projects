package pieces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import Game.Board;
import Game.CheckMateDetection;
import Game.Square;

public abstract class Piece extends JComponent{
	protected Square placement;
	public Image image;
	protected String pieceColor;
	protected boolean firstMove=true;
	public Square[][] board;
	private String pieceType;
	protected CheckMateDetection detect = null;
	
	
	public Piece(Square[][] board, String imageFile,Square placement,String pieceColor, String pieceType) throws IOException
	{
		image = ImageIO.read(new File(imageFile));
		//image = image.getScaledInstance(70, 70, Image.SCALE_DEFAULT);//maybe change board size later so images can be smaller
		this.placement=placement;
		this.pieceColor=pieceColor;
		this.board=board;
		this.pieceType=pieceType;
	}
	
	public void setDetect(CheckMateDetection detect)
	{
		this.detect = detect;
	}
	
	public Square getPlacement()
	{
		return placement;
	}
	
	public String getPieceType()
	{
		return pieceType;
	}
	
	public void setPlacement(Square sq)
	{
		placement=sq;
	}
	
	public String getColor()
	{
		return pieceColor;
	}
	
	public Image getImage()
	{
		return image;
	}

	public void setFirstMoveDone()
	{
		firstMove=false;
	}
	
	public abstract void populateLegalMoves();
	
	public abstract void showLegalMoves();
	
	public abstract void removeHighlights();
	
	public abstract ArrayList<Square> getLegalMoves();
	
	public abstract void clearLegalMoves();
	
	

}
