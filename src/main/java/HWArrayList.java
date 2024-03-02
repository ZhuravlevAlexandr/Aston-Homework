import java.util.Arrays;

/**
 * Aston Homework non thread-safe Arraylist.
 *
 * @param <T> - the type of elements in this list
 * @author: Alexandr Zhuravlev.
 */
public class HWArrayList<T> {


    private static final int DEFAULT_CAPACITY = 10;

    private int capacity;

    private int currentIndex;

    private Object[] array;

    /**
     * Constructs an ArrayList with default capacity of 10
     */
    public HWArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    /**
     * Constructs an ArrayList with specified capacity.
     *
     * @param capacity - int value which defines array capacity.
     */
    public HWArrayList(int capacity) {
        if (capacity > 0) {
            array = new Object[capacity];
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException
                    ("Capacity should be greater than 0," +
                            "current specified capacity =" + capacity);
        }
    }

    /**
     * Appends specified element to the end of the list.
     *
     * @param t - element to be added.
     */
    public void add(T t) {
        if (currentIndex >= capacity) {
            expandCapacity();
        }
        array[currentIndex] = t;
        currentIndex++;
    }

    /**
     * Adding element to specified index of array.
     *
     * @param indexToInsert - int value, which defines index of element to be added
     *                      this parameter value should be in a range of 0 and current amount of elements.
     * @param t             - element to be added.
     */
    public void add(int indexToInsert, T t) {
        if (indexToInsert >= 0 && indexToInsert < currentIndex) {
            if (capacity - currentIndex == 0) {
                expandCapacity();
            }
            int tempIndex = currentIndex;
            for (int i = currentIndex - indexToInsert; i > 0; i--) {
                array[tempIndex] = array[tempIndex - 1];
                tempIndex--;
            }
            array[indexToInsert] = t;
            currentIndex++;
        } else if (indexToInsert == currentIndex) {
            if ((capacity - currentIndex) == 0) {
                expandCapacity();
            }
            array[indexToInsert] = t;
            currentIndex++;
        } else {
            throw new IllegalArgumentException("Index of insertion should be" +
                    "in a range of 0 and current amount of enlisted elements" +
                    "current amount of elements = " + currentIndex);

        }
    }

    /**
     * return element from array with specified index.
     *
     * @param indexToReturn - int value of element to be returned from array.
     *                      If specified index contains nothing, method will return null.
     * @return Element of specified position in this array.
     * throws IndexOutOfBounds Exception, if indexToReturn value is out of
     * list dimension.
     */
    @SuppressWarnings("unchecked")
    public T get(int indexToReturn) {
        return (T) array[indexToReturn];
    }

    /**
     * this method is called automatically when the array runs out of space
     * to create new array with expanded capacity, and put all elements
     * from old array, to new one.
     */
    private void expandCapacity() {
        int expandedCapacity = array.length * 2;
        array = Arrays.copyOf(array, expandedCapacity);
        capacity = expandedCapacity;
    }

    /**
     * Removes element from specified position in the array,
     * and moves all elements next by removed one backward to 1 position in list.
     *
     * @param indexToRemove defines index of element to be removed.
     */
    public void remove(int indexToRemove) {
        if (indexToRemove < currentIndex && indexToRemove >= 0) {
            int tempIndex = indexToRemove;
            for (int i = currentIndex - tempIndex; i > 0; i--) {
                array[tempIndex] = array[tempIndex + 1];
                tempIndex++;
            }
            currentIndex--;
        } else {
            throw new IllegalArgumentException("Index of deletion should be " +
                    "greater than or equal to 0 and be less than amount of enlisted elements"
                    + "current amount of elements = " + currentIndex);
        }
    }

    /**
     * Removes all elements from array
     */
    public void clear() {
        int tempIndex = currentIndex;
        for (int i = 0; i < currentIndex; i++) {
            array[tempIndex - 1] = null;
            tempIndex--;
        }
        currentIndex = 0;
    }

    /**
     * Method to sort an array.
     */
    public void sort() {
        Arrays.sort(array, 0, currentIndex);
    }

    @Override
    public String toString() {
        return "HWArrayList{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
