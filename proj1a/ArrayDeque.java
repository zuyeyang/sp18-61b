public class ArrayDeque<T> {
    private T[] Items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static int REFACTOR = 2;
    private static int initial_capacity = 8;

    public ArrayDeque(){
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        Items = (T[]) new Object[initial_capacity];
    }
    private void resize(int capacity){
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(Items,0,temp,0,size);
        Items = temp;
    }
    private boolean isfull(){
        return Items.length == size;
    }
    public void addFirst(T item){
        if (isfull()){
            resize(REFACTOR*Items.length);
        }
        Items[nextFirst] = item;
        nextFirst = (nextFirst - 1) % Items.length;
        size++;
    }

    public void addLast(T item) {
        if (isfull()){
            resize(REFACTOR*Items.length);
        }

        Items[nextLast] = item;
        nextLast = (nextLast + 1) % Items.length;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int cursor = (nextFirst + 1)%Items.length;
        while(cursor!=nextLast){
            System.out.print(Items[cursor]);
            System.out.print(" ");
            cursor = (cursor + 1)%Items.length;
        }
        System.out.println();
    }

    public T removeFirst(){
        T deleted = Items[(nextFirst+1)% Items.length];
        size--;
        if (Items.length >= 16 && size < Items.length/4){
            resize(Items.length/REFACTOR);
        }
        Items[(nextFirst+1)% Items.length] = null;
        nextFirst = (nextFirst+1)% Items.length;
        return deleted;
    }

    public T removeLast(){
        T deleted = Items[(nextLast-1)% Items.length];
        size--;
        if (Items.length >= 16 && size < Items.length/4){
            resize(Items.length/REFACTOR);
        }
        Items[(nextLast-1)% Items.length] = null;
        nextFirst = (nextLast-1)% Items.length;
        return deleted;
    }

    public T get(int index){
        return Items[(nextFirst+index+1)%Items.length];
    }

}
