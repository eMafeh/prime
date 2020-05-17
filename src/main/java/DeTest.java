import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class DeTest {
    public static void main(String[] args) {
        System.out.println(VM.current()
                .details());
        System.out.println(ClassLayout.parseInstance(new B())
                .toPrintable());
    }

}

class A {
    boolean b;
}

class B extends A {
    long a;
}