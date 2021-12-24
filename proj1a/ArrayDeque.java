public class ArrayDeque<Item> {
    /* the stored integers */
    private Item[] items;
    private int size;
    private int front;
    private int rear;
    private int maxDepth;

    private static int RFACTOR = 2;

    /** Creates an empty list. */
    public ArrayDeque() {
        size = 0;
        items = (Item[]) new Object[8];
        front = 4;
        rear = 5;
        maxDepth = rear - 1;
    }

    /** Resize our backing array so that it is
     * of the given capacity. */
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public boolean isEmpty(){
        return (size == 0) ? (true) : (false);
    }

    private boolean isFull() {
        return (size == items.length) ? (true) : (false);
    }

    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if(isFull()){
            resize(RFACTOR * items.length);
        }
        items[rear] = x;
        rear = (rear + 1) % items.length;
        if (rear % items.length > maxDepth){
            maxDepth = rear;
        }
        size += 1;
    }

    public void addFirst(Item x){
        if(isFull()){
            resize(RFACTOR * items.length);
        }
        items[front] = x;
        front = (front - 1) % items.length;
        if (front % items.length > maxDepth){
            maxDepth = front;
        }
        size += 1;
    }


    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    public void printDeque() {
        int cursor = (front + 1) % items.length;
        while(cursor != rear){
            System.out.print(items[cursor]);
            System.out.print(' ');
            cursor = (cursor + 1) % items.length;
        }
        System.out.println();
    }

    public Item removeFirst(){
        Item deleteObject = items[(front + 1) % items.length];
        size -= 1;
        if (size <= items.length / 4 && items.length > 16 && maxDepth < items.length / 2){
            resize(items.length / 2);
        }
        items[(front + 1) % items.length] = null;
        front = (front + 1) % items.length;
        if (front % items.length > maxDepth){
            maxDepth = front;
        }
        return deleteObject;
    }

    public Item get(int i){
        return items[(front + i + 1) % items.length];
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public Item removeLast() {
        Item deleteObject = items[(rear - 1) % items.length];
        size -= 1;
        if (size <= items.length / 4 && items.length > 16 && maxDepth < items.length / 2){
            resize(items.length / 2);
        }
        items[(rear - 1) % items.length] = null;
        rear = (rear - 1) % items.length;
        if (rear % items.length > maxDepth){
            maxDepth = rear;
        }
        return deleteObject;
    }
    

}
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
