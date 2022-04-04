package array;

public interface MyList<T> extends Iterable<T>{
    boolean add(T element);
    void delete(int index);
    T get(int index);
    int size();
    void update(int index, T element);
}
