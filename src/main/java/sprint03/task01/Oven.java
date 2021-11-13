package sprint03.task01;

public class Oven {
    public static Pizza cook(){
        return Pizza.base().addCheese("cheese").addMeat("meat").addMushroom("mushroom").build();
    }
}
