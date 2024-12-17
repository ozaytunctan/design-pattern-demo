package tr.com.otunctan.dp.behavioural.command;

import java.util.Collections;
import java.util.List;

public class BankAccount {

    private String name;
    private int balance;
    private final int overdraftLimit = -500;


    public void deposit(int amount) {
        this.balance += amount;
        System.out.println("Deposited " + amount + " , balance is now " + balance);
    }

    public void withdraw(int amount) {
        if (balance - amount >= overdraftLimit) {
            this.balance -= amount;
            System.out.println(" Withdraw " + amount +
                    " , balance is now " + balance);
        }
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}


interface Command {
    void execute();
}


class BankAccountCommand implements Command {

    private BankAccount account;

    enum Action {
        DEPOSIT,
        WITHDRAW
    }

    private Action action;
    private int amount;

    public BankAccountCommand(BankAccount account, Action action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }

    @Override
    public void execute() {
        switch (action) {
            case DEPOSIT -> this.account.deposit(amount);
            case WITHDRAW -> this.account.withdraw(amount);
        }
    }
}

class Demo {
    public static void main(String[] args) {

        BankAccount ba = new BankAccount();
        System.out.println(ba);
        List<BankAccountCommand> commands = List.of(
                new BankAccountCommand(ba, BankAccountCommand.Action.DEPOSIT, 100),
                new BankAccountCommand(ba, BankAccountCommand.Action.WITHDRAW, 1000)
        );


        commands.forEach(bankAccountCommand -> {
            bankAccountCommand.execute();
            System.out.println(ba);
        });

    }
}


