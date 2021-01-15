package pieces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Game.Board;
import Game.CheckMateDetection;
import Game.Square;

public class Knight extends Piece{
	ArrayList<Square> legalMoves = new ArrayList<Square>();
	public Knight(Square[][] board,String imageFile, Square placement,String pieceColor,String pieceType) throws IOException {
		super(board,imageFile, placement,pieceColor,pieceType);
		this.placement=placement;
		this.pieceColor=pieceColor;
		this.board=board;
	}

	public ArrayList<Square> getLegalMoves()
	{
		return legalMoves;
	}
	
	private void isLegalMove(int row, int col)
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
			legalMoves.add(board[row][col]);
		}
		else if(!(board[row][col].getPiece().getColor().equalsIgnoreCase(this.getColor())))//square has enemy team piece
		{
			legalMoves.add(board[row][col]);
		}
	}
	
	private boolean inBounds(int row, int col)
	{
		return (row >= 0 && row <= 7 && col >= 0 && col <= 7) ? true : false;
	}
	
	@Override
	public void populateLegalMoves() 
	{
		int row = placement.getRow();
		int col = placement.getCol();
		legalMoves.add(board[row][col]);
		
		
		if(inBounds(row-2,col-1))//checks top left square
		{
			isLegalMove(row-2, col-1);
		}
		if(inBounds(row-2, col+1))
		{
			isLegalMove(row-2, col+1);
		}
		if(inBounds(row-1, col-2))
		{
			isLegalMove(row-1, col-2);
		}
		if(inBounds(row-1, col+2))
		{
			isLegalMove(row-1, col+2);
		}
		if(inBounds(row+2, col-1))
		{
			isLegalMove(row+2, col-1);
		}
		if(inBounds(row+2, col+1))
		{
			isLegalMove(row+2, col+1);
		}
		if(inBounds(row+1, col-2))
		{
			isLegalMove(row+1, col-2);
		}
		if(inBounds(row+1, col+2))
		{
			isLegalMove(row+1, col+2);
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
