import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.valueOf(args[0]);
        String str = null;

        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            str = StdIn.readString();
            queue.enqueue(str);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
