package com.amn.tictactoe;

public class Move 
{
	public int i;
	public int j;
	public int score;

	public Move(int score)
	{
		this.score = score;
	}

	public Move(int i, int j)
	{
		this.i = i;
		this.j = j;
	}

	public Move(int i, int j, int score)
	{
		this.i = i;
		this.j = j;
		this.score = score;
	}
}
