package tr.com.otunctan.solid.ocp.v2;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author ozay.tunctan
 * <p>
 * Open Closing Principles(OCP) / Değişime kapılı gelişime açık prensibi
 * <p>
 * aşağıda görüldüğü gibi her yeni kriter de yeni metod veya varolanı değiştimek zorunda kalıyoruz. o yüzden ocp aykırı bir hareket.
 * <p>
 * 2. Yönteme baktığımızda değişime kapalı gelişime açık görünüyor. Yeni bir kriter geldiğinde sadece implement etmesini yeterli olmaktadır.
 */
public enum Color {
    RED,
    GREEN,
    BLUE;
}

enum Size {
    SMALL,
    MEDIUM,
    LARGE;
}

class Product {
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

class ProductFilter {

    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream()
                .filter(p -> p.color == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size) {
        return products.stream()
                .filter(p -> p.size == size);
    }

    public Stream<Product> filterBySizeAndColor(List<Product> products,
                                                Size size,
                                                Color color) {
        return products.stream()
                .filter(p -> p.size == size && p.color == color);
    }
}

interface Specification<T> {
    boolean isSatisfied(T item);
}

//ocp aykırı
interface Filter<T> {
    Stream<T> filter(List<T> items, Specification<T> spec);
}


//daha iyi
class BetterFilter implements Filter<Product> {

    @Override
    public Stream<Product> filter(List<Product> items,
                                  Specification<Product> spec) {

        return items.stream().filter(spec::isSatisfied);
    }
}

class SimpleFilter<T> {

    private Specification<T> filter;

    public SimpleFilter(Specification<T> filter) {
        this.filter = filter;
    }

    public SimpleFilter<T> and(Specification<T> and) {
        this.filter = new AndSpecification<>(filter, and);
        return this;
    }

    public SimpleFilter<T> or(Specification<T> or) {
        this.filter = new OrSpecification<>(filter, or);
        return this;
    }


}

class ColorSpecification implements Specification<Product> {

    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return this.color == item.color;
    }
}

class SizeSpecification implements Specification<Product> {

    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return this.size == item.size;
    }
}

class AndSpecification<T> implements Specification<T> {

    private Specification<T> first, second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return this.first.isSatisfied(item) && this.second.isSatisfied(item);
    }
}

class OrSpecification<T> implements Specification<T> {

    private final Specification<T> first, second;

    public OrSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return this.first.isSatisfied(item) || this.second.isSatisfied(item);
    }
}

class Demo {

    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = List.of(apple, tree, house);
        ProductFilter pf = new ProductFilter();
        System.out.println("Green products (old):");
        pf.filterByColor(products, Color.GREEN)
                .forEach(p -> System.out.println(" - " + p.name + " is green"));


        BetterFilter betterFilter = new BetterFilter();
        System.out.println("Green products (new):");
        betterFilter.filter(products, new ColorSpecification(Color.GREEN))
                .forEach(p -> System.out.println(" - " + p.name + " is green"));

        System.out.println("Large blue items:");
        betterFilter.filter(products,
                        new AndSpecification<>(
                                new ColorSpecification(Color.BLUE),
                                new SizeSpecification(Size.LARGE)
                        ))
                .forEach(p -> System.out.println(" - " + p.name + " is large and blue "));

//        System.out.println("Large blue items:");
//        betterFilter.filter(products, new AndSpecification<>(new ColorSpecification(Color.GREEN),
//                        new OrSpecification<>(new SizeSpecification(Size.LARGE), new SizeSpecification(Size.SMALL))))
//                .forEach(p -> System.out.println(" - " + p.name + " is green"));


//        SimpleFilter<Product> simpleFilter = new SimpleFilter<Product>(new ColorSpecification(Color.GREEN))
//                .and(new SizeSpecification(Size.SMALL))
//                .and(new SizeSpecification(Size.LARGE))
//                .or(new AndSpecification<>(new ColorSpecification(Color.BLUE), new SizeSpecification(Size.LARGE)));
    }
}