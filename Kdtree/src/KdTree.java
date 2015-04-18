import java.util.TreeSet;

public class KdTree {

	private Node node = null;;
	private int size = 0;
	private RectHV rect;

	// construct an empty set of points
	public KdTree() {

		node = new Node();
	}

	// is the set empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// number of points in the set
	public int size() {
		return size;
	}

	// add the point p to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (isEmpty()) {
			node.p = p;
			rect = new RectHV(0, 0, 1, 1);
			node.rect = rect;
			// System.out.println(rect.toString());
			size++;
		} else {
			Node temp = new Node();
			temp.p = p;
			insertNode(node, temp, 0);
		}

	}

	private void insertNode(Node node, Node temp, int level) {
		// if the level is even use x axis, if the level is odd use the y axis.
		boolean xaxis = (level % 2 == 0);
		int compare;
		if (node.p.equals(temp.p)) {
			return;
		}
		if (xaxis) {
			compare = Point2D.X_ORDER.compare(node.p, temp.p);
		} else {
			compare = Point2D.Y_ORDER.compare(node.p, temp.p);
		}

		// What should I do if a point has the same x-coordinate as the point in
		// a node when inserting / searching in a 2d-tree? Go the right subtree
		// as specified.
		if (compare < 0 || xaxis && compare <= 0) {
			if (node.rt == null) {
				node.rt = temp;
				rect = createRect(node, temp, xaxis);
				node.rt.rect = rect;
				// System.out.println(rect.toString());
				size++;
				return;
			} else {
				insertNode(node.rt, temp, level + 1);
			}
		} else {
			if (node.lb == null) {
				node.lb = temp;
				rect = createRect(node, temp, xaxis);
				node.lb.rect = rect;
				// System.out.println(rect.toString());
				size++;
				return;
			} else {
				insertNode(node.lb, temp, level + 1);
			}
		}
	}

	private RectHV createRect(Node node, Node temp, boolean xory) {
		RectHV rect = null;
		if (xory) {
			// if point is on left
			int comp = Point2D.X_ORDER.compare(node.p, temp.p);
			if (comp > 0) {
				rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax());
				return rect;
			} else if (comp < 0) {
				// write for if the point is on right.
				rect = new RectHV(node.p.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
				return rect;
			}
		} else {
			// if the point is on bottom
			int comp = Point2D.Y_ORDER.compare(node.p, temp.p);
			if (comp > 0) {
				rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.p.y());
				return rect;
			} else if (comp < 0) {
				// write for if the point is on right.
				rect = new RectHV(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.rect.ymax());
				return rect;
			}
			// write for if the point is on top.
		}
		return node.rect;
	}

	// does the set contain the point p?
	public boolean contains(Point2D p) {
		if(isEmpty())return false;
		return isPresent(node, p, 0);

	}

	// need to check with all the element if the present element is present.
	// can we optimize it using the kd-tree property?
	private boolean isPresent(Node node, Point2D p, int level) {
		boolean left = false;
		boolean right = false;
		boolean finalState = false;
		boolean xaxis = (level % 2 == 0);
		int compare;
		if (node == null) {
			return false;
		} else if (node.p.equals(p)) {
			return true;
		}

		if (xaxis) {
			compare = Point2D.X_ORDER.compare(node.p, p);
		} else {
			compare = Point2D.Y_ORDER.compare(node.p, p);
		}

		if (compare < 0 || xaxis && compare <= 0) {

			right = isPresent(node.rt, p, level + 1);

		} else {

			left = isPresent(node.lb, p, level + 1);

		}
		if (left) {
			finalState = left;
		}
		if (right) {
			finalState = right;
		}
		return finalState;
	}

	// draw all of the points to standard draw
	public void draw() {

	}

	// all points in the set that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		TreeSet<Point2D> iterable = new TreeSet<Point2D>();
		if(isEmpty())return iterable;
		isPresentRect(node, rect, iterable);
		return iterable;
	}

	private void isPresentRect(Node node, RectHV rect, TreeSet<Point2D> iterable) {
		if (node == null) {
			return;
		} else if (rect.intersects(node.rect)) {
			if (rect.contains(node.p)) {
				iterable.add(node.p);
			}
			isPresentRect(node.lb, rect, iterable);
			isPresentRect(node.rt, rect, iterable);
			return;
		}

	}

	// a nearest neighbor in the set to p; null if set is empty
	public Point2D nearest(Point2D p) {
		if (isEmpty())
			return null;
		Point2D nearest = node.p;
		nearest = nearestPointNew(node, p, nearest, Double.MAX_VALUE, 0);
		return nearest;
	}

	private Point2D nearestPointNew(Node node, Point2D pQuery, Point2D pFound, double distPrev, int level) {
		if (node == null) {
			return pFound;
		}
		boolean xaxis = (level % 2 == 0);
		int compare;
		double distFound=Double.MAX_VALUE;
		if (xaxis) {
			compare = Point2D.X_ORDER.compare(node.p, pQuery);
		} else {
			compare = Point2D.Y_ORDER.compare(node.p, pQuery);
		}
		//it will tell weather node is greater compared to query point in respect of x and y.
		if (compare > 0) {
			// query point is in left
			// or point is on bottom
			double dist = node.p.distanceSquaredTo(pQuery);
			if (dist < distPrev) {
				pFound = node.p;
				distPrev = dist;
			}
			Point2D pointlb = nearestPointNew(node.lb, pQuery, pFound, distPrev, level+1);
			// check for rectangle pruning.
			Point2D pointrt = null;
			if (node.rt != null) {
				double distPru = node.rt.rect.distanceSquaredTo(pQuery);
				//calculate the distance of found point and query point.
				distFound = pointlb.distanceSquaredTo(pQuery);
				if (distPru < distFound) {
					pointrt = nearestPointNew(node.rt, pQuery, pFound, distFound, level+1);
				}
			}

			//if less than found distance then update.
			if (pointrt != null && pointrt.distanceSquaredTo(pQuery) <= distFound) {
				pFound = pointrt;
			} else if (pointlb.distanceSquaredTo(pQuery) <= distFound) {
				pFound = pointlb;
			}

		} else {
			// query point is in right of the node.
			// or it is on top
			// so first check right tree and then check for pruning and then
			// check left if required.
			double dist = node.p.distanceSquaredTo(pQuery);
			if (dist < distPrev) {
				pFound = node.p;
				distPrev = dist;
			}
			Point2D pointrt = nearestPointNew(node.rt, pQuery, pFound, distPrev, level+1);
			// check for rectangle pruning.
			Point2D pointlb = null;
			if (node.lb != null) {
				double distPru = node.lb.rect.distanceSquaredTo(pQuery);
				//calculate the distance of found point and query point.
				distFound = pointrt.distanceSquaredTo(pQuery);
				if (distPru < distFound) {
					pointlb = nearestPointNew(node.lb, pQuery, pFound, distFound, level+1);
				}
			}
			if (pointlb != null && pointlb.distanceSquaredTo(pQuery) <= distFound) {
				pFound = pointlb;
			} else if (pointrt.distanceSquaredTo(pQuery) <= distFound) {
				pFound = pointrt;
			}
		}
		return pFound;

	}

	private static class Node {
		private Point2D p; // the point
		private RectHV rect; // the axis-aligned rectangle corresponding to this
								// node
		private Node lb; // the left/bottom subtree
		private Node rt; // the right/top subtree

	}

	public static void main(String[] args) {
		In in = new In("circle10.txt");
		KdTree kdtree = new KdTree();
		
		while (!in.isEmpty()) {
			double x = in.readDouble();
			double y = in.readDouble();
			Point2D p = new Point2D(x, y);
			System.out.println(kdtree.contains(p));
			kdtree.insert(p);
			// System.out.println(kdtree.contains(p));

		}
		/*
		 * RectHV rect = new RectHV(.20, .65, 1, 1); Iterable<Point2D> range =
		 * kdtree.range(rect); for (Point2D point2d : range) {
		 * System.out.println(point2d.toString()); }
		 */
		Point2D p = new Point2D(.8, .9);
		Point2D nearest = kdtree.nearest(p);
		System.out.println(nearest);
	}
}
