import java.util.Iterator;

public class Queue<T extends Comparable<T>> implements Iterable<T> {

    // Inner helper class for the Nodes of the singly linked list to be created
    private class Node {
        T data;
        Node nextNode;

        Node(T data) {
            this.data = data;
            nextNode = null;
        }
    }

    // Fields

    private Node head;
    private Node tail;

    // Constructor

    public Queue() {
        head = null;
        tail = null;
    }

    // Enqueues (inserts) the element (Node data) passed as parameter to the singly linked list according to its priority.
    public void enqueue(T data) {
        if (isEmpty()) {
            head = tail = new Node(data);
        }
        else {
            Node newNode = new Node(data);

            Node currentNode = head;

            int counter = 0;

            while (currentNode != null) {
                if (newNode.data.compareTo(currentNode.data) > 0) {
                    break;
                }
                counter++;
                currentNode = currentNode.nextNode;
            }

            if (counter == 0) {
                Node oldHead = head;
                head = newNode;
                newNode.nextNode = oldHead;
            }
            else if (counter == size()) {
                Node oldTail = tail;
                tail = newNode;
                oldTail.nextNode = newNode;
            }
            else {
                Node higherNode = head;
                for (int i = 0; i < counter - 1; i++) {
                    higherNode = higherNode.nextNode;
                }
                Node lowerNode = higherNode.nextNode;

                higherNode.nextNode = newNode;
                newNode.nextNode = lowerNode;
            }
        }
    }

    /* Dequeues (deletes) the element (Node data) passed as parameter from the singly linked list and
     * returns that element or returns null if the singly linked list is empty. */
    public T dequeue(T data) {
        if (isEmpty()) {
            return null;
        }
        else {
            if (size() == 1) {
                T ans = head.data;
                head = null;
                tail = null;
                return ans;
            }
            else {
                if (head.data.equals(data)) {
                    T ans = head.data;
                    head = head.nextNode;
                    return ans;
                }

                Node prevNode = head;
                Node currentNode = head.nextNode;

                while (currentNode != null) {
                    if (currentNode.data.equals(data)) {
                        break;
                    }
                    currentNode = currentNode.nextNode;
                    prevNode = prevNode.nextNode;
                }

                T ans = currentNode.data;
                prevNode.nextNode = currentNode.nextNode;

                return ans;
            }
        }
    }

    // Returns true if the singly linked list is empty, false otherwise.
    public boolean isEmpty() {
        return head == null;
    }

    // Returns the size of the singly linked list
    public int size() {
        if (head == null) {
            return 0;
        }
        else {
            Node currentNode = head;

            int counter = 1;

            while (currentNode.nextNode != null) {
                currentNode = currentNode.nextNode;
                counter++;
            }

            return counter;
        }
    }

    // Helper class that implements Iterator interface in order to provide iteration operation for the Queue objects
    private class QueueIterator implements Iterator<T> {
        private Node currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T ans = currentNode.data;
                currentNode = currentNode.nextNode;
                return ans;
            }
            return null;
        }
    }

    /* Method implementation of interface Iterable that returns an Iterator instance,
     * specifically an instance of the QueueIterator helper class that implements Iterator, since QueueIterator is-an Iterator. */
    @Override
    public QueueIterator iterator() {
        return new QueueIterator();
    }
}
