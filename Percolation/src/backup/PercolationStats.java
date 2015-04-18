/*package backup;
public class PercolationStats {
	private int T = 0;

	// increase it when you call call to open grid.
	private int openGrid = 0;
	private double[] meanData;

	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException();
		}

		meanData = new double[T];
		this.T = T;
		for (int i = 0; i < T; i++) {
			openGrid = 0;
			Percolation percolation = new Percolation(N);
			while (!percolation.percolates()) {
				int temp1 = StdRandom.uniform(1, N + 1);
				int temp2 = StdRandom.uniform(1, N + 1);
				if (!percolation.isOpen(temp1, temp2)) {
					percolation.open(temp1, temp2);
					// System.out.println(percolation.isFull(temp1, temp2));
					openGrid++;
				}

			}

			meanData[i] = (double) openGrid / (N * N);
		}

	}

	// sample mean of percolation threshold
	public double mean() {

		return StdStats.mean(meanData);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		if (T == 1)
			return Double.NaN;
		return StdStats.stddev(meanData);
	}

	// returns lower bound of the 95% confidence interval
	public double confidenceLo() {

		return (mean() - (1.96 * stddev() / Math.sqrt(T)));
	}

	// returns upper bound of the 95% confidence interval
	public double confidenceHi() {
		return (mean() + (1.96 * stddev() / Math.sqrt(T)));
	}

	// test client, described below
	public static void main(String[] args) {
		int N = 0, T = 0;

		String item = StdIn.readString();
		N = Integer.parseInt(item);
		item = StdIn.readString();
		T = Integer.parseInt(item);

		PercolationStats stats = new PercolationStats(N, T);
		System.out.println("mean  = " + stats.mean());
		System.out.println("stddev = " + stats.stddev());
		System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());

	}
}
*/