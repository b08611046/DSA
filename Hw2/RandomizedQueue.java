import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int num = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[8];
        num = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return num == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return num;
    }

    private void resize(int len) {
        Item[] tmp = (Item[]) new Object[len];
        for (int i = 0; i < num; i++) {
            tmp[i] = queue[i];
        }
        queue = tmp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null");
        }

        if (this.size() == queue.length) {
            resize(2 * queue.length);
        }
        queue[num++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        
        int delIndex = StdRandom.uniform(num);
        Item item = queue[delIndex];
        if (delIndex !=  num-1) {
            queue[delIndex] = queue[num-1];
        }
        /*
        for (int i=delIndex; i<num-1; i++) {
            queue[i] = queue[i+1];
        }
        */
        queue[num-1] = null;
        num--;

        if (num > 0 && num == queue.length/4) {
            resize(queue.length/2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return queue[StdRandom.uniform(num)];
    }

    public Iterator<Item> iterator() {
        return new RandQueueiterator();
    }

    // return an independent iterator over items in random order
    private class RandQueueiterator implements Iterator<Item> {
        private Item[] item = (Item[]) new Object[queue.length];
        private int n = num;

        public RandQueueiterator() {
            for (int i = 0; i < num; i++) {
                item[i] = queue[i];
            }
        }

        public boolean hasNext() {
            return n != 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int delIndex = StdRandom.uniform(n);
            Item item2 = item[delIndex];

            if (delIndex != n-1) {
                item[delIndex] = item[n-1];
            }
            item[n-1] = null;
            n--;
            return item2;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        System.out.println(queue.size());

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");
        System.out.println(queue.size());

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.size());

        Iterator<String> itr1 = queue.iterator();
        Iterator<String> itr2 = queue.iterator();

        while (itr1.hasNext()) {
            System.out.println(itr1.next());
        }

        while (itr2.hasNext()) {
            System.out.println(itr2.next());
        }
    }
    
}
