package pieces;

import java.io.IOException;
import java.util.ArrayList;

import Game.Board;
import Game.Square;

public class Knight extends Piece{
	ArrayList<Square> legalMoves = new ArrayList<Square>();
	public Knight(Square[][] board,String imageFile, Square placement,String pieceColor) throws IOException {
		super(board,imageFile, placement,pieceColor);
		this.placement=placement;
		this.pieceColor=pieceColor;
		this.board=board;
	}

	public ArrayList<Square> getLegalMoves()
	{
		return legalMoves;
	}
	@Override
	public void populateLegalMoves() 
	{
		int row = placement.getRow();
		int col = placement.getCol();
		if(row+2<=7 && col+1<=7)
		{
			
			if(board[row+2][col+1].getPiece()==null || //if square is empty. Normal L shape
			!(placement.getPiece().getColor().equalsIgnoreCase(board[row+2][col+1].getPiece().getColor())))//if pieces are enemies
			{
				legalMoves.add(board[row+2][col+1]);
			}
		}
		if(row+2<=7 && col-1>=0) //Mirror of normal L shape
		{
			if(board[row+2][col-1].getPiece()==null || //if square is empty. Normal L shape
					!(placement.getPiece().getColor().equalsIgnoreCase(board[row+2][col-1].getPiece().getColor())))//if pieces are enemies
					{
						legalMoves.add(board[row+2][col-1]);
					}
		}
		if(row-2>=0 && col+1<=7)//upside down normal L
		{
			if(board[row-2][col+1].getPiece()==null || //if square is empty. Normal L shape
					!(placement.getPiece().getColor().equalsIgnoreCase(board[row-2][col+1].getPiece().getColor())))//if pieces are enemies
					{
						legalMoves.add(board[row-2][col+1]);
					}
		}
		if(row-2>=0 && col-1>=0)//Mirror of upside down normal L
		{
			if(board[row-2][col-1].getPiece()==null || //if square is empty. Normal L shape
					!(placement.getPiece().getColor().equalsIgnoreCase(board[row-2][col-1].getPiece().getColor())))//if pieces are enemies
					{
						legalMoves.add(board[row-2][col-1]);
					}
		}
		if(col-2>=0 && row+1<=7)//left side down L
		{
			if(board[row+1][col-2].getPiece()==null || //if square is empty. Normal L shape
					!(placement.getPiece().getColor().equalsIgnoreCase(board[row+1][col-2].getPiece().getColor())))//if pieces are enemies
					{
						legalMoves.add(board[row+1][col-2]);
					}
		}
		if(col-2>=0 && row-1>=0)//left side up L
		{
			if(board[row-1][col-2].getPiece()==null || //if square is empty. Normal L shape
					!(placement.getPiece().getColor().equalsIgnoreCase(board[row-1][col-2].getPiece().getColor())))//if pieces are enemies
					{
						legalMoves.add(board[row-1][col-2]);
					}
		}
		if(col+2<=7 && row-1>=0)//right side up L
		{
			if(board[row-1][col+2].getPiece()==null || //if square is empty. Normal L shape
					!(placement.getPiece().getColor().equalsIgnoreCase(board[row-1][col+2].getPiece().getColor())))//if pieces are enemies
					{
						legalMoves.add(board[row-1][col+2]);
					}
		}
		if(col+2<=7 && row+1<=7)
		{
			if(board[row+1][col+2].getPiece()==null || //if square is empty. Normal L shape
					!(placement.getPiece().getColor().equalsIgnoreCase(board[row+1][col+2].getPiece().getColor())))//if pieces are enemies
					{
						legalMoves.add(board[row+1][col+2]);
					}
		}
	}

	@Override
	public void showLegalMoves() 
	{
		for(int i=0;i<legalMoves.size();++i)
		{
			legalMoves.get(i).highlight();
		}
		
	}

	@Override
	public void removeHighlights()
	{
		for(int i=0;i<legalMoves.size();++i)
		{
			legalMoves.get(i).newColor(4);
		}
		legalMoves.clear();
		
	}
}
