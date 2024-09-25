import java.io.Serializable;

public class Account implements Serializable {
  private String accountNumber;
  private String holderName;
  private double balance;

  public Account(String accountNumber, String holderName, double balance) {
    this.accountNumber = accountNumber;
    this.holderName = holderName;
    this.balance = balance;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getHolderName() {
    return holderName;
  }

  public double getBalance() {
    return balance;
  }

  public void deposit(double amount) {
    balance += amount;
  }

  public boolean withdraw(double amount) {
    if (amount <= balance) {
      balance -= amount;
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return "Account Number: " + accountNumber + "\nHolder Name: " + holderName + "\nBalance: $" + balance;
  }
}