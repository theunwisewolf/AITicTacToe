package com.amn.tictactoe;

import com.amn.tictactoe.Player;

public class Human extends Player
{
	public String playerName = "You";
	
	public static final int ID = 1;
	public static final char SYMBOL = 'O';
	
	public Human() 
	{
		super(ID);
	}
	
	public boolean makeMove(Board board, int i, int j)
	{
		if(!board.squareIsEmpty(i, j))
		{
			return false;
		}
		
		board.setSquare(i, j, Player.HUMAN);
		return true;
	}
	
	public char getSymbol()
	{
		return SYMBOL;
	}
	
	public String toString()
	{
		return playerName;
	}
	
	public int getID()
	{
		return ID;
	}
}
