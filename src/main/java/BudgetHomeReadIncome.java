import java.util.List;

public class BudgetHomeReadIncome {
    public static void main(String[] args) {
        TransactionDao transactionDao = new TransactionDao();
        List<Transaction> readIncome = transactionDao.read(Type.INCOME);
        System.out.println(readIncome);
        transactionDao.close();
    }
}