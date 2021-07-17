import java.util.Iterator;

public class Stack<T> implements Iterable<T> {

    // Inner helper class for the Nodes of the singly linked list to be created
    private class Node {
        T data;
        Node nextNode;

        public Node(T data) {
            this.data = data;
            nextNode = null;
        }
    }

    // Field

    Node top;

    // Constructor

    public Stack() {
        top = null;
    }

    // Pushes the element (Node data) passed as parameter to the end of the singly linked list.
    public void push(T data) {
        Node newNode = new Node(data);

        if (!isEmpty()) {
            newNode.nextNode = top;
        }

        top = newNode;
    }

    /* Pops (deletes) the last element (Node data) of the singly linked list and returns the deleted element or
     * returns null if the singly linked list is empty. */
    public T pop() {
        if (isEmpty()) {
            return null;
        } else {
            T ans = top.data;
            top = top.nextNode;
            return ans;
        }
    }

    // Overrides the toString method and returns a String containing information about the Stack object.
    public String toString() {
        String ans = "Stack: ";

        if (isEmpty()) {
            return ans.concat("is empty.");
        }

        Stack<T> newStack = new Stack<>();
        for (T data : this) {
            newStack.push(data);
        }

        for (T data : newStack) {
            ans = ans.concat(data + ", ");
        }

        return ans.replaceAll(", $", "");
    }

    // Returns true if the singly linked list is empty, false otherwise
    public boolean isEmpty() {
        return (top == null);
    }

    // Returns the size of the singly linked list
    public int size() {
        if (isEmpty()) {
            return 0;
        } else {
            int size = 1;

            Node current = top;

            while (current.nextNode != null) {
                current = current.nextNode;
                size++;
            }

            return size;
        }
    }

    // Helper class that implements Iterator interface in order to provide iteration operation for the Stack objects
    private class StackIterator implements Iterator<T> {
        Node currentNode = top;

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
     * specifically an instance of the StackIterator helper class that implements Iterator, since StackIterator is-an Iterator. */
    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }
}