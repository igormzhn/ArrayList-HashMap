package array;

import java.util.Iterator;

public class MyIterator<T> implements Iterator<T> {

    private int index = 0;
    private T[] element;

    MyIterator(T[] element){
        this.element = element;
    }

    @Override
    public boolean hasNext() {
        return index < element.length;
    }

    @Override
    public T next() {
        return element[index++];
    }
}
