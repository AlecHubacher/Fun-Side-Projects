package pieces;

import java.io.IOException;
import java.util.ArrayList;

import Game.Board;
import Game.CheckMateDetection;
import Game.Square;

public class Pawn extends Piece{
	public ArrayList<Square> legalMoves = new ArrayList<Square>();
	public Pawn(Square[][] board, String imageFile, Square placement,String pieceColor,String pieceType) throws IOException {
		super(board, imageFile,placement,pieceColor,pieceType);
		this.placement=placement;
		this.pieceColor=pieceColor;
		this.board=board;
		
	}
	
	private void isLegalMove(int row, int col, boolean isDiag)
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
			if(!isDiag)
			{
				legalMoves.add(board[row][col]);
			}
		}
		else if(!(board[row][col].getPiece().getColor().equalsIgnoreCase(this.getColor())) && isDiag)//square has enemy team piece
		{
			legalMoves.add(board[row][col]);
		}
	}
	
	public ArrayList<Square> getLegalMoves()
	{
		return legalMoves;
	}
	
	private boolean inBounds(int row, int col)
	{
		return (row >= 0 && row <= 7 && col >= 0 && col <= 7) ? true : false;
	}
	
	public void setFirstMoveDone()
	{
		firstMove = false;
	}
	public void populateLegalMoves() //account for check and en passant later
	{
		int row = placement.getRow();
		int col = placement.getCol();
		legalMoves.add(board[row][col]);
		
		if(board[row][col].getPiece().getColor().equalsIgnoreCase("white"))
		{
			if(inBounds(row-1,col))
			{
				isLegalMove(row-1, col, false);
			}
			if(firstMove)
			{
				isLegalMove(row-2, col, false);
			}
			if(inBounds(row-1, col+1))
			{
				isLegalMove(row-1, col+1, true);
			}
			if(inBounds(row-1, col-1))
			{
				isLegalMove(row-1, col-1, true);
			}
		}
		else
		{
			if(inBounds(row+1,col))
			{
				isLegalMove(row+1, col, false);
			}
			if(firstMove)
			{
				isLegalMove(row+2, col, false);
			}
			if(inBounds(row+1, col+1))
			{
				isLegalMove(row+1, col+1, true);
			}
			if(inBounds(row+1, col-1))
			{
				isLegalMove(row+1, col-1, true);
			}
		}
	}
	
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
