//public class ArrayDeque<T> {
//    private T[] Items;
//    private int size;
//    private int nextFirst;
//    private int nextLast;
//    private static int REFACTOR = 2;
//    private static int INITIAL_CAPACITY = 8;
//
//    public ArrayDeque(){
//        size = 0;
//        nextFirst = 4;
//        nextLast = 5;
//        Items = (T[]) new Object[INITIAL_CAPACITY];
//    }
//    private void resize(int capacity){
//        T[] temp = (T[]) new Object[capacity];
//        System.arraycopy(Items,0,temp,0,size);
//        Items = temp;
//    }
//    private boolean isfull(){
//        return Items.length == size;
//    }
//    public void addFirst(T item){
//        if (isfull()){
//            resize(REFACTOR*Items.length);
//        }
//        Items[nextFirst] = item;
//        nextFirst = (nextFirst - 1) % Items.length;
//        size++;
//    }
//
//    public void addLast(T item) {
//        if (isfull()){
//            resize(REFACTOR*Items.length);
//        }
//
//        Items[nextLast] = item;
//        nextLast = (nextLast + 1) % Items.length;
//        size++;
//    }
//
//    public boolean isEmpty(){
//        return size == 0;
//    }
//
//    public int size(){
//        return size;
//    }
//
//    public void printDeque(){
//        int cursor = (nextFirst + 1)%Items.length;
//        while(cursor!=nextLast){
//            System.out.print(Items[cursor]);
//            System.out.print(" ");
//            cursor = (cursor + 1)%Items.length;
//        }
//        System.out.println();
//    }
//
//    public T removeFirst(){
//        T deleted = Items[(nextFirst+1)% Items.length];
//        size--;
//        if (Items.length >= 16 && size < Items.length/4){
//            resize(Items.length/REFACTOR);
//        }
//        Items[(nextFirst+1)% Items.length] = null;
//        nextFirst = (nextFirst+1)% Items.length;
//        return deleted;
//    }
//
//    public T removeLast(){
//        T deleted = Items[(nextLast-1)% Items.length];
//        size--;
//        if (Items.length >= 16 && size < Items.length/4){
//            resize(Items.length/REFACTOR);
//        }
//        Items[(nextLast-1)% Items.length] = null;
//        nextFirst = (nextLast-1)% Items.length;
//        return deleted;
//    }
//
//    public T get(int index){
//        return Items[(nextFirst+index+1)%Items.length];
//    }
//
//}
public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    private static final int INITIAL_CAPACITY = 8;
    private static final int RFACTOR = 2;

    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    private void calculateFront() {
        nextFirst = (nextFirst + items.length - 1) % (items.length);
    }

    private void calculateLast() {
        nextLast = (nextLast + 1) % items.length;
    }

    private int respect(int index, int length) {
        return index % length;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    private int plusOne(int index, int length) {
        return (index + 1) % length;
    }


    private int minusOne(int index) {
        return (index + items.length - 1) % items.length;
    }

    private int minusOne(int index, int length) {
        return (index + length - 1) % length;
    }

    private boolean isFull() {
        return items.length == size;
    }

    private void extend() {
        reSize(size, RFACTOR * size);
    }

    private void shrink() {
        reSize(items.length, items.length / RFACTOR);
    }

    private boolean checkLarger(int f, int l) {
        return f > l;
    }

    private void reSize(int inputSize, int capacity) {
        T[] a = (T[]) new Object[capacity];

        if (capacity < inputSize) {
            int start = Math.min(nextFirst, nextLast);
            int first = nextFirst;
            int p = respect(start, capacity);
            if (!checkLarger(nextFirst, nextLast)) {
                for (int i = 0; i < capacity; i++) {
                    a[i] = items[start];
                    start = plusOne(start);
                }
                nextFirst = 0;
                nextLast = capacity / 2;
            } else {
                for (int i = 0; i < capacity; i++) {
                    start = minusOne(start, items.length);
                    p = minusOne(p, capacity);
                    a[p] = items[start];

                }
                nextFirst = respect(first, capacity);
                System.out.println();
            }
        } else {
            if (nextFirst == items.length - 1) {
                int temp = minusOne(nextLast);
                System.arraycopy(items, 0, a, 0, nextFirst + 1);
                nextFirst = a.length - 1;
                nextLast = temp + 1;
            } else {
                System.arraycopy(items, 0, a, 0, nextLast);
                System.arraycopy(items, nextLast, a, size + nextFirst + 1, size - nextLast);
                nextFirst = size + nextFirst;
            }
        }
        items = a;
        System.out.println();

    }

    public void addFirst(T item) {
        if (isFull()) {
            extend();
        }

        items[nextFirst] = item;
        calculateFront();
        size++;
    }

    public void addLast(T item) {
        if (isFull()) {
            extend();
        }
        items[nextLast] = item;
        calculateLast();
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private boolean isSparse() {
        return items.length >= 16 && size() < items.length / 4;
    }

    public T removeFirst() {
        if (isSparse()) {
            shrink();
        }
        nextFirst = plusOne(nextFirst);
        T returnItem = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (size < 0) {
            size = 0;
        }
        return returnItem;
    }

    public T removeLast() {
        if (isSparse()) {
            shrink();
        }
        nextLast = minusOne(nextLast);
        T returnItem = items[nextLast];
        items[nextLast] = null;
        size--;
        if (size < 0) {
            size = 0;
        }
        return returnItem;
    }


    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            T item = get(i);
            if (item == null) {
                System.out.println();
                return;
            }
            System.out.print(item);
            System.out.print(" ");
        }
    }

    public T get(int index) {
        return items[(nextFirst + 1 + index) % items.length];
    }

//    public static void main(String[] args) {
//        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
//        arrayDeque.addFirst(1);
//        arrayDeque.addFirst(2);
//        arrayDeque.addFirst(3);
//        arrayDeque.addFirst(4);
//        arrayDeque.addFirst(5);
//        arrayDeque.addLast(-1);
//        arrayDeque.addLast(-2);
//        arrayDeque.addLast(-3);
//        arrayDeque.addLast(-4);
//        arrayDeque.addLast(-5);
//        arrayDeque.addLast(-6);
//        arrayDeque.addLast(-7);
//        arrayDeque.addLast(-8);
//        arrayDeque.addLast(-9);
//        arrayDeque.addLast(-10);
//        arrayDeque.addLast(-11);
//        arrayDeque.addLast(-11);
//        arrayDeque.addLast(-11);
//        arrayDeque.addLast(-11);
//        arrayDeque.addLast(-11);
//        arrayDeque.addLast(-11);
//
//
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//        arrayDeque.removeLast();
//
//
//
//        arrayDeque.removeFirst();
//
//        System.out.println();
//    }


}