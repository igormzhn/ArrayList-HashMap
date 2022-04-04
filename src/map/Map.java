package map;

public interface Map<K,V> extends Iterable<V> {
    boolean add(K key, V value);
    boolean delete(K key);
    V get(K key);
    int size();
}
