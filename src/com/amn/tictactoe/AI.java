package com.amn.tictactoe;

import java.util.ArrayList;

public class AI extends Player
{
	public static final int ID = 0;
	public static final char SYMBOL = 'X';

	public AI()
	{
		super(ID, true);
	}

	public Move figureOutMove(Board board)
	{
		Move move;
		if (board.FilledSquares == 0)
		{
			int i = this.getRandomIndex();
			int j = this.getRandomIndex();
			move = new Move(i, j);
		}
		else
		{
			move = this.getBestMove(board, Player.AI);
		}

		return move;
	}

	public void makeMove(Board board, Move move) throws Exception
	{
		if (!board.squareIsEmpty(move.i, move.j))
		{
			throw new Exception("Square " + move.i + ", " + move.j + " not empty!");
		}

		board.setSquare(move.i, move.j, Player.AI);
	}

	private int getRandomIndex()
	{
		int min = 0;
		int max = Board.ROWS - 1;
		return min + (int)(Math.random() * ((max - min) + 1));
	}

	public char getSymbol()
	{
		return SYMBOL;
	}

	Move getBestMove(Board board, int player)
	{
		int winner = board.gameOver();
		if (winner == Board.AI)
		{
			return new Move(10);
		}
		else if (winner == Board.HUMAN)
		{
			return new Move(-10);
		}
		else if (winner == Board.DRAW)
		{
			return new Move(0);
		}

		ArrayList<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < board.getSize(); ++i)
		{
			for (int j = 0; j < board.getSize(); j++)
			{
				if (board.squareIsEmpty(i, j))
				{
					Move move = new Move(i, j);

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
