package map;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/*public class Node<K,V> {

    private List<Node<K,V>> nodes;
    private int hash;
    private K key;
    private V value;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        nodes = new LinkedList<Node<K,V>>();
    }

    private int hash(final K key){
        int hash = 31;
        hash = hash * 17 + key.hashCode();
        return hash % hashTable.length;
    }

    public List<Node<K,V>> getNodes(){
        return nodes;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int hash(){
        return hashCode() % hashTable.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Node) {
            Node<K,V> node = (Node<K,V>) o;
            return (Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value) ||
                    Objects.equals(hash, node.hashCode()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        hash = 31;
        hash = hash * 17 + key.hashCode();
        hash = hash *  17 + value.hashCode();
        return hash;
    }
}*/
