package com.amn.tictactoe;

public class AIMove {
	public int i;
	public int j;
	public int score;

	public AIMove(int score)
	{
		this.score = score;
	}

	public AIMove(int i, int j)
	{
		this.i = i;
		this.j = j;
	}

	public AIMove(int i, int j, int score)
	{
		this.i = i;
		this.j = j;
		this.score = score;
	}
}
