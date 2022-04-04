package array;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        myArrayList.add("My");
        myArrayList.add("array");
        myArrayList.add("List");

        System.out.println(myArrayList.get(1));
        System.out.println(myArrayList.size());
        myArrayList.update(1, "list");
        myArrayList.delete(2);

        for (Object el : myArrayList){
            System.out.println(el);
        }
    }
}
