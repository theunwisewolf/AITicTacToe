package com.amn.tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class Main implements ActionListener
{
	public static final boolean DEBUG = false;

	public static final int ROWS = 3;
	public static final int COLS = 3;

	public Board board;
	public AI ai = new AI();
	public Human human = new Human();
	public int currentPlayer = Player.HUMAN;

	// GUI Elements
	public JFrame frame;
	public JButton squares[][] = new JButton[ROWS][COLS];
	public JButton clickedSquare;

	// GUI Variables
	public static final String WINDOW_TITLE = "Impossible Tic Tac Toe";
	public static final Dimension WINDOW_SIZE = new Dimension(400, 300);

	// GUI Colors
	public Color squareColor = new Color(52, 152, 219, 255);//new Color(155, 89, 182, 255);
	public Color borderColor = new Color(41, 128, 185, 255);//new Color(142, 68, 173, 255);

	public static void main(String[] args)
	{
		new Main().run();
	}

	public void run()
	{
		frame = new JFrame();
		frame.setTitle(WINDOW_TITLE);
		frame.setSize(WINDOW_SIZE);
		frame.setLayout(new GridLayout(ROWS, COLS));

		for (int i = 0; i < ROWS; ++i)
		{
			for (int j = 0; j < COLS; j++)
			{
				squares[i][j] = new JButton("");
				squares[i][j].addActionListener(this);
				squares[i][j].setBackground(squareColor);
				squares[i][j].setFocusPainted(false);
				squares[i][j].setBorder(new LineBorder(borderColor));
				squares[i][j].setForeground(new Color(255,255,255,255));
				frame.add(squares[i][j]);
			}
		}

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		board = new Board(ai, human);
	}

	public void actionPerformed(ActionEvent e)
	{
		clickedSquare = (JButton)e.getSource();

		for (int i = 0; i < ROWS; ++i)
		{
			for (int j = 0; j < COLS; j++)
			{
				if (clickedSquare == squares[i][j])
				{
					Player player = (currentPlayer == Player.HUMAN) ? human : ai;
					if (currentPlayer == Player.HUMAN)
					{
						if (!((Human)player).makeMove(board, i, j))
						{
							this.showMessage("That move is invalid.");
						}
						else
						{
							clickedSquare.setText(String.valueOf(player.getSymbol()));
							
							this.switchPlayer();
							AIMove move = ai.makeMove(board);
							squares[move.i][move.j].setText(String.valueOf(ai.getSymbol()));

							if (DEBUG)
							{
								board.printBoardState();
							}

							this.switchPlayer();
						}
					}
				}
			}
		}

		// What's with src()? Use proper function names. Should be something like isGameOver() or victory() or hasPlayerWon(), etc
		int winner = board.gameOver();
		if (winner == Board.DRAW)
		{
			this.showMessage("Draw!");
			this.disableSquares();
		}
		else if (winner != Board.EMPTY_VAL)
		{
			Player player = (winner == Player.AI) ? ai : human;
			this.showMessage( player + " has won the game!" );

			// Disable All buttons
			this.disableSquares();
		}
	}

	public void disableSquares()
	{
		for (int i = 0; i < ROWS; ++i)
		{
			for (int j = 0; j < COLS; j++)
			{
				squares[i][j].setEnabled(false);
			}
		}
	}

	public void switchPlayer()
	{
		currentPlayer = (currentPlayer + 1) % 2;
	}

	public void showMessage(String message)
	{
		JOptionPane.showConfirmDialog(null, message, "TicTacToe", JOptionPane.OK_CANCEL_OPTION);
	}
}

