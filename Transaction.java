public abstract class Transaction {

    // protected instance fields
    protected int transactionID;
    protected Date date;
    protected double transactionAmount;

    // default constructor
    public Transaction() {
        this.transactionID = 0;
        this.date = new Date(1, 1, 1970);
        this.transactionAmount = 0.0;
    }

    // non-default constructor
    public Transaction(int transactionID, int month, int day, int year, double transactionAmount) {
        this.transactionID = transactionID;
        this.date = new Date(month, day, year);
        this.transactionAmount = transactionAmount;
    }

    // accessor methods
    public int getTransactionID() {
        return transactionID;
    }

    public Date getDate() {
        return date;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    // mutator methods
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    // abstract method which outputs transaction information to the console
    public abstract void list();

    // returns a String of transaction date, transaction id and transaction amount
    @Override
    public String toString() {
        return this.date.toString() + "~" +
                this.transactionID + "~" +
                this.transactionAmount;
    }

    // checks if two transactions are equal by comparing transactionID
    public boolean equals(Transaction o) {
        return this.transactionID == o.transactionID;
    }
}
