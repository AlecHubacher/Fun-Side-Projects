package Game;

import java.awt.Color;
import java.util.ArrayList;

import pieces.Bishop;
import pieces.King;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class CheckMateDetection {
	private Square[][] board;
	private Piece wking;
	private Piece bking;
	public static boolean wInCheck=false;
	public static boolean bInCheck=false;
	public static ArrayList<Piece> causingCheck = new ArrayList<Piece>();
	public static ArrayList<Square> allWMoves = new ArrayList<Square>();
	public static ArrayList<Square> allBMoves = new ArrayList<Square>();
	public static ArrayList<Square> pathToKing = new ArrayList<Square>();
	public ArrayList<Square> kingMovesBad = new ArrayList<Square>();
	private Piece lastPieceMoved;
	
	public CheckMateDetection(Square[][] board, Piece wking, Piece bking) {
		this.board = board;
		this.wking = wking;
		this.bking = bking;
	}
	public Piece getWking() {
		return wking;
	}
	
	public ArrayList<Piece> getCausingCheck()
	{
		return causingCheck;
	}
	
	public void setLastPieceMoved(Piece p)
	{
		lastPieceMoved = p;
	}
	
	public Piece getBking() {
		return bking;
	}
	
	
	public boolean getWInCheck()
	{
		return wInCheck;
	}
	
	
	public boolean getBInCheck()
	{
		return bInCheck;
	}
	
	public boolean absoluteIsLegalMove(Square s, String color)
	{
		if(color.equalsIgnoreCase("white"))
		{
			for(int i=0;i<Board.bTeam.size();++i)
			{	
				if(!(Board.bTeam.get(i) instanceof King))
				{
					Board.bTeam.get(i).populateLegalMoves();
				}
				else
				{
					continue;
				}
				
				if(Board.bTeam.get(i).getLegalMoves().contains(s))
				{
					Board.bTeam.get(i).clearLegalMoves();
					return false;
				}
				Board.bTeam.get(i).clearLegalMoves();
			}
			return true;
		}
		else
		{
			for(int i=0;i<Board.wTeam.size();++i)
			{
				
				if(!(Board.wTeam.get(i) instanceof King))
				{
					Board.wTeam.get(i).populateLegalMoves();
				}
				else
				{
					continue;
				}
				
				if(Board.wTeam.get(i).getLegalMoves().contains(s))
				{
					Board.wTeam.get(i).clearLegalMoves();
					return false;
				}
				Board.wTeam.get(i).clearLegalMoves();
			}
			return true;
		}
	}
	
	public boolean isInCheck(boolean whiteTurn)
	{
		lastPieceMoved.populateLegalMoves();
		if(!whiteTurn)
		{
			if(lastPieceMoved.getLegalMoves().contains(wking.getPlacement()))
			{
				lastPieceMoved.clearLegalMoves();
				wking.getPlacement().setBackground(Color.red);
				wInCheck = true;
				pathToKing();
				return true;
			}
			lastPieceMoved.clearLegalMoves();
			return false;
		}
		else
		{
			if(lastPieceMoved.getLegalMoves().contains(bking.getPlacement()))
			{
				lastPieceMoved.clearLegalMoves();
				bking.getPlacement().setBackground(Color.red);
				bInCheck = true;
				pathToKing();
				return true;
			}
			lastPieceMoved.clearLegalMoves();
			return false;
		}
	}
	
	private String getDirection(Piece checkingPiece, Piece king)
	{
		Square pSquare = checkingPiece.getPlacement();
		System.out.println("CHECKING PIECE ROW: "+pSquare.getRow()+" CHECKING PIECE COL: "+pSquare.getCol());
		Square kSquare = king.getPlacement();
		System.out.println("KING PIECE ROW: "+kSquare.getRow()+" KING PIECE COL: "+kSquare.getCol());
		if(pSquare.getCol() > kSquare.getCol())
		{
			if(pSquare.getRow() < kSquare.getRow())//The piece is to the north east of the king
			{
				return "ne";
			}
			else
			{
				return "se";
			}
		}
		else if(pSquare.getCol() < kSquare.getCol())
		{
			if(pSquare.getRow() < kSquare.getRow())//The piece is to the north west of the king
			{
				System.out.println("THIS SHOULD PRINT WHEN THE KING IS PUT IN CHECK");
				return "nw";
			}
			else
			{
				return "sw";
			}
		}
		else if(pSquare.getCol() == kSquare.getCol())
		{
			if(pSquare.getRow() < kSquare.getRow())
			{
				return "n"; 
			}
			else
			{
				return "s";
			}
		}
		else
		{
			if(pSquare.getCol() < kSquare.getCol())
			{
				return "e";
			}
			else
			{
				return "w";
			}
		}
	}
	
	public void pathToKing() //only one piece can put king in check at once fix this later so no need for for loop
	{
		Piece king = null;
		if(wInCheck)
		{
			king = wking;
		}
		else
		{
			king = bking;
		}
		int row = king.getPlacement().getRow();
		int col = king.getPlacement().getCol();
		
		if(getDirection(lastPieceMoved, king).equalsIgnoreCase("nw"))
		{
			System.out.println("ThIS ONE SHOULD BE PRINTED");
			System.out.println("king row "+row+" king col "+col);
			row--;
			col--;
			while((row+1) != lastPieceMoved.getPlacement().getRow())
			{
				pathToKing.add(board[row][col]);
				row--;
				col--;
			}
			System.out.println("size of path to king is so huge "+pathToKing.size());
			for(int i=0;i<pathToKing.size();++i)
			{
				System.out.println("Row: "+pathToKing.get(i).getRow()+" Col: "+pathToKing.get(i).getCol());
			}
		}
		else if(getDirection(lastPieceMoved, king).equalsIgnoreCase("ne"))
		{
			System.out.println("ThIS ONE SHOULD NOT BE PRINTED");
			while((row-1) != lastPieceMoved.getPlacement().getRow())
			{
				pathToKing.add(board[row][col]);
				row--;
				col++;
			}
		}
		else if(getDirection(lastPieceMoved, king).equalsIgnoreCase("sw"))
		{
			while(row != lastPieceMoved.getPlacement().getRow())
			{
				pathToKing.add(board[row][col]);
				row++;
				col--;
			}
		}
		else if(getDirection(lastPieceMoved, king).equalsIgnoreCase("se"))
		{
			while(row != lastPieceMoved.getPlacement().getRow())
			{
				pathToKing.add(board[row][col]);
				row++;
				col++;
			}
		}
		else if(getDirection(lastPieceMoved, king).equalsIgnoreCase("e"))
		{
			while(col != lastPieceMoved.getPlacement().getCol())
			{
				pathToKing.add(board[row][col]);
				col++;
			}
		}
		else if(getDirection(lastPieceMoved, king).equalsIgnoreCase("s"))
		{
			while(col != lastPieceMoved.getPlacement().getCol())
			{
				pathToKing.add(board[row][col]);
				row++;
			}
		}
		else if(getDirection(lastPieceMoved, king).equalsIgnoreCase("w"))
		{
			while(col != lastPieceMoved.getPlacement().getCol())
			{
				pathToKing.add(board[row][col]);
				col--;
			}
		}
		else if(getDirection(lastPieceMoved, king).equalsIgnoreCase("n"))
		{
			while(col != lastPieceMoved.getPlacement().getCol())
			{
				pathToKing.add(board[row][col]);
				row--;
			}
		}
	}
	
	private void clearPathToKing()
	{
		pathToKing.clear();
	}
	
	public boolean isKingInCheck()
	{
		return wInCheck || bInCheck;
	}
	
	public ArrayList<Square> getPathToKing()
	{
		return pathToKing;
	}
	
	public boolean isCheckMate()
	{
		return false;
	}

}
