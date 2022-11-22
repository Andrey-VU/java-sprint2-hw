import java.util.ArrayList;
import java.util.HashMap;

public class ShapitoCalc {

    Integer sumOfBestSeller = 0;
    Integer sumOfBestExpenser = 0;

    String bestSellerExpenser(HashMap<String, ArrayList<Integer>> monthIncome, boolean isExpense) {
        int maxCost = 0;
        String bestKey = "";
        for (String key : monthIncome.keySet()) {
            ArrayList<Integer> tmp = monthIncome.get(key);
            if (tmp.get(0) * tmp.get(1) > maxCost) {
                maxCost = tmp.get(0) * tmp.get(1);
                if (isExpense) {
                    sumOfBestSeller = maxCost;
                } else {
                    sumOfBestSeller = maxCost;
                }
                bestKey = key;
            }

        }
        return bestKey;
    }

    public double doMean(ArrayList<Double> data) {
        double sum = 0.0;
        for (double i : data) {
            sum += i;
        }
        double mean = sum / data.size();
        return mean;
    }

    double profit(double allIncome, double allExpense) {
        return allIncome - allExpense;
    }
}

