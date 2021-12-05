package sprint09.task02;

import java.time.LocalDate;

@Review(reviewer = "me")
class Util {
    public static void review(String className) {
        Class<?> someClass = null;
        try {
            someClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.out.printf("Class %s was not found", className);
            return;
        }
        if(!someClass.isAnnotationPresent(Review.class)){
            System.out.printf("Class %s isn't marked as Reviewed\n", className);
            return;
        }
        String date = someClass.getAnnotation(Review.class).date().equals("today") ? LocalDate.now().toString() : someClass.getAnnotation(Review.class).date();
        System.out.printf("Class %s was reviewed %s by %s.\n", className, date, someClass.getAnnotation(Review.class).reviewer());
    }

    public static void main(String[] args) {
        review("sprint09.task02.Util");
    }
}
