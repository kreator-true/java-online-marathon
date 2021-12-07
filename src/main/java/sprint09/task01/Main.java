package sprint09.task01;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println(CheckCamelCase.checkAndPrint(Class1.class));
        System.out.println(CheckCamelCase.checkAndPrint(Class2.class));
    }
}

abstract class CheckCamelCase {
    public static final Pattern CAMELCASE_PATTERN = Pattern.compile("([A-Z].*)");

    public static boolean checkAndPrint(Class clazz){
        boolean isSatisfy = true;
        String methodName;
        for (Method method : clazz.getMethods()) {
            if(method.isAnnotationPresent(CamelCase.class)) {
                methodName = method.getName();
                if (CAMELCASE_PATTERN.matcher(methodName).matches()) {
                    System.out.printf("method %s.%s doesn't satisfy camelCase naming convention\n", clazz.getSimpleName(), methodName);
                    isSatisfy = false;
                }
            }
        }
        return isSatisfy;
    }

}
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface CamelCase{}

class ClassForAnnot {
    @CamelCase
    public static void example() {
    }

    @CamelCase
    public void Example() {
    }

    public static void _main(String args[]) {
    }
}
class Class1 {
    @CamelCase
    public void correct(){}
    @CamelCase
    public void InCorrect(){}
    @CamelCase
    public void JustMethod(){}

    public void Just1Method(){}
}

class Class2 {
    @CamelCase
    public void correct(){}
    @CamelCase
    public void oneMoreCorrect(){}
}
