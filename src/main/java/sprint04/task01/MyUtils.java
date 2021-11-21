package sprint04.task01;

import java.util.*;
import java.util.stream.Collectors;


class MyUtils extends ArrayList<String>{
    private Map<String, List<String>> result;
    public Map<String, List<String>> createNotebook(Map<String, String> phones) {
        if(phones == null) return null;
        if(phones.isEmpty()) return Map.of();
        List<String> list;
        result = new HashMap<>();
        for(Map.Entry<String, String> p : phones.entrySet()){
            String key = p.getValue();
            String value = p.getKey();
            if(!result.containsKey(key)) {
                list = new ArrayList<>();
                list.add(value);
                result.put(key, list);
            }
            else {
                list = result.get(key);
                list.add(value);
                result.put(key, list);
            }
        }
        /*for(var entry : phones.entrySet()){
            result.computeIfAbsent(entry.getValue(), k -> new ArrayList<>()).add(entry.getKey());
        }*/
        return result;
        /*return phones.entrySet().stream().filter(p -> p.getValue() != null)
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())));*/
    }
}
