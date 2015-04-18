import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int N; // size of the dequeue
    private Node first; // first of dequeue
    private Node last; // last of dequeue

    // helper linked list class
    private class Node {
        private Item item = null;
        private Node next = null;
        private Node prev = null;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        N = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null || last == null || N == 0;

    }

    // return the number of items on the deque
    public int size() {
        return N;

    }

    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException("Can not add null item.");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        N++;
        if (size() == 1) {
            last = first;
        } else {
            oldfirst.prev = first;
        }

    }

    // insert the item at the end
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException("Can not add null item.");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        last.next = null;
        N++;
        if (size() == 1) {
            first = last;
        } else {
            oldLast.next = last;
        }
        
    }

    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;

        N--;
        if (size() != 0) {
            first.prev = null;
        } 
        if (size() == 0) {
            first = null;
            last = null;
        }
        return item;

    }

    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        Item item = last.item;
        last = last.prev;

        N--;
        if (size() != 0) {
            last.next = null;
        }
        if (size() == 0) {
            first = null;
            last = null;
        }
        return item;

    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListIterator();

    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing
    public static void main(String[] args) {
        Deque<String> dequeue = new Deque<String>();

       // dequeue.addLast("01Last");
       // dequeue.removeLast();
       // dequeue.addLast("12Last");
       // dequeue.removeLast();
        //dequeue.removeLast();
        //dequeue.removeLast();
        //dequeue.removeFirst();
        //dequeue.removeFirst();
        dequeue.addFirst("1First");
        dequeue.addLast("1Last");
        dequeue.removeFirst();
        dequeue.removeLast();
       dequeue.addFirst("01First");
       // dequeue.addLast("13Last");
       // dequeue.removeLast();

        Iterator<String> iterator = dequeue.iterator();
        while (iterator.hasNext()) {
            String string = (String) iterator.next();
            System.out.println(string);
        }

    }
}
