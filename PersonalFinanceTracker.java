import java.util.*;
import java.text.SimpleDateFormat;

public class PersonalFinanceTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Transaction> transactions = new ArrayList<>();

        while (true) {
            System.out.println("Personal Finance Tracker Menu:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Transactions");
            System.out.println("4. Generate Financial Report");
            System.out.println("5. View Transactions by Category");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter income amount: ");
                    double incomeAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter income source: ");
                    String incomeSource = scanner.nextLine();
                    transactions.add(new Transaction("Income", incomeAmount, incomeSource, new Date()));
                    break;
                case 2:
                    System.out.print("Enter expense amount: ");
                    double expenseAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter expense category: ");
                    String expenseCategory = scanner.nextLine();
                    transactions.add(new Transaction("Expense", -expenseAmount, expenseCategory, new Date()));
                    break;
                case 3:
                    System.out.println("Transaction History:");
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction);
                    }
                    break;
                case 4:
                    double totalIncome = 0;
                    double totalExpenses = 0;
                    for (Transaction transaction : transactions) {
                        if (transaction.getType().equals("Income")) {
                            totalIncome += transaction.getAmount();
                        } else if (transaction.getType().equals("Expense")) {
                            totalExpenses += Math.abs(transaction.getAmount());
                        }
                    }
                    double netIncome = totalIncome - totalExpenses;

                    System.out.println("Financial Report:");
                    System.out.println("Total Income: " + totalIncome);
                    System.out.println("Total Expenses: " + totalExpenses);
                    System.out.println("Net Income: " + netIncome);
                    break;
                case 5:
                    System.out.print("Enter category (Income/Expense): ");
                    String category = scanner.nextLine();
                    System.out.println("Transactions in the " + category + " category:");
                    for (Transaction transaction : transactions) {
                        if (transaction.getType().equalsIgnoreCase(category)) {
                            System.out.println(transaction);
                        }
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Transaction {
    private String type;
    private double amount;
    private String description;
    private Date date;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Transaction(String type, double amount, String description, Date date) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return dateFormat.format(date);
    }

    @Override
    public String toString() {
        return "Transaction [Type: " + type + ", Amount: " + amount + ", Description: " + description + ", Date: " + getDate() + "]";
    }
}
