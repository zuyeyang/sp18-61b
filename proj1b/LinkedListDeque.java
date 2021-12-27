public class LinkedListDeque<T> implements Deque<T> {
    private class Node{
        private T Item;
        private Node prev;
        private Node next;

        private Node(){
            Item = null;
            next = null;
            prev = null;
        }

        private Node(T item, Node n, Node p){
            Item = item;
            next = n;
            prev = p;
        }
    }

    private Node sentinel; //Lookingforward: Sentinel.next -> first one, Sentinel.prev -> last one
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T item){
        size ++;
        Node n = new Node(item,sentinel.next,sentinel);
        sentinel.next.prev = n;
        sentinel.next = n;
    }
    @Override
   public void addLast(T item) {
        size++;
        Node n = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.next = n;
        sentinel.prev = n;

   }
   @Override
   public boolean isEmpty(){
        return sentinel.next == sentinel;
   }
    @Override
   public int size(){
        if (size <= 0){
            return 0;
        }
        return size;
   }
    @Override
   public void printDeque(){
        Node p = sentinel;
        while(p.next != sentinel) {
            p = p.next;
            System.out.print(p.Item);
            System.out.print(" ");
        }
        System.out.println();
   }
    @Override
   public T removeFirst(){
        if (sentinel.next == sentinel){
            return null;
        }
        size--;
        T remvoed = sentinel.next.Item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;

        return remvoed;
   }
    @Override
    public T removeLast(){
        if (sentinel.prev == sentinel){
            return null;
        }
        size--;
        T removed = sentinel.prev.Item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next= sentinel;

        return removed;
    }
    @Override
    public T get(int index){
        if (index >= size){
            return null;
        }
        Node n = sentinel;
        if (n.next == n){
            return null;
        }
        for(int i = 0; i <= index; i++){
            n = n.next;
        }
        return n.Item;
    }

    public T getRecursive(int index){
        if (index >= size){
            return null;
        }
        Node n = sentinel;
        if (n.next == n){
            return null;
        }
        return getrecursivehelper(n, index);
    }

    private T getrecursivehelper(Node n, int index){
        if (index == 0){
            return n.next.Item;
        }
        return getrecursivehelper(n.next, index - 1);
    }

}