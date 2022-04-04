package array;

import java.util.Iterator;

public class MyArrayList<T> implements MyList<T>{

    private T[] elements;

    public MyArrayList(){
        elements = (T[]) new Object[0];
    }

    @Override
    public boolean add(T element) {
        try{
            T[] temp = elements;
            elements = (T[]) new Object[temp.length + 1];
            System.arraycopy(temp, 0, elements, 0, temp.length);
            elements[elements.length - 1] = element;
            return true;

        }catch (ClassCastException e){
         e.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(int index) {
        try{
            T[] temp = elements;
            elements = (T[]) new Object[temp.length - 1];
            System.arraycopy(temp, 0, elements, 0, index);
            int countElementsAfterIndex = temp.length - index -1;
            System.arraycopy(temp, index +1, elements , index, countElementsAfterIndex);
        }catch (ClassCastException e){
            e.printStackTrace();
        }

    }

    @Override
    public T get(int index) {
        return elements[index];
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public void update(int index, T element) {
        elements[index] = element;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(elements);
    }
}
