package com.amn.tictactoe;

public abstract class Player {	
	public static final int AI = 0;
	public static final int HUMAN = 1;
	
	public int currentPlayer;
	
	public Player(int player)
	{
		this.setPlayer(player);
	}
	
	public char getSymbol(Player player)
	{
		return player.getSymbol();
	}
	
	public abstract char getSymbol();
	public abstract int getID();
	
	public void setPlayer(int player)
	{
		this.currentPlayer = player;
	}
	
	public int getCurrentPlayer()
	{
		return this.currentPlayer;
	}
}
