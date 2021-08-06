package JavaTest;

import java.util.ArrayList;
import java.util.HashSet;

import java.lang.reflect.*;
import java.util.Scanner;
import java.lang.ClassNotFoundException;
import java.lang.annotation.Target;



abstract class TestCase {
    abstract protected void Run();

    final public void RunTestCase() {
        System.out.println("=========[START TEST]=========" + this.getClass().getName() + "=====================");
        Run();
        System.out.println("=========[END   TEST]=========" + this.getClass().getName() + "=====================");
    }
}

class Data {
    public String s;
    public int i;

    public Data(String s, int i) {
        this.s = s;
        this.i = i;
    }
    public Data() {
        s = "";
        i = 0;
    }

    @Override
    public String toString() {
        return "[" + s + ", " + i + "]";
    }
}

class MethodTest extends TestCase {

    public void PrintString(String s /* = "not support" */) {
        System.out.println("MethodTest print :" + s);
    }

    public void ChangeValue(String s, Data d, StringBuilder sb) {
        System.out.println("s = " + System.identityHashCode(s) + ", d = " + System.identityHashCode(d) + ", sb = " + System.identityHashCode(sb));
        
        s = " hehe.";
        
        sb.delete(0, sb.length()-1);
        sb.append(s);
        
        d.s = "hehe";
        d.i = 123;
        
        System.out.println("s = " + System.identityHashCode(s) + ", d = " + System.identityHashCode(d) + ", sb = " + System.identityHashCode(sb));
    }

    @Override
    protected void Run() {
        PrintString("Hello");


        String s = new String();
        StringBuilder sb = new StringBuilder("sb");
        Data d = new Data();
        System.out.println("s = " + System.identityHashCode(s) + ", d = " + System.identityHashCode(d) + ", sb = " + System.identityHashCode(sb));
        s += "xxxx";
        sb.append("xxxx");
        System.out.println("s = " + System.identityHashCode(s) + ", d = " + System.identityHashCode(d) + ", sb = " + System.identityHashCode(sb));
        ChangeValue(s, d, sb);
        System.out.println("s = " + System.identityHashCode(s) + ", d = " + System.identityHashCode(d) + ", sb = " + System.identityHashCode(sb));
        System.out.println("s = " + s + ", d = " + d + ", sb = " + sb);
    }
}

class CollectionTest extends TestCase {
    
    @Override
    protected void Run() {
        Data d1 = new Data("Data 1", 1);

        ArrayList<Data> list = new ArrayList<>();
        list.add(d1);

        System.out.println("list[0].i = " + list.get(0).i);
        d1.i = 2;
        System.out.println("list[0].i = " + list.get(0).i);

        list.add(d1);
        System.out.println("list.size = " + list.size());

        for (Data d : list) {
            System.out.println("list obj = " + System.identityHashCode(d));
        }
    }
}

class ReflectTest extends TestCase {
    
    @Override
    protected void Run() {
        System.out.println("Please input class name:");
        Scanner scanner = new Scanner(System.in);
        String className = scanner.next();

        try {
            Class<?> cls = Class.forName(className);
            Method[] methods = cls.getMethods();

            for (Method m : methods) {
                StringBuilder s = new StringBuilder();
                s.append(m.getReturnType().getName());
                s.append(" ");
                s.append(m.getName());
                s.append("(");

                Class<?>[] params = m.getParameterTypes();
                for(Class<?> param : params) {
                    s.append(param.getName());
                    s.append(", ");
                }
                s.append(");");
                System.out.println("Class[" + className + "] method : " + s);
            }

        } catch(ClassNotFoundException e) {
            System.out.println("Class[" + className + "] not found : " + e);
        }
        
        scanner.close();
    }
}

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        HashSet<TestCase> testList = new HashSet<>();
        testList.add(new MethodTest());
        testList.add(new CollectionTest());
        testList.add(new ReflectTest());

        for (TestCase test : testList) {
            test.RunTestCase();
        }
    }
}