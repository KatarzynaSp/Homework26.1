import java.util.Scanner;

public class BudgetHomeUpdate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id transakcji, którą chcesz zaktualizować");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Podaj typ transakcji: 1 - wydatek, 2 - przychód");
        String type1 = scanner.nextLine();
        Type type = null;
        if (type1.equals("1")) {
            type = Type.EXPENSE;
        } else if (type1.equals("2")) {
            type = Type.INCOME;
        } else System.out.println("Nie ma takiej opcji");

        System.out.println("Podaj opis transakcji");
        String description = scanner.nextLine();

        System.out.println("Podaj sumę transakcji");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Podaj datę transakcji");
        String date = scanner.nextLine();

        Transaction transaction = new Transaction(id, type, description, amount, date);

        TransactionDao transactionDao = new TransactionDao();
        transactionDao.update(transaction);
    }
}