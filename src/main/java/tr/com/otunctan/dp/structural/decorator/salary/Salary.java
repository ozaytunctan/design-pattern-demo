package tr.com.otunctan.dp.structural.decorator.salary;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Salary {

    List<Salary> getItems();

    BigDecimal calculateTotalSalary();


}



class AdditionalFamilySalary implements Salary {

    private Salary salary;

    private int childCount;
    private BigDecimal childPerPrice = BigDecimal.valueOf(100);

    public AdditionalFamilySalary(Salary salary, int childCount) {
        this.salary = salary;
        this.childCount = childCount;
    }


    @Override
    public List<Salary> getItems() {
        List<Salary> items = salary.getItems();
        items.add(this);
        return items;
    }

    @Override
    public BigDecimal calculateTotalSalary() {
        BigDecimal amount = childPerPrice.multiply(new BigDecimal(childCount));
        return this.salary.calculateTotalSalary().add(amount);
    }
}


class SocialAssistance implements Salary {
    private final BigDecimal SOCIAL_ASSISTANCE_AMOUNT = new BigDecimal(1000);
    private Salary salary;

    public SocialAssistance(Salary salary) {
        this.salary = salary;
    }

    @Override
    public List<Salary> getItems() {
        List<Salary> items = salary.getItems();
        items.add(this);
        return items;
    }

    @Override
    public BigDecimal calculateTotalSalary() {
        return salary.calculateTotalSalary().add(SOCIAL_ASSISTANCE_AMOUNT);
    }
}

class BaseSalary implements Salary {

    private Salary salary;

    private int kidemYear;
    private int kidemDegree;

    private Map<Integer, BigDecimal> baseSalaryMap = new HashMap<>();

    public BaseSalary(Salary salary, int kidemYear, int kidemDegree) {
        this.salary = salary;
        this.kidemYear = kidemYear;
        this.kidemDegree = kidemDegree;
        baseSalaryMap = new HashMap<>();
        baseSalaryMap.put(1, new BigDecimal(1000));
        baseSalaryMap.put(2, new BigDecimal(2000));
        baseSalaryMap.put(3, new BigDecimal(3000));
    }

    @Override
    public List<Salary> getItems() {
        List<Salary> items = salary.getItems();
        items.add(this);
        return items;
    }

    @Override
    public BigDecimal calculateTotalSalary() {
        BigDecimal total = salary.calculateTotalSalary();
        BigDecimal baseSalary = baseSalaryMap.get(this.kidemYear);
        return total.add(baseSalary);
    }
}


class EmptySalary implements Salary {

    public EmptySalary() {
    }

    @Override
    public List<Salary> getItems() {
        return new ArrayList<>();
    }

    @Override
    public BigDecimal calculateTotalSalary() {
        return new BigDecimal(0);
    }
}
class SalaryDemo{

    public static void main(String[] args) {
        EmptySalary startSalary=new EmptySalary();
        System.out.println("Empty Salary:");
        System.out.println(startSalary.getItems().size());
        System.out.println(startSalary.calculateTotalSalary());

        BaseSalary baseSalary=new BaseSalary(startSalary,1,10);
        System.out.println("Added base salary:");
        System.out.println(baseSalary.getItems().size());
        System.out.println(baseSalary.calculateTotalSalary());

        AdditionalFamilySalary familySalary=new AdditionalFamilySalary(baseSalary,3);
        System.out.println("Added family salary");
        System.out.println(familySalary.getItems().size());
        System.out.println(familySalary.calculateTotalSalary());

        SocialAssistance socialAssistance=new SocialAssistance(familySalary);
        System.out.println("Social asistance salary:");
        System.out.println(socialAssistance.getItems().size());
        System.out.println(socialAssistance.calculateTotalSalary());
    }
}