package pieces;

import java.io.IOException;
import java.util.ArrayList;

import Game.Square;
import Game.Board;

public class Pawn extends Piece{
	private int count=0;
	public ArrayList<Square> legalMoves = new ArrayList<Square>();
	
	public Pawn(Square[][] board, String imageFile, Square placement,String pieceColor) throws IOException {
		super(board, imageFile,placement,pieceColor);
		this.placement=placement;
		this.pieceColor=pieceColor;
		this.board=board;
		
	}
	
	public ArrayList<Square> getLegalMoves()
	{
		return legalMoves;
	}
	
	
	public void populateLegalMoves() //account for check and en passant later
	{
		int row = placement.getRow();
		int col = placement.getCol();
		System.out.println("placement is: "+row+" "+col);
		if(pieceColor=="black")
		{
			if(firstMove)
			{
				if(board[row+2][col].getPiece()==null)
				{
					legalMoves.add(board[row+2][col]);
				}
			}
			if(board[row+1][col].getPiece()==null)
			{
				legalMoves.add(board[row+1][col]);
			}
			if(col<7)
			{
				if(board[row+1][col+1].getPiece()!=null&&board[row+1][col+1].getPiece().getColor().equalsIgnoreCase("white"))
				{
					legalMoves.add(board[row+1][col+1]);
				}
			}
			if(col>0)
			{
				if(board[row+1][col-1].getPiece()!=null&&board[row+1][col-1].getPiece().getColor().equalsIgnoreCase("white"))
				{
					legalMoves.add(board[row+1][col-1]);
				}
			}
		}
		else
		{
			if(firstMove)
			{
				if(board[row-2][col].getPiece()==null)
				{
					legalMoves.add(board[row-2][col]);
				}
			}
			if(board[row-1][col].getPiece()==null)
			{
				legalMoves.add(board[row-1][col]);
			}
			if(col<7)
			{
				if(board[row-1][col+1].getPiece()!=null&&board[row-1][col+1].getPiece().getColor().equalsIgnoreCase("white"))
				{
					legalMoves.add(board[row-1][col+1]);
				}
			}
			if(col>0)
			{
				if(board[row-1][col-1].getPiece()!=null&&board[row-1][col-1].getPiece().getColor().equalsIgnoreCase("white"))
				{
					legalMoves.add(board[row-1][col-1]);
				}
			}
		}
		
	}
	
	public void showLegalMoves()
	{
		for(int i=0;i<legalMoves.size();++i)
		{
			legalMoves.get(i).highlight();
		}
	}
	
	public void removeHighlights()
	{
		for(int i=0;i<legalMoves.size();++i)
		{
			legalMoves.get(i).newColor(4);//This resets highlights to origColor. For some reason resetColor does not work
		}
		legalMoves.clear();
	}

}
