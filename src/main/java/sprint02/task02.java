package sprint02;

import java.util.*;

public class task02 {
    public static void main(String[] args) {
        Caffee espresso = new Espresso("Espresso", 10);
        Caffee espresso1 = new Espresso("Espresso", 4);
        Caffee espresso2 = new Espresso("Espresso", 5);
        Caffee cappucino = new Cappuccino("Cappuccino", 7);
        Caffee cappucino1 = new Cappuccino("Cappuccino", 2);
        Caffee cappucino2 = new Cappuccino("Cappuccino", 1);
        Caffee caffe = new Caffee("Caffee", 4);
        Caffee caffe1 = new Caffee("Caffee", 6);
        Caffee caffe2 = new Caffee("Caffee", 7);
        List<Caffee> caffees = List.of(espresso, espresso1, espresso2, cappucino, cappucino1, cappucino2, caffe, caffe1, caffe2);
        long count = caffees.stream().filter(c -> c.getName().equals("Caffe")).count();
        System.out.println(new MyUtils().averageRating(caffees).get("Caffee"));

    }
}

interface DrinkReceipt {
    String getName();
    DrinkReceipt addComponent(String componentName, int componentCount);
}
interface DrinkPreparation {
    Map<String, Integer> makeDrink();
}
interface Rating {
    int getRating();
}

class Caffee implements DrinkReceipt, DrinkPreparation, Rating {
    private static Map<String, Integer> instantiatedDerivedType = new HashMap<>();
    private String name;
    private int rating;
    private Map<String, Integer> ingredients;

    {
        instantiatedDerivedType.merge(this.getClass().getSimpleName(), 1, (oldval, newVal) -> oldval + newVal);
        ingredients = new HashMap<>();
        ingredients.put("Water", 100);
        ingredients.put("Arabica", 20);
    }

    public Caffee(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public static Map<String, Integer> getInstantiatedDerivedType() {
        return instantiatedDerivedType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DrinkReceipt addComponent(String componentName, int componentCount) {
        ingredients.merge(componentName, componentCount, (oldVal, newVal) -> oldVal + newVal);
        return this;
    }

    @Override
    public Map<String, Integer> makeDrink() {
        return getIngredients();
    }

    @Override
    public int getRating() {
        return rating;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }
}
class Espresso extends Caffee {

    public Espresso(String name, int rating) {
        super(name, rating);
        addComponent("Water", -50);
    }

    @Override
    public Map<String, Integer> makeDrink() {
        return super.makeDrink();
    }
}
class Cappuccino extends Caffee {

    public Cappuccino(String name, int rating) {
        super(name, rating);
        addComponent("Milk", 50);
    }

    @Override
    public Map<String, Integer> makeDrink() {
        return super.makeDrink();
    }
}
class MyUtils {
    public Map<String, Double> averageRating(List<Caffee> coffees) {
        Map<String, Double> result = new HashMap<>();
        coffees.stream().forEach(coffe -> result.merge(coffe.getName(), (double)coffe.getRating(), (oldVal, newVal) -> oldVal + newVal));
        Caffee.getInstantiatedDerivedType().entrySet().forEach(className -> {
            result.entrySet().forEach(average -> {
                String name = average.getKey();
                if(className.getKey().equals(name))
                    result.merge(name, (double)className.getValue(), (oldVal, newVal) -> oldVal / newVal);
            });
        });
        return result;
    }
}
