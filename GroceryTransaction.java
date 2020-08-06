public class GroceryTransaction extends Transaction implements Rewardable{

    // private instance field
    private String storeName;

    // default constructor
    public GroceryTransaction() {
        super();
        this.storeName = "";
    }

    // non-default constructor
    public GroceryTransaction(int transactionID, int month, int day, int year,
                              double transactionAmount, String storeName) {
        super(transactionID, month, day, year, transactionAmount);
        this.storeName = storeName;
    }

    // accessor method
    public String getStoreName() {
        return storeName;
    }

    // mutator method
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    // returns a String containing transaction type, date, ID,
    // amount, type, and charge
    public String toString() {
        return "GR~" + super.toString() + "~" + storeName;
    }

    // implements the list method from Transaction
    // prints transaction information
    @Override
    public void list() {
        System.out.printf("%-12s%-20s%-20s$%-10.2f\n", date.toString(),
                "Grocery", storeName, transactionAmount);
    }

    // implements the earnPoints method from Rewardable
    // 5 points per $1
    @Override
    public double earnPoints(double amount) {
        return amount*5;
    }
}
