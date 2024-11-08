package tr.com.otunctan.solid.dip.v1;

import java.util.ArrayList;
import java.util.List;

public enum Relationship {
    PARENT,
    CHILD,
    SIBLING;

}

class Person {

    public String name;

    public Person(String name) {
        this.name = name;
    }
}


// low-level
class Relationships {

    private List<Triplet<Person, Relationship, Person>> relations =
            new ArrayList<>();

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }
}


//high-level
class Research {

    public Research(Relationships relationships) {
        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
        relations.stream()
                .filter(x->x.getValue0().name.equals("John") //john çouklarına erişmek istiyorum
                       && x.getValue1()==Relationship.PARENT
                )
                .forEach(ch->System.out.println(
                        " John has a child called "+ ch.getValue3().name
                ));
    }
}
class Demo{

    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child1= new Person("Ada Can");
        Person child2= new Person("Eylül");



        Relationships relationships=new Relationships();
        relationships.addParentAndChild(parent,child1);
        relationships.addParentAndChild(parent,child2);

//        Person child1Child1=new Person("Efe");
//        relationships.addParentAndChild(child1,child1Child1);

        Research research = new Research(relationships);
    }
}

class Triplet<T1,T2,T3> {

    private T1 value0;

    private T2 value1;

    private T3 value3;

    public Triplet(T1 value0, T2 value1, T3 value3) {
        this.value0 = value0;
        this.value1 = value1;
        this.value3 = value3;
    }

    public T1 getValue0() {
        return value0;
    }

    public void setValue0(T1 value0) {
        this.value0 = value0;
    }

    public T2 getValue1() {
        return value1;
    }

    public void setValue1(T2 value1) {
        this.value1 = value1;
    }

    public T3 getValue3() {
        return value3;
    }

    public void setValue3(T3 value3) {
        this.value3 = value3;
    }
}