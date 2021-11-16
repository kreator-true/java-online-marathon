package sprint04.task03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main {
    public static void main(String[] args) {
        System.out.println(checkEmpty());
    }

    public static boolean checkEmpty(){
        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        return new MyUtils().listMapCompare(list, map);
    }
}
