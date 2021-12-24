public class ArrayDeque<T> {
    private T[] Items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static int RFACTOR = 2;
    private static int INITIAL_CAPACITY = 8;

    public ArrayDeque(){
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        Items = (T[]) new Object[INITIAL_CAPACITY];
    }
    private void calculateFront(){
        nextFirst = (nextFirst +Items.length - 1) % Items.length;
    }
    private void calculateLast(){
        nextLast = (nextLast + 1) % Items.length;
    }
    private int respect(int index, int length){
        return index % length;
    }
    private int plusOne(int index){
        return (index+1) % Items.length;
    }
    private int plusOne(int index, int length) {
        return (index + 1) % length;
    }
    private int minusOne(int index) {
        return (index + Items.length - 1) % Items.length;
    }

    private int minusOne(int index, int length) {
        return (index + length - 1) % length;
    }
    private boolean isfull(){
        return Items.length <= size;
    }
    private void extend(){
        resize(RFACTOR*Items.length);
    }
    private void shrink(){
        resize(Items.length/RFACTOR);
    }
    private void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        int begin = plusOne(nextFirst);
        int end = minusOne(nextLast);
        nextFirst = 0;
        nextLast = 1;
        for (int i = begin; i != end; i = plusOne(i)) {
            a[nextLast] = Items[i];
            nextLast = plusOne(nextLast, capacity);
        }
        a[nextLast] = Items[end];
        nextLast = plusOne(nextLast, capacity);
        Items = a;
    }

    public void addFirst(T item){
        if (isfull()){
            resize(RFACTOR*Items.length);
        }
        Items[nextFirst] = item;
        calculateFront();
        size++;
    }

    public void addLast(T item) {
        if (isfull()){
            extend();
        }
        Items[nextLast] = item;
        calculateLast();
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for (int i = 0; i < Items.length; i++){
            if (get(i) == null){
                System.out.println();
                return;
            }
            System.out.print(get(i));
            System.out.print(" ");
        }
        System.out.println();
    }
    private boolean isspare(){
        return Items.length >= 16 && size() > Items.length/4;
    }
    public T removeFirst(){
        nextFirst = plusOne(nextFirst);
        T deleted = Items[nextFirst];
        Items[nextFirst] = null;
        size--;
        if (size < 0){
            size = 0;
        }
        if (isspare()){
            shrink();
        }
        return deleted;
    }

    public T removeLast(){
        nextLast = minusOne(nextLast);
        T deleted = Items[nextLast];
        Items[nextLast] = null;
        size--;
        if (size < 0){
            size = 0;
        }
        if (isspare()){
            shrink();
        }
        return deleted;
    }

    public T get(int index){
        return Items[(nextFirst+index+1)%Items.length];
    }

}
