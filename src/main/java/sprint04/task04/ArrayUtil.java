package sprint04.task04;

class ArrayUtil {
    @SuppressWarnings("unchecked")
    public static <T> T setAndReturn(T[] obj, T t, int y){
        obj[y] = t;
        return obj[y];
    }
}
