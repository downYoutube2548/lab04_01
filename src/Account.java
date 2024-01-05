public class Account {
    public enum Status { ACTIVE, CLOSED, SUSPENDED};

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

    public Account deposit(double amount) {
        if (status != Status.ACTIVE)
            throw new IllegalStateException("Account is unavailable.");
        else if (amount <= 0.)
            throw new IllegalArgumentException("Amount must be positive.");
        else
            balance += amount;
        return this;
    }

    public Account withdraw(double amount) {
        if (status != Status.ACTIVE)
            throw new IllegalStateException("Account is unavailable.");
        else if (amount <= 0. || amount > balance)
            throw new IllegalArgumentException("Amount must be positive and not exceeding balance.");
        else
            balance -= amount;
        return this;
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

    public void suspend() {
        status = Status.SUSPENDED;
    }

    public void reactivate() {
        status = Status.ACTIVE;
    }

    public boolean isActive() {
        return status == Status.ACTIVE;
    }

    public Account transferTo(Account destAcc, double amount) {
        if (!this.isActive() || !destAcc.isActive()) {
            throw new IllegalStateException("Account is unavailable.");
        }
        this.withdraw(amount);
        destAcc.deposit(amount);
        return this;
    }
}