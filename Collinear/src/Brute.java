import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        // StdDraw.setPenRadius(0.01); // make the points a bit larger

        // read in the input

        String filename = args[0];
        In in = new In(filename);

        // In in = new In("input80.txt");
        int N = in.readInt();
        Point[] data = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            data[i] = p;
            p.draw();

        }
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
        Arrays.sort(data);
        Point p1, p2, p3, p4;
        double slope1, slope2, slope3;
        for (int i = 0; i < N - 3; i++) {
            p1 = data[i];
            for (int j = i + 1; j < N - 2; j++) {
                p2 = data[j];
                slope1 = p1.slopeTo(p2);
                for (int k = j + 1; k < N - 1; k++) {
                    p3 = data[k];
                    slope2 = p1.slopeTo(p3);
                    if (slope1 != slope2)
                        // break;
                        continue;
                    for (int l = k + 1; l < N; l++) {
                        p4 = data[l];
                        slope3 = p1.slopeTo(p4);
                        if (slope1 == slope3) {
                            p1.drawTo(p4);
                            // data[0].drawTo(data[3]);
                            StdDraw.show(0);
                            StdOut.println(p1 + " -> " + p2 + " -> " + p3 + " -> " + p4);
                            continue;
                        }

                    }
                }

            }

        }

    }
}
