public class DepartmentStoreTransaction extends Transaction implements Rewardable {

    // private instance fields
    private String departmentName;
    private int returnPolicy;

    // default constructor
    public DepartmentStoreTransaction() {
        super();
        this.departmentName = "";
        this.returnPolicy = 30;
    }

    // non-default constructor
    public DepartmentStoreTransaction(int transactionID, int month, int day, int year,
                                      double transactionAmount, String departmentName,int returnPolicy) {
        super(transactionID, month, day, year, transactionAmount);
        this.departmentName = departmentName;
        this.returnPolicy = returnPolicy;
    }

    // accessor methods
    public String getDepartmentName() {
        return departmentName;
    }

    public int getReturnPolicy() {
        return returnPolicy;
    }

    // mutator methods
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setReturnPolicy(int returnPolicy) {
        this.returnPolicy = returnPolicy;
    }

    // returns a String containing transaction type, date,
    // ID, amount, department, and return policy
    public String toString() {
        return "DS~" + super.toString() + "~" +
                departmentName + "~" + returnPolicy + " days";
    }

    // implements the list method from Transaction
    // prints transaction information
    @Override
    public void list() {
        System.out.printf("%-12s%-20s%-20s$%-10.2f(Return in %d days)\n", date.toString(),
                "Department Store", departmentName, transactionAmount, returnPolicy);
    }


    // implements the earnPoints method from Rewardable
    // 3 points per $1
    @Override
    public double earnPoints(double amount) {
        return amount*3;
    }
}
