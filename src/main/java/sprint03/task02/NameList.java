package sprint03.task02;


class NameList {
    private String[] names = {"Mike", "Emily", "Nick", "Patric", "Sara"};

    public Iterator getIterator() {
        return new Iterator();
    }

    public class Iterator {
        private int counter = 0;
        private int length = 0;

        private Iterator() {
            length = names.length;
        }

        public boolean hasNext(){
            if(counter < length) return true;
            return false;
        }

        public String next(){
            return names[counter++];
        }
    }
}