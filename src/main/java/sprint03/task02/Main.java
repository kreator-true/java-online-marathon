package sprint03.task02;

public class Main {
    public static void main(String[] args) {
        NameList.Iterator iterator = new NameList().getIterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
