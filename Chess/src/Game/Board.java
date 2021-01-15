package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;
public class Board extends JPanel implements MouseListener, MouseMotionListener{
	public final static String bbishop = "C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\bbishop.png";
	public final static String bking = "C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\bking.png";
	public final static String bknight = "C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\bknight.png";
	public final static String bpawn = "C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\bpawn.png";
	public final static String bqueen = "C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\bqueen.png";
	public final static String brook = "C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\brook.png";
	public final static String wbishop = "C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\wbishop.png";
	public final static String wking = "C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\wking.png";
	public final static String wknight = "C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\wknight.png";
	public final static String wpawn = "C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\wpawn.png";
	public final static String wqueen = "C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\wqueen.png";
	public final static String wrook = "C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\wrook.png";
	
	public static ArrayList<Piece> wTeam = new ArrayList<Piece>();
	public static ArrayList<Piece> bTeam = new ArrayList<Piece>();
	public String imageStr = "C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\wqueen.png";
	public Image image = ImageIO.read(new File("C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\wqueen.png"));
	public Square[][] board= new Square[8][8];
	public int count =0;
	public Square startSquare;
	public Square endSquare;
	private Piece startPiece;
	private Piece endPiece;
	private Square oldS2;
	public static boolean whiteTurn=true;
	public static CheckMateDetection detect;
	public Board(GameWindow gw) throws IOException
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		GridLayout grid = new GridLayout(8,8,0,0);
		//JFrame f = new JFrame("Chess");
		//JPanel p = new JPanel();
		this.setLayout(grid);
		//this.setBackground(Color.BLUE);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(800,800));
        //p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //p.add(new Piece("ji"));
        for(int i=0;i<8;++i)
        {
        	for(int j=0;j<8;++j)
        	{
        		if(i%2==0)
        		{
        			if(j%2==0)
            		{
        				Color c = new Color(153, 102, 51);
            			board[i][j] = new Square(i,j, 100, 100,c);
            			this.add(board[i][j]);
            			board[i][j].setBackground(new Color(153, 102, 51));
            		}
            		else
            		{
            			Color c = new Color(223, 189, 159);
            			board[i][j] = new Square(i,j, 100, 100,c);
            			this.add(board[i][j]);
            			board[i][j].setBackground(new Color(223, 189, 159));
            		}
        		}
        		else
        		{
        			if((j+1)%2==0)
            		{
        				Color c = new Color(153, 102, 51);
            			board[i][j] = new Square(i,j, 100, 100,c);
            			this.add(board[i][j]);
            			board[i][j].setBackground(new Color(153, 102, 51));
            		}
            		else
            		{
            			Color c = new Color(223, 189, 159);
            			board[i][j] = new Square(i,j, 100, 100,c);
            			this.add(board[i][j]);
            			board[i][j].setBackground(new Color(223, 189, 159));
            		}
        		}
        	}
        }
        initPieces();

        
	}
	
	public Square getBoardPos(int x, int y)
	{
		return board[x][y];
	}

	public void initPieces() throws IOException
	{
		board[0][0].put(new Rook(board, brook,board[0][0],"black","rook"));
		board[0][1].put(new Knight(board, bknight, board[0][1],"black","knight"));
		board[0][2].put(new Bishop(board, bbishop, board[0][2],"black","bishop"));
		board[0][3].put(new Queen(board, bqueen, board[0][3],"black","queen"));
		board[0][4].put(new King(board, bking, board[0][4],"black","king"));
		board[0][5].put(new Bishop(board, bbishop, board[0][5],"black","bishop"));
		board[0][6].put(new Knight(board, bknight, board[0][6],"black","knight"));
		board[0][7].put(new Rook(board, brook, board[0][7],"black","rook"));
		
		
		for(int i=0;i<8;++i)
		{
			bTeam.add(board[0][i].getPiece());
			board[1][i].put(new Pawn(board, bpawn, board[1][i],"black","pawn"));
			bTeam.add(board[1][i].getPiece());
		}
		
		for(int i=0;i<8;++i)
		{
			board[6][i].put(new Pawn(board, wpawn, board[6][i],"white","pawn"));
		}
		
		
		board[7][0].put(new Rook(board, wrook,board[7][0],"white","rook"));
		board[7][1].put(new Knight(board, wknight, board[7][1],"white","knight"));
		board[7][2].put(new Bishop(board, wbishop, board[7][2],"white","bishop"));
		board[7][3].put(new Queen(board, wqueen, board[7][3],"white","queen"));
		board[7][4].put(new King(board, wking, board[7][4],"white","king"));
		board[7][5].put(new Bishop(board, wbishop, board[7][5],"white","bishop"));
		board[7][6].put(new Knight(board, wknight, board[7][6],"white","knight"));
		board[7][7].put(new Rook(board, wrook, board[7][7],"white","rook"));
		
		for(int i=0;i<8;i++)
		{
			wTeam.add(board[7][i].getPiece());
			wTeam.add(board[6][i].getPiece());
		}
		
		detect = new CheckMateDetection(board, board[7][4].getPiece(), board[0][4].getPiece());
		
		for(int i=0;i<8;++i)
		{
			board[0][i].getPiece().setDetect(detect);
			board[1][i].getPiece().setDetect(detect);
			board[6][i].getPiece().setDetect(detect);
			board[7][i].getPiece().setDetect(detect);
		}
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i=0;i<8;++i)
		{
			for(int j=0;j<8;++j)
			{
				Square s = board[i][j];
				s.paintComponent(g);
			}
		}
	}
	
	private boolean isLegalMove(Piece sPiece, Square eSquare)
	{
		if(!sPiece.getLegalMoves().contains(eSquare))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	private boolean sameColor(Piece sPiece, Piece ePiece)
	{
		if(sPiece.getColor().equalsIgnoreCase(ePiece.getColor()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void movePiece(Piece sPiece, Square sSquare, Square eSquare)
	{
		startPiece.setPlacement(endSquare);
		endSquare.put(startPiece);
		startSquare.removePiece();
		startPiece.removeHighlights();
		startPiece.clearLegalMoves();
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		count++;
		if(count == 1)
		{
			startSquare = (Square) this.getComponentAt(new Point(e.getX(),e.getY()));
			startPiece = startSquare.getPiece();
			if(startPiece == null)
			{
				count = 0;
				return;
			}
			if(startPiece.getColor().equalsIgnoreCase("white") && !whiteTurn || startPiece.getColor().equalsIgnoreCase("black") && whiteTurn)
			{
				count=0;
				return;
			}
			startPiece.clearLegalMoves();
			System.out.println("THIS SHOULD PRINT AFTER IN CHECK CLICK BISHOP");
			startPiece.populateLegalMoves();
			
			startPiece.showLegalMoves();
		}
		else
		{
			endSquare = (Square) this.getComponentAt(new Point(e.getX(),e.getY()));
			Piece endPiece = endSquare.getPiece();
			
			if(isLegalMove(startPiece, endSquare))
			{
				movePiece(startPiece, startSquare, endSquare);
				if(startPiece instanceof Pawn)
				{
					startPiece.setFirstMoveDone();
				}
				count = 0;
				detect.setLastPieceMoved(startPiece);
				if(detect.isInCheck(whiteTurn))
				{
					detect.getWking().populateLegalMoves();
					detect.getBking().populateLegalMoves();
				}
				
				repaint();
				whiteTurn = whiteTurn ? false : true;
				return;
			}
			else
			{
				if(endSquare.getPiece() == null)
				{
					count = 0;
					return;
				}
				if(sameColor(startPiece, endPiece))
				{
					startPiece.removeHighlights();
					startPiece.clearLegalMoves();
					endPiece.populateLegalMoves();
					endPiece.showLegalMoves();
					count = 1;
					startPiece = endPiece;
					startSquare = endSquare;
					return;
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

	
	
	
	



