import java.util.ArrayList;
public class ShapitoCalc {


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

