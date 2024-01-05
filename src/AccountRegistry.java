import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AccountRegistry {
    private ArrayList<Account> accounts = new ArrayList<Account>();

    private int countActiveAccounts(Account.Status status) {
        int count = 0;
        for (Account account: accounts)
            if (account.getStatus() == status)
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
        return listAccounts(Account.Status.ACTIVE);
    }

    public Account[] listAccounts(Account.Status status) {
        Account[] output = new Account[countActiveAccounts(status)];

        int i = 0;
        for (Account acc : accounts) {
            if (acc.getStatus() == status) {
                output[i] = acc;
                i++;
            }
        }

        return output;
    }
}
