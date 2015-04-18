import java.util.ArrayList;

public class Board {

	// (where blocks[i][j] = block in row i, column j)
	// construct a board from an N-by-N array of blocks
	private int[][] tiles;
	// use char array to save memory.
	private int N;

	private int iFree;
	private int jFree;

	public Board(int[][] blocks) {
		N = blocks.length;
		tiles = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.tiles[i][j] = blocks[i][j];
				if (blocks[i][j] == 0) {
					iFree = i;
					jFree = j;
				}
			}
		}

	}

	// board dimension N
	public int dimension() {
		return N;

	}

	// number of blocks out of place
	public int hamming() {
		int hamm = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (this.tiles[i][j] != (i * N + j + 1)) {
					if (this.tiles[i][j] == 0)
						continue;
					hamm++;
				}
			}
		}
		return hamm;

	}

	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int sum = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (this.tiles[i][j] != (i * N + j + 1)) {
					if (this.tiles[i][j] == 0)
						continue;
					sum = sum + distance((this.tiles[i][j]), i, j);// send value
																	// n
																	// this.tiles[i][j]
																	// and its
																	// position
																	// and where
																	// it should
																	// be.
																	// , i and j
																	// index.
				}

			}
		}

		return sum;

	}

	// this will calculate the Manhattan distance of number n and its position
	// in unsolved board.
	// this method is called for every element.
	// this is not required as you can calculate the position of element by
	// adding the indexex.
	// try to optimize this later.
	private int distance(int n, int iIndex, int jIndex) {
		int dist = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if ((i * N + j + 1) == n) {

					dist = Math.abs(i - iIndex) + Math.abs(j - jIndex);

				}

			}
		}

		return dist;
	}

	// is this board the goal board?
	public boolean isGoal() {
		boolean match = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i * N + j + 1) != this.tiles[i][j]) {
					if ((i * N + j + 1) == N * N)
						continue;// to mark the end. End can have either 0 or
									// length square.
					match = false;
					break;
				}
			}
		}
		return match;

	}

	// a board obtained by exchanging two adjacent blocks in the same row
	public Board twin() {
		int[][] twin = cloneBoard(this.tiles);
		int i1 = StdRandom.uniform(N);
		int j1 = StdRandom.uniform(N - 1);
		while (twin[i1][j1] == 0 || twin[i1][j1 + 1] == 0) {
			i1 = StdRandom.uniform(N);
			j1 = StdRandom.uniform(N - 1);
		}
		int temp = twin[i1][j1];
		twin[i1][j1] = twin[i1][j1 + 1];
		twin[i1][j1 + 1] = temp;
		Board board = new Board(twin);
		return board;

	}

	// does this board equal y?
	public boolean equals(Object y) {
		boolean match = true;
		if (y == null) {
			return false;
		}

		if (y instanceof Board) {
			Board temp = (Board) y;
			if (((Board) y).dimension() != this.dimension()) {
				return false;
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (this.tiles[i][j] != temp.tiles[i][j]) {
						match = false;
						break;
					}

				}
			}

			return match;
		}

		return false;

	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		ArrayList<Board> list = new ArrayList<Board>();
		int iTemp = iFree;
		int jTemp = jFree;
		int[][] neigh = cloneBoard(this.tiles);
		if (iTemp + 1 < N) {
			int temp = neigh[iTemp][jTemp];
			neigh[iTemp][jTemp] = neigh[iTemp + 1][jTemp];
			neigh[iTemp + 1][jTemp] = temp;
			Board board = new Board(neigh);
			list.add(board);
			neigh = cloneBoard(this.tiles);
		}
		if (iTemp - 1 >= 0) {
			int temp = neigh[iTemp][jTemp];
			neigh[iTemp][jTemp] = neigh[iTemp - 1][jTemp];
			neigh[iTemp - 1][jTemp] = temp;
			Board board = new Board(neigh);
			list.add(board);
			neigh = cloneBoard(this.tiles);
		}
		if (jTemp + 1 < N) {
			int temp = neigh[iTemp][jTemp];
			neigh[iTemp][jTemp] = neigh[iTemp][jTemp + 1];
			neigh[iTemp][jTemp + 1] = temp;
			Board board = new Board(neigh);
			list.add(board);
			neigh = cloneBoard(this.tiles);
		}
		if (jTemp - 1 >= 0) {
			int temp = neigh[iTemp][jTemp];
			neigh[iTemp][jTemp] = neigh[iTemp][jTemp - 1];
			neigh[iTemp][jTemp - 1] = temp;
			Board board = new Board(neigh);
			list.add(board);
		}
		return list;

	}

	private int[][] cloneBoard(int b[][]) {
		N = b.length;
		int tilesTemp[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tilesTemp[i][j] = b[i][j];

			}
		}
		return tilesTemp;
	}

	// string representation of the board (in the output format specified below)
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(N + "\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				s.append(String.format("%2d ", tiles[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

}
