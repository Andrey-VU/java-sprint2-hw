
import java.util.Scanner;
public  class Main {
    public  static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 111;
        YearlyReport yearlyReport = new YearlyReport();
        ShapitoResult shapitoResult = new ShapitoResult();

        printMenu();

        while (userInput != 0) {
            userInput = scanner.nextInt();
            if (userInput == 1){   // Считать все месячные отчёты
                System.out.println("Команда 1 находится в разработке");
            }

            else if (userInput == 2){   // Считать годовой отчёт
                yearlyReport.stringSeparator();
                }

            else if (userInput == 3){ // Сверить отчёты
                System.out.println("Команда 3 находится в разработке");
            }

            else if (userInput == 4){ // Вывести инфо о месячном отчёте
                System.out.println("Команда 4 находится в разработке");
                // предусмотреть проверку, считал ли пользователь отчёт
            }

            else if (userInput == 5){  // Вывести информацию о годовом отчёте
                if (yearlyReport.amountList.isEmpty()) { // проверяем, считал ли пользователь годовой отчёт
                    System.out.println("Вы не выполнили загрузку отчёта в программу. Возврат в главное меню");
                    }
                else {
                    yearlyReport.printResultYear();
                }
            }

            else if (userInput == 0) {
                System.out.println("Всего доброго!");
                return;
            }

            else {
                System.out.println("К сожалению, такой команды не предусмотрено в проекте");
            }
        printMenu();
        }
    }

    static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из приложения");
    }
}
