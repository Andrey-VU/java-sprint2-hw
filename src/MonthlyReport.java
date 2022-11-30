import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MonthlyReport {
    String[] namesMonth = {"Январь", "Февраль", "Март"};
    HashMap<String, ArrayList<Record>> allData = new HashMap<String, ArrayList<Record>>();
    void stringSeparator() {
        for (int i = 1; i < 4; i++) {  // читаем 3 файла из папки
            String readFile = readFileContentsOrNull("resources/" + "m.20210" + i + ".csv");
            // далее каждый файл перекладываем во временный массив
            String[] monthArray = (readFile).split("\r?\n|\r");  // в массив кладём строки, с данными по 4-м столбцам
            // в массиве monthArray попадают строки ["строка1", "строка2", "строка3"...]
            ArrayList<Record> storageForAll = new ArrayList<Record>();
            for (int j = 1; j < monthArray.length; j++) {    // перебираем все строки в массиве, кроме заглавной
                String[] tmpStringArray = monthArray[j].split(",");   // разбиваем строку, кладём в массив по 4 элемента
                // "Воздушные шарики,TRUE,5000,5"  --->  ["Воздушные шарики", "TRUE", "5000", "5"]
                String keyTitle = tmpStringArray[0]; // наименование затраты/прихода  "Воздушные шарики"
                int quantity = Integer.parseInt(tmpStringArray[3]);  //  заявленная цена 5
                int price = Integer.parseInt(tmpStringArray[2]);  // количество  5000
                boolean isExpense = Boolean.parseBoolean(tmpStringArray[1]);  // расход/приход true
                Record record = new Record(keyTitle, quantity, price, isExpense);
                storageForAll.add(record);    // собираем данные по всем операциям за месяц в один массив
            }
            allData.put(namesMonth[i - 1], storageForAll);  // данные за все месяцы
        }
        System.out.println("Месячные отчёты загружены!");
    }

    public void printResultMonth() {
        for (String i : namesMonth) {
            System.out.println("Отчётный месяц " + i);
            System.out.println("   Самый прибыльный товар:");
            printBestSellerExpenser(i, allData.get(i) , false);
            System.out.println("   Самые большие затраты:");
            printBestSellerExpenser(i, allData.get(i), true);
        }
    }

    void printBestSellerExpenser(String month, ArrayList<Record> monthIE, boolean isExpense) {
        int maxCostExpense = 0;
        String bestItemExpense = "";
        int maxCostIncome = 0;
        String bestItemIncome = "";
        for (Record record : monthIE) {
            if (record.isExpense & (record.quantity * record.sumOfOne > maxCostExpense)){
                maxCostExpense = record.quantity * record.sumOfOne;
                bestItemExpense = record.name;
            }
            else if (!record.isExpense & (record.quantity * record.sumOfOne > maxCostIncome)){
                maxCostIncome = record.quantity * record.sumOfOne;
                bestItemIncome = record.name;
            }
        }
        if (isExpense) {
            System.out.println(bestItemExpense + ". Затраты составили: " + maxCostExpense + " рублей.");
        }
        else{
            System.out.println(bestItemIncome + ". Выручка составила: " + maxCostIncome + " рублей.");
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
