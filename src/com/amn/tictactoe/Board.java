package com.amn.tictactoe;

public class Board {
	public static final int AI = 0;
	public static final int HUMAN = 1;
	
	public static final int ROWS = 3;
	public static final int COLS = 3;
	public static final int EMPTY_VAL = -1;
	public static final int DRAW = -2;
	
	public int[][] squares = new int[ROWS][COLS];
	
	public AI ai;
	public Human human;
	
	public Board(AI ai, Human human)
	{
		this.ai = ai;
		this.human = human;
		
		this.clearBoard();
	}
	
	public void clearBoard()
	{
		for(int i = 0; i < ROWS; ++i)
			for(int j = 0; j < COLS; ++j)
				this.squares[i][j] = EMPTY_VAL;
	}
	
	public int getSquare(int i, int j)
	{
		return this.squares[i][j];
	}
	
	public void setSquare(int i, int j, int player)
	{
		this.squares[i][j] = player;
	}
	
	public boolean squareIsEmpty(int i, int j)
	{
		return (this.squares[i][j] == -1);
	}
	
	public boolean makeMove(int i, int j, int player)
	{
		if(this.squares[i][j] != -1)
		{
			return false;
		}
			
		this.squares[i][j] = player;
		
		return true;
	}
	
	public int getSize()
	{
		return ROWS;
	}
	
	public int Empty()
	{
		return EMPTY_VAL;
	}
	
	public void printToConsole()
	{
		System.out.println("Board: ");
		for(int i = 0; i < ROWS; ++i)
		{
			for(int j = 0; j < COLS; j++)
			{
				System.out.print(this.squares[i][j] + " ");
			}
			
			System.out.println();
		}
	}
	
	public int gameOver()
    {
    	// Rows
    	for(int i = 0; i < ROWS; ++i)
    	{
    		if(this.getSquare(i, 0) != -1 && this.getSquare(i, 1) != -1 && this.getSquare(i, 0) == this.getSquare(i, 1) && this.getSquare(i, 1) == this.getSquare(i, 2))
    		{
    			if(this.getSquare(i, 0) == Board.AI)
    			{
    				return Board.AI;
    			}
    			else
    			{
    				return Board.HUMAN;
    			}
    		}
    	}
    	
    	// Cols
    	for(int i = 0; i < ROWS; ++i)
    	{
    		if(this.getSquare(0, i) != -1 && this.getSquare(1, i) != -1 && this.getSquare(0, i) == this.getSquare(1, i) && this.getSquare(1, i) == this.getSquare(2, i))
    		{
    			if(this.getSquare(0, i) == Board.AI)
    			{
    				return Board.AI;
    			}
    			else
    			{
    				return Board.HUMAN;
    			}
    		}
    	}
    	
    	// Left Diagonal
    	if(this.getSquare(0, 0) != -1 && this.getSquare(1, 1) != -1 && this.getSquare(0, 0) == this.getSquare(1, 1) && this.getSquare(1, 1) == this.getSquare(2, 2))
		{
    		if(this.getSquare(0, 0) == Board.AI)
			{
				return Board.AI;
			}
			else
			{
				return Board.HUMAN;
			}
		}
    	
    	// Right Diagonal
    	if(this.getSquare(0, 2) != -1 && this.getSquare(1, 1) != -1 && this.getSquare(0, 2) == this.getSquare(1, 1) && this.getSquare(1, 1) == this.getSquare(2, 0))
		{
    		if(this.getSquare(0, 2) == Board.AI)
			{
				return Board.AI;
			}
			else
			{
				return Board.HUMAN;
			}
		}
    	
    	boolean draw = true;
    	
    	// Check for draw
    	for(int i = 0; i < ROWS; ++i)
    	{
    		for(int j = 0; j < COLS; j++)
    		{
    			// A square is empty, can't be a draw
    			if(this.squares[i][j] == -1)
    			{
    				draw = false;
    				break;
    			}
    		}
    		
    		if(draw == false)
    			break;
    	}
    	
    	return (draw == true) ? DRAW : EMPTY_VAL;
    }
}
