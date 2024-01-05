public class MainApp {
    public static void main(String[] args) {
        AccountRegistry registry = new AccountRegistry();
        registry.addAccount(new Account(100.));
        registry.addAccount(new Account(200.));
        registry.addAccount(new Account(300.));
        registry.addAccount(new Account(400.));
        registry.addAccount(new Account(500.));

        System.out.println("Newly created accounts:");
        Account[] accounts = registry.listAccounts();
        for (int i = 0; i < accounts.length; i++) {
            System.out.printf("Account Number: %s, Balance: %,.2f Baht\n",
                              accounts[i].getAccountNumber(), accounts[i].getBalance());
        }

        // Close account 1234-0002 and suspend 1234-0004
        accounts[1].close();
        registry.getAccount(accounts[3].getAccountNumber()).suspend();

        accounts[0].withdraw(100.);
        Account acc = registry.getAccount("1234-0003");
        acc.withdraw(150.);
        try {
            registry.getAccount("1234-0005").deposit(200.).transferTo(accounts[3], 100.);
        } catch (IllegalStateException e) {
            System.out.printf("Exception caught: %s\n", e.getMessage());
        }

        System.out.println("Active accounts:");
        accounts = registry.listAccounts(Account.Status.ACTIVE);
        for (Account account: accounts) {
            System.out.printf("Account Number: %s, Balance: %,.2f Baht\n",
                              account.getAccountNumber(), account.getBalance());
        }

        System.out.println("Suspended accounts:");
        accounts = registry.listAccounts(Account.Status.SUSPENDED);
        for (Account account: accounts) {
            System.out.printf("Account Number: %s, Balance: %,.2f Baht\n",
                              account.getAccountNumber(), account.getBalance());
        }

        System.out.println("Closed accounts:");
        accounts = registry.listAccounts(Account.Status.CLOSED);
        for (Account account: accounts) {
            System.out.printf("Account Number: %s, Balance: %,.2f Baht\n",
                              account.getAccountNumber(), account.getBalance());
        }
    }
}
