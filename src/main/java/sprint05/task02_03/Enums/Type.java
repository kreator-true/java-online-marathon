package sprint05.task02_03.Enums;

public enum Type {
    RARE, ORDINARY;

    @Override
    public String toString() {
        return this.name().substring(0, 1) + this.name().substring(1, this.name().length()).toLowerCase();
    }
}
