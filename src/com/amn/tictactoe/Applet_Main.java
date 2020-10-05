package com.amn.tictactoe;

import com.amn.tictactoe.Board;
import com.amn.tictactoe.AI;
import com.amn.tictactoe.Human;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class Applet_Main extends Applet implements ActionListener
{
	private static final long serialVersionUID = 1L;
	public static final boolean DEBUG = false;
	public static final int ROWS = 3;
	public static final int COLS = 3;

	// Window stuff
	private Timer timer;
	public Dimension screenSize, appletSize;
	public Window window;

	public Board board;
	public AI ai = new AI();
	public Human human = new Human();
	public int currentPlayer = Player.HUMAN;

	public Button squares[][] = new Button[ROWS][COLS];
	public Button clickedSquare;

	public void init()
	{
		board = new Board(ai, human);
		this.setLayout(new GridLayout(3,3));

		for (int i = 0; i < ROWS; ++i)
		{
			for (int j = 0; j < COLS; j++)
			{
				squares[i][j] = new Button("");
				squares[i][j].addActionListener(this);
				this.add(squares[i][j]);
			}
		}

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		appletSize = this.getSize();

		ActionListener listener = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				centerAppletViewer();
			}
		};

		timer = new Timer(0, listener);
		timer.start();
	}

	public void start()
	{
		Container container = squares[0][0].getParent();

		while (container.getParent() != null)
		{
			container = container.getParent();
		}

		if (container instanceof Window)
		{
			window = (Window)container;
		}
		else
		{
			System.out.println(container);
		}
	}

	public void centerAppletViewer()
	{
		if (this.window != null)
		{
			int x = (int) (screenSize.getWidth() / 2 - appletSize.getWidth() / 2);
			int y = (int) (screenSize.getHeight() / 2 - appletSize.getHeight() / 2 - 50);

			this.window.setLocation(x,y);
			timer.stop();
		}
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		clickedSquare = (Button)event.getSource();

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
							clickedSquare.setLabel(String.valueOf(player.getSymbol()));
							this.switchPlayer();
							
							AIMove move = ai.makeMove(board);
							squares[move.i][move.j].setLabel(String.valueOf(ai.getSymbol()));

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

		int winner = board.gameOver();
		if (winner == Board.DRAW)
		{
			this.showMessage("Draw!");
		}
		else if (winner != Board.EMPTY_VAL)
		{
			Player player = (winner == Player.AI) ? ai : human;
			this.showMessage(player + " has won the game!");
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

