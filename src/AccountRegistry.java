import java.util.ArrayList;
import java.util.NoSuchElementException;

public class AccountRegistry {
    private ArrayList<Account> accounts = new ArrayList<Account>();

    private int countActiveAccounts() {
        int count = 0;
        for (Account account: accounts)
            if (account.getStatus() == Account.Status.ACTIVE)
                count++;
        return count;
    }

    public void addAccount(Account acc) {
        if (acc == null)
            throw new NullPointerException("Account is null.");
        else
            accounts.add(acc);
    }

    public Account getAccount(String accountNumber) {
        for (Account account: accounts)
            if (account.getAccountNumber().equals(accountNumber))
                return account;
        throw new NoSuchElementException("No such account number.");
    }

    public Account[] listAccounts() {
        Account[] results = new Account[countActiveAccounts()];

        int idx = 0;
        for (Account account: accounts)
            if (account.getStatus() == Account.Status.ACTIVE)
                results[idx++] = account;

        return results;
    }
}
