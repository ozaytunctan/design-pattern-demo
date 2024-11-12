package tr.com.otunctan.dp.creational.builder.facet;

import java.math.BigDecimal;

public class Person {

    public String firstName;
    public String lastName;

    public String city;
    public String postCode;

    public String companyName;
    public BigDecimal salary;


    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", postCode='" + postCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", salary=" + salary +
                '}';
    }
}

class PersonBuilder {

    protected Person person = new Person();

    public PersonBuilder() {
    }


    public Person build() {
        return person;
    }

    public PersonAddressBuilder lives() {
        return new PersonAddressBuilder(person);
    }

    public PersonJobBuilder works() {
        return new PersonJobBuilder(person);
    }

    public PersonBuilder firstName(String firstName) {
        this.person.firstName = firstName;
        return this;
    }

    public PersonBuilder lastName(String lastName) {
        this.person.lastName = lastName;
        return this;
    }
}

class PersonAddressBuilder extends PersonBuilder {
    public PersonAddressBuilder(Person person) {
        this.person = person;
    }

    public PersonAddressBuilder at(String city) {
        this.person.city = city;
        return this;
    }

    public PersonAddressBuilder postCode(String postCode) {
        this.person.postCode = postCode;
        return this;
    }
}

class PersonJobBuilder extends PersonBuilder {

    public PersonJobBuilder(Person person) {
        this.person=person;
    }

    public PersonJobBuilder companyName(String companyName) {
        this.person.companyName = companyName;
        return this;
    }

    public PersonJobBuilder salary(BigDecimal salary) {
        this.person.salary = salary;
        return this;
    }
}


class Demo {
    public static void main(String[] args) {


        PersonBuilder pb = new PersonBuilder();
        Person person = pb
                .firstName("Özay")
                .lastName("TUNÇTAN")
                .lives()
                .at("Bitlis")
                .postCode("130000")
                .works()
                .companyName("İçişleri Bakanlığı")
                .salary(new BigDecimal(50_000))
                .build();

        System.out.println(person);
    }
}