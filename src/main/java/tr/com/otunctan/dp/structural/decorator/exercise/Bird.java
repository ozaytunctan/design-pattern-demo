
package tr.com.otunctan.dp.structural.decorator.exercise;

public class Bird {
    public int age;

    public String fly() {
        return age < 10 ? "flying" : "too old";
    }
}

class Lizard {
    public int age;

    public String crawl() {
        return (age > 1) ? "crawling" : "too young";
    }
}

class Dragon {
    private int age;

    private Bird bird = new Bird();
    private Lizard lizard = new Lizard();

    public void setAge(int age) {
        bird.age = age;
        lizard.age = age;
        this.age = age;
    }

    public String fly() {
        return bird.fly();
    }

    public String crawl() {
        return lizard.crawl();
    }
}

class Demo{

    public static void main(String[] args) {
        Dragon dragon = new Dragon();
        dragon.setAge(10);
        System.out.println(dragon.fly());
        System.out.println(dragon.crawl());
        dragon.setAge(2);
        System.out.println(dragon.fly());
        System.out.println(dragon.crawl());
    }
}