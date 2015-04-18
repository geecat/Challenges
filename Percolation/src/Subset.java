public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int number = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readString());
        }
       // String [] store = new String[number];
        for (int i = 0; i < number; i++) {
            //String s = q.dequeue();
            StdOut.println(q.dequeue());
        }
        
    }
}
