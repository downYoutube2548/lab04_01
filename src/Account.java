public class Account {
    public enum Status { ACTIVE, CLOSED };

    private static final String ACCOUNT_PREFIX = "1234";
    private static int nextId = 1;

    private String accountNumber;
    private double balance;
    private Status status;

    public Account(double initialBalance) {
        this.accountNumber = getNextAccountNumber();
        nextId++;

        if (initialBalance <= 0.)
            throw new IllegalArgumentException("Initial balance must be positive.");
        else
            this.balance = initialBalance;

        this.status = Status.ACTIVE;
    }

    public void deposit(double amount) {
        if (status != Status.ACTIVE)
            throw new IllegalStateException("Account is inactive.");
        else if (amount <= 0.)
            throw new IllegalArgumentException("Amount must be positive.");
        else
            balance += amount;
    }

    public void withdraw(double amount) {
        if (status != Status.ACTIVE)
            throw new IllegalStateException("Account is inactive.");
        else if (amount <= 0. || amount > balance)
            throw new IllegalArgumentException("Amount must be positive and not exceeding balance.");
        else
            balance -= amount;
    }

    public void close() {
        if (status == Status.CLOSED)
            throw new IllegalStateException("Account is already closed.");
        else
            status = Status.CLOSED;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Status getStatus() {
        return status;
    }

    public static String getNextAccountNumber() {
        return String.format("%s-%04d", ACCOUNT_PREFIX, nextId);
    }
}