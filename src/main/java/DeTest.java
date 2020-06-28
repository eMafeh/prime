import java.util.HashMap;

public class DeTest {
    public static void main(String[] args) {
//        System.out.println(VM.current().details());
//        System.out.println(ClassLayout.parseInstance(new B()).toPrintable());
        HashMap<Object, Object> map = new HashMap<>();
        map.put(null,1);
        System.out.println(map.get(null));
    }

}

class A {
    boolean b;
}

class B extends A {
    long a;
}