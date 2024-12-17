package tr.com.otunctan.dp.behavioural.command.undo;

import java.util.ArrayList;
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

    public boolean withdraw(int amount) {
        if (balance - amount >= overdraftLimit) {
            this.balance -= amount;
            System.out.println(" Withdraw " + amount +
                    " , balance is now " + balance);
            return true;
        }
        return false;
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

    void undo();
}


class BankAccountCommand implements Command {

    private BankAccount account;

    enum Action {
        DEPOSIT,
        WITHDRAW
    }

    private Action action;
    private int amount;
    private boolean succeeded;

    public BankAccountCommand(BankAccount account, Action action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }

    @Override
    public void execute() {
        switch (action) {
            case DEPOSIT -> {
                this.succeeded = true;
                this.account.deposit(amount);
            }
            case WITHDRAW -> this.succeeded = this.account.withdraw(amount);
        }
    }

    @Override
    public void undo() {
        if (!succeeded) {
            return;
        }
        switch (action) {
            case DEPOSIT -> this.account.withdraw(amount);
            case WITHDRAW -> this.account.deposit(amount);
        }
    }
}

class Demo {
    public static void main(String[] args) {

        BankAccount ba = new BankAccount();
        System.out.println(ba);
        List<Command> commands = new ArrayList<>();
        commands.add(new BankAccountCommand(ba, BankAccountCommand.Action.DEPOSIT, 100));
        commands.add(new BankAccountCommand(ba, BankAccountCommand.Action.WITHDRAW, 1000));

        commands.forEach((Command command) -> {
            command.execute();
            System.out.println(ba);
        });

        Collections.reverse(commands);
        commands.forEach((Command command) -> {
            command.undo();
            System.out.println(ba);
        });

    }
}


