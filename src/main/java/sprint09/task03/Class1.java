package sprint09.task03;

@TestSuite({"m1", "m2", "m3"})
class Class1 {

    private Class1() {
    }

    public void m1() {
        System.out.println("m1!");
    }

    public static boolean m1(String s) {
        return false;
    }

    public boolean m2(String s) {
        System.out.println("false");
        return false;
    }
    public String m2(){
        System.out.println("m2");
        return "";
    }
}
