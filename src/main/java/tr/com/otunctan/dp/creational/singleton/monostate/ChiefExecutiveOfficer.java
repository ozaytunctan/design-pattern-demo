package tr.com.otunctan.dp.creational.singleton.monostate;

public class ChiefExecutiveOfficer {

    private static String name;
    private static int age;

    public void setName(String name) {
        ChiefExecutiveOfficer.name = name;
    }

    public void setAge(int age) {
        ChiefExecutiveOfficer.age = age;
    }


    @Override
    public String toString() {
        return "ChiefExecutiveOfficer{" +
                "name=" + name + "\n" +
                "age=" + age + " }";
    }


    public static void main(String[] args) {

        ChiefExecutiveOfficer ceo = new ChiefExecutiveOfficer();
        ceo.setName("Özay Tunçtan");
        ceo.setAge(30);

        System.out.println(ceo);

        ChiefExecutiveOfficer ceo2 = new ChiefExecutiveOfficer();
        System.out.println(ceo2);

        ceo2.setAge(32);
        System.out.println(ceo);
    }
}
