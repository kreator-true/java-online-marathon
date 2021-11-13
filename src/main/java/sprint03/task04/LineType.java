package sprint03.task04;

public enum LineType {
    SOLID("solid"),
    DOTTED("dotted"),
    DASHED("dashed"),
    DOUBLE("double");

    private String name;

    LineType(String name) {
        this.name = name;
    }

    String getName(){
        return name;
    }
}
