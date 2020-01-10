import java.util.Scanner;

public class BudgetHomeDelete {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id traksakcji którą chcesz wykasować");
        Long id = scanner.nextLong();

        TransactionDao transactionDao = new TransactionDao();
        transactionDao.delete(id);
        transactionDao.close();
    }
}