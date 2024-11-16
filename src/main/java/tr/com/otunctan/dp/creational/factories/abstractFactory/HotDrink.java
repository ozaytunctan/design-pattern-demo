package tr.com.otunctan.dp.creational.factories.abstractFactory;

import org.reflections.Reflections;
import tr.com.otunctan.common.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface HotDrink {
    void consume();
}


class Tea implements HotDrink {

    @Override
    public void consume() {
        System.out.println("This tea  is delicious");
    }
}

class Coffee implements HotDrink {

    @Override
    public void consume() {
        System.out.println("This coffee is delicious");
    }
}

interface HotDrinkFactory {
    HotDrink prepare(double amount);
}

class TeaFactory implements HotDrinkFactory {

    @Override
    public HotDrink prepare(double amount) {
        System.out.println(
                "Put in tea bag , boil water , liras " + amount + " ml, add lemon , enjoy!"
        );
        return new Tea();
    }
}

class CoffeeFactory implements HotDrinkFactory {

    @Override
    public HotDrink prepare(double amount) {
        System.out.println(
                "Grind some beans , boil water , pour " + amount + " ml iadd cream and sugar , enjoy! "
        );
        return new Coffee();
    }
}

class HotDrinkMachine {

    private List<Pair<String, HotDrinkFactory>> namedFactories = new ArrayList<>();

    public HotDrinkMachine() throws Exception {

        Set<Class<? extends HotDrinkFactory>> types = new Reflections("tr.com.otunctan.dp.creational.factories.abstractFactory")
                .getSubTypesOf(HotDrinkFactory.class);

        for (Class<? extends HotDrinkFactory> type : types) {
            namedFactories.add(
                    new Pair<>(
                            type.getSimpleName().replace("Factory", ""),
                            type.getDeclaredConstructor().newInstance()
                    )
            );
        }
    }

    public HotDrink makeDrink() throws Exception {
        System.out.println("Available drinks:");
        for (int index = 0; index < namedFactories.size(); index++) {
            Pair<String, HotDrinkFactory> item = namedFactories.get(index);
            System.out.println(index + " :" + item.getKey());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s;
            int i;
            double amount;
            if ((s = br.readLine()) != null
                    && (i = Integer.parseInt(s)) >= 0
                    && i < namedFactories.size()) {

                System.out.println("Specify amount:");
                s = br.readLine();

                if (s != null && (amount = Double.parseDouble(s)) > 0) {
                    return namedFactories.get(i).getValue().prepare(amount);
                }
            }

            System.out.println("Incorrect input , try again. ");
        }
    }
}

class Demo{

    public static void main(String[] args) throws Exception {

        HotDrinkMachine hotDrinkMachine = new HotDrinkMachine();
        hotDrinkMachine.makeDrink();

    }
}