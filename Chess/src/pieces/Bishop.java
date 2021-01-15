package pieces;

import java.io.IOException;
import java.util.ArrayList;

import Game.Board;
import Game.CheckMateDetection;
import Game.Square;

public class Bishop extends Piece{
	public ArrayList<Square> legalMoves = new ArrayList<Square>();
	private boolean nw = false;
	private boolean sw = false;
	private boolean ne = false;
	private boolean se = false;
	
	public Bishop(Square[][] board,String imageFile, Square placement,String pieceColor,String pieceType) throws IOException {
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
		if(detect.isKingInCheck())
		{
			if(detect.getPathToKing().contains(board[row][col]))
			{
				System.out.println("The square is legal");
				legalMoves.add(board[row][col]);
				return;
			}
		}
		else if(board[row][col].getPiece() == null)//if square is empty
		{
			System.out.println("this square is also legal");
			legalMoves.add(board[row][col]);
		}
		else
		{
			if(!(board[row][col].getPiece().getColor().equalsIgnoreCase(this.getColor())))//square has enemy team piece
			{
				legalMoves.add(board[row][col]);
			}
			switch(direction)
			{
				case 1:
					nw = true;
					break;
				case 2:
					ne = true;
					break;
				case 3:
					sw = true;
					break;
				case 4:
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
		nw = false;
		sw = false;
		ne = false;
		se = false;
		
		int row = placement.getRow();
		int col = placement.getCol();
		legalMoves.add(board[row][col]);
		int i = 1;
		
		//this function finds all legal moves for the north west diagonal
		while(!nw && inBounds(row-i, col-i))
		{
			isLegalMove(row-i, col-i, 1);
			i++;
		}
		i=1;
		while(!ne && inBounds(row-i,col+i)) //this function finds all legal moves for the north east diagonal
		{
			isLegalMove(row-i, col+i, 2);
			i++;
		}
		i=1;
		while(!sw && inBounds(row+i,col-i)) //this function finds all legal moves for the south west diagonal
		{
			isLegalMove(row+i, col-i, 3);
			i++;
		}
		i=1;
		while(!se && inBounds(row+i,col+i)) //this function finds all legal moves for the south east diagonal
		{
			isLegalMove(row+i, col+i, 4);
			i++;
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
