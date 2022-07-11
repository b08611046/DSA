import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int num;

    private class Node {
        Item item;
        Node next;
        Node prev;

        Node(Item item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }
    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.num = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return num;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null ) {
            throw new IllegalArgumentException("null");
        }

        else if (this.isEmpty()) {
            first = new Node(item);
            first.item = item;
            last = first;
        }

        else {
            Node node = new Node(item);
            node.item = item;
            node.next = first;
            first.prev = node;
            first = node;
        }
        num++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null");
        }

        else if (this.isEmpty()) {
            last = new Node(item);
            last.item = item;
            first = last;
        }

        else {
            Node node = new Node(item);
            node.item = item;
            last.next = node;
            node.prev = last;
            last = node;
        }
        num++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) throw new NoSuchElementException();

        Item item = first.item;
        if (this.size() == 1) {
            first = null;
            last = first;
        }

        else {
            first.next.prev = null;
            first = first.next;
        }
        num--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (num == 0)   throw new NoSuchElementException();

        Item item = last.item;
        if (num == 1) {
            first = null;
            last = first;
        }
        else {
            last.prev.next = null;
            last = last.prev;
        }
        num--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deq = new Deque<String>();
        System.out.println(deq.size());

        deq.addFirst("A");
        deq.addFirst("B");
        deq.addFirst("C");
        deq.addFirst("D");
        deq.addFirst("E");
        System.out.println(deq.size());

        deq.removeLast();
        System.out.println(deq.size());

        deq.removeFirst();
        System.out.println(deq.size());

        deq.addLast("A");
        System.out.println(deq.size());

        deq.addFirst("E");
        System.out.println(deq.size());

        Iterator<String> itr = deq.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
