package tr.com.otunctan.dp.behavioural.nullObject.staticNull;

interface Log {
    void info(String message);

    void error(String message);
}

class ConsoleLogger implements Log {
    @Override
    public void info(String message) {
        System.out.println(message);
    }

    @Override
    public void error(String message) {
        System.err.println(message);
    }
}

class NullLog implements Log {
    @Override
    public void info(String message) {

    }

    @Override
    public void error(String message) {

    }

    public NullLog() {


    }
}

public class BankAccount {

    private int balance = 0;
    private final Log log;

    public BankAccount(Log log) {
        this.log = log;
    }


    public void deposit(int amount)
    {
        balance += amount;

        // check for null everywhere?
        if (log != null)
        {
            log.info("Deposited " + amount
                    + ", balance is now " + balance);
        }
    }

    public void withdraw(int amount)
    {
        if (balance >= amount)
        {
            balance -= amount;
            if (log != null)
            {
                log.info("Withdrew " + amount
                        + ", we have " + balance + " left");
            }
        }
        else {
            if (log != null)
            {
                log.error("Could not withdraw "
                        + amount + " because balance is only " + balance);
            }
        }
    }
}


class Demo {
    public static void main(String[] args) {

//        Log log=new ConsoleLogger();
//        BankAccount ba = new BankAccount(null);
        Log log = new NullLog();
        BankAccount ba = new BankAccount(log);
        ba.deposit(100);
        ba.withdraw(500);
    }
}