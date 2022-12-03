import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReport {
    String[] namesMonth = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    ArrayList<Record> allData = new ArrayList<>();
    Checker checker;

    void stringSeparator() {
        String pathToFile = readFileContentsOrNull("resources/y.2021.csv");
        String[] yearArray = (pathToFile).split("\r?\n|\r");  // ['1 строка', ..., '7 строка']

        for (int i = 1; i < yearArray.length; i++) {  // перебираем все строки в массиве кроме заглавной
            String[] tmpStringArray = yearArray[i].split(","); // "01,1593150,false" --> ['01', '1593150', 'false']
            String keyMonth = tmpStringArray[0]; // наименование месяца
            int amount = Integer.parseInt(tmpStringArray[1]);  //  сумма
            boolean isExpense = Boolean.parseBoolean(tmpStringArray[2]);  // расход/приход true
            Record record = new Record(keyMonth, 1, amount, isExpense);
            allData.add(record); // собираем все данные в один список
        }
        System.out.println("Годовой отчёт загружен в программу");
        }

    public void printResultYear() {
        int sumOfIncome = 0;
        int sumOfExpense = 0;
        ArrayList<Integer> incomeYR = new ArrayList<>(); // Из годовых отчётов все суммарные помесячные доходы
        ArrayList<Integer> expenseYR = new ArrayList<>(); // Из годовых отчётов все суммарные помесячные расходы
        for (int i = 0; i < allData.size(); i++) {
            int expense;
            int income;
            if (allData.get(i).isExpense) {
                expense = allData.get(i).sumOfOne;
                sumOfExpense += expense;
                expenseYR.add(expense);
            }
            else {
                income = allData.get(i).sumOfOne;
                sumOfIncome += income;
                incomeYR.add(income);
            }
        }


        System.out.println("По итогам 2021-го года:");
        System.out.println("   прибыль по каждому месяцу составила: ");

        for (int i = 0; i < allData.size() / 2; i++) {
            System.out.println("        " + namesMonth[i] + "  " + (incomeYR.get(i) - expenseYR.get(i)) + "рублей.");
        }
        System.out.println("   средний расход за все месяцы составил: " + sumOfExpense / expenseYR.size() );
        System.out.println("   средний доход за все месяцы составил: " + sumOfIncome / incomeYR.size() );
    }


        String readFileContentsOrNull (String path){
            try {
                return Files.readString(Path.of(path));
            } catch (IOException e) {
                System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл не находится в нужной директории.");
                return null;
            }
        }
    }
