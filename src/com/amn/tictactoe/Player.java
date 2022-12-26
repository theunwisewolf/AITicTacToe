package com.amn.tictactoe;

public abstract class Player 
{
	public static final int AI = 0;
	public static final int HUMAN = 1;

	public int currentPlayer;
	public boolean _aiPlayer = false;

	public Player(int player, boolean isAI)
	{
		this.setPlayer(player);
		this._aiPlayer = isAI;
	}

	public char getSymbol(Player player)
	{
		return player.getSymbol();
	}

	public void setPlayer(int player)
	{
		this.currentPlayer = player;
	}

	public int getCurrentPlayer()
	{
		return this.currentPlayer;
	}

	public boolean IsAI()
	{
		return this._aiPlayer;
	}

	public boolean IsHuman()
	{
		return !this.IsAI();
	}

	public abstract char getSymbol();
	public abstract int getID();
	public abstract void makeMove(Board board, Move move) throws Exception;
}
