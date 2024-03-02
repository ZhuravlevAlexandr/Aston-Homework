/**
 * Aston Homework non thread-safe LinkedList.
 *
 * @param <T> - the type of elements in this list
 * @author: Alexandr Zhuravlev.
 */
public class HWLinkedList<T> {

    private int size;

    private Node<T> firstElement;

    private Node<T> lastElement;


    /**
     * Constructs empty LinkedList, with no Nodes, related with it.
     */
    public HWLinkedList() {

    }

    /**
     * Container of single element
     * @param <T> - Type of element contained within Node.
     */
    private static class Node<T> {

        T element;
        Node<T> previous;
        Node<T> next;

        Node (T element, Node<T> previous, Node<T> next) {

            this.element = element;
            this.previous = previous;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }

    /**
     * Creates a new Node, with T type element inside it.
     *
     * @param t - element to be added.
     */
    public void add(T t) {

        Node<T> newNode = new Node<>(t, null, null);
        if (size == 0) {
            firstElement = newNode;
            size++;
        } else if (size == 1) {
            lastElement = newNode;
            setRelations(firstElement, newNode);
            size++;
        } else {
            setRelations(lastElement, newNode);
            lastElement = newNode;
            size++;
        }
    }

    /**
     * Creates a new Node, with T type element inside it,
     * at certain index.
     * @param index - specified position of element to be added.
     * @param element - element to be added.
     */
    public void add(int index, T element) {

        checkIndex(index);
        Node<T> newNode = new Node<>(element, null, null);
        if (size == 0) {
            firstElement = newNode;
            size++;
        } else if (index == 0 && size > 0) {
            Node<T> temp = firstElement;
            setRelations(newNode, temp);
            firstElement = newNode;
            size++;
        } else if (index > 0 && index == size) {
            Node<T> temp = lastElement;
            newNode.previous = temp;
            temp.next = newNode;
            lastElement = newNode;
            size++;
        } else {
            Node<T> temp = get(index);
            Node<T> prevTemp = temp.previous;
            setRelations(newNode, temp);
            setRelations(prevTemp, newNode);
            size++;
        }
    }
    /**
     * Return element from lust with specified index.
     *
     * @param index - int value of element to be returned from list.
     *                      If specified index contains nothing, method will return null.
     * @return Element of specified position in this array.
     * throws IndexOutOfBounds Exception, if index value is out of
     * array size.
     */
    public Node<T> get(int index) {

        checkIndex(index);
        if (index <= size / 2) {
            return directSearch(index);
        } else {
            return reverseSearch(index);
        }
    }

    /**
     * Installing element into node with specified index;
     * @param index - position in list to install the element;
     * @param element - T type element to be installed.
     */
    public void set(int index, T element) {

        checkIndex(index);
        Node<T> item = get(index);
        item.element = element;
    }

    /**
     * Removes element from specified position in the array,
     *
     * @param index defines index of element to be removed.
     */
    public void remove(int index) {

        checkIndex(index);
        performDeletion(get(index));
    }

    /**
     * @return first element of the list
     */
    public Node<T> getFirst() {
        return firstElement;
    }

    /**
     * @return last element of the list
     */
    public Node<T> getLast() {
        return lastElement;
    }

    /**
     * Removes all elements from list.
     */
    public void clear() {

        firstElement = null;
        lastElement = null;
        size = 0;

    }

    /**
     * Method to sort the list.
     */
    public void sort() {
        HWArrayList<T> arrayList = new HWArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(get(i).element);
        }
        arrayList.sort();
        int temp = 0;
        for (int i = 0; i < size; i++) {
            this.set(temp, arrayList.get(temp));
            temp++;
        }
    }

    private Node<T> directSearch(int index) {
        Node<T> temp = firstElement;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    private Node<T> reverseSearch(int index) {
        Node<T> temp = lastElement;
        for (int i = 0; i < (size - (index + 1)); i++) {
            temp = temp.previous;
        }
        return temp;
    }

    private void checkIndex(int index) {
        if (index >= 0 && index <= size) {
            return;
        } else {
            throw new IndexOutOfBoundsException();
        }

    }

    private void setRelations(Node<T> prevNode, Node<T> nextNode) {

        prevNode.next = nextNode;
        nextNode.previous = prevNode;
    }

    private void performDeletion(Node<T> node) {

        if (node.previous == null && node.next == null) {
            firstElement = null;
        } else if (node.previous == null) {
            firstElement = node.next;
        } else if (node.next == null) {
            lastElement = node.previous;
        } else {
            setRelations(node.previous, node.next);
        }
        size--;
    }

    @Override
    public String toString() {
        return "HWLinkedList{" +
                "size=" + size +
                ", firstElement=" + firstElement +
                ", lastElement=" + lastElement +
                '}';
    }
}


