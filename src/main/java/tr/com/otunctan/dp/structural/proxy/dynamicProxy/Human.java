package tr.com.otunctan.dp.structural.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public interface Human {
    void talk();

    void walk();

}

class Person implements Human {

    @Override
    public void talk() {
        System.out.println("I am walking.");
    }

    @Override
    public void walk() {
        System.out.println("I am talking.");
    }
}

class LoggingHandler implements InvocationHandler {

    private final Object target;

    private Map<String, Integer> calls = new HashMap<>();

    public LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String name = method.getName();
        if (name.contains("toString")) {
            return calls.toString();
        }

        calls.merge(name, 1, Integer::sum);
        return method.invoke(target, args);
    }
}


class Demo {

    @SuppressWarnings("unchecked")
    public static <T> T withLogging(T target, Class<T> itf) {
        return (T) Proxy.newProxyInstance(
                itf.getClassLoader(),
                new Class[]{itf},
                new LoggingHandler(target)
        );

    }

    public static void main(String[] args) {

        Person person = new Person();
        Human logged = withLogging(person, Human.class);
        logged.talk();
        logged.walk();
        logged.walk();

        System.out.println(logged);
    }
}

