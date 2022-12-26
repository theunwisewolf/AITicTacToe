package com.amn.tictactoe;

public class Human extends Player
{
	public String playerName = "You";

	public static final int ID = 1;
	public static final char SYMBOL = 'O';

	public Human()
	{
		super(ID, false);
	}

	public void makeMove(Board board, Move move) throws Exception
	{
		if (!board.squareIsEmpty(move.i, move.j))
		{
			throw new Exception("Square " + move.i + ", " + move.j + " not empty!");
		}

		board.setSquare(move.i, move.j, Player.HUMAN);
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
