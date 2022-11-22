import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MonthlyReport {
    String[] namesMonth = {"Январь", "Февраль", "Март"};
    ShapitoCalc shapitoCalc = new ShapitoCalc();
    HashMap<String, HashMap<String, ArrayList<Integer>>> monthIncomeHashMap = new HashMap<>();
    HashMap<String, HashMap<String, ArrayList<Integer>>> monthExpenseHashMap = new HashMap<>();

    void stringSeparator() {
        HashMap<String, ArrayList<Integer>> income = new HashMap<>();
        HashMap<String, ArrayList<Integer>> expense = new HashMap<>();
        int tmpCounter = 0;
        for (int i = 1; i < 4; i++) {  // читаем 3 файла из папки
            String readFile = readFileContentsOrNull("/home/andrey/dev/hw2/java-sprint2-hw/resources/" + "m.20210" + i + ".csv");
            // далее каждый файл перекладываем во временный массив
            String[] monthArray = (readFile).split(System.lineSeparator());  // в массив кладём строки, с данными по 4-м столбцам

            for (int j = 1; j < monthArray.length; j++) {    // перебираем все строки в массиве, кроме заглавной
                String[] tmpStringArray = monthArray[j].split(",");     // разбиваем строку, кладём в массив по 4 элемента

                for (int k = 1; k < tmpStringArray.length; k++) {             // будем раскладывать строку вначале по временным переменным,
                    // затем в объекты класса - хэшМэпы
                    // задаём временные переменные
                    ArrayList<Integer> quantityPriceArray = new ArrayList<>();  // сюда положим количество и сумму
                    boolean isExpense = true;                                   // сюда положим метку затрата / приход
                    String key = "";                                            // сюда положим ключ - наименование затраты / прихода

                    for (String s : tmpStringArray) {
                        System.out.println(s);
                    }


                    if (k == 1) {
                        key = tmpStringArray[0];
                    } else if (k == 2) {
                        isExpense = Boolean.parseBoolean(tmpStringArray[1]);
                    } else if (k == 3) {
                        quantityPriceArray.add(Integer.parseInt(tmpStringArray[2]));
                    } else {
                        quantityPriceArray.add(Integer.parseInt(tmpStringArray[3]));
                    }
                    if (isExpense) {
                        expense.put(key, quantityPriceArray);
                    } else {
                        income.put(key, quantityPriceArray);
                    }
                    monthIncomeHashMap.put(namesMonth[k - 1], income);
                    monthExpenseHashMap.put(namesMonth[k - 1], expense);

                    //}
                }
            }
            //System.out.println("Месячные отчёты загружены!");
            System.out.println("Производится тестовый вывод всех данных:");
            for (String keyTmp : monthIncomeHashMap.keySet()) {

                for (String keyTmp2 : monthIncomeHashMap.get(keyTmp).keySet()) {
                    for (Integer x : monthIncomeHashMap.get(keyTmp).get(keyTmp2)) {
                        System.out.println(x);
                    }
                }
            }
        }
        /*for (String keyTmp : monthExpenseHashMap.keySet()) {
            System.out.println("Ключи словаря  monthExpenseHashMap" + keyTmp);
            for (String keyTmp2 : monthExpenseHashMap.get(keyTmp).keySet()) {
                System.out.println("Здесь должны быть ключи вложенного хэшмэпа" + keyTmp2);
            }
        }

        tmpCounter ++;
                System.out.println("Номер вывода" + tmpCounter);
                System.out.println(monthArray[j]);

*/
         /*   //for (String inventory : officeTool.keySet())
            //    System.out.println(inventory);

        for (int y = 0; y < monthIncomeHashMap.size(); y++) {
            System.out.println(monthIncomeHashMap.size());
        }

            */

                //ArrayList<Integer> al = l.get(key);
                //for (Integer j : al) {
                  //  System.out.println(key + j);
                //}

        }


    public void printResultMonth() {
        for (String i : namesMonth) {
            System.out.println("Отчётный месяц " + i);

            System.out.println("   Самый прибыльный товар: " + shapitoCalc.bestSellerExpenser(monthIncomeHashMap.get(i), false) + ". Выручка составила: " + shapitoCalc.sumOfBestSeller + " рублей.");
            //System.out.println("   Самая большая трата: " + shapitoCalc.bestSellerExpenser(monthExpenseHashMap.get(i), true) + ". Затраты составили: " + shapitoCalc.sumOfBestExpenser + " рублей.");
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
