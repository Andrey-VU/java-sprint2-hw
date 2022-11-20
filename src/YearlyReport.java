import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReport {
    ShapitoCalc shapitoCalc = new ShapitoCalc();

    ArrayList<String> monthNameList = new ArrayList<>();
    ArrayList<Boolean> expenseOrIncome = new ArrayList<>();
    ArrayList<Double> amountList = new ArrayList<>();
    ArrayList<Double> income = new ArrayList<>();
    ArrayList<Double> expense = new ArrayList<>();
    String[] namesMonth = {"Январь", "Февраль", "Март"};

    public void printResultYear() {
        for (String i : monthNameList){
            System.out.println(i);
        }
        System.out.println("По итогам 2021-го года:");
        System.out.println("   прибыль по каждому месяцу составила: ");
        for (int i = 0; i < income.size(); i++) {
            System.out.println("        " + namesMonth[i] + "  " + shapitoCalc.profit(income.get(i), expense.get(i)));
            }
        System.out.println("   средний расход за все месяцы составил: " + shapitoCalc.doMean(expense));
        System.out.println("   средний доход за все месяцы составил: " + shapitoCalc.doMean(income));
    }

    void stringSeparator() {
        String pathToFile = readFileContentsOrNull("/home/andrey/dev/hw2/java-sprint2-hw/resources/y.2021.csv");
        ArrayList<String[]> yearArrayList = new ArrayList<>();
        String[] yearArray = (pathToFile).split(System.lineSeparator());  // в массив кладём 7 строк, содержащих данные по 3-м столбцам
        for (int i = 0; i < yearArray.length; i++) {  //
            yearArrayList.add(i, (yearArray[i]).split(",")); // Раскладываем в список из 7-ми массивов по 3 элемента
        }
        // Разложить содержимое списка по нужным нам объектам класса YearReport
        for (int i = 1; i < yearArray.length; i++) {
            String[] tmpArray = yearArrayList.get(i);
            for (int j = 0; j < tmpArray.length; j++) {
                if (j == 0) {
                    if (tmpArray[j].equals("01")) {
                        monthNameList.add("Январь");
                    } else if (tmpArray[j].equals("02")) {
                        monthNameList.add("Февраль");
                    } else if (tmpArray[j].equals("03")) {
                        monthNameList.add("Март");
                    }

                } else if (j == 1) {
                    amountList.add(Double.parseDouble(tmpArray[j]));
                } else {
                    expenseOrIncome.add(Boolean.parseBoolean(tmpArray[j]));
                }
            }
        }
          // Разложить отдельно приход и расход
         for (int i = 0; i < expenseOrIncome.size(); i++) {
                if (expenseOrIncome.get(i)) {
                    expense.add(amountList.get(i));
                } else {
                    income.add(amountList.get(i));
                }
          }
            System.out.println("Годовой отчёт загружен в программу");
        }

        String readFileContentsOrNull (String path){
            try {
                return Files.readString(Path.of(path));
            } catch (IOException e) {
                System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
                return null;
            }
        }
    }