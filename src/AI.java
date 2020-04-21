import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class AI
{
	private double minimax(Board board, String option)
	{
		int score = board.check();
		if (score == 1)
			return -10.0;
		else if (score == 2)
			return 10.0;

		int[][] testBoard = board.getBoard();
		ArrayList<int[]> availablePosition = new ArrayList<int[]>();
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++)
				if (testBoard[y][x] == 0)
					availablePosition.add(new int[] {y, x});

		if (score == 0 && availablePosition.isEmpty())
			return 0.0;

		if (option.equals("Max"))
		{
			double bestScore = Double.NEGATIVE_INFINITY;
			for (int[] position : availablePosition)
			{
				board.place(position[0], position[1], 2, true);
				bestScore = Double.max(bestScore, minimax(board, "Min"));
				board.remove(position[0], position[1]);
			}
			return bestScore;
		}
		if (option.equals("Min"))
		{
			double bestScore = Double.POSITIVE_INFINITY;
			for (int[] position : availablePosition)
			{
				board.place(position[0], position[1], 1, true);
				bestScore = Double.min(bestScore, minimax(board, "Max"));
				board.remove(position[0], position[1]);
			}
			return bestScore;
		}
		return 0.0;
	}

	public void makeMove(Board board)
	{
		int[][] testBoard = board.getBoard();
		ArrayList<int[]> availablePosition = new ArrayList<int[]>();
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++)
				if (testBoard[y][x] == 0)
					availablePosition.add(new int[] {y, x});

		int[] bestMove = null;
		double bestScore = Double.NEGATIVE_INFINITY;
		//Test out each available position, to get score.
		for (int[] position : availablePosition)
		{
			board.place(position[0], position[1], 2, true);
			double score = minimax(board, "Min");
			System.out.println(Integer.toString(position[0]) + position[1] + score);
			if (score > bestScore)
			{
				bestScore = score;
				bestMove = position;
			}
			board.remove(position[0], position[1]);
		}
		board.place(bestMove[0], bestMove[1], 2);
	}

}
