import java.util.*;
import java.util.regex.*;

public class Main
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		AI bot = new AI();
		Pattern pattern = Pattern.compile("\\D");
		Board board = new Board();
		board.printBoard();

//		ArrayList<int[]> moves = new ArrayList<int[]>();
//		moves.add(new int[]{1, 0, 0});
//		moves.add(new int[]{2, 0, 1});
//		moves.add(new int[]{1, 0, 0});
//		moves.add(new int[]{1, 2, 0});
//		moves.add(new int[]{1, 0, 1});
//		moves.add(new int[]{1, 0, 2});
//		moves.add(new int[]{1, 1, 1});
//		moves.add(new int[]{1, 0, 0});
//
//		Iterator<int[]> move = moves.iterator();

		boolean playerTurn = true;
		while (board.check() == 0)
		{
			if (!playerTurn)
			{
				bot.makeMove(board);
				System.out.println("Bots Turn Completed");
			}
			while (playerTurn)
			{
				int[] choice = {};
				while (choice.length == 0)
				{
					System.out.print("Please key in your desired position (ie 2, 2): \n");
					String input = scanner.nextLine();
					String[] results = pattern.split(input);
					if (results.length != 2)
					{
						System.out.print("\nInvalid Input!\n");
					} else
					{
						choice = Arrays.stream(results).mapToInt(Integer::parseInt).toArray();
					}
				}
				if (board.place(choice[0], choice[1], 1) == 1)
					break;
			}
			board.printBoard();
			playerTurn = !playerTurn;
		}
		char winner = board.check() == 1 ? 'x' : 'o';
		System.out.println("Winner: " + winner);
	}
}
