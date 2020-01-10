import java.util.Scanner;

public class BudgetHomeApp {
    public static void main(String[] args) {
        TransactionDao transactionDao = new TransactionDao();
        transactionDao.createTable();
        transactionDao.close();
        boolean running = true;

        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.println("Co chcesz zrobić?");
            System.out.println("1. Dodaj transakcję");
            System.out.println("2. Modyfikuj transakcję");
            System.out.println("3. Usuń transakcję");
            System.out.println("4. Wyświetl przychody");
            System.out.println("5. Wyświetl wydatki");
            System.out.println("0. Koniec");

            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    BudgetHomeSave.main(new String[0]);
                    break;
                case "2":
                    BudgetHomeUpdate.main(new String[0]);
                    break;
                case "3":
                    BudgetHomeDelete.main(new String[0]);
                    break;
                case "4":
                    BudgetHomeReadIncome.main(new String[0]);
                    break;
                case "5":
                    BudgetHomeReadExpense.main(new String[0]);
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Błędny wybór!");
            }
        }
        scanner.close();
    }
}