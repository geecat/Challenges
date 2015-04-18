/*package backup;
public class Percolation {
	// between 1 and N
	private int[][] grid;
	private int N = 0;
	private WeightedQuickUnionUF wFind;

	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		if (N <= 0) {
			throw new IllegalArgumentException();
		}
		this.N = N;
		this.grid = new int[N + 1][N + 1];
		wFind = new WeightedQuickUnionUF((N * N) + 2);
	}

	// open site (row i, column j) if it is not already
	public void open(int i, int j) {
		if (this.indexBoundCheck(i, j)) {
			this.indexBoundThrow();
		}

		grid[i][j] = 1;
		if (i == 1) {
			wFind.union(0, xyTo1D(i, j));
		}
		if (i == N) {
			wFind.union(((N * N) + 1), xyTo1D(i, j));
		}
		if (!indexBoundCheck(i - 1, j) && isOpen(i - 1, j)) {
			int temp1 = xyTo1D(i, j);
			int temp2 = xyTo1D(i - 1, j);
			wFind.union(temp1, temp2);

		}
		if (!indexBoundCheck(i + 1, j) && isOpen(i + 1, j)) {
			int temp1 = xyTo1D(i, j);
			int temp2 = xyTo1D(i + 1, j);
			wFind.union(temp1, temp2);

		}
		if (!indexBoundCheck(i, j - 1) && isOpen(i, j - 1)) {
			int temp1 = xyTo1D(i, j);
			int temp2 = xyTo1D(i, j - 1);
			wFind.union(temp1, temp2);

		}
		if (!indexBoundCheck(i, j + 1) && isOpen(i, j + 1)) {
			int temp1 = xyTo1D(i, j);
			int temp2 = xyTo1D(i, j + 1);
			wFind.union(temp1, temp2);

		}

	}

	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		if (this.indexBoundCheck(i, j)) {
			this.indexBoundThrow();
		}
		return grid[i][j] == 1 ? true : false;
	}

	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		// is connected
		if (this.indexBoundCheck(i, j)) {
			this.indexBoundThrow();
		}
		boolean isConnected = false;
		for (int k = 1; k <= N; k++) {
			if (isOpen(i, j)) {
				isConnected = wFind.connected(0, xyTo1D(i, j));
			}
			if (isConnected) {
				break;
			}

		}

		return wFind.connected(0, xyTo1D(i, j));//wFind.find(xyTo1D(i,j)) == 0 ? true : false;
	}

	// does the system percolate?
	public boolean percolates() {
		return wFind.find(0) == wFind.find(N * N + 1);// wFind.connected(0, N *
														// N + 1);
	}

	private int xyTo1D(int i, int j) {
		if (this.indexBoundCheck(i, j)) {
			this.indexBoundThrow();
		}
		return ((i - 1) * N + j);
	}

	private boolean indexBoundCheck(int i, int j) {
		if (i <= 0 || j <= 0 || i > N || j > N) {
			return true;
		}
		return false;
	}

	private void indexBoundThrow() {
		throw new IndexOutOfBoundsException();
	}
}
*/