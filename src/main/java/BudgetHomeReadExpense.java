import java.util.List;

public class BudgetHomeReadExpense {
    public static void main(String[] args) {
        TransactionDao transactionDao = new TransactionDao();
        List<Transaction> expense = transactionDao.read(Type.EXPENSE);
        System.out.println(expense);
        transactionDao.close();
    }
}