package tr.com.otunctan.dp.behavioural.observer.v1;

import java.util.ArrayList;
import java.util.List;

class PropertyChangeEvent<T> {

    private T source;

    private String propertyName;
    private Object newValue;

    public PropertyChangeEvent(T source, String propertyName, Object newValue) {
        this.source = source;
        this.propertyName = propertyName;
        this.newValue = newValue;
    }

    public T getSource() {
        return source;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Object getNewValue() {
        return newValue;
    }
}

interface Observer<T> {
    void handle(PropertyChangeEvent<T> args);
}

class Observable<T> {

    private List<Observer<T>> observers = new ArrayList<>();

    public void subscribe(Observer<T> observer) {
        observers.add(observer);
    }

    protected void propertyChanged(T source,
                                   String propertyName,
                                   Object newValue
    ) {
        for (Observer<T> observer : observers) {
            observer.handle(new PropertyChangeEvent<>(source, propertyName, newValue));
        }
    }
}

public class Person extends Observable<Person> {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age == age) return;
        this.age = age;
        propertyChanged(this, "age", age);
    }
}


class Demo implements Observer<Person> {

    public Demo() {
        Person person = new Person();
//        person.subscribe((observer)->{
//            System.out.println(observer.getNewValue());
//        });
//        person.subscribe(new Observer<Person>() {
//            @Override
//            public void handle(PropertyChangeEvent<Person> args) {
//                System.out.println(args.getNewValue());
//            }
//        });
        person.subscribe(this);
        for (int i = 20; i < 24; i++) {
            person.setAge(i);
        }
    }

    @Override
    public void handle(PropertyChangeEvent<Person> args) {
        System.out.println("Person' s " + args.getPropertyName()
                + " has changed to " + args.getNewValue());

    }

    public static void main(String[] args) {
        new Demo();
    }
}



