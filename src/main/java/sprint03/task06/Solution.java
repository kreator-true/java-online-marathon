package sprint03.task06;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        testSorted();

    }

    public static void  testSorted(){
        String[] expected = { "First name: John, Last name: Brown, Address: Address #1",
             "First name: John, Last name: Taylor, Address: Address #1",
             "First name: Karen, Last name: Davis, Address: Address #2",
             "First name: Susan, Last name: Brown, Address: Address #4" };
         String[] actual = new String[4];
         AddressBook addressBook = new AddressBook(4);
         addressBook.create("John", "Brown", "Address #1");
         addressBook.create("Susan", "Brown", "Address #4");
         addressBook.create("Karen", "Davis", "Address #2");
         addressBook.create("John", "Taylor", "Address #1");
         addressBook.sortedBy(SortOrder.ASC);
         int counter = 0;
         for (Object record : addressBook)
             actual[counter++] = record.toString();

        System.out.println(Arrays.toString(expected));
        System.out.println("__________________________");
        System.out.println(Arrays.toString(actual));
        System.out.println(Arrays.equals(actual, expected));


    }
}
@SuppressWarnings("unchecked")
class AddressBook implements Iterable {
    private NameAddressPair[] addressBook;
    private List<NameAddressPair> nameAddressPairList;
    private int counter = 0;

    public AddressBook(int capacity) {
        addressBook = new NameAddressPair[0];
        nameAddressPairList = new ArrayList<>();
        counter = capacity;
    }

    private NameAddressPair[] getAddressBook() {
        addressBook = new NameAddressPair[size()];
        for(int i = 0; i < size(); i++)
            addressBook[i] = nameAddressPairList.get(i);

        return addressBook;
    }

    private boolean isPresence(NameAddressPair.Person person, NameAddressPair nameAddressPair){
        if(nameAddressPair.getPerson().equals(person)) return true;
        return false;
    }

    public boolean create(String firstName, String lastName, String address) {
        NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);
        for(NameAddressPair nameAddressPair : nameAddressPairList) {
           if (isPresence(person, nameAddressPair)) {
               return false;
           }
        }

        nameAddressPairList.add(new NameAddressPair(person, address));
        return true;
    }

    public String read(String firstName, String lastName) {
        NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);
        String address = null;
        for(NameAddressPair nameAddressPair : nameAddressPairList)
            if(isPresence(person, nameAddressPair)) address = nameAddressPair.getAddress();

        return address;
    }

    public boolean update(String firstName, String lastName, String address) {
        NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);

        for(NameAddressPair nameAddressPair : nameAddressPairList) {
            if (isPresence(person, nameAddressPair)) {
                nameAddressPair.setAddress(address);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String firstName, String lastName) {
        NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);
        return nameAddressPairList.removeIf(nameAddressPair -> isPresence(person, nameAddressPair));

    }

    public int size() {
        return nameAddressPairList.size();
    }

    public void sortedBy(SortOrder order) {
        Comparator compareByAddress = Comparator.comparing(NameAddressPair::getAddress);

        if(order == SortOrder.ASC) Collections.sort(nameAddressPairList, compareByAddress);
        else Collections.sort(nameAddressPairList, compareByAddress.reversed());


    }

    public Iterator iterator() {
        return new AddressBookIterator();
    }

    /*
    --------------------------------------------------
    Inner classes AddressBookIterator, NameAddressPair
    --------------------------------------------------
     */
    @SuppressWarnings("unchecked")
    private class AddressBookIterator implements Iterator<String> {
        private int counter = 0;
        private NameAddressPair[] addressPair = getAddressBook();

        @Override
        public boolean hasNext() {
            if (counter < addressPair.length) return true;
            else return false;
        }

        @Override
        public String next() {
            NameAddressPair nameAddressPair = null;
            if(this.hasNext()) nameAddressPair = addressPair[counter++];

            if(nameAddressPair == null) return null;
            return nameAddressPair.getPerson() + String.format(", Address: %s", nameAddressPair.getAddress());
        }
    }

    private static class NameAddressPair{
        private final Person person;
        private String address;

        private NameAddressPair(Person person, String address) {
            this.person = person;
            this.address = address;
        }

        public Person getPerson() {
            return person;
        }

        public String getAddress() {
            return address;
        }

        public boolean setAddress(String address) {
            this.address = address;
            return true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NameAddressPair that = (NameAddressPair) o;
            return Objects.equals(person, that.person) && Objects.equals(address, that.address);
        }

        @Override
        public int hashCode() {
            return Objects.hash(person, address);
        }

        private static class Person {
            private String firstName;
            private String lastName;

            private Person(String firstName, String lastName) {
                this.firstName = firstName;
                this.lastName = lastName;
            }


            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Person that = (Person) o;
                return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
            }

            @Override
            public int hashCode() {
                return Objects.hash(firstName, lastName);
            }

            @Override
            public String toString() {
                return String.format("First name: %s, Last name: %s", firstName, lastName);
            }
        }
    }
    /*
    -----------------
    End Inner Classes
    -----------------
    */
}

enum SortOrder {
    ASC,
    DESC
}


