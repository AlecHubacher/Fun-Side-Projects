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
	
	public String imageStr = "C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\wqueen.png";
	public Image image = ImageIO.read(new File("C:\\Users\\Alec\\Desktop\\FunProjects\\Chess\\resources\\wqueen.png"));
	public Square[][] board= new Square[8][8];
	public int count =0;
	public Square oldS;
	public Square newS;
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
            			board[i][j] = new Square(i,j,0,100,100);
            			this.add(board[i][j]);
            		}
            		else
            		{
            			board[i][j] = new Square(i,j,1,100,100);
            			this.add(board[i][j]);
            		}
        		}
        		else
        		{
        			if((j+1)%2==0)
            		{
            			board[i][j] = new Square(i,j,0,100,100);
            			this.add(board[i][j]);
            		}
            		else
            		{
            			board[i][j] = new Square(i,j,1,100,100);
            			this.add(board[i][j]);
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
		board[0][0].put(new Rook(board, brook,board[0][0],"black"));
		board[0][1].put(new Knight(board, bknight, board[0][1],"black"));
		board[0][2].put(new Bishop(board, bbishop, board[0][2],"black"));
		board[0][3].put(new Queen(board, bqueen, board[0][3],"black"));
		board[0][4].put(new King(board, bking, board[0][4],"black"));
		board[0][5].put(new Bishop(board, bbishop, board[0][5],"black"));
		board[0][6].put(new Knight(board, bknight, board[0][6],"black"));
		board[0][7].put(new Rook(board, brook, board[0][7],"black"));
		
		for(int i=0;i<8;++i)
		{
			board[1][i].put(new Pawn(board, bpawn, board[1][i],"black"));
		}
		
		for(int i=0;i<8;++i)
		{
			board[6][i].put(new Pawn(board, wpawn, board[6][i],"white"));
		}
		
		
		board[7][0].put(new Rook(board, wrook,board[7][0],"white"));
		board[7][1].put(new Knight(board, wknight, board[7][1],"white"));
		board[7][2].put(new Bishop(board, wbishop, board[7][2],"white"));
		board[7][3].put(new Queen(board, wqueen, board[7][3],"white"));
		board[7][4].put(new King(board, wking, board[7][4],"white"));
		board[7][5].put(new Bishop(board, wbishop, board[7][5],"white"));
		board[7][6].put(new Knight(board, wknight, board[7][6],"white"));
		board[7][7].put(new Rook(board, wrook, board[7][7],"white"));
		
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
		if(count==1)
		{
			oldS = (Square) this.getComponentAt(new Point(e.getX(),e.getY()));
			if(oldS.getPiece()==null)//if a player clicks a square without a piece on it
			{
				count=0;
				return;
			}
			oldS.getPiece().populateLegalMoves();
			oldS.getPiece().showLegalMoves();
			repaint();
			
		}
		else
		{
			newS = (Square) this.getComponentAt(new Point(e.getX(),e.getY()));
			if(!(oldS.getPiece().getLegalMoves().contains(newS)))
			{
				System.out.println("not legal");
				count=0;
				return;
			}
			newS.put(oldS.getPiece());//sets currentPiece of square 
			oldS.getPiece().removeHighlights();
			oldS.removePiece();//sets currentPiece of oldS
			newS.getPiece().setPlacement(newS);//sets the currentSquare that the piece is on
 
			newS.getPiece().setFirstMove(false);
			
			count=0;
			repaint();
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

	
	
	
	



