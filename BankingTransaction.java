public class BankingTransaction extends Transaction {

    // private instance fields
    private boolean type;
    private double charge;

    // default constructor
    public BankingTransaction() {
        super();
        this.type = false;
        this.charge = 0;
    }

    // non-default constructor
    public BankingTransaction(int transactionID, int month, int day, int year,
                              double transactionAmount, boolean type, double charge) {
        super(transactionID, month, day, year, transactionAmount);
        this.type = type;
        this.charge = charge;
    }

    // accessor methods
    public boolean getType() {
        return type;
    }

    public double getCharge() {
        return charge;
    }

    // mutator methods
    public void setType(boolean type) {
        this.type = type;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    // uses the boolean type instance field to set the type to CASH or ATM
    public String getTypeString() {
        if (!type) {
            return "CASH";
        }
        else {
            return "ATM";
        }
    }

    // returns a String containing transaction type, date, ID,
    // amount, type, and charge
    public String toString() {
        return "BK~" + super.toString() + "~" +
                getTypeString() + "~" + charge;
    }

    // prints transaction information
    @Override
    public void list() {
        System.out.printf("%-12s%-20s%-20s$%-10.2f<<<$%.2f amount and $%.2f charge\n", date.toString(),
                "Banking", getTypeString()+" withdraw", (transactionAmount+charge), transactionAmount, charge);
    }
}
