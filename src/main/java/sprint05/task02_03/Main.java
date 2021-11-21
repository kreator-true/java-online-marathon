package sprint05.task02_03;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Plant("Rare", "White", "Plant1"));
        System.out.println(new Plant("Ordinary", "Blue", "Plant2"));
        System.out.println(new Plant("Rare", "Red", "Plant3"));
        System.out.println(Plant.tryCreatePlant("Silver", "Blue", "Plant4"));
        System.out.println(Plant.tryCreatePlant("Rare", "Black", "Plant5"));
        System.out.println(Plant.tryCreatePlant("Pastel", "Black", "Plant6"));
    }
}
