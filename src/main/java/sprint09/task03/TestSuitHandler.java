package sprint09.task03;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class TestSuitHandler {
    public static void run(Class<?> clazz){
        String className = clazz.getSimpleName();
        TestSuite annotation = clazz.getAnnotation(TestSuite.class);
        if(annotation == null) {
            System.out.printf("Class %s isn't annotated", className);
            return;
        }
        Method method = null;
        for(String nameMethod : annotation.value()) {
            try {
                method =  clazz.getMethod(nameMethod);
            } catch (NoSuchMethodException e) {
                System.out.printf("Method with name %s doesn't exists or not public in class %s\n", nameMethod, className);
            }
            if(method != null && method.getParameterCount() == 0 && method.getModifiers() == 1) {
                System.out.printf("\t -- Method %s.%s started --\n", className, nameMethod);
                try {
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);  // for private constructor
                    method.invoke(constructor.newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                System.out.printf("\t -- Method %s.%s finished --\n", className, nameMethod);
                method = null;
            }
        }
    }

    public static void main(String[] args) {
        run(Class1.class);
    }
}

