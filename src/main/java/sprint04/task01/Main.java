package sprint04.task01;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(chek());

    }

    public static boolean checkNull() {
        Map<String, String> phones = new HashMap();
        phones.put("0501234567", null);
        List<String> ivan = new ArrayList();
        ivan.add("0501234567");
        Map<String, List<String>> expected = new HashMap();
        expected.put(null, ivan);
        Map<String, List<String>> actual = (new MyUtils()).createNotebook(phones);
        return expected.equals(actual);
    }
    public static boolean checkDoubleNull() {
        Map<String, String> phones = new HashMap();
        phones.put("0501234567", null);
        phones.put("0671234567", null);
        List<String> ivan = new ArrayList();
        ivan.add("0501234567");
        ivan.add("0671234567");
        Map<String, List<String>> expected = new HashMap();
        expected.put(null, ivan);
        Map<String, List<String>> actual = (new MyUtils()).createNotebook(phones);
        boolean result = true;
        Iterator var6 = expected.entrySet().iterator();

        while(var6.hasNext()) {
            Map.Entry<String, List<String>> entry = (Map.Entry)var6.next();
            result = result && actual.containsKey(entry.getKey()) && ((List)actual.get(entry.getKey())).containsAll((Collection)entry.getValue());
            if (!result) {
                break;
            }
        }

        return result;
    }

    public static boolean chek(){
        Map<String, String> phones = new HashMap<>();
        phones.put("0967654321", "Petro");
        phones.put("0677654321", "Petro");
        phones.put("0501234567", "Ivan");
        phones.put("0970011223", "Stepan");
        phones.put("0631234567", "Ivan");
        phones.put(null, "Stepan");
        phones.put(null, null);
        phones.put(null, "Egor");

        List<String> egor = new ArrayList<>();
        //egor.add(null);
        Map<String, List<String>> expected = new HashMap<>();
        expected.put("Egor", egor);
        expected.put("Petro", List.of("0967654321", "0677654321"));
        expected.put("Ivan", List.of("0501234567", "0631234567"));
        expected.put("Stepan", List.of("0970011223"));


        Map<String, List<String>> actual = new MyUtils().createNotebook(phones);

        expected.entrySet().forEach(System.out::println);
        actual.entrySet().forEach(System.out::println);

        return expected.equals(actual);
    }
}
