import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;
    private int count;

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the queue
    public int size() {
        return count;

    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("Can not add null item.");
        if (N == a.length)
            resize(2 * a.length);
        a[N++] = item;
        count++;
    }

    // delete and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        int random = StdRandom.uniform(0, N);
        while (a[random] == null) {
            random = StdRandom.uniform(0, N);
        }
        Item item = a[random];
        a[random] = null;
        count--;
        if (count > 0 && count == a.length / 4)
            resize(a.length / 2);
        return item;

    }

    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        int random = StdRandom.uniform(0, N);
        while (a[random] == null) {
            random = StdRandom.uniform(0, N);
        }
        return a[random];

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();

    }

    private class RandomArrayIterator implements Iterator<Item> {
        private Item[] temp;
        private int countTemp;

        public RandomArrayIterator() {
            temp = (Item[]) new Object[count];
            int countItem = 0;
            for (int i = 0; i < N; i++) {
                if (a[i] == null)
                    continue;
                temp[countItem++] = a[i];
            }
            StdRandom.shuffle(temp);
            countTemp = countItem;
        }

        public boolean hasNext() {
            return countTemp > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return temp[--countTemp];
        }
    }

    // Resize
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        int countItem = 0;
        for (int i = 0; i < N; i++) {
            if (a[i] == null)
                continue;
            temp[countItem++] = a[i];
        }

        a = temp;
        N = countItem;
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        //q.enqueue(null);
        //q.dequeue();
        q.enqueue(1);
        System.out.println(q.dequeue());
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        System.out.println(q.dequeue());
        //System.out.println(q.sample());
        //System.out.println(q.sample());
        Iterator<Integer> iterator = q.iterator();
        /*while (iterator.hasNext()) {

            //System.out.println((Integer) iterator.next());
        }*/
        iterator = q.iterator();
        /*while (iterator.hasNext()) {

        //    System.out.println((Integer) iterator.next());
        }*/
    }
}
