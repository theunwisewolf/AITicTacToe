package com.amn.tictactoe;

import java.util.ArrayList;

public class AI extends Player
{
	public static final int ID = 0;
	public static final char SYMBOL = 'X';

	public AI()
	{
		super(ID);
	}

	public AIMove makeMove(Board board)
	{
		AIMove move = this.getBestMove(board, Player.AI);
		board.makeMove(move.i, move.j, Player.AI);

		return move;
	}

	public char getSymbol()
	{
		return SYMBOL;
	}

	AIMove getBestMove(Board board, int player)
	{
		int winner = board.gameOver();
		if (winner == Board.AI)
		{
			return new AIMove(10);
		}
		else if (winner == Board.HUMAN)
		{
			return new AIMove(-10);
		}
		else if (winner == Board.DRAW)
		{
			return new AIMove(0);
		}

		ArrayList<AIMove> moves = new ArrayList<AIMove>();

		for (int i = 0; i < board.getSize(); ++i)
		{
			for (int j = 0; j < board.getSize(); j++)
			{
				if (board.squareIsEmpty(i, j))
				{
					AIMove move = new AIMove(i, j);

					// Make a move
					board.setSquare(i, j, player);

					// Get Score
					if (player == Player.AI)
					{
						move.score = getBestMove(board, Player.HUMAN).score;
					}
					else
					{
						move.score = getBestMove(board, Player.AI).score;
					}

					// Reset the move
					board.setSquare(i, j, Board.EMPTY_VAL);
					moves.add(move);
				}
			}
		}

		int bestMove = 0;
		if (player == Player.AI)
		{
			// Find maximum score for player
			int bestScore = -1000000;
			for (int i = 0; i < moves.size(); ++i)
			{
				if (moves.get(i).score > bestScore)
				{
					bestMove = i;
					bestScore = moves.get(i).score;
				}
			}
		}
		else
		{
			// Find minimum score for player
			int bestScore = 1000000;
			for (int i = 0; i < moves.size(); ++i)
			{
				if (moves.get(i).score < bestScore)
				{
					bestMove = i;
					bestScore = moves.get(i).score;
				}
			}
		}

		return moves.get(bestMove);
	}

	public String toString()
	{
		return "Friday";
	}

	public int getID()
	{
		return ID;
	}
}
