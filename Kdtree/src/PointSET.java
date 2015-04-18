import java.util.Iterator;
import java.util.TreeSet;

public class PointSET {
	private TreeSet<Point2D> set;

	// construct an empty set of points
	public PointSET() {
		set = new TreeSet<Point2D>();
	}

	// is the set empty?
	public boolean isEmpty() {
		return set.isEmpty();
	}

	// number of points in the set
	public int size() {
		return set.size();
	}

	// add the point p to the set (if it is not already in the set)
	public void insert(Point2D p) {
		set.add(p);
	}

	// does the set contain the point p?
	public boolean contains(Point2D p) {
		return set.contains(p);
	}

	// draw all of the points to standard draw
	public void draw() {
		for (Iterator<Point2D> iterator = set.iterator(); iterator.hasNext();) {
			Point2D type = iterator.next();
			type.draw();

		}

	}

	// all points in the set that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		TreeSet<Point2D> iterable = new TreeSet<Point2D>();
		for (Iterator<Point2D> iterator = set.iterator(); iterator.hasNext();) {
			Point2D p = iterator.next();
			if (rect.contains(p)) {
				iterable.add(p);
			}
		}

		return iterable;
	}

	// a nearest neighbor in the set to p; null if set is empty
	public Point2D nearest(Point2D p) {

		Point2D nearest = null;
		double distPrev = Double.MAX_VALUE, dist = Double.MAX_VALUE;
		for (Iterator<Point2D> iterator = set.iterator(); iterator.hasNext();) {
			Point2D point = iterator.next();
			dist = point.distanceSquaredTo(p);
			if (dist < distPrev) {
				distPrev = dist;
				nearest = point;
			}

		}

		return nearest;
	}
}
