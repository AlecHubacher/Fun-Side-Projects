package pieces;

import java.io.IOException;
import java.util.ArrayList;

import Game.Board;
import Game.CheckMateDetection;
import Game.Square;

public class King extends Piece{
	
	private boolean north=false;
	private boolean south=false;
	private boolean east=false;
	private boolean west=false;
	private boolean nw=false;
	private boolean sw=false;
	private boolean ne=false;
	private boolean se=false;
	public ArrayList<Square> legalMoves = new ArrayList<Square>();
	public King(Square[][] board,String imageFile, Square placement,String pieceColor,String pieceType) throws IOException {
		super(board,imageFile, placement,pieceColor,pieceType);
		this.placement=placement;
		this.pieceColor=pieceColor;
		this.board=board;
	}
	
	public ArrayList<Square> getLegalMoves()
	{
		return legalMoves;
	}
	
	private void isLegalMove(int row, int col, int direction)
	{
		if(board[row][col].getPiece() == null)//if square is empty
		{
			if(Board.detect.absoluteIsLegalMove(board[row][col],this.getColor()))
			{
				legalMoves.add(board[row][col]);
			}
		}
		else
		{
			
			if(!(board[row][col].getPiece().getColor().equalsIgnoreCase(this.getColor())))//square has enemy piece
			{
				legalMoves.add(board[row][col]);
			}
			switch(direction)
			{
				case 1:
					north = true;
					break;
				case 2:
					east = true;
					break;
				case 3:
					south = true;
					break;
				case 4:
					west = true;
					break;
				case 5:
					nw = true;
					break;
				case 6:
					ne = true;
					break;
				case 7:
					sw = true;
					break;
				case 8:
					se = true;
					break;
			}
		}
	}
	
	private boolean inBounds(int row, int col)
	{
		return (row >= 0 && row <= 7 && col >= 0 && col <= 7) ? true : false;
	}
	
	@Override
	public void populateLegalMoves() 
	{
		north=false;
		south=false;
		east=false;
		west=false;
		nw=false;
		sw=false;
		ne=false;
		se=false;
		
		int row = placement.getRow();
		int col = placement.getCol();
		int i = 1;
		legalMoves.add(board[row][col]);
		
		if(!north && inBounds(row-i, col) && i<=1) //this function finds all legal moves towards the north
		{
			isLegalMove(row-i, col, 1);
		}
		if(!east && inBounds(row,col+i)) //this function finds all legal moves towards the east
		{
			isLegalMove(row, col+i, 2);
		}
		if(!south && inBounds(row+i,col)) //this function finds all legal moves towards the south
		{
			isLegalMove(row+i, col, 3);
		}
		if(!west && inBounds(row,col-i)) //this function finds all legal moves towards the west
		{
			isLegalMove(row, col-i, 4);
		}
		if(!nw && inBounds(row-i, col-i)) //this function finds all legal moves for the north west diagonal
		{
			isLegalMove(row-i, col-i, 5);
		}
		if(!ne && inBounds(row-i,col+i)) //this function finds all legal moves for the north east diagonal
		{
			isLegalMove(row-i, col+i, 6);
		}
		if(!sw && inBounds(row+i,col-i)) //this function finds all legal moves for the south west diagonal
		{
			isLegalMove(row+i, col-i, 7);
		}
		if(!se && inBounds(row+i,col+i)) //this function finds all legal moves for the south east diagonal
		{
			isLegalMove(row+i, col+i, 8);
		}
		
		
	}

	@Override
	public void showLegalMoves() 
	{
		legalMoves.get(0).highlightMain();
		for(int i=1;i<legalMoves.size();++i)
		{
			legalMoves.get(i).highlight();
		}
		
	}

	@Override
	public void removeHighlights()
	{
		for(int i=0;i<legalMoves.size();++i)
		{
			legalMoves.get(i).makeOriginalColor();
		}
		
	}
	
	public void clearLegalMoves()
	{
		legalMoves.clear();
	}
}
