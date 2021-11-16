package sprint04.task05;

class ArrayUtil {

    public static double averageValue(Array<? extends Number> set){
        if(set.length() == 0) return 0;
        double result = 0;
        for(int i = 0; i < set.length(); i++){
            result += set.get(i).doubleValue();
        }
        return Math.round(result / set.length());
    }
}
