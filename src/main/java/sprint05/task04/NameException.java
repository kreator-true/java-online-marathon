package sprint05.task04;

public class NameException extends RuntimeException{
    public NameException(String name) {
        super(String.format("Incorrect value %s for firstName (should start from upper case and contains only alphabetic characters and symbols -, _)", name));
    }

}
