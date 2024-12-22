package tr.com.otunctan.dp.behavioural.observer.v2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class Event<TArgs> {

    private int count;
    private Map<Integer, Consumer<TArgs>>
            handlers = new HashMap<>();

    public Subscription addHandler(Consumer<TArgs> handler) {
        int i = count;
        handlers.put(count++, handler);
        return new Subscription(this, i);
    }

    public void fire(TArgs args) {
        for (Consumer<TArgs> handlers : handlers.values()) {
            handlers.accept(args);
        }
    }

    public class Subscription implements AutoCloseable {
        private Event<TArgs> event;
        private int id;

        public Subscription(Event<TArgs> event, int id) {
            this.event = event;
            this.id = id;
        }

        @Override
        public void close() throws Exception {
            event.handlers.remove(id);
        }
    }

}

class PropertyChangeEventArgs {
    private Object source;
    private String propertyName;

    public PropertyChangeEventArgs(Object source, String propertyName) {
        this.source = source;
        this.propertyName = propertyName;
    }

    public Object getSource() {
        return source;
    }

    public String getPropertyName() {
        return propertyName;
    }
}

class Person {

    public Event<PropertyChangeEventArgs> propertyChanged =
            new Event<>();

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age == age) return;
        this.age = age;
        propertyChanged.fire(new PropertyChangeEventArgs(this, "age"));
    }

//    public Event.Subscription subscribe(Consumer<PropertyChangeEventArgs> args) {
//        return this.propertyChanged.addHandler(args);
//    }
}


class Demo {

    public static void main(String[] args) throws Exception {

        Person person = new Person();
        Event<PropertyChangeEventArgs>.Subscription sub = person.propertyChanged.addHandler(
                event -> {
                    System.out.println("Person's " +
                            event.getPropertyName() + " has changed"
                    );

                }
        );

        person.setAge(10);
        person.setAge(20);
        sub.close();
        person.setAge(30);
    }
}