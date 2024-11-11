package tr.com.otunctan.dp.creational.builder.fluent;

public class Person {
    public String name;
    public String position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

class PersonBuilder<SELF extends PersonBuilder<SELF>> {

    protected Person person = new Person();

    public SELF withName(String name) {
        this.person.name = name;
        return self();
    }

    protected SELF self() {
        return (SELF) this;
    }

    public Person build() {
        return person;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {

    public EmployeeBuilder worksAt(String position) {
        person.position = position;
        return this;
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}

class Demo {
    public static void main(String[] args) {
        EmployeeBuilder eb = new EmployeeBuilder();
        Person ozay = eb.withName("Özay")
                .worksAt("Developper")
                .build();

        Person alihan = eb.withName("Alihan")
                .worksAt("Developper")
                .build();

        System.out.println(ozay);
        System.out.println(alihan);
    }
}