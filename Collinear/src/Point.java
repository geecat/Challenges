/*************************************************************************
 * Name: Ravi
 * Email: ravikumarchaurasia@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder(); // YOUR
                                                                    // DEFINITION
                                                                    // HERE

    private final int x; // x coordinate
    private final int y; // y coordinate

    private class SlopeOrder implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            // TODO Auto-generated method stub
            double slope1 = slopeTo(o1);
            double slope2 = slopeTo(o2);
            /*
             * if((slope1==Double.POSITIVE_INFINITY)){ return 1; }
             */
            /*
             * if((slope1==Double.NEGATIVE_INFINITY)){ return 0; }
             */
            int result;
            if (slope1 < slope2) {
                result = -1;
            } else if (slope1 == slope2) {
                result = 0;
            } else {
                result = 1;
            }
            return result;
        }

    }

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        double dx = that.x - this.x;
        double dy = that.y - this.y;
        if (that.y == this.y && that.x == this.x) {
            return Double.NEGATIVE_INFINITY;
        }

        if (dx == 0) {
            return Double.POSITIVE_INFINITY;
        }
        if (dy == 0) {
            return +0.0;
        }

        return dy / dx;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */

        int result;
        if ((this.y < that.y) || (this.y == that.y && this.x < that.x)) {
            result = -1;
        } else if (that.y == this.y && that.x == this.x) {
            result = 0;
        } else {
            result = 1;
        }

        return result;

    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}