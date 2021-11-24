package sprint06.task04;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.clients.add(new Person("person1").goShopping);
        shop.clients.add(new Person("person2").goShopping);
        shop.clients.add(new Person("person3").goShopping);
        shop.clients.add(new Person("person4").goShopping);
        shop.clients.add(((product, percent) -> percent == 0));
        System.out.println(shop.sale("product2", 10));
        System.out.println(shop.sale("product1", 8));
        System.out.println(shop.sale(null, 0));
        System.out.println(shop.sale(null, 11));
    }
}

class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    DecisionMethod goShopping = (product, percent) -> "product1".equals(product) && percent > 10;
}

interface DecisionMethod {
    boolean decide(String product, Integer percent);
}

class Shop {
    public List<DecisionMethod> clients = new ArrayList<>();

    public int sale(String product, int percent) {
        return (int) clients.stream().filter(decisionMethod -> decisionMethod.decide(product, percent)).count();
    }
}
