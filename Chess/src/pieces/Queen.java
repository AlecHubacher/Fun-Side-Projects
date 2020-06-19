package pieces;

import java.io.IOException;
import java.util.ArrayList;

import Game.Board;
import Game.Square;

public class Queen extends Piece{
	public ArrayList<Square> legalMoves = new ArrayList<Square>();
	public Queen(Square[][] board,String imageFile, Square placement,String pieceColor) throws IOException {
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
		boolean n=true;
		boolean s=true;
		boolean e=true;
		boolean w=true;
		boolean nw=true;
		boolean sw=true;
		boolean ne=true;
		boolean se=true;
		int row = placement.getRow();
		int col = placement.getCol();
		for(int i=1;i<8;++i)
		{
			//Rook type moves
			if(row+i<=7 && s)//south checking if near edge of board
			{
				if(board[row+i][col].getPiece()==null || //if square is empty
				!(placement.getPiece().getColor().equalsIgnoreCase(board[row+i][col].getPiece().getColor())))//if pieces are enemies
				{
					legalMoves.add(board[row+i][col]);
					//This if statement checks for if current square being checked is enemy then end checking this diagonal for legalSquares
					if(board[row+i][col].getPiece()!=null && //if square is not empty
					!(placement.getPiece().getColor().equalsIgnoreCase(board[row+i][col].getPiece().getColor())))// if pieces are enemies
					{
						s=false;
					}
				}
				else
				{
					s=false;
				}
			}
			if(col+i<=7 && e)//east
			{
				if(board[row][col+i].getPiece()==null || 
						!(placement.getPiece().getColor().equalsIgnoreCase(board[row][col+i].getPiece().getColor())))
						{
							legalMoves.add(board[row][col+i]);
							if(board[row][col+i].getPiece()!=null && //if square is not empty
									!(placement.getPiece().getColor().equalsIgnoreCase(board[row][col+i].getPiece().getColor())))// if pieces are enemies
									{
										e=false;
									}
						}
						else
						{
							e=false;
						}
			}
			if(row-i>=0 && n)//north
			{
				System.out.println("1");
				if(board[row-i][col].getPiece()==null ||
				!(placement.getPiece().getColor().equalsIgnoreCase(board[row-i][col].getPiece().getColor())))
						
						{
					System.out.println("2");
							legalMoves.add(board[row-i][col]);
							if(board[row-i][col].getPiece()!=null && //if square is not empty
									!(placement.getPiece().getColor().equalsIgnoreCase(board[row-i][col].getPiece().getColor())))// if pieces are enemies
									{
										n=false;
									}
						}
						else
						{
							n=false;
						}
			}
			if(col-i>=0 && w)//west
			{
				if(board[row][col-i].getPiece()==null || 
						!(placement.getPiece().getColor().equalsIgnoreCase(board[row][col-i].getPiece().getColor())))
						{
							legalMoves.add(board[row][col-i]);
							if(board[row][col-i].getPiece()!=null && //if square is not empty
									!(placement.getPiece().getColor().equalsIgnoreCase(board[row][col-i].getPiece().getColor())))// if pieces are enemies
									{
										w=false;
									}
						}
						else
						{
							w=false;
						}
			}
			//Bishop type moves
			if(row+i<=7 && row+i>=0 && col+i<=7 && col+i>=0 && se)//south east checking if near edge of board
			{
				if(board[row+i][col+i].getPiece()==null || //if square is empty
				!(placement.getPiece().getColor().equalsIgnoreCase(board[row+i][col+i].getPiece().getColor())))//if pieces are enemies
				{
					legalMoves.add(board[row+i][col+i]);
					//This if statement checks for if current square being checked is enemy then end checking this diagonal for legalSquares
					if(board[row+i][col+i].getPiece()!=null && //if square is not empty
					!(placement.getPiece().getColor().equalsIgnoreCase(board[row+i][col+i].getPiece().getColor())))// if pieces are enemies
					{
						se=false;
					}
				}
				else
				{
					se=false;
				}
			}
			if(row-i<=7 && row-i>=0 && col-i<=7 && col-i>=0 && nw)//north west
			{
				if(board[row-i][col-i].getPiece()==null || 
						!(placement.getPiece().getColor().equalsIgnoreCase(board[row-i][col-i].getPiece().getColor())))
						{
							legalMoves.add(board[row-i][col-i]);
							if(board[row-i][col-i].getPiece()!=null && //if square is not empty
									!(placement.getPiece().getColor().equalsIgnoreCase(board[row-i][col-i].getPiece().getColor())))// if pieces are enemies
									{
										nw=false;
									}
						}
						else
						{
							nw=false;
						}
			}
			if(row+i<=7 && row+i>=0 && col-i<=7 && col-i>=0 && sw)//south west
			{
				System.out.println("1");
				if(board[row+i][col-i].getPiece()==null ||
				!(placement.getPiece().getColor().equalsIgnoreCase(board[row+i][col-i].getPiece().getColor())))
						
						{
					System.out.println("2");
							legalMoves.add(board[row+i][col-i]);
							if(board[row+i][col-i].getPiece()!=null && //if square is not empty
									!(placement.getPiece().getColor().equalsIgnoreCase(board[row+i][col-i].getPiece().getColor())))// if pieces are enemies
									{
										sw=false;
									}
						}
						else
						{
							sw=false;
						}
			}
			if(row-i<=7 && row-i>=0 && col+i<=7 && col+i>=0 && ne)//north east
			{
				if(board[row-i][col+i].getPiece()==null || 
						!(placement.getPiece().getColor().equalsIgnoreCase(board[row-i][col+i].getPiece().getColor())))
						{
							legalMoves.add(board[row-i][col+i]);
							if(board[row-i][col+i].getPiece()!=null && //if square is not empty
									!(placement.getPiece().getColor().equalsIgnoreCase(board[row-i][col+i].getPiece().getColor())))// if pieces are enemies
									{
										ne=false;
									}
						}
						else
						{
							ne=false;
						}
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
