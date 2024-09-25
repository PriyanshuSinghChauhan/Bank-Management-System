import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class BankManagementSystemGUI {

  // Using a HashMap to simulate account storage for demonstration purposes
  private static Map<String, Account> accountMap = new HashMap<>();

  public static void main(String[] args) {
    // Create the JFrame (main window)
    JFrame frame = new JFrame("Bank Management System");
    frame.setSize(400, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create JPanel to hold components
    JPanel panel = new JPanel();
    frame.add(panel);
    placeComponents(panel);

    // Display the window
    frame.setVisible(true);
  }

  private static void placeComponents(JPanel panel) {
    panel.setLayout(null);

    // Create Labels and Text Fields for Account Number, Name, Amount
    JLabel accountNumberLabel = new JLabel("Account Number:");
    accountNumberLabel.setBounds(10, 20, 150, 25);
    panel.add(accountNumberLabel);

    JTextField accountNumberText = new JTextField(20);
    accountNumberText.setBounds(150, 20, 200, 25);
    panel.add(accountNumberText);

    JLabel holderNameLabel = new JLabel("Holder Name:");
    holderNameLabel.setBounds(10, 50, 150, 25);
    panel.add(holderNameLabel);

    JTextField holderNameText = new JTextField(20);
    holderNameText.setBounds(150, 50, 200, 25);
    panel.add(holderNameText);

    JLabel amountLabel = new JLabel("Amount:");
    amountLabel.setBounds(10, 80, 150, 25);
    panel.add(amountLabel);

    JTextField amountText = new JTextField(20);
    amountText.setBounds(150, 80, 200, 25);
    panel.add(amountText);

    // Create Buttons for Create Account, Deposit, Withdraw, Check Balance
    JButton createButton = new JButton("Create Account");
    createButton.setBounds(10, 120, 150, 25);
    panel.add(createButton);

    JButton depositButton = new JButton("Deposit");
    depositButton.setBounds(170, 120, 150, 25);
    panel.add(depositButton);

    JButton withdrawButton = new JButton("Withdraw");
    withdrawButton.setBounds(10, 160, 150, 25);
    panel.add(withdrawButton);

    JButton balanceButton = new JButton("Check Balance");
    balanceButton.setBounds(170, 160, 150, 25);
    panel.add(balanceButton);

    // TextArea to show output messages
    JTextArea outputArea = new JTextArea();
    outputArea.setBounds(10, 200, 340, 50);
    panel.add(outputArea);

    // Action Listeners for Buttons
    createButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String accountNumber = accountNumberText.getText();
        String holderName = holderNameText.getText();
        String amountStr = amountText.getText();
        if (accountNumber.isEmpty() || holderName.isEmpty() || amountStr.isEmpty()) {
          outputArea.setText("Please enter all details.");
        } else {
          double initialDeposit = Double.parseDouble(amountStr);
          if (accountMap.containsKey(accountNumber)) {
            outputArea.setText("Account already exists.");
          } else {
            accountMap.put(accountNumber, new Account(accountNumber, holderName, initialDeposit));
            outputArea.setText("Account created successfully!");
          }
        }
      }
    });

    depositButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String accountNumber = accountNumberText.getText();
        String amountStr = amountText.getText();
        if (accountNumber.isEmpty() || amountStr.isEmpty()) {
          outputArea.setText("Please enter account number and amount.");
        } else {
          double amount = Double.parseDouble(amountStr);
          Account account = accountMap.get(accountNumber);
          if (account != null) {
            account.deposit(amount);
            outputArea.setText("Amount deposited successfully.");
          } else {
            outputArea.setText("Account not found.");
          }
        }
      }
    });

    withdrawButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String accountNumber = accountNumberText.getText();
        String amountStr = amountText.getText();
        if (accountNumber.isEmpty() || amountStr.isEmpty()) {
          outputArea.setText("Please enter account number and amount.");
        } else {
          double amount = Double.parseDouble(amountStr);
          Account account = accountMap.get(accountNumber);
          if (account != null) {
            if (account.withdraw(amount)) {
              outputArea.setText("Amount withdrawn successfully.");
            } else {
              outputArea.setText("Insufficient balance.");
            }
          } else {
            outputArea.setText("Account not found.");
          }
        }
      }
    });

    balanceButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String accountNumber = accountNumberText.getText();
        if (accountNumber.isEmpty()) {
          outputArea.setText("Please enter account number.");
        } else {
          Account account = accountMap.get(accountNumber);
          if (account != null) {
            outputArea.setText("Balance: $" + account.getBalance());
          } else {
            outputArea.setText("Account not found.");
          }
        }
      }
    });
  }

  // Account class (same as before, used to store data)
  static class Account {
    private String accountNumber;
    private String holderName;
    private double balance;

    public Account(String accountNumber, String holderName, double initialDeposit) {
      this.accountNumber = accountNumber;
      this.holderName = holderName;
      this.balance = initialDeposit;
    }

    public double getBalance() {
      return balance;
    }

    public void deposit(double amount) {
      this.balance += amount;
    }

    public boolean withdraw(double amount) {
      if (amount <= balance) {
        balance -= amount;
        return true;
      }
      return false;
    }
  }
}
