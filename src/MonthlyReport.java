import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MonthlyReport {
    String[] namesMonth = {"Январь", "Февраль", "Март"};
    ShapitoCalc shapitoCalc = new ShapitoCalc();
    HashMap<String, MonthData> monthIncomeHashMap = new HashMap<>();
    HashMap<String, MonthData> monthExpenseHashMap = new HashMap<>();
    MonthData income = new MonthData;
    MonthData expense = new MonthData;
    /*MonthlyReport(String name, HashMap<String, ArrayList<Integer>> hashMapNew) {
        income.month = name;
        income.monthData = hashMapNew;
        expense.month = name;
        expense.monthData = hashMapNew;
    }*/
    class MonthData {
        String month;                       // Месяц за который учтены данные
        HashMap<String, ArrayList<Integer>> monthData; // словарь, содержащий все позиции, количество и цену
        // Конструктор
        MonthData(String monthName, HashMap<String, ArrayList<Integer>> monthDataNew){
            month = monthName;
            monthData = monthDataNew;
        }
    }

    void stringSeparator() {
    HashMap<String, ArrayList<Integer>> income = new HashMap<>();
    HashMap<String, ArrayList<Integer>> expense = new HashMap<>();
        for (int i = 1; i < 4; i++) {  // читаем 3 файла из папки
            String readFile = readFileContentsOrNull("/home/andrey/dev/hw2/java-sprint2-hw/resources/" + "m.20210" + i + ".csv");
            // далее каждый файл перекладываем во временный массив
            String[] monthArray = (readFile).split(System.lineSeparator());  // в массив кладём строки, с данными по 4-м столбцам
            for (int j = 1; j < monthArray.length; j++) {    // перебираем все строки в массиве, кроме заглавной
                String[] tmpStringArray = monthArray[j].split(",");     // разбиваем строку, кладём в массив по 4 элемента

                // раскладываем массив tmpStringArray вначале по временным переменным,
                // задаём временные переменные
                ArrayList<Integer> quantityPriceArray = new ArrayList<>();  // сюда положим количество и сумму
                boolean isExpense = true;                                   // сюда положим метку затрата / приход
                String key = "";                                            // сюда положим ключ - наименование затраты / прихода
                key = tmpStringArray[0];
                isExpense = Boolean.parseBoolean(tmpStringArray[1]);
                quantityPriceArray.add(Integer.parseInt(tmpStringArray[2]));
                quantityPriceArray.add(Integer.parseInt(tmpStringArray[3]));

                if (isExpense) { // раскладываем по двум словарям: для расходов и для доходов
                    expense.put(key, quantityPriceArray);
                } else {
                    income.put(key, quantityPriceArray);
                    }
                monthIncomeHashMap.put(namesMonth[i - 1], income);  // упаковываем в словари с ключами - месяцами
                monthExpenseHashMap.put(namesMonth[i - 1], expense);
            }
        }
        //System.out.println("Месячные отчёты загружены!");
        System.out.println("Производится тестовый вывод всех данных:");
        for (String keyTmp : monthIncomeHashMap.keySet()) {
            System.out.println("Ключ верхнего словаря:  " + keyTmp);
           for (String keyTmp2 : monthIncomeHashMap.get(keyTmp).keySet()) {
               System.out.println("Ключ ВЛОЖЕННОГО словаря:  " + keyTmp2);
               for (Integer x : monthIncomeHashMap.get(keyTmp).get(keyTmp2)) {
                   System.out.println(x);
                    }
                }
            }
        }

    public void printResultMonth() {
        for (String i : namesMonth) {
            System.out.println("Отчётный месяц " + i);
            System.out.println("   Самый прибыльный товар:");
            shapitoCalc.printBestSellerExpenser(i, monthIncomeHashMap.get(i), false);
            System.out.println("   Самые большие затраты:");
            shapitoCalc.printBestSellerExpenser(i, monthExpenseHashMap.get(i), true);
        }
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

          /*for (String keyTmp : monthExpenseHashMap.keySet()) {
            System.out.println("Ключи словаря  monthExpenseHashMap" + keyTmp);
            for (String keyTmp2 : monthExpenseHashMap.get(keyTmp).keySet()) {
                System.out.println("Здесь должны быть ключи вложенного хэшмэпа" + keyTmp2);
            }
        }
            System.out.println(monthArray.length);  // печать для отладки
            for (String s: monthArray) {
                System.out.println(s);
            }

                 // for (String s : tmpStringArray) {
                     //   System.out.println(s);
                   // }


        tmpCounter ++;
                System.out.println("Номер вывода" + tmpCounter);
                System.out.println(monthArray[j]);

*/
         /*   //for (String inventory : officeTool.keySet())
            //    System.out.println(inventory);

        for (int y = 0; y < monthIncomeHashMap.size(); y++) {
            System.out.println(monthIncomeHashMap.size());
        }



                //ArrayList<Integer> al = l.get(key);
                //for (Integer j : al) {
                  //  System.out.println(key + j);
                //}

                    System.out.println("длинна массива tmpStringArray" + tmpStringArray.length);
                for (String s: tmpStringArray) {
        System.out.println(s);
    }
*/
