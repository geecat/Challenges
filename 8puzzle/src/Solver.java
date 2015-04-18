import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Solver {

	private int move = 0;
	private volatile boolean isSolve = true;
	private ArrayList<Board> solution = new ArrayList<Board>();
	private ArrayList<Board> solutionTwin = new ArrayList<Board>();
	private MinPQ<SearchNode> mq;
	private MinPQ<SearchNode> mqTwin;
	// TreeSet<Board> set = new TreeSet<>();
	// find a solution to the initial board (using the A* algorithm)
	private Board board;
	private Board twin;
	private Board prev = null;
	private Board prevTwin = null;
	private final Comparator<SearchNode> BOARD_ORDER = new BoardOrder();
	private SearchNode bq;
	private SearchNode bqT;
	public Solver(Board initial) {

		board = initial;
		twin = board.twin();
		mq = new MinPQ<SearchNode>(BOARD_ORDER);
		mqTwin = new MinPQ<SearchNode>(BOARD_ORDER);
		bq = new SearchNode(board, prev, move);
		mq.insert(bq);
		bqT = new SearchNode(twin, prev, move);
		mqTwin.insert(bqT);
		// set.add(board);
		// solution.add(board);
		while (!board.isGoal()) {
			if (twin.isGoal()) {
				isSolve = false;
				solution = null;
				return;
			}
			
			bq = mq.delMin();
			bqT = mqTwin.delMin();
			board = bq.boardS;
			twin = bqT.boardS;
			prev = bq.prevS;
			prevTwin = bqT.prevS;
			System.out.println("Priority: "+(board.manhattan()+move)+" Total move is: "+(move));
			System.out.println(board.toString());
			solution.add(board);
			solutionTwin.add(twin);
			// System.out.println("Neighbour of:"+board+" are :");
			for (Board iterable_element : board.neighbors()) {
				// System.out.println("Neighbour :"+iterable_element+"");
				// System.out.println("Priority: "+(iterable_element.manhattan()+move)+" Total move is: "+(move));
				if (prev != null && !prev.equals(iterable_element) && !solution.contains(iterable_element))
					bq = new SearchNode(iterable_element, prev, move);
					mq.insert(bq);
			}
			for (Board iterable_element : twin.neighbors()) {
				// System.out.println("Neighbour :"+iterable_element+"");
				// System.out.println("Priority: "+(iterable_element.manhattan()+move)+" Total move is: "+(move));
				if (prevTwin != null && !prevTwin.equals(iterable_element) && !solutionTwin.contains(iterable_element))
					bqT = new SearchNode(iterable_element, prev, move);
					mqTwin.insert(bqT);
			}
			move++;

		}
		// isSolve = true;

	}
	
	
	private class SearchNode {
		Board boardS;
		Board prevS;
		int moveS;
		int priorityS;
		SearchNode(Board b, Board p, int m) {
			this.boardS = b;
			this.prevS = p;
			this.moveS = m;
			this.priorityS = b.manhattan() + m;
		}
		
	}
	private class BoardOrder implements Comparator<SearchNode> {

		@Override
		public int compare(SearchNode o1, SearchNode o2) {
			int result;
			if ((o1.priorityS < o2.priorityS)) {
				result = -1;
			} else if (o2.priorityS == o1.priorityS) {
				result = 0;
			} else {
				result = 1;
			}

			return result;
		}

	}

	// is the initial board solvable?
	public boolean isSolvable() {

		return isSolve;

	}

	// min number of moves to solve initial board; -1 if no solution
	public int moves() {
		if (!isSolve) {
			return -1;
		}
		if (move == 0) {
			return 0;
		} else {
			return move - 1;
		}

	}

	// sequence of boards in a shortest solution; null if no solution
	public Iterable<Board> solution() {

		return solution;

	}

	// solve a slider puzzle (given below)
	public static void main(String[] args) throws NumberFormatException, IOException {
		// create initial board from file
		// In in = new In(args[0]);
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(rd.readLine());
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++) {
			String input[] = rd.readLine().split(" ");
			int n = 0;
			for (int j = 0; j < N;) {
				if (input[n].equals("")) {
					n++;
					continue;
				}

				blocks[i][j] = Integer.parseInt(input[n]);
				n++;
				j++;
			}

		}

		Board initial = new Board(blocks);

		// solve the puzzle]
		Solver solver = null;
		try {
			solver = new Solver(initial);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return;
		}

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
			StdOut.println("Minimum number of moves = " + solver.moves());
		}
	}
}