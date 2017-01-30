package com.amn.tictactoe;

import com.amn.tictactoe.Board;
import com.amn.tictactoe.AI;
import com.amn.tictactoe.Human;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class Applet_Main extends Applet implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	public static final boolean DEBUG = false;
	
	public static final int ROWS = 3;
	public static final int COLS = 3;
	
	public Dimension screenSize, appletSize;
	public Window window;
	private Timer timer;
	
	public Board board;
	public AI ai = new AI();
	public Human human = new Human();
	public int currentPlayer = Player.HUMAN;
	
	public Button squares[][] = new Button[ROWS][COLS];
	
    public Button clickedSquare; 
    public int c = 0; 
    
    public void init()
    {
    	board = new Board(ai, human);
        setLayout(new GridLayout(3,3));
        
        for(int i = 0; i < ROWS; ++i)
        {
        	for(int j = 0; j < COLS; j++)
	        {
	        	squares[i][j] = new Button("");
	        	squares[i][j].addActionListener(this);
	            this.add(squares[i][j]);
	        }
        }
        
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        appletSize = this.getSize();
        
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                centerAppletViewer();
            }
        };
        
        timer = new Timer(0, al);
        timer.start();
    }
    
    public void start()
    {
    	Container c = squares[0][0].getParent();
    	
    	while(c.getParent() != null)
    	{
    		c = c.getParent();
    	}
    	
    	if(c instanceof Window)
    	{
    		window = (Window)c;
    	}
    	else
    	{
    		System.out.println(c);
    	}
    }
    
    public void centerAppletViewer()
    {
    	if(this.window != null)
    	{
    		 int x = (int) (screenSize.getWidth() / 2 - appletSize.getWidth() / 2);
             int y = (int) (screenSize.getHeight() / 2 - appletSize.getHeight() / 2 - 50);
             this.window.setLocation(x,y);
             timer.stop();
    	}
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        clickedSquare = (Button) e.getSource();
        
        for(int i = 0; i < ROWS; ++i)
        {
        	for(int j = 0; j < COLS; j++)
	        {
        		if( clickedSquare == squares[i][j] )
        		{
        			Player player = ( currentPlayer == Player.HUMAN ) ? human : ai;
        			if(currentPlayer == Player.HUMAN)
        			{
        				if( !((Human)player).makeMove(board, i, j) )
        				{
        					this.showMessage("That move is invalid.");
        				}
        				else
        				{
        					clickedSquare.setLabel(String.valueOf(player.getSymbol()));
            				this.changePlayer();
            				AIMove move = ai.makeMove(board);
            				squares[move.i][move.j].setLabel(String.valueOf(ai.getSymbol()));
            				
            				if(DEBUG)
            					board.printToConsole();
            				
            				this.changePlayer();
        				}
        			}        					
        		}
	        }
        }

        // What's with src()? Use proper function names. Should be something like isGameOver() or victory() or hasPlayerWon(), etc
        int winner = board.gameOver();
        if(winner == Board.DRAW)
        {
        	this.showMessage("Draw!");
        }
        else if(winner != Board.EMPTY_VAL)
        {
        	Player player = (winner == Player.AI) ? ai : human;
            this.showMessage( player + " has won the game!" );
        }
    }
    
    public void changePlayer()
    {
    	if(currentPlayer == 1)
    		currentPlayer = 0;
    	else
    		currentPlayer = 1;
    }
    
    public void showMessage(String message)
    {
    	JOptionPane.showConfirmDialog( null, message, "TicTacToe", JOptionPane.OK_CANCEL_OPTION );
    }
}

