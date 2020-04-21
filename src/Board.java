public class Board
{
	private int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
	private int round = 0;

	public int[][] getBoard()
	{
		return board;
	}

	public void printBoard()
	{
		System.out.println("Move Number: " + round);
		round += 1;
		for (int[] row : board)
		{
			for (int item : row)
			{
				switch (item)
				{
					case 0:
						System.out.print("[ ]");
						break;
					case 1:
						System.out.print("[x]");
						break;
					case 2:
						System.out.print("[o]");
						break;
				}

			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	/**
	 * Places item at x, y
	 **/
	public int place(int y, int x, int option)
	{
		char player = option == 1 ? 'x' : 'o';
		if (x < 0 || x > 2 || y < 0 || y > 2 || board[y][x] != 0)
		{
			System.out.println("\nInvalid Move!");
			return -1;
		}

		System.out.println("\nPlacing " + player + " at: x=" + x + ", y=" + y);
		board[y][x] = option;
		return 1;
	}

	/**
	 * Minimax Place Method, uses overloading
	 */
	public void place(int y, int x, int option, boolean minimax)
	{
		board[y][x] = option;
	}

	public void remove(int y, int x)
	{
		board[y][x] = 0;
	}

	/**
	 * Checks if game is won
	 **/
	public int check()
	{
		//Check Horizontal
		if (board[0][0] == board[0][1] && board[0][1] == board[0][2])
			return board[0][1];
		if (board[1][0] == board[1][1] && board[1][1] == board[1][2])
			return board[1][1];
		if (board[2][0] == board[2][1] && board[2][1] == board[2][2])
			return board[2][1];

		//Check Horizontal
		if (board[0][0] == board[1][0] && board[1][0] == board[2][0])
			return board[1][0];
		if (board[0][1] == board[1][1] && board[1][1] == board[2][1])
			return board[1][1];
		if (board[0][2] == board[1][2] && board[1][2] == board[2][2])
			return board[1][2];

		//Check Diagonal
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] ||
				board[0][2] == board[1][1] && board[1][1] == board[2][0])
			return board[1][1];

		//Return 0 if nothing
		return 0;
	}
}
