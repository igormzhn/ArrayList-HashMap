package map;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;




public class MyHashMap<K,V> implements Map<K,V> {
    public Node<K, V>[] hashTable;
    private int size = 0;
    private double limit;

    public MyHashMap(){
        hashTable = new Node[16];
        limit = hashTable.length * 0.75;
    }

    @Override
    public boolean add(final K key, final V value) {
        if(size + 1 <= limit){
            limit *= 2;
            arrayDoubling();
        }
        Node<K, V> node = new Node<>(key, value);
        int index = hash(key);

        if(hashTable[index] == null){
                return simpleAdd(index, node);
        }

        List<Node<K, V>> nodeList = hashTable[index].getNodes();

        for(Node<K, V> n : nodeList){
            if(keyExistButValueNew(n, node, (V) nodeList) || collisianProcessing(n, node, nodeList)){
                return true;
            }
        }
        return false;
    }

    private boolean simpleAdd(int index, Node<K, V> newNode){
        hashTable[index] = new Node<>(null, null);
        hashTable[index].getNodes().add(newNode);
        size++;
        return true;
    }

    private boolean keyExistButValueNew(final Node<K, V> nodeFromList, final Node<K, V> newNode, final V value){

        if(newNode.getKey().equals(nodeFromList.getKey()) && !newNode.getValue().equals(nodeFromList.getValue())){
            nodeFromList.setValue(value);
            return  true;
        }
        return false;
    }

    private boolean collisianProcessing(final Node<K, V> nodeFromList, final Node<K, V> newNode, final List<Node<K, V>> nodes){

        if(newNode.hashCode() == nodeFromList.hashCode() &&
                !Objects.equals(newNode.getKey(), nodeFromList.getKey()) &&
                !Objects.equals(newNode.getValue(), nodeFromList.getValue())){
            nodes.add(newNode);
            size++;
            return true;
        }
        return false;
    }

    private void arrayDoubling(){
        Node<K,V>[] oldHashTable = hashTable;
        hashTable = new Node[oldHashTable.length * 2];
        size = 0;
        for(Node<K, V> n : oldHashTable){
            if(n != null){
                for(Node<K, V> node : n.getNodes()){
                    add(node.key, node.value);
                }
            }
        }
    }

    @Override
    public boolean delete(final K key) {

        int index = myHash(key);
        if(hashTable[index] == null){
            return false;
        }
        if(hashTable[index].getNodes().size() == 1){
            hashTable[index].getNodes().remove(0);
            return true;
        }

        List<Node<K,V>> nodeList = hashTable[index].getNodes();
        for(Node<K,V> node : nodeList){
            if(key.equals(node.getKey())){
                nodeList.remove(node);
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(final K key) {
        int index = myHash(key);
        if(index < hashTable.length && hashTable[index] != null){

            if(hashTable[index].getNodes().size() == 1){
                return hashTable[index].getNodes().get(0).getValue();
            }

            List<Node<K,V>> list = hashTable[index].getNodes();
            for(Node<K,V> node : list){
                if(key.equals(node.getKey())){
                    return node.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int counterArray = 0;
            int valuesCounter = 0;
            Iterable<Node<K, V>> subIterator = null;

            @Override
            public boolean hasNext() {
                if (valuesCounter == size)
                    return false;


                if (subIterator == null || !subIterator.hasNext()) {
                    if (moveToNextCell()) {
                        subIterator = hashTable[counterArray].getNodes().iterator();
                    } else {
                        return false;
                    }
                }
                return subIterator.hasNext();
            }
            private boolean moveToNextCell(){
                counterArray++;
                while(hashTable[counterArray] != null){
                    valuesCounter++;
                }
                return hashTable[counterArray] != null;
            }


            @Override
            public V next() {
                valuesCounter++;
                return subIterator.next().gettValue();
            }
        };
    }

    public class Node<K,V> {

        private List<Node<K,V>> nodes;
        private int hash;
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            nodes = new LinkedList<Node<K,V>>();
        }

        public int hash(final K key){
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

        public int myHash(){
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
    }
}
