import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
/*
Месячные отчёты состоят из четырёх полей:
item_name — название товара;
is_expense — одно из двух значений: TRUE или FALSE. Обозначает, является ли запись тратой (TRUE) или доходом (FALSE);
quantity — количество закупленного или проданного товара;
sum_of_one — стоимость одной единицы товара. Целое число.

item_name,is_expense,quantity,sum_of_one
Воздушные шарики,TRUE,5000,5
Автоматы с мороженным,TRUE,12,15000
Продажа мороженного,FALSE,1000,120
 */

public class MonthlyReport {
    String[] namesMonth = {"Январь", "Февраль", "Март"};
    ShapitoCalc shapitoCalc = new ShapitoCalc();
    ArrayList<String> monthNameList = new ArrayList<>();
    ArrayList<Boolean> expenseOrIncome = new ArrayList<>();
    ArrayList<Double> amountList = new ArrayList<>();
    HashMap<String, Integer> income = new HashMap();
    ArrayList<Double> expense = new ArrayList<>();

    String readFileContentsOrNull (String path){
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    void stringSeparator() {
        ArrayList<String[]> allMonthArrayList = new ArrayList<>();

        for (int i = 1;  i < 4; i++) {
            String redFile = readFileContentsOrNull("/home/andrey/dev/hw2/java-sprint2-hw/resources/" + "m.20210" + i + ".csv");
            String[] monthArray = (redFile).split(System.lineSeparator());  // в массив кладём 7 строк, содержащих данные по 4-м столбцам
            allMonthArrayList.add(monthArray);   // формируем список из трёх массивов

        }
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



}