package sprint05.task04;

public class CodeException extends RuntimeException{
    public CodeException(String idCode) {
        super(String.format( "Incorrect value %s for code (should contains exactly 10 digits)", idCode));
    }
}
