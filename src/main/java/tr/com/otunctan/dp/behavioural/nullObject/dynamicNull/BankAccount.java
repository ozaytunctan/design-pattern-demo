package tr.com.otunctan.dp.behavioural.nullObject.dynamicNull;

import org.checkerframework.checker.units.qual.C;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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


    public void deposit(int amount) {
        balance += amount;

        // check for null everywhere?
        if (log != null) {
            log.info("Deposited " + amount
                    + ", balance is now " + balance);
        }
    }

    public void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            if (log != null) {
                log.info("Withdrew " + amount
                        + ", we have " + balance + " left");
            }
        } else {
            if (log != null) {
                log.error("Could not withdraw "
                        + amount + " because balance is only " + balance);
            }
        }
    }
}


class Demo {

    @SuppressWarnings("unchecked")
    public static <T> T noOp(Class<T> itf) {
        return (T) Proxy.newProxyInstance(
                itf.getClassLoader(),
                new Class[]{itf},
                ((proxy, method, args) -> {

                    if (method.getReturnType().equals(Void.TYPE)) {
                        return null;
                    } else {
                        return method.getReturnType().getConstructor().newInstance();
                    }
                })
        );
    }

    public static void main(String[] args) {

//        Log log=new ConsoleLogger();
//        BankAccount ba = new BankAccount(null);
//        Log log = new NullLog();

        Log log = noOp(Log.class);//Proxy boş bir obje veriyorum null pointer yememek için.
        BankAccount ba = new BankAccount(log);
        ba.deposit(100);
        ba.withdraw(500);


        Log consoleLog = ProxyLoggerFactory.newProxyInstance(new ConsoleLogger(), Log.class);
        BankAccount bankAccount = new BankAccount(consoleLog);
        bankAccount.deposit(100);
        bankAccount.withdraw(500);
    }
}


class ProxyLoggerFactory {

    public static Log newProxyInstance(Log target, Class<Log> itf) {
        return (Log) Proxy.newProxyInstance(
                itf.getClassLoader(),
                new Class[]{itf},
                new LoggerInvocationHandler(target)
        );
    }
}

class LoggerInvocationHandler implements InvocationHandler {
    private Object target;

    public LoggerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }


}