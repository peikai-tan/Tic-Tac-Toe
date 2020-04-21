import java.util.*;

public class AI
{
	private double[] minimax(Board board, boolean Maximising)
	{
		int score = board.check();
		if (score == 1)
			return new double[]{-10.0, 0, 0};
		else if (score == 2)
			return new double[]{10.0, 0, 0};

		int[][] testBoard = board.getBoard();
		ArrayList<int[]> availablePosition = new ArrayList<int[]>();
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++)
				if (testBoard[y][x] == 0)
					availablePosition.add(new int[]{y, x});

		if (score == 0 && availablePosition.isEmpty())
			return new double[]{0.0, 0, 0};

		double[] bestScore = Maximising ? new double[]{Double.NEGATIVE_INFINITY} : new double[]{Double.POSITIVE_INFINITY};
		int[][] bestMove = {{0, 0}};
		int option = Maximising ? 2 : 1;
		availablePosition.forEach(position ->
		{
			board.place(position[0], position[1], option, true);
			double[] results = minimax(board, !Maximising);
			if (Maximising)
			{
				if (results[0] > bestScore[0])
				{
					bestScore[0] = results[0];
					bestMove[0] = position;
				}
			} else if (results[0] < bestScore[0])
			{
				bestScore[0] = results[0];
				bestMove[0] = position;
			}
			board.remove(position[0], position[1]);
		});
		return new double[]{bestScore[0], bestMove[0][0], bestMove[0][1]};
	}

	public void makeMove(Board board)
	{
		double[] results = minimax(board, true);
		board.place((int) results[1], (int) results[2], 2);
	}

}
