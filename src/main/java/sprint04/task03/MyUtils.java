package sprint04.task03;

import java.util.*;


class MyUtils {
    private List<String> compare;

    public boolean listMapCompare(List<String> list, Map<String, String> map) {
        if(list == null || map == null) return false;
        if(list.isEmpty() && map.isEmpty()) return true;

        compare = new ArrayList<>();
        for(Map.Entry<String, String> m : map.entrySet()){
            String key = m.getKey();
            String value = m.getValue();
                for(String s : list){
                    if (value == s) {
                        compare.add(key);
                        break;
                    }
                }
        }
        if (compare.size() == map.size()) return true;

        return false;
    }
}
