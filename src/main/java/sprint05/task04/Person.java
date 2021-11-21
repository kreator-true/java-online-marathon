package sprint05.task04;

import java.util.Objects;

class Person {
    private String idCode;
    private String firstName;
    private String lastName;

    public static Person buildPerson(String firstName, String lastName, String idCode){
        Person person = new Person();
        RuntimeException runtimeException = new RuntimeException();
        String message = "";
        if(firstName != null) {
            try {
                person.setFirstName(firstName);
            } catch (NameException nameException) {
                message += nameException.getMessage();
                runtimeException.addSuppressed(nameException);
            }
        }

        if(lastName != null) {
            try {
                person.setLastName(lastName);
            } catch (IllegalArgumentException illegalArgumentException) {
                if (message.isEmpty()) message += illegalArgumentException.getMessage();
                else message += "; " + illegalArgumentException.getMessage();
                runtimeException.addSuppressed(illegalArgumentException);
            }
        }

        if(idCode != null) {
            try {
                person.setIdCode(idCode);
            } catch (CodeException codeException) {
                if (message.isEmpty()) message += codeException.getMessage();
                else message += "; " + codeException.getMessage();
                runtimeException.addSuppressed(codeException);
            }
        }

        int length = runtimeException.getSuppressed().length;

        if (length == 1){
            String nameexception = runtimeException.getSuppressed()[0].getClass().getSimpleName();
            if(nameexception.equals(NameException.class)) throw new NameException(firstName);
            if(nameexception.equals(CodeException.class)) throw new CodeException(idCode);
            throw new IllegalArgumentException(message);
        }if(length > 1) throw new IllegalArgumentException(message);

        return person;
    }

    public String getIdCode() {
        return idCode;
    }
    private boolean checkName(String name){
        String startCharacter = name.substring(0, 1);
        if(!startCharacter.equals(startCharacter.toUpperCase()) || startCharacter.equals("-")) return false;
        if (!name.replaceAll("[^a-zA-Z_-]", "").equals(name)) return false;
        return true;
    }

    public void setIdCode(String idCode) {
        if (!idCode.replaceAll("[\\D]", "").equals(idCode)) throw new CodeException(idCode);
        if (idCode.length() != 10) throw new CodeException(idCode);
        this.idCode = idCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (checkName(firstName)) this.firstName = firstName;
        else throw new NameException(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(checkName(lastName)) this.lastName = lastName;
        else throw new IllegalArgumentException(
                String.format("Incorrect value %s for lastName (should start from upper case and contains only alphabetic characters and symbols -, _)", lastName));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(idCode, person.idCode) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCode, firstName, lastName);
    }

    @Override
    public String toString() {
        return String.format("%s %s: %s", firstName, lastName, idCode);
    }
}
