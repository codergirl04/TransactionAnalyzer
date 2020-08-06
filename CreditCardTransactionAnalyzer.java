// @version1.0 07-18-2020
// @author Maya Itty
//  File name: TransactionAnalyzer.java
//  Program purpose: this program allows customers to analyze their credit card
//// transactions at grocery stores, department stores, and banks
//  Revision history:
//   Date                  Programmer          Change ID      Description
//   07/18/2020            Maya Itty           0000           Initial implementation

public class CreditCardTransactionAnalyzer {
    public static void main(String[] args) {
        Customer customer = new Customer("Maya", "1234234534564567",
                1000, 100);
        customer.readTransactions("src/CustomerTransactions.txt");
        customer.reportTransactions();
        customer.reportBankingCharges();
        customer.reportRewardSummary();
    }
}
