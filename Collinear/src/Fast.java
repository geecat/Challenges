import java.util.ArrayList;
import java.util.Arrays;

public class Fast {
    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        // StdDraw.setPenRadius(0.01); // make the points a bit larger

        // read in the input
       // String filename = args[0];
       // In in = new In(filename);
        In in = new In("mystery10089.txt");
        int N = in.readInt();
        Point[] data = new Point[N];
        Point[] dataOri = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            data[i] = p;
            dataOri[i] = p;
            p.draw();

        }
        // data[2].drawTo(data[4]);
        StdDraw.show(0);
        Arrays.sort(dataOri);

        // iterate over original array.
        for (int i = 0; i < N; i++) {
            System.arraycopy(dataOri, 0, data, 0, dataOri.length);
            Arrays.sort(data, dataOri[i].SLOPE_ORDER);
            ArrayList<Point> list = new ArrayList<Point>();
            int count = 1;
            int startIndex = 1;
            Point p1 = data[0];
            list.add(p1);
            // Point p2 = data[i+1];
            Point pEnd = null;
            // double slope =p1.slopeTo(p2);
            double slope;
            double slopeNext;
            StringBuilder builder = new StringBuilder(p1.toString());
            for (int j = startIndex + 1; j < N; j++) {
                slope = p1.slopeTo(data[startIndex]);
                if (count == 1) {
                    builder.append(" -> " + data[startIndex].toString());
                    list.add(data[startIndex]);
                }

                slopeNext = p1.slopeTo(data[j]);

                if (slope != slopeNext) {
                    if (count >= 3) {
                        boolean print = false;
                        // check in the list if the p1 is the origin.
                        for (int k = 1; k < list.size(); k++) {
                            if (list.get(0).compareTo(list.get(k)) < 0) {
                                print = true;
                            } else {
                                print = false;
                                break;
                            }
                        }
                        if (print) {
                            p1.drawTo(pEnd);
                            StdDraw.show(0);
                            StdOut.println(builder.toString());
                        }

                    }
                    count = 1;
                    builder = new StringBuilder(p1.toString());
                    list = new ArrayList<Point>();
                    list.add(p1);
                    startIndex = j;
                    // break;
                } else {
                    count++;
                    builder.append(" -> " + data[j].toString());
                    pEnd = data[j];
                    list.add(pEnd);
                }

            }
            if (count >= 3) {
                boolean print = false;
                // check in the list if the p1 is the origin.
                for (int k = 1; k < list.size(); k++) {
                    if (list.get(0).compareTo(list.get(k)) < 0) {
                        print = true;
                    } else {
                        print = false;
                        break;
                    }
                }
                if (print) {
                    p1.drawTo(pEnd);
                    StdDraw.show(0);
                    StdOut.println(builder.toString());
                }

            }

        }

    }
}
