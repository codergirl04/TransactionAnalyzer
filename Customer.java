import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.Arrays;

public class Customer {

    private static final int maxSize = 100;

    // private instance fields
    private String customerName;
    private String creditCardNumber;
    private double totalBalance;
    private double rewardPointsBalance;
    private Transaction[] transactions;
    private Rewardable[] rewardables;

    // default constructor
    public Customer() {
        this.customerName = "John Doe";
        this.creditCardNumber = "1111222233334444";
        this.totalBalance = 0.0;
        this.rewardPointsBalance = 1000.0;
        this.transactions = new Transaction[maxSize];
        this.rewardables = new Rewardable[maxSize];
        Arrays.fill(transactions, null);
        Arrays.fill(rewardables, null);
    }

    // non-default constructor
    public Customer(String customerName, String creditCardNumber,
                    double totalBalance, double rewardPointsBalance) {
        this.customerName = customerName;
        this.creditCardNumber = creditCardNumber;
        this.totalBalance = totalBalance;
        this.rewardPointsBalance = rewardPointsBalance;
        this.transactions = new Transaction[maxSize];
        this.rewardables = new Rewardable[maxSize];
        Arrays.fill(transactions, null);
        Arrays.fill(rewardables, null);
    }

    // reads transactions from a provided file to populate the transactions array and
    // rewardable array
    public void readTransactions(String fileName) {
        Path inputFilePath = Paths.get(fileName);
        BufferedReader reader;
        String line;
        int index = 0;
        try {
            reader =  Files.newBufferedReader(inputFilePath, StandardCharsets.US_ASCII);
            while ((line = reader.readLine ()) != null) {
                addTransaction(line, index++);
            }
            reader.close ();
        } catch (IOException e)  {
            e.printStackTrace ();
        }
        int rewardableIndex = 0;
        for (Transaction transaction : transactions) {
            if (transaction instanceof Rewardable) {
                rewardables[rewardableIndex++] = (Rewardable) transaction;
            }
        }
    }

    // helper method for readTransactions which instantiates new transaction objects
    // (banking, grocery, or department store) and adds them to the transactions array
    public void addTransaction(String line, int index) {
        String type;
        int month;
        int day;
        int year;
        int transactionID;
        double transactionAmount;

        String[] data = line.split ("~");
        type = data[0];
        String[] date = data[1].split("/");
        month = Integer.parseInt(date[0]);
        day = Integer.parseInt(date[1]);
        year = Integer.parseInt(date[2]);
        transactionID = Integer.parseInt(data[2]);
        transactionAmount = Double.parseDouble(data[3]);

        switch (type) {
            case "DS" -> {
                String departmentName;
                int returnPolicy;
                departmentName = data[4];
                returnPolicy = Integer.parseInt(data[5]);
                transactions[index] = new DepartmentStoreTransaction(transactionID,
                        month, day, year, transactionAmount, departmentName, returnPolicy);
            }
            case "BK" -> {
                boolean bankTransactionType;
                double charge;
                bankTransactionType = !data[4].equals("CASH");
                charge = Double.parseDouble(data[5]);
                transactions[index] = new BankingTransaction(transactionID, month, day,
                        year, transactionAmount, bankTransactionType, charge);
            }
            case "GR" -> {
                String storeName;
                storeName = data[4];
                transactions[index] = new GroceryTransaction(transactionID, month, day,
                        year, transactionAmount, storeName);
            }
        }
    }

    // prints all transactions
    public void reportTransactions() {
        System.out.println("TRANSACTION LISTING REPORT");
        for (Transaction transaction : transactions) {
            if (transaction == null) {
                break;
            }
            transaction.list();
        }
    }

    // prints all banking charges
    public void reportBankingCharges() {
        double totalCharges = 0;
        for (Transaction transaction : transactions) {
            if (transaction instanceof BankingTransaction) {
                totalCharges += ((BankingTransaction) transaction).getCharge();
            }
        }
        System.out.println("\nTotal charges: $" + totalCharges);
    }

    // prints previous reward points balance, department store rewards points,
    // grocery store rewards points, and total points available for redemption
    public void reportRewardSummary() {
        double departmentStorePoints = 0;
        double groceryStorePoints = 0;
        System.out.println("\nRewards summary for " + customerName + " " +
                creditCardNumber.substring(12));
        System.out.println("Previous points balance                                "
                + rewardPointsBalance);
        for (Rewardable transaction : rewardables) {
            if (((Transaction) transaction) instanceof GroceryTransaction) {
                groceryStorePoints += transaction.earnPoints(((Transaction) transaction)
                        .getTransactionAmount());
            }
            else if (((Transaction) transaction) instanceof DepartmentStoreTransaction) {
                departmentStorePoints += transaction.earnPoints(((Transaction) transaction)
                        .getTransactionAmount());
            }
        }
        System.out.println("+Points earned on department store purchases           "
                + departmentStorePoints);
        System.out.println("+Points earned on grocery store purchases              "
                + groceryStorePoints);
        System.out.println("---------------------------------------------------------------");
        System.out.println("=Total points available for redemption                 "
                + (groceryStorePoints+departmentStorePoints+this.rewardPointsBalance));
    }
}