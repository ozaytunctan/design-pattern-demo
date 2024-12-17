package tr.com.otunctan.dp.behavioural.command.v3;

import java.util.Stack;

class BankAccount {
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

public interface Command {
    void execute();

    void undo();
}

enum Action {
    DEPOSIT,
    WITHDRAW
}

class BankAccountCommand implements Command {
    private BankAccount account;
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


class BankAccountManager {
    private Stack<Command> redos = new Stack<>();
    private Stack<Command> undos = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        this.undos.push(command);
        this.redos = new Stack<>();//clear redo stack
    }

    public void undo() {
        if (this.undos.isEmpty()) {
            return;
        }
        Command command = this.undos.pop();
        if (command != null) {
            command.undo();
            this.redos.push(command);
        }
    }

    public void redo() {
        if (this.redos.isEmpty()) return;
        Command command = this.redos.pop();
        if (command != null) {
            command.execute();
            this.undos.push(command);
        }
    }
}

class Demo {

    public static void main(String[] args) {

        BankAccount ba = new BankAccount();
        System.out.println(ba);
        BankAccountManager accountManager = new BankAccountManager();
        accountManager.executeCommand(new BankAccountCommand(ba, Action.DEPOSIT, 100));
        System.out.println(ba);

        accountManager.undo();
        System.out.println(ba);
        accountManager.executeCommand(new BankAccountCommand(ba, Action.WITHDRAW, 300));
        System.out.println(ba);
        accountManager.redo();
        System.out.println(ba);
        accountManager.executeCommand(new BankAccountCommand(ba, Action.DEPOSIT, 400));
        System.out.println(ba);


    }
}