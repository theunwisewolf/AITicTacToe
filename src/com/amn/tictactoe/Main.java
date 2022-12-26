package com.amn.tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalButtonUI;

public class Main implements ActionListener
{
	public static final boolean DEBUG = false;

	public static final int ROWS = 3;
	public static final int COLS = 3;

	public Board _board;
	public AI _AI;
	public Human _human;
	public int _currentPlayer = Player.HUMAN;

	// GUI Elements
	public JFrame _frame;
	public JButton _buttons[][] = new JButton[ROWS][COLS];

	// GUI Variables
	public static final String WINDOW_TITLE = "Impossible Tic Tac Toe";
	public static final Dimension WINDOW_SIZE = new Dimension(600, 450);

	// GUI Colors
	public static Color SQUARE_COLOR = new Color(52, 152, 219, 255);//new Color(155, 89, 182, 255);
	public static Color SQUARE_BORDER_COLOR = new Color(41, 128, 185, 255);//new Color(142, 68, 173, 255);

	public static void main(String[] args)
	{
		new Main().run();
	}

	public void run()
	{
		_frame = new JFrame();
		_frame.setTitle(WINDOW_TITLE);
		_frame.setSize(WINDOW_SIZE);
		_frame.setLayout(new GridLayout(ROWS, COLS));

		for (int i = 0; i < ROWS; ++i)
		{
			for (int j = 0; j < COLS; j++)
			{
				_buttons[i][j] = new JButton("");
				_buttons[i][j].addActionListener(this);
				// _buttons[i][j].setBackground(SQUARE_COLOR);
				_buttons[i][j].setContentAreaFilled(true);
				// _squares[i][j].setFocusPainted(false);
				_buttons[i][j].setBorder(new LineBorder(SQUARE_BORDER_COLOR));
				_buttons[i][j].setFont(new Font("Arial", Font.BOLD, 40));
				// squares[i][j].setForeground(new Color(255,255,255,255));
				_frame.add(_buttons[i][j]);
			}
		}

		_frame.setLocationRelativeTo(null);
		_frame.setVisible(true);

		_frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) 
			{
				if (JOptionPane.showConfirmDialog(_frame, 
					"Quit the game?", "QUIT", 
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
			}
		});

		_AI = new AI();
		_human = new Human();
		_board = new Board();
		if (Math.random() > 0.5f)
		{
			_currentPlayer = Player.AI;
			this.playTurn(_AI, _AI.figureOutMove(_board));
		}
	}

	public void Reset()
	{
		for (int i = 0; i < ROWS; ++i)
		{
			for (int j = 0; j < COLS; j++)
			{
				_buttons[i][j].setText("");
			}
		}
	}

	public Move GetMoveIndex(JButton square)
	{
		for (int i = 0; i < ROWS; ++i)
		{
			for (int j = 0; j < COLS; j++)
			{
				if (square == _buttons[i][j])
				{
					return new Move(i, j);
				}
			}
		}

		return null;
	}

	public void actionPerformed(ActionEvent e)
	{
		JButton clickedSquare = (JButton)e.getSource();

		this.playTurn(_human, this.GetMoveIndex(clickedSquare));
		this.playTurn(_AI, _AI.figureOutMove(_board));

		this.checkGameOver();
	}

	public void playTurn(Player player, Move move)
	{
		try
		{
			player.makeMove(_board, move);
			JButton button = _buttons[move.i][move.j];
			button.setText(String.valueOf(player.getSymbol()));
			button.setEnabled(false);

			if (player.IsAI())
			{
				button.setUI(new MetalButtonUI() {
					protected Color getDisabledTextColor() 
					{
						return Color.RED;
					}
				});
			}
			else
			{
				button.setUI(new MetalButtonUI() {
					protected Color getDisabledTextColor() 
					{
						return Color.BLACK;
					}
				});
			}

			this.switchPlayer();
		}
		catch (Exception e)
		{
			System.out.println("Turn - " + this._currentPlayer);
			e.printStackTrace();
		}
	}

	public void checkGameOver()
	{
		int winner = _board.gameOver();
		if (winner == Board.DRAW)
		{
			this.showMessage("Draw!");
			this.disableSquares();
		}
		else if (winner != Board.EMPTY_VAL)
		{
			Player player = (winner == Player.AI) ? _AI : _human;
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
				_buttons[i][j].setEnabled(false);
			}
		}
	}

	public void switchPlayer()
	{
		_currentPlayer = (_currentPlayer + 1) % 2;
	}

	public void showMessage(String message)
	{
		int choice = JOptionPane.showConfirmDialog(null, message, "TicTacToe", JOptionPane.OK_CANCEL_OPTION);
		if (choice == JOptionPane.OK_OPTION)
		{
			// Reset();
			_frame.dispose();
			new Main().run();
		}
		else
		{
			System.exit(0);
		}
	}
}
